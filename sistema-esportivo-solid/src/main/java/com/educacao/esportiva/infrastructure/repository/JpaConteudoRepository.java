package com.educacao.esportiva.infrastructure.repository;

import com.educacao.esportiva.domain.Conteudo;
import com.educacao.esportiva.domain.repository.ConteudoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementação JPA do repositório de Conteudo.
 * Demonstra aplicação do DIP - a infraestrutura depende da abstração do domínio.
 */
@Repository
public interface JpaConteudoRepository extends JpaRepository<Conteudo, Long>, ConteudoRepository {
    
    @Query("SELECT c FROM Conteudo c WHERE c.esporte.id = :esporteId")
    List<Conteudo> buscarPorEsporte(@Param("esporteId") Long esporteId);
    
    @Query("SELECT c FROM Conteudo c WHERE LOWER(c.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Conteudo> buscarPorTituloContendo(@Param("titulo") String titulo);
    
    @Query("SELECT c FROM Conteudo c WHERE c.nivel = :nivel")
    List<Conteudo> buscarPorNivel(@Param("nivel") String nivel);
    
    @Query("SELECT c FROM Conteudo c WHERE c.duracaoMinutos >= :duracaoMinima AND c.duracaoMinutos <= :duracaoMaxima")
    List<Conteudo> buscarPorFaixaDuracao(@Param("duracaoMinima") int duracaoMinima, 
                                        @Param("duracaoMaxima") int duracaoMaxima);
}
