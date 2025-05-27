package com.educacao.esportiva.infrastructure.config;

import com.educacao.esportiva.domain.Esporte;
import com.educacao.esportiva.domain.repository.EsporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializador de dados simples para Esporte.
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private EsporteRepository esporteRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Verificar se já existem dados
        if (esporteRepository.findAll().isEmpty()) {
            carregarEsportes();
        }
    }
    
    private void carregarEsportes() {
        // Dados de exemplo de esportes
        esporteRepository.save(new Esporte("Futebol", "Coletivo"));
        esporteRepository.save(new Esporte("Basquete", "Coletivo"));
        esporteRepository.save(new Esporte("Vôlei", "Coletivo"));
        esporteRepository.save(new Esporte("Tênis", "Individual"));
        esporteRepository.save(new Esporte("Natação", "Individual"));
        esporteRepository.save(new Esporte("Atletismo", "Individual"));
        
        System.out.println("✅ Dados de esportes carregados com sucesso!");
    }
}
