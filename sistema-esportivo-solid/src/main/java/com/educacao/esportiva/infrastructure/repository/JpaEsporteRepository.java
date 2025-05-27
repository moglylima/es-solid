package com.educacao.esportiva.infrastructure.repository;

import com.educacao.esportiva.domain.Esporte;
import com.educacao.esportiva.domain.repository.EsporteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementação JPA do repositório de Esporte.
 * Demonstra aplicação do DIP - a infraestrutura depende da abstração do domínio,
 * não o contrário.
 */
@Repository
public interface JpaEsporteRepository extends JpaRepository<Esporte, Long>, EsporteRepository {
    
    @Query("SELECT e FROM Esporte e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Esporte> buscarPorNomeContendo(@Param("nome") String nome);
    
    @Query("SELECT e FROM Esporte e WHERE e.ativo = true")
    List<Esporte> buscarAtivos();
    
    @Query("SELECT e FROM Esporte e WHERE e.nome = :nome AND e.ativo = true")
    Optional<Esporte> buscarPorNomeExato(@Param("nome") String nome);
}