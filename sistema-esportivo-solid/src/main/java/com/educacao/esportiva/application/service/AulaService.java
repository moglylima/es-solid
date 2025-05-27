package com.educacao.esportiva.application.service;

import com.educacao.esportiva.application.dto.AulaResponseDTO;
import com.educacao.esportiva.application.dto.CriarAulaDTO;
import com.educacao.esportiva.domain.Aula;
import com.educacao.esportiva.domain.Conteudo;
import com.educacao.esportiva.domain.Professor;
import com.educacao.esportiva.domain.repository.AulaRepository;
import com.educacao.esportiva.domain.repository.ConteudoRepository;
import com.educacao.esportiva.domain.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ========================================================================
 * SERVIÇO DE APLICAÇÃO AULA - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SRP (Single Responsibility Principle):
 *    - Responsabilidade única: orquestrar operações de negócio relacionadas a Aula
 *    - Não gerencia persistência, validação de domínio ou controle HTTP
 * 
 * 2. OCP (Open/Closed Principle):
 *    - Aberto para extensão via composição e herança
 *    - Fechado para modificação das responsabilidades core
 * 
 * 3. LSP (Liskov Substitution Principle):
 *    - Pode ser substituído por qualquer implementação de AulaService
 * 
 * 4. ISP (Interface Segregation Principle):
 *    - Usa DTOs específicos para entrada e saída
 *    - Não força dependentes a conhecer métodos desnecessários
 * 
 * 5. DIP (Dependency Inversion Principle):
 *    - Depende das abstrações dos repositórios
 *    - Inversão de controle via injeção de dependência
 */
@Service
public class AulaService {
    
    private final AulaRepository aulaRepository;
    private final ProfessorRepository professorRepository;
    private final ConteudoRepository conteudoRepository;
    
    @Autowired
    public AulaService(AulaRepository aulaRepository, 
                      ProfessorRepository professorRepository,
                      ConteudoRepository conteudoRepository) {
        this.aulaRepository = aulaRepository;
        this.professorRepository = professorRepository;
        this.conteudoRepository = conteudoRepository;
    }
    
    /**
     * Cria uma nova aula aplicando as regras de negócio do domínio.
     */
    public AulaResponseDTO criarAula(CriarAulaDTO dto) {
        // Busca o professor associado
        Professor professor = professorRepository.findById(dto.getProfessorId())
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com ID: " + dto.getProfessorId()));
        
        if (!professor.isAtivo()) {
            throw new IllegalArgumentException("Não é possível criar aula para professor inativo");
        }
        
        // Busca o conteúdo associado
        Conteudo conteudo = conteudoRepository.findById(dto.getConteudoId())
            .orElseThrow(() -> new IllegalArgumentException("Conteúdo não encontrado com ID: " + dto.getConteudoId()));
        
        // Verifica se o professor tem especialização compatível com o esporte do conteúdo
        if (!professor.getEspecializacao().equalsIgnoreCase(conteudo.getEsporte().getNome())) {
            throw new IllegalArgumentException("Professor não tem especialização no esporte: " + conteudo.getEsporte().getNome());
        }
        
        // Verifica conflitos de horário para o professor
        List<Aula> aulasConflitantes = aulaRepository.buscarPorProfessorEPeriodo(
            dto.getProfessorId(),
            dto.getDataHora().minusMinutes(conteudo.getDuracao()),
            dto.getDataHora().plusMinutes(conteudo.getDuracao())
        );
        
        if (!aulasConflitantes.isEmpty()) {
            throw new IllegalArgumentException("Professor já possui aula agendada no horário solicitado");
        }
        
        // Cria a entidade usando o construtor do domínio (que aplica validações)
        Aula aula = new Aula(dto.getDataHora(), professor, conteudo);
        
        // Persiste usando a abstração do repositório
        Aula aulaSalva = aulaRepository.save(aula);
        
        // Converte para DTO de resposta
        return converterParaResponseDTO(aulaSalva);
    }
    
    /**
     * Busca aula por ID.
     */
    public Optional<AulaResponseDTO> buscarPorId(Long id) {
        return aulaRepository.findById(id)
            .map(this::converterParaResponseDTO);
    }
    
    /**
     * Lista todas as aulas.
     */
    public List<AulaResponseDTO> listarTodas() {
        return aulaRepository.findAll()
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca aulas por professor.
     */
    public List<AulaResponseDTO> buscarPorProfessor(Long professorId) {
        return aulaRepository.buscarPorProfessor(professorId)
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca aulas por conteúdo.
     */
    public List<AulaResponseDTO> buscarPorConteudo(Long conteudoId) {
        return aulaRepository.buscarPorConteudo(conteudoId)
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca aulas futuras.
     */
    public List<AulaResponseDTO> buscarFuturas() {
        return aulaRepository.buscarFuturas(LocalDateTime.now())
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca aulas por período.
     */
    public List<AulaResponseDTO> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return aulaRepository.buscarPorPeriodo(inicio, fim)
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Cancela uma aula (remove do sistema).
     */
    public void cancelarAula(Long id) {
        Aula aula = aulaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aula não encontrada com ID: " + id));
        
        // Verifica se a aula ainda não aconteceu
        if (aula.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível cancelar aula que já aconteceu");
        }
        
        aulaRepository.deleteById(id);
    }
    
    /**
     * Converte entidade do domínio para DTO de resposta.
     * Método privado aplicando SRP.
     */
    private AulaResponseDTO converterParaResponseDTO(Aula aula) {
        return new AulaResponseDTO(
            aula.getId(),
            aula.getDataHora(),
            aula.getProfessor().getNome(),
            aula.getProfessor().getId(),
            aula.getConteudo().getTitulo(),
            aula.getConteudo().getId(),
            aula.getConteudo().getEsporte().getNome()
        );
    }
}
