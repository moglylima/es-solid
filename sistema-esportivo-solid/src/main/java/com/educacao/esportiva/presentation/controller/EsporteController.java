package com.educacao.esportiva.presentation.controller;

import com.educacao.esportiva.application.service.EsporteService;
import com.educacao.esportiva.domain.Esporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST simples para operações com Esporte.
 */
@RestController
@RequestMapping("/api/esportes")
public class EsporteController {
    
    @Autowired
    private EsporteService esporteService;
    
    @PostMapping
    public ResponseEntity<Esporte> criarEsporte(@RequestBody EsporteRequest request) {
        try {
            Esporte esporte = esporteService.criarEsporte(request.getNome(), request.getCategoria());
            return ResponseEntity.status(HttpStatus.CREATED).body(esporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Esporte>> listarTodos() {
        List<Esporte> esportes = esporteService.listarTodos();
        return ResponseEntity.ok(esportes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Esporte> buscarPorId(@PathVariable Long id) {
        Optional<Esporte> esporte = esporteService.buscarPorId(id);
        return esporte.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Esporte>> buscarPorCategoria(@PathVariable String categoria) {
        List<Esporte> esportes = esporteService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(esportes);
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Esporte> buscarPorNome(@PathVariable String nome) {
        Optional<Esporte> esporte = esporteService.buscarPorNome(nome);
        return esporte.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Esporte> atualizarEsporte(@PathVariable Long id, @RequestBody EsporteRequest request) {
        try {
            Esporte esporte = esporteService.atualizarEsporte(id, request.getNome(), request.getCategoria());
            return ResponseEntity.ok(esporte);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEsporte(@PathVariable Long id) {
        try {
            esporteService.excluirEsporte(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Classe interna para request body
     */
    public static class EsporteRequest {
        private String nome;
        private String categoria;
        
        public EsporteRequest() {}
        
        public String getNome() {
            return nome;
        }
        
        public void setNome(String nome) {
            this.nome = nome;
        }
        
        public String getCategoria() {
            return categoria;
        }
        
        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
    }
}