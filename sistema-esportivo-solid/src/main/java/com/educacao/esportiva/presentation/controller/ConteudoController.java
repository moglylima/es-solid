package com.educacao.esportiva.presentation.controller;

import com.educacao.esportiva.application.dto.ConteudoResponseDTO;
import com.educacao.esportiva.application.dto.CriarConteudoDTO;
import com.educacao.esportiva.application.service.ConteudoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ========================================================================
 * CONTROLADOR CONTEÚDO - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SRP (Single Responsibility Principle):
 *    - Responsabilidade única: controle HTTP para operações de Conteúdo
 *    - Não contém lógica de negócio, validação de domínio ou persistência
 * 
 * 2. OCP (Open/Closed Principle):
 *    - Aberto para extensão via novos endpoints
 *    - Fechado para modificação das responsabilidades core
 * 
 * 3. LSP (Liskov Substitution Principle):
 *    - Pode ser substituído por qualquer implementação de controlador REST
 * 
 * 4. ISP (Interface Segregation Principle):
 *    - Endpoints específicos para cada operação
 *    - DTOs específicos para entrada e saída
 * 
 * 5. DIP (Dependency Inversion Principle):
 *    - Depende da abstração ConteudoService
 *    - Inversão de controle via injeção de dependência
 */
@RestController
@RequestMapping("/api/conteudos")
@CrossOrigin(origins = "*")
public class ConteudoController {
    
    private final ConteudoService conteudoService;
    
    @Autowired
    public ConteudoController(ConteudoService conteudoService) {
        this.conteudoService = conteudoService;
    }
    
    /**
     * Cria um novo conteúdo.
     * POST /api/conteudos
     */
    @PostMapping
    public ResponseEntity<ConteudoResponseDTO> criarConteudo(@Valid @RequestBody CriarConteudoDTO dto) {
        try {
            ConteudoResponseDTO conteudoCriado = conteudoService.criarConteudo(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(conteudoCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Busca conteúdo por ID.
     * GET /api/conteudos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConteudoResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<ConteudoResponseDTO> conteudo = conteudoService.buscarPorId(id);
        return conteudo.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Lista todos os conteúdos.
     * GET /api/conteudos
     */
    @GetMapping
    public ResponseEntity<List<ConteudoResponseDTO>> listarTodos() {
        List<ConteudoResponseDTO> conteudos = conteudoService.listarTodos();
        return ResponseEntity.ok(conteudos);
    }
    
    /**
     * Busca conteúdos por esporte.
     * GET /api/conteudos/esporte/{esporteId}
     */
    @GetMapping("/esporte/{esporteId}")
    public ResponseEntity<List<ConteudoResponseDTO>> buscarPorEsporte(@PathVariable Long esporteId) {
        List<ConteudoResponseDTO> conteudos = conteudoService.buscarPorEsporte(esporteId);
        return ResponseEntity.ok(conteudos);
    }
    
    /**
     * Busca conteúdos por título.
     * GET /api/conteudos/buscar?titulo={titulo}
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ConteudoResponseDTO>> buscarPorTitulo(@RequestParam String titulo) {
        List<ConteudoResponseDTO> conteudos = conteudoService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(conteudos);
    }
    
    /**
     * Busca conteúdos por nível.
     * GET /api/conteudos/nivel/{nivel}
     */
    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<ConteudoResponseDTO>> buscarPorNivel(@PathVariable String nivel) {
        List<ConteudoResponseDTO> conteudos = conteudoService.buscarPorNivel(nivel);
        return ResponseEntity.ok(conteudos);
    }
    
    /**
     * Busca conteúdos por faixa de duração.
     * GET /api/conteudos/duracao?min={min}&max={max}
     */
    @GetMapping("/duracao")
    public ResponseEntity<List<ConteudoResponseDTO>> buscarPorFaixaDuracao(
            @RequestParam int min, 
            @RequestParam int max) {
        List<ConteudoResponseDTO> conteudos = conteudoService.buscarPorFaixaDuracao(min, max);
        return ResponseEntity.ok(conteudos);
    }
}
