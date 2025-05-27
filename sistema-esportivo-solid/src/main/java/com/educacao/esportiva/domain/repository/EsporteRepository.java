package com.educacao.esportiva.domain.repository;

import com.educacao.esportiva.domain.Esporte;
import java.util.List;
import java.util.Optional;

/**
 * ========================================================================
 * INTERFACE REPOSITORY ESPORTE - APLICAÇÃO DO DIP
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    ✅ ABSTRAÇÃO para persistência de Esporte
 *    ✅ Camada de domínio NÃO depende de implementação concreta
 *    ✅ Infraestrutura depende desta abstração
 * 
 * 2. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    ✅ Interface específica para operações de Esporte
 *    ✅ Métodos relacionados apenas ao domínio Esporte
 * 
 * 3. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    ✅ Responsabilidade única: contrato para persistência de Esporte
 * 
 * PROBLEMA RESOLVIDO:
 * - Versão monolítica: dependência direta de HashMap
 * - Versão SOLID: dependência de abstração, permite trocar implementação
 */
public interface EsporteRepository {
    
    /**
     * Salvar um esporte (criar ou atualizar)
     * 
     * @param esporte esporte a ser salvo
     * @return esporte salvo com ID gerado
     */
    Esporte save(Esporte esporte);
    
    /**
     * Buscar esporte por ID
     * 
     * @param id ID do esporte
     * @return Optional contendo o esporte se encontrado
     */
    Optional<Esporte> findById(Long id);
    
    /**
     * Buscar todos os esportes
     * 
     * @return lista de todos os esportes
     */
    List<Esporte> findAll();
    
    /**
     * Buscar esportes por categoria
     * 
     * @param categoria categoria do esporte
     * @return lista de esportes da categoria
     */
    List<Esporte> findByCategoria(String categoria);
    
    /**
     * Buscar esporte por nome (case insensitive)
     * 
     * @param nome nome do esporte
     * @return Optional contendo o esporte se encontrado
     */
    Optional<Esporte> findByNomeIgnoreCase(String nome);
    
    /**
     * Verificar se existe esporte com o nome
     * 
     * @param nome nome para verificar
     * @return true se existe
     */
    boolean existsByNomeIgnoreCase(String nome);
    
    /**
     * Contar esportes por categoria
     * 
     * @param categoria categoria para contar
     * @return número de esportes na categoria
     */
    long countByCategoria(String categoria);
    
    /**
     * Deletar esporte por ID
     * 
     * @param id ID do esporte a ser deletado
     */
    void deleteById(Long id);
    
    /**
     * Verificar se existe esporte com o ID
     * 
     * @param id ID para verificar
     * @return true se existe
     */
    boolean existsById(Long id);
}
