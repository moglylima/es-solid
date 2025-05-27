package com.educacao.esportiva.application.service;

import com.educacao.esportiva.domain.Esporte;
import com.educacao.esportiva.domain.repository.EsporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço simples para operações com Esporte.
 */
@Service
public class EsporteService {
    
    @Autowired
    private EsporteRepository esporteRepository;
    
    public Esporte criarEsporte(String nome, String categoria) {
        Esporte esporte = new Esporte(nome, categoria);
        return esporteRepository.save(esporte);
    }
    
    public List<Esporte> listarTodos() {
        return esporteRepository.findAll();
    }
    
    public Optional<Esporte> buscarPorId(Long id) {
        return esporteRepository.findById(id);
    }
    
    public List<Esporte> buscarPorCategoria(String categoria) {
        return esporteRepository.findByCategoria(categoria);
    }
    
    public Optional<Esporte> buscarPorNome(String nome) {
        return esporteRepository.findByNome(nome);
    }
    
    public Esporte atualizarEsporte(Long id, String nome, String categoria) {
        Optional<Esporte> esporteExistente = esporteRepository.findById(id);
        if (esporteExistente.isPresent()) {
            Esporte esporte = esporteExistente.get();
            esporte.setNome(nome);
            esporte.setCategoria(categoria);
            return esporteRepository.save(esporte);
        }
        throw new RuntimeException("Esporte não encontrado com ID: " + id);
    }
    
    public void excluirEsporte(Long id) {
        if (esporteRepository.existsById(id)) {
            esporteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Esporte não encontrado com ID: " + id);
        }
    }
}