package com.educacao.esportiva.domain.repository;

import com.educacao.esportiva.domain.Aula;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * ========================================================================
 * INTERFACE REPOSITORY AULA - APLICAÇÃO DO DIP
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    ✅ ABSTRAÇÃO para persistência de Aula
 *    ✅ Camada de domínio NÃO depende de implementação concreta
 *    ✅ Infraestrutura depende desta abstração
 * 
 * 2. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    ✅ Interface específica para operações de Aula
 *    ✅ Métodos relacionados apenas ao domínio Aula
 * 
 * 3. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    ✅ Responsabilidade única: contrato para persistência de Aula
 */
public interface AulaRepository {
    
    /**
     * Salvar uma aula (criar ou atualizar)
     * 
     * @param aula aula a ser salva
     * @return aula salva com ID gerado
     */
    Aula save(Aula aula);
    
    /**
     * Buscar aula por ID
     * 
     * @param id ID da aula
     * @return Optional contendo a aula se encontrada
     */
    Optional<Aula> findById(Long id);
    
    /**
     * Buscar todas as aulas
     * 
     * @return lista de todas as aulas
     */
    List<Aula> findAll();
    
    /**
     * Buscar aulas por professor ID
     * 
     * @param professorId ID do professor
     * @return lista de aulas do professor
     */
    List<Aula> findByProfessorId(Long professorId);
    
    /**
     * Buscar aulas por conteúdo ID
     * 
     * @param conteudoId ID do conteúdo
     * @return lista de aulas do conteúdo
     */
    List<Aula> findByConteudoId(Long conteudoId);
    
    /**
     * Buscar aulas por data específica
     * 
     * @param data data da aula
     * @return lista de aulas na data
     */
    List<Aula> findByDataAula(java.time.LocalDate data);
    
    /**
     * Buscar aulas futuras de um professor
     * 
     * @param professorId ID do professor
     * @param dataHora data/hora atual
     * @return lista de aulas futuras do professor
     */
    List<Aula> findByProfessorIdAndDataAulaAfter(Long professorId, java.time.LocalDate data);
    
    /**
     * Buscar aulas por local
     * 
     * @param local local da aula
     * @return lista de aulas no local
     */
    List<Aula> findByLocalContainingIgnoreCase(String local);
    
    /**
     * Verificar se professor tem aula em conflito
     * 
     * @param professorId ID do professor
     * @param dataAula data da aula
     * @param horario horário da aula
     * @return true se existe conflito
     */
    boolean existsByProfessorIdAndDataAulaAndHorarioInicio(Long professorId, java.time.LocalDate dataAula, java.time.LocalTime horario);
    
    /**
     * Contar aulas por professor
     * 
     * @param professorId ID do professor
     * @return número de aulas do professor
     */
    long countByProfessorId(Long professorId);
    
    /**
     * Deletar aula por ID
     * 
     * @param id ID da aula a ser deletada
     */
    void deleteById(Long id);
    
    /**
     * Verificar se existe aula com o ID
     * 
     * @param id ID para verificar
     * @return true se existe
     */
    boolean existsById(Long id);
    
    /**
     * Buscar aulas por professor e período
     * 
     * @param professorId ID do professor
     * @param inicio data/hora de início
     * @param fim data/hora de fim
     * @return lista de aulas no período
     */
    List<Aula> buscarPorProfessorEPeriodo(Long professorId, LocalDateTime inicio, LocalDateTime fim);
    
    /**
     * Buscar aulas por professor
     */
    List<Aula> buscarPorProfessor(Long professorId);
    
    /**
     * Buscar aulas por conteúdo
     */
    List<Aula> buscarPorConteudo(Long conteudoId);
    
    /**
     * Buscar aulas futuras
     */
    List<Aula> buscarFuturas(LocalDateTime dataHora);
    
    /**
     * Buscar aulas por período
     */
    List<Aula> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim);
}