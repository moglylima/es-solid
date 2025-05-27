package com.educacao.esportiva.presentation.controller;

import com.educacao.esportiva.application.dto.AulaResponseDTO;
import com.educacao.esportiva.application.dto.CriarAulaDTO;
import com.educacao.esportiva.application.service.AulaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * ========================================================================
 * CONTROLADOR AULA - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SRP (Single Responsibility Principle):
 *    - Responsabilidade única: controle HTTP para operações de Aula
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
 *    - Depende da abstração AulaService
 *    - Inversão de controle via injeção de dependência
 */
@RestController
@RequestMapping("/api/aulas")
@CrossOrigin(origins = "*")
public class AulaController {
    
    private final AulaService aulaService;
    
    @Autowired
    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }
    
    /**
     * Cria uma nova aula.
     * POST /api/aulas
     */
    @PostMapping
    public ResponseEntity<AulaResponseDTO> criarAula(@Valid @RequestBody CriarAulaDTO dto) {
        try {
            AulaResponseDTO aulaCriada = aulaService.criarAula(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(aulaCriada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Busca aula por ID.
     * GET /api/aulas/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<AulaResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<AulaResponseDTO> aula = aulaService.buscarPorId(id);
        return aula.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Lista todas as aulas.
     * GET /api/aulas
     */
    @GetMapping
    public ResponseEntity<List<AulaResponseDTO>> listarTodas() {
        List<AulaResponseDTO> aulas = aulaService.listarTodas();
        return ResponseEntity.ok(aulas);
    }
    
    /**
     * Lista aulas futuras.
     * GET /api/aulas/futuras
     */
    @GetMapping("/futuras")
    public ResponseEntity<List<AulaResponseDTO>> listarFuturas() {
        List<AulaResponseDTO> aulas = aulaService.buscarFuturas();
        return ResponseEntity.ok(aulas);
    }
    
    /**
     * Busca aulas por professor.
     * GET /api/aulas/professor/{professorId}
     */
    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<AulaResponseDTO>> buscarPorProfessor(@PathVariable Long professorId) {
        List<AulaResponseDTO> aulas = aulaService.buscarPorProfessor(professorId);
        return ResponseEntity.ok(aulas);
    }
    
    /**
     * Busca aulas por conteúdo.
     * GET /api/aulas/conteudo/{conteudoId}
     */
    @GetMapping("/conteudo/{conteudoId}")
    public ResponseEntity<List<AulaResponseDTO>> buscarPorConteudo(@PathVariable Long conteudoId) {
        List<AulaResponseDTO> aulas = aulaService.buscarPorConteudo(conteudoId);
        return ResponseEntity.ok(aulas);
    }
    
    /**
     * Busca aulas por período.
     * GET /api/aulas/periodo?inicio={inicio}&fim={fim}
     */
    @GetMapping("/periodo")
    public ResponseEntity<List<AulaResponseDTO>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        List<AulaResponseDTO> aulas = aulaService.buscarPorPeriodo(inicio, fim);
        return ResponseEntity.ok(aulas);
    }
    
    /**
     * Cancela uma aula.
     * DELETE /api/aulas/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarAula(@PathVariable Long id) {
        try {
            aulaService.cancelarAula(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
