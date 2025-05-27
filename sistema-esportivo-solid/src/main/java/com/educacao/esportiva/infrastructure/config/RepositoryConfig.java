package com.educacao.esportiva.infrastructure.config;

import com.educacao.esportiva.domain.repository.AulaRepository;
import com.educacao.esportiva.domain.repository.ConteudoRepository;
import com.educacao.esportiva.domain.repository.EsporteRepository;
import com.educacao.esportiva.domain.repository.ProfessorRepository;
import com.educacao.esportiva.infrastructure.repository.JpaAulaRepository;
import com.educacao.esportiva.infrastructure.repository.JpaConteudoRepository;
import com.educacao.esportiva.infrastructure.repository.JpaEsporteRepository;
import com.educacao.esportiva.infrastructure.repository.JpaProfessorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ========================================================================
 * CONFIGURAÇÃO DE REPOSITÓRIOS - VERSÃO SOLID
 * ========================================================================
 * 
 * DEMONSTRAÇÃO DO DIP (Dependency Inversion Principle):
 * 
 * Esta classe configura a inversão de dependência, fazendo com que:
 * - As camadas superiores (Application, Presentation) dependam das abstrações do domínio
 * - A camada de infraestrutura implementa essas abstrações
 * - Spring gerencia a injeção das implementações concretas
 * 
 * BENEFÍCIOS:
 * - Testabilidade: fácil mockar repositórios nos testes
 * - Flexibilidade: trocar implementações sem afetar outras camadas
 * - Baixo acoplamento: camadas não dependem de detalhes de infraestrutura
 */
@Configuration
public class RepositoryConfig {
    
    /**
     * Configura o bean do repositório de Esporte.
     * Spring automaticamente injeta a implementação JPA.
     */
    @Bean
    public EsporteRepository esporteRepository(JpaEsporteRepository jpaRepository) {
        return jpaRepository;
    }
    
    /**
     * Configura o bean do repositório de Professor.
     * Spring automaticamente injeta a implementação JPA.
     */
    @Bean
    public ProfessorRepository professorRepository(JpaProfessorRepository jpaRepository) {
        return jpaRepository;
    }
    
    /**
     * Configura o bean do repositório de Conteúdo.
     * Spring automaticamente injeta a implementação JPA.
     */
    @Bean
    public ConteudoRepository conteudoRepository(JpaConteudoRepository jpaRepository) {
        return jpaRepository;
    }
    
    /**
     * Configura o bean do repositório de Aula.
     * Spring automaticamente injeta a implementação JPA.
     */
    @Bean
    public AulaRepository aulaRepository(JpaAulaRepository jpaRepository) {
        return jpaRepository;
    }
}
