package com.educacao.esportiva.domain.repository;

import com.educacao.esportiva.domain.Esporte;
import java.util.List;
import java.util.Optional;

/**
 * Interface simples do repositório de Esporte.
 */
public interface EsporteRepository {
    
    Esporte save(Esporte esporte);
    
    Optional<Esporte> findById(Long id);
    
    List<Esporte> findAll();
    
    List<Esporte> findByCategoria(String categoria);
    
    Optional<Esporte> findByNome(String nome);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
}
