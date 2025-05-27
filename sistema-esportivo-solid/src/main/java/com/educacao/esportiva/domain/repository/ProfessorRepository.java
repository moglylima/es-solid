package com.educacao.esportiva.domain.repository;

import com.educacao.esportiva.domain.Professor;
import java.util.List;
import java.util.Optional;

/**
 * ========================================================================
 * INTERFACE REPOSITORY PROFESSOR - APLICAÇÃO DO DIP
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    ✅ ABSTRAÇÃO para persistência de Professor
 *    ✅ Camada de domínio NÃO depende de implementação concreta
 *    ✅ Infraestrutura depende desta abstração
 * 
 * 2. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    ✅ Interface específica para operações de Professor
 *    ✅ Métodos relacionados apenas ao domínio Professor
 * 
 * 3. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    ✅ Responsabilidade única: contrato para persistência de Professor
 */
public interface ProfessorRepository {
    
    /**
     * Salvar um professor (criar ou atualizar)
     * 
     * @param professor professor a ser salvo
     * @return professor salvo com ID gerado
     */
    Professor save(Professor professor);
    
    /**
     * Buscar professor por ID
     * 
     * @param id ID do professor
     * @return Optional contendo o professor se encontrado
     */
    Optional<Professor> findById(Long id);
    
    /**
     * Buscar todos os professores
     * 
     * @return lista de todos os professores
     */
    List<Professor> findAll();
    
    /**
     * Buscar professores por especialização
     * 
     * @param especializacao especialização do professor
     * @return lista de professores com a especialização
     */
    List<Professor> findByEspecializacaoContainingIgnoreCase(String especializacao);
    
    /**
     * Buscar professor por nome (case insensitive)
     * 
     * @param nome nome do professor
     * @return Optional contendo o professor se encontrado
     */
    Optional<Professor> findByNomeIgnoreCase(String nome);
    
    /**
     * Verificar se existe professor com o nome
     * 
     * @param nome nome para verificar
     * @return true se existe
     */
    boolean existsByNomeIgnoreCase(String nome);
    
    /**
     * Contar professores por especialização
     * 
     * @param especializacao especialização para contar
     * @return número de professores com a especialização
     */
    long countByEspecializacaoContainingIgnoreCase(String especializacao);
    
    /**
     * Deletar professor por ID
     * 
     * @param id ID do professor a ser deletado
     */
    void deleteById(Long id);
    
    /**
     * Verificar se existe professor com o ID
     * 
     * @param id ID para verificar
     * @return true se existe
     */
    boolean existsById(Long id);
}
