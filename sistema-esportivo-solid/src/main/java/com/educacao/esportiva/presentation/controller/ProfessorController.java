package com.educacao.esportiva.presentation.controller;

import com.educacao.esportiva.application.dto.CriarProfessorDTO;
import com.educacao.esportiva.application.dto.ProfessorResponseDTO;
import com.educacao.esportiva.application.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ========================================================================
 * CONTROLADOR PROFESSOR - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SRP (Single Responsibility Principle):
 *    - Responsabilidade única: controle HTTP para operações de Professor
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
 *    - Depende da abstração ProfessorService
 *    - Inversão de controle via injeção de dependência
 */
@RestController
@RequestMapping("/api/professores")
@CrossOrigin(origins = "*")
public class ProfessorController {
    
    private final ProfessorService professorService;
    
    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    
    /**
     * Cria um novo professor.
     * POST /api/professores
     */
    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> criarProfessor(@Valid @RequestBody CriarProfessorDTO dto) {
        try {
            ProfessorResponseDTO professorCriado = professorService.criarProfessor(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(professorCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Busca professor por ID.
     * GET /api/professores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<ProfessorResponseDTO> professor = professorService.buscarPorId(id);
        return professor.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Lista todos os professores ativos.
     * GET /api/professores
     */
    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarAtivos() {
        List<ProfessorResponseDTO> professores = professorService.listarAtivos();
        return ResponseEntity.ok(professores);
    }
    
    /**
     * Busca professores por nome.
     * GET /api/professores/buscar?nome={nome}
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ProfessorResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<ProfessorResponseDTO> professores = professorService.buscarPorNome(nome);
        return ResponseEntity.ok(professores);
    }
    
    /**
     * Busca professores por especialização.
     * GET /api/professores/especializacao/{especializacao}
     */
    @GetMapping("/especializacao/{especializacao}")
    public ResponseEntity<List<ProfessorResponseDTO>> buscarPorEspecializacao(@PathVariable String especializacao) {
        List<ProfessorResponseDTO> professores = professorService.buscarPorEspecializacao(especializacao);
        return ResponseEntity.ok(professores);
    }
    
    /**
     * Desativa um professor.
     * DELETE /api/professores/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarProfessor(@PathVariable Long id) {
        try {
            professorService.desativarProfessor(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
