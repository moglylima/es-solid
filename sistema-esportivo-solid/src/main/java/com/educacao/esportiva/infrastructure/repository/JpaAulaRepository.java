package com.educacao.esportiva.infrastructure.repository;

import com.educacao.esportiva.domain.Aula;
import com.educacao.esportiva.domain.repository.AulaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementação JPA do repositório de Aula.
 * Demonstra aplicação do DIP - a infraestrutura depende da abstração do domínio.
 */
@Repository
public interface JpaAulaRepository extends JpaRepository<Aula, Long>, AulaRepository {
    
    @Query("SELECT a FROM Aula a WHERE a.professor.id = :professorId")
    List<Aula> buscarPorProfessor(@Param("professorId") Long professorId);
    
    @Query("SELECT a FROM Aula a WHERE a.conteudo.id = :conteudoId")
    List<Aula> buscarPorConteudo(@Param("conteudoId") Long conteudoId);
    
    @Query("SELECT a FROM Aula a WHERE a.dataHora >= :inicio AND a.dataHora <= :fim")
    List<Aula> buscarPorPeriodo(@Param("inicio") LocalDateTime inicio, 
                               @Param("fim") LocalDateTime fim);
    
    @Query("SELECT a FROM Aula a WHERE a.dataHora >= :dataInicio ORDER BY a.dataHora ASC")
    List<Aula> buscarFuturas(@Param("dataInicio") LocalDateTime dataInicio);
    
    @Query("SELECT a FROM Aula a WHERE a.professor.id = :professorId AND a.dataHora >= :inicio AND a.dataHora <= :fim")
    List<Aula> buscarPorProfessorEPeriodo(@Param("professorId") Long professorId,
                                         @Param("inicio") LocalDateTime inicio,
                                         @Param("fim") LocalDateTime fim);
}
