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
    
    // Implementações simples para os métodos com LocalDateTime
    @Override
    default List<Aula> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        // Por enquanto, retorna todas as aulas (implementação simples)
        return findAll();
    }
    
    @Override
    default List<Aula> buscarFuturas(LocalDateTime dataHora) {
        // Por enquanto, retorna todas as aulas (implementação simples)
        return findAll();
    }
    
    @Override
    default List<Aula> buscarPorProfessorEPeriodo(Long professorId, LocalDateTime inicio, LocalDateTime fim) {
        // Implementação simples usando apenas professor
        return buscarPorProfessor(professorId);
    }
    
    // Additional method implementations for compatibility
    @Override
    default List<Aula> findByDataAula(java.time.LocalDate data) {
        return findAll().stream()
                .filter(aula -> aula.getDataAula().equals(data))
                .toList();
    }
    
    @Override
    default List<Aula> findByProfessorIdAndDataAulaAfter(Long professorId, java.time.LocalDate data) {
        return findAll().stream()
                .filter(aula -> aula.getProfessorId().equals(professorId))
                .filter(aula -> aula.getDataAula().isAfter(data))
                .toList();
    }
    
    @Override
    default List<Aula> findByLocalContainingIgnoreCase(String local) {
        return findAll().stream()
                .filter(aula -> aula.getLocal().toLowerCase().contains(local.toLowerCase()))
                .toList();
    }
    
    @Override
    default boolean existsByProfessorIdAndDataAulaAndHorarioInicio(Long professorId, java.time.LocalDate dataAula, java.time.LocalTime horario) {
        return findAll().stream()
                .anyMatch(aula -> aula.getProfessorId().equals(professorId) 
                        && aula.getDataAula().equals(dataAula) 
                        && aula.getHorarioInicio().equals(horario));
    }
}
