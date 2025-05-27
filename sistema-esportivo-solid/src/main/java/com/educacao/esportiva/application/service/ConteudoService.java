package com.educacao.esportiva.application.service;

import com.educacao.esportiva.application.dto.CriarConteudoDTO;
import com.educacao.esportiva.application.dto.ConteudoResponseDTO;
import com.educacao.esportiva.domain.Conteudo;
import com.educacao.esportiva.domain.Esporte;
import com.educacao.esportiva.domain.repository.ConteudoRepository;
import com.educacao.esportiva.domain.repository.EsporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ========================================================================
 * SERVIÇO DE APLICAÇÃO CONTEÚDO - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SRP (Single Responsibility Principle):
 *    - Responsabilidade única: orquestrar operações de negócio relacionadas a Conteúdo
 *    - Não gerencia persistência, validação de domínio ou controle HTTP
 * 
 * 2. OCP (Open/Closed Principle):
 *    - Aberto para extensão via composição e herança
 *    - Fechado para modificação das responsabilidades core
 * 
 * 3. LSP (Liskov Substitution Principle):
 *    - Pode ser substituído por qualquer implementação de ConteudoService
 * 
 * 4. ISP (Interface Segregation Principle):
 *    - Usa DTOs específicos para entrada e saída
 *    - Não força dependentes a conhecer métodos desnecessários
 * 
 * 5. DIP (Dependency Inversion Principle):
 *    - Depende das abstrações ConteudoRepository e EsporteRepository
 *    - Inversão de controle via injeção de dependência
 */
@Service
public class ConteudoService {
    
    private final ConteudoRepository conteudoRepository;
    private final EsporteRepository esporteRepository;
    
    @Autowired
    public ConteudoService(ConteudoRepository conteudoRepository, EsporteRepository esporteRepository) {
        this.conteudoRepository = conteudoRepository;
        this.esporteRepository = esporteRepository;
    }
    
    /**
     * Cria um novo conteúdo aplicando as regras de negócio do domínio.
     */
    public ConteudoResponseDTO criarConteudo(CriarConteudoDTO dto) {
        // Busca o esporte associado
        Esporte esporte = esporteRepository.findById(dto.getEsporteId())
            .orElseThrow(() -> new IllegalArgumentException("Esporte não encontrado com ID: " + dto.getEsporteId()));
        
        if (!esporte.isAtivo()) {
            throw new IllegalArgumentException("Não é possível criar conteúdo para esporte inativo");
        }
        
        // Cria a entidade usando o construtor do domínio (que aplica validações)
        Conteudo conteudo = new Conteudo(dto.getTitulo(), dto.getDescricao(), dto.getNivel(), dto.getDuracao(), esporte);
        
        // Persiste usando a abstração do repositório
        Conteudo conteudoSalvo = conteudoRepository.save(conteudo);
        
        // Converte para DTO de resposta
        return converterParaResponseDTO(conteudoSalvo);
    }
    
    /**
     * Busca conteúdo por ID.
     */
    public Optional<ConteudoResponseDTO> buscarPorId(Long id) {
        return conteudoRepository.findById(id)
            .map(this::converterParaResponseDTO);
    }
    
    /**
     * Lista todos os conteúdos.
     */
    public List<ConteudoResponseDTO> listarTodos() {
        return conteudoRepository.findAll()
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca conteúdos por esporte.
     */
    public List<ConteudoResponseDTO> buscarPorEsporte(Long esporteId) {
        return conteudoRepository.buscarPorEsporte(esporteId)
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca conteúdos por título.
     */
    public List<ConteudoResponseDTO> buscarPorTitulo(String titulo) {
        return conteudoRepository.buscarPorTituloContendo(titulo)
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca conteúdos por nível.
     */
    public List<ConteudoResponseDTO> buscarPorNivel(String nivel) {
        return conteudoRepository.buscarPorNivel(nivel)
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca conteúdos por faixa de duração.
     */
    public List<ConteudoResponseDTO> buscarPorFaixaDuracao(int duracaoMinima, int duracaoMaxima) {
        return conteudoRepository.buscarPorFaixaDuracao(duracaoMinima, duracaoMaxima)
            .stream()
            .map(this::converterParaResponseDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Converte entidade do domínio para DTO de resposta.
     * Método privado aplicando SRP.
     */
    private ConteudoResponseDTO converterParaResponseDTO(Conteudo conteudo) {
        return new ConteudoResponseDTO(
            conteudo.getId(),
            conteudo.getTitulo(),
            conteudo.getDescricao(),
            conteudo.getNivel(),
            conteudo.getDuracao(),
            conteudo.getEsporte().getNome(),
            conteudo.getEsporte().getId()
        );
    }
}
