package com.educacao.esportiva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ========================================================================
 * APLICAÇÃO PRINCIPAL - VERSÃO SOLID
 * ========================================================================
 * 
 * Classe principal que inicializa a aplicação Spring Boot demonstrando
 * a implementação dos princípios SOLID.
 * 
 * MELHORIAS EM RELAÇÃO À VERSÃO MONOLÍTICA:
 * - Arquitetura em camadas bem definidas
 * - Aplicação dos princípios SOLID
 * - Separação de responsabilidades
 * - Facilidade de manutenção e extensão
 */
@SpringBootApplication
public class SistemaEsportivoSolidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaEsportivoSolidApplication.class, args);
    }
}