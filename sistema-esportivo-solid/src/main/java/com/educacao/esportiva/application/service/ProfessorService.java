package com.educacao.esportiva.application.service;

import com.educacao.esportiva.application.dto.CriarProfessorDTO;
import com.educacao.esportiva.application.dto.ProfessorResponseDTO;
import com.educacao.esportiva.domain.Professor;
import com.educacao.esportiva.domain.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ========================================================================
 * SERVIÇO DE APLICAÇÃO PROFESSOR - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SRP (Single Responsibility Principle):
 *    - Responsabilidade única: orquestrar operações de negócio relacionadas a Professor
 *    - Não gerencia persistência, validação de domínio ou controle HTTP
 * 
 * 2. OCP (Open/Closed Principle):
 *    - Aberto para extensão via composição e herança
 *    - Fechado para modificação das responsabilidades core
 * 
 * 3. LSP (Liskov Substitution Principle):
 *    - Pode ser substituído por qualquer implementação de ProfessorService
 * 
 * 4. ISP (Interface Segregation Principle):
 *    - Usa DTOs específicos para entrada e saída
 *    - Não força dependentes a conhecer métodos desnecessários
 * 
 * 5. DIP (Dependency Inversion Principle):
 *    - Depende da abstração ProfessorRepository, não da implementação
 *    - Inversão de controle via injeção de dependência
 */
@Service
public class ProfessorService {
    
    private final ProfessorRepository professorRepository;
    
    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    
    /**
     * Cria um novo professor aplicando as regras de negócio do domínio.
     */
    public ProfessorResponseDTO criarProfessor(CriarProfessorDTO dto) {
        // Verifica se já existe professor com o mesmo email
        Optional<Professor> professorExistente = professorRepository.buscarPorEmail(dto.getEmail());
        if (professorExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um professor com este email: " + dto.getEmail());
        }
        
        // Cria a entidade usando o construtor do domínio (que aplica validações)
        Professor professor = new Professor(dto.getNome(), dto.getEmail(), dto.getEspecializacao());
        
        // Persiste usando a abstração do repositório
        Professor professorSalvo = professorRepository.save(professor);
        
        // Converte para DTO de resposta
        return converterParaResponseDTO(professorSalvo);
    }
    
    /**
     * Busca professor por ID.
     */
    public Optional<ProfessorResponseDTO> buscarPorId(Long id) {
        return professorRepository.findById(id)
            .map(this::converterParaResponseDTO);
    }
    
    /**
     * Lista todos os professores ativos.
     */
    public List<ProfessorResponseDTO> listarAtivos() {
        return professorRepository.buscarAtivos()
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca professores por nome.
     */
    public List<ProfessorResponseDTO> buscarPorNome(String nome) {
        return professorRepository.buscarPorNomeContendo(nome)
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca professores por especialização.
     */
    public List<ProfessorResponseDTO> buscarPorEspecializacao(String especializacao) {
        return professorRepository.buscarPorEspecializacao(especializacao)
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Desativa um professor.
     */
    public void desativarProfessor(Long id) {
        Professor professor = professorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com ID: " + id));
        
        professor.desativar();
        professorRepository.save(professor);
    }
    
    /**
     * Converte entidade do domínio para DTO de resposta.
     * Método privado aplicando SRP.
     */
    private ProfessorResponseDTO converterParaResponseDTO(Professor professor) {
        return new ProfessorResponseDTO(
            professor.getId(),
            professor.getNome(),
            professor.getEmail(),
            professor.getEspecializacao(),
            professor.isAtivo()
        );
    }
}
