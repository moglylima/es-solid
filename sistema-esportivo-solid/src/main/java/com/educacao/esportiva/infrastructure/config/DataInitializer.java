package com.educacao.esportiva.infrastructure.config;

import com.educacao.esportiva.domain.Aula;
import com.educacao.esportiva.domain.Conteudo;
import com.educacao.esportiva.domain.Esporte;
import com.educacao.esportiva.domain.Professor;
import com.educacao.esportiva.domain.repository.AulaRepository;
import com.educacao.esportiva.domain.repository.ConteudoRepository;
import com.educacao.esportiva.domain.repository.EsporteRepository;
import com.educacao.esportiva.domain.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * ========================================================================
 * INICIALIZADOR DE DADOS - VERSÃO SOLID
 * ========================================================================
 * 
 * DEMONSTRAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * Esta classe carrega dados de exemplo para demonstrar o funcionamento
 * do sistema, aplicando os princípios SOLID:
 * 
 * 1. SRP: Responsabilidade única de inicializar dados
 * 2. DIP: Depende das abstrações dos repositórios
 * 3. ISP: Usa apenas os métodos necessários dos repositórios
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    private final EsporteRepository esporteRepository;
    private final ProfessorRepository professorRepository;
    private final ConteudoRepository conteudoRepository;
    private final AulaRepository aulaRepository;
    
    @Autowired
    public DataInitializer(EsporteRepository esporteRepository,
                          ProfessorRepository professorRepository,
                          ConteudoRepository conteudoRepository,
                          AulaRepository aulaRepository) {
        this.esporteRepository = esporteRepository;
        this.professorRepository = professorRepository;
        this.conteudoRepository = conteudoRepository;
        this.aulaRepository = aulaRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existem dados
        if (esporteRepository.count() > 0) {
            return; // Dados já carregados
        }
        
        System.out.println("========================================================================");
        System.out.println("INICIALIZANDO DADOS DE EXEMPLO - SISTEMA ESPORTIVO SOLID");
        System.out.println("========================================================================");
        
        // Cria esportes
        Esporte futebol = new Esporte("Futebol", "Esporte coletivo com bola");
        Esporte basquete = new Esporte("Basquete", "Esporte coletivo com cesta");
        Esporte tenis = new Esporte("Tênis", "Esporte individual com raquete");
        Esporte natacao = new Esporte("Natação", "Esporte aquático individual");
        
        esporteRepository.save(futebol);
        esporteRepository.save(basquete);
        esporteRepository.save(tenis);
        esporteRepository.save(natacao);
        
        // Cria professores
        Professor profCarlos = new Professor("Carlos Silva", "carlos@exemplo.com", "Futebol");
        Professor profAna = new Professor("Ana Santos", "ana@exemplo.com", "Basquete");
        Professor profJoao = new Professor("João Oliveira", "joao@exemplo.com", "Tênis");
        Professor profMaria = new Professor("Maria Costa", "maria@exemplo.com", "Natação");
        
        professorRepository.save(profCarlos);
        professorRepository.save(profAna);
        professorRepository.save(profJoao);
        professorRepository.save(profMaria);
        
        // Cria conteúdos
        Conteudo fundFutebol = new Conteudo("Fundamentos do Futebol", 
            "Aprenda os fundamentos básicos do futebol", "Iniciante", 60, futebol);
        Conteudo dribleFutebol = new Conteudo("Técnicas de Drible", 
            "Domine as principais técnicas de drible", "Intermediário", 45, futebol);
        
        Conteudo fundBasquete = new Conteudo("Fundamentos do Basquete", 
            "Aprenda os fundamentos básicos do basquete", "Iniciante", 50, basquete);
        Conteudo arremBasquete = new Conteudo("Técnicas de Arremesso", 
            "Melhore sua precisão nos arremessos", "Intermediário", 40, basquete);
        
        Conteudo fundTenis = new Conteudo("Fundamentos do Tênis", 
            "Aprenda os golpes básicos do tênis", "Iniciante", 55, tenis);
        Conteudo saqueTenis = new Conteudo("Técnica do Saque", 
            "Domine a técnica do saque", "Avançado", 35, tenis);
        
        Conteudo fundNatacao = new Conteudo("Fundamentos da Natação", 
            "Aprenda os nados básicos", "Iniciante", 45, natacao);
        Conteudo crawlNatacao = new Conteudo("Aperfeiçoamento do Crawl", 
            "Melhore sua técnica no nado crawl", "Intermediário", 40, natacao);
        
        conteudoRepository.save(fundFutebol);
        conteudoRepository.save(dribleFutebol);
        conteudoRepository.save(fundBasquete);
        conteudoRepository.save(arremBasquete);
        conteudoRepository.save(fundTenis);
        conteudoRepository.save(saqueTenis);
        conteudoRepository.save(fundNatacao);
        conteudoRepository.save(crawlNatacao);
        
        // Cria aulas futuras
        LocalDateTime agora = LocalDateTime.now();
        
        Aula aulaFut1 = new Aula(agora.plusDays(1).withHour(9).withMinute(0), profCarlos, fundFutebol);
        Aula aulaFut2 = new Aula(agora.plusDays(2).withHour(14).withMinute(0), profCarlos, dribleFutebol);
        
        Aula aulaBas1 = new Aula(agora.plusDays(1).withHour(10).withMinute(0), profAna, fundBasquete);
        Aula aulaBas2 = new Aula(agora.plusDays(3).withHour(16).withMinute(0), profAna, arremBasquete);
        
        Aula aulaTen1 = new Aula(agora.plusDays(1).withHour(15).withMinute(0), profJoao, fundTenis);
        Aula aulaTen2 = new Aula(agora.plusDays(4).withHour(11).withMinute(0), profJoao, saqueTenis);
        
        Aula aulaNat1 = new Aula(agora.plusDays(2).withHour(8).withMinute(0), profMaria, fundNatacao);
        Aula aulaNat2 = new Aula(agora.plusDays(5).withHour(17).withMinute(0), profMaria, crawlNatacao);
        
        aulaRepository.save(aulaFut1);
        aulaRepository.save(aulaFut2);
        aulaRepository.save(aulaBas1);
        aulaRepository.save(aulaBas2);
        aulaRepository.save(aulaTen1);
        aulaRepository.save(aulaTen2);
        aulaRepository.save(aulaNat1);
        aulaRepository.save(aulaNat2);
        
        System.out.println("✅ Dados inicializados com sucesso!");
        System.out.println("   • 4 Esportes criados");
        System.out.println("   • 4 Professores criados");
        System.out.println("   • 8 Conteúdos criados");
        System.out.println("   • 8 Aulas agendadas");
        System.out.println("========================================================================");
    }
}
