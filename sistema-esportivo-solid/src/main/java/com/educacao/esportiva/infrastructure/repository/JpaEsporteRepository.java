package com.educacao.esportiva.infrastructure.repository;

import com.educacao.esportiva.domain.Esporte;
import com.educacao.esportiva.domain.repository.EsporteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementação JPA do repositório de Esporte.
 */
@Repository
public interface JpaEsporteRepository extends JpaRepository<Esporte, Long>, EsporteRepository {
    
    @Override
    List<Esporte> findByCategoria(String categoria);
    
    @Override
    Optional<Esporte> findByNome(String nome);
}