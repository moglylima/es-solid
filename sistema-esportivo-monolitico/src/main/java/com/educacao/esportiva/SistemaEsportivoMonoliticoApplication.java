package com.educacao.esportiva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ========================================================================
 * CLASSE PRINCIPAL DO SPRING BOOT - VERSÃO MONOLÍTICA
 * ========================================================================
 * 
 * Esta é a classe principal que inicializa a aplicação Spring Boot.
 * Na versão monolítica, toda a lógica está concentrada no SistemaEsportivoController.
 * 
 * CARACTERÍSTICAS DESTA VERSÃO:
 * - Uma única classe controller gerencia tudo
 * - Persistência em memória (HashMap)
 * - Violação de princípios SOLID
 * - Código difícil de manter e testar
 * 
 * PARA EXECUTAR:
 * mvn spring-boot:run
 * 
 * A API estará disponível em: http://localhost:8080/api
 */
@SpringBootApplication
public class SistemaEsportivoMonoliticoApplication {

    /**
     * Método principal que inicializa a aplicação Spring Boot
     * @param args Argumentos da linha de comando
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("INICIANDO SISTEMA ESPORTIVO MONOLÍTICO");
        System.out.println("========================================");
        System.out.println("⚠️  ATENÇÃO: Esta é uma implementação NÃO-SOLID");
        System.out.println("⚠️  Propositalmente viola princípios de design");
        System.out.println("⚠️  Todos os endpoints em: /api/*");
        System.out.println("========================================");
        
        SpringApplication.run(SistemaEsportivoMonoliticoApplication.class, args);
        
        System.out.println("🚀 Aplicação iniciada em: http://localhost:8080");
        System.out.println("📚 Endpoints disponíveis:");
        System.out.println("   POST /api/esportes");
        System.out.println("   GET  /api/esportes");
        System.out.println("   POST /api/conteudos");
        System.out.println("   GET  /api/conteudos/esporte/{id}");
        System.out.println("   POST /api/professores");
        System.out.println("   GET  /api/professores/especializacao/{especializacao}");
        System.out.println("   POST /api/aulas");
        System.out.println("   GET  /api/aulas/filtrar?categoria={categoria}");
        System.out.println("   GET  /api/debug/status");
    }
}
