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
    
    /**
     * Buscar professor por email
     * 
     * @param email email do professor
     * @return Optional contendo o professor se encontrado
     */
    Optional<Professor> findByEmailIgnoreCase(String email);
    
    /**
     * Buscar professor por email (alias)
     * 
     * @param email email do professor
     * @return Optional contendo o professor se encontrado
     */
    default Optional<Professor> buscarPorEmail(String email) {
        return findByEmailIgnoreCase(email);
    }
    
    /**
     * Buscar professores ativos
     * 
     * @return lista de professores ativos
     */
    List<Professor> findByAtivoTrue();
    
    /**
     * Buscar professores ativos (alias)
     * 
     * @return lista de professores ativos
     */
    default List<Professor> buscarAtivos() {
        return findByAtivoTrue();
    }
    
    /**
     * Buscar professores por nome contendo
     * 
     * @param nome nome para buscar
     * @return lista de professores com o nome
     */
    List<Professor> findByNomeContainingIgnoreCase(String nome);
    
    /**
     * Buscar professores por nome contendo (alias)
     * 
     * @param nome nome para buscar
     * @return lista de professores com o nome
     */
    default List<Professor> buscarPorNomeContendo(String nome) {
        return findByNomeContainingIgnoreCase(nome);
    }
    
    /**
     * Buscar professores por especialização (alias)
     * 
     * @param especializacao especialização para buscar
     * @return lista de professores com a especialização
     */
    default List<Professor> buscarPorEspecializacao(String especializacao) {
        return findByEspecializacaoContainingIgnoreCase(especializacao);
    }
}
