package com.educacao.esportiva.domain.repository;

import com.educacao.esportiva.domain.Conteudo;
import java.util.List;
import java.util.Optional;

/**
 * ========================================================================
 * INTERFACE REPOSITORY CONTEUDO - APLICAÇÃO DO DIP
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    ✅ ABSTRAÇÃO para persistência de Conteudo
 *    ✅ Camada de domínio NÃO depende de implementação concreta
 *    ✅ Infraestrutura depende desta abstração
 * 
 * 2. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    ✅ Interface específica para operações de Conteudo
 *    ✅ Métodos relacionados apenas ao domínio Conteudo
 * 
 * 3. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    ✅ Responsabilidade única: contrato para persistência de Conteudo
 */
public interface ConteudoRepository {
    
    /**
     * Salvar um conteúdo (criar ou atualizar)
     * 
     * @param conteudo conteúdo a ser salvo
     * @return conteúdo salvo com ID gerado
     */
    Conteudo save(Conteudo conteudo);
    
    /**
     * Buscar conteúdo por ID
     * 
     * @param id ID do conteúdo
     * @return Optional contendo o conteúdo se encontrado
     */
    Optional<Conteudo> findById(Long id);
    
    /**
     * Buscar todos os conteúdos
     * 
     * @return lista de todos os conteúdos
     */
    List<Conteudo> findAll();
    
    /**
     * Buscar conteúdos por título (case insensitive)
     * 
     * @param titulo título para buscar
     * @return lista de conteúdos com o título
     */
    List<Conteudo> findByTituloContainingIgnoreCase(String titulo);
    
    /**
     * Buscar conteúdos por tipo
     * 
     * @param tipo tipo do conteúdo
     * @return lista de conteúdos do tipo especificado
     */
    List<Conteudo> findByTipoIgnoreCase(String tipo);
    
    /**
     * Buscar conteúdos por esporte ID
     * 
     * @param esporteId ID do esporte
     * @return lista de conteúdos do esporte
     */
    List<Conteudo> findByEsporteId(Long esporteId);
    
    /**
     * Buscar conteúdos por esporte ID (alias)
     * 
     * @param esporteId ID do esporte
     * @return lista de conteúdos do esporte
     */
    default List<Conteudo> buscarPorEsporte(Long esporteId) {
        return findByEsporteId(esporteId);
    }
    
    /**
     * Buscar conteúdos por título (alias)
     * 
     * @param titulo título para buscar
     * @return lista de conteúdos com o título
     */
    default List<Conteudo> buscarPorTituloContendo(String titulo) {
        return findByTituloContainingIgnoreCase(titulo);
    }
    
    /**
     * Buscar conteúdos por nível (alias)
     * 
     * @param nivel nível para buscar
     * @return lista de conteúdos do nível
     */
    default List<Conteudo> buscarPorNivel(String nivel) {
        return findByTipoIgnoreCase(nivel);
    }
    
    /**
     * Buscar conteúdos por faixa de duração
     * 
     * @param duracaoMin duração mínima
     * @param duracaoMax duração máxima
     * @return lista de conteúdos na faixa de duração
     */
    List<Conteudo> findByDuracaoMinutosBetween(Integer duracaoMin, Integer duracaoMax);
    
    /**
     * Buscar conteúdos por faixa de duração (alias)
     * 
     * @param duracaoMin duração mínima
     * @param duracaoMax duração máxima
     * @return lista de conteúdos na faixa de duração
     */
    default List<Conteudo> buscarPorFaixaDuracao(int duracaoMin, int duracaoMax) {
        return findByDuracaoMinutosBetween(duracaoMin, duracaoMax);
    }
    
    /**
     * Verificar se existe conteúdo com o título
     * 
     * @param titulo título para verificar
     * @return true se existe
     */
    boolean existsByTituloIgnoreCase(String titulo);
    
    /**
     * Contar conteúdos por tipo
     * 
     * @param tipo tipo para contar
     * @return número de conteúdos do tipo
     */
    long countByTipoIgnoreCase(String tipo);
    
    /**
     * Deletar conteúdo por ID
     * 
     * @param id ID do conteúdo a ser deletado
     */
    void deleteById(Long id);
    
    /**
     * Verificar se existe conteúdo com o ID
     * 
     * @param id ID para verificar
     * @return true se existe
     */
    boolean existsById(Long id);
}