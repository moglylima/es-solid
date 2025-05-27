package com.educacao.esportiva.infrastructure.repository;

import com.educacao.esportiva.domain.Professor;
import com.educacao.esportiva.domain.repository.ProfessorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementação JPA do repositório de Professor.
 * Demonstra aplicação do DIP - a infraestrutura depende da abstração do domínio.
 */
@Repository
public interface JpaProfessorRepository extends JpaRepository<Professor, Long>, ProfessorRepository {
    
    @Query("SELECT p FROM Professor p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Professor> buscarPorNomeContendo(@Param("nome") String nome);
    
    @Query("SELECT p FROM Professor p WHERE p.ativo = true")
    List<Professor> buscarAtivos();
    
    @Query("SELECT p FROM Professor p WHERE p.email = :email")
    Optional<Professor> buscarPorEmail(@Param("email") String email);
    
    @Query("SELECT p FROM Professor p WHERE p.especializacao = :especializacao AND p.ativo = true")
    List<Professor> buscarPorEspecializacao(@Param("especializacao") String especializacao);
}
