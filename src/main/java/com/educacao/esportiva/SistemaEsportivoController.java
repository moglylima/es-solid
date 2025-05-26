package com.educacao.esportiva;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ========================================================================
 * SISTEMA ESPORTIVO CONTROLLER - VERSÃO MONOLÍTICA (NÃO-SOLID)
 * ========================================================================
 * 
 * ATENÇÃO: Esta implementação INTENCIONALMENTE viola os princípios SOLID!
 * 
 * PROBLEMAS DESTA IMPLEMENTAÇÃO:
 * 
 * 1. VIOLAÇÃO DO SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - Uma única classe gerencia TODAS as responsabilidades:
 *      * Controle de requisições HTTP
 *      * Lógica de negócio
 *      * Persistência de dados (HashMap)
 *      * Validações
 *      * Filtros e consultas
 * 
 * 2. VIOLAÇÃO DO OPEN/CLOSED PRINCIPLE (OCP):
 *    - Para adicionar nova funcionalidade, precisa modificar esta classe
 *    - Não é extensível sem modificação
 * 
 * 3. VIOLAÇÃO DO DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Depende diretamente de implementações concretas (HashMap)
 *    - Não usa abstrações/interfaces
 * 
 * 4. PROBLEMAS ADICIONAIS:
 *    - Código duplicado
 *    - Difícil manutenção
 *    - Testes complexos
 *    - Alto acoplamento
 *    - Baixa coesão
 */
@RestController
@RequestMapping("/api")
public class SistemaEsportivoController {

    // ========================================================================
    // ARMAZENAMENTO EM MEMÓRIA - SIMULA BANCO DE DADOS
    // ========================================================================
    
    /**
     * PROBLEMA: Persistência hardcoded na classe de controle
     * Deveria estar em uma camada separada (Repository)
     */
    private final Map<Long, Esporte> esportes = new HashMap<>();
    private final Map<Long, Conteudo> conteudos = new HashMap<>();
    private final Map<Long, Professor> professores = new HashMap<>();
    private final Map<Long, Categoria> categorias = new HashMap<>();
    private final Map<Long, Aula> aulas = new HashMap<>();
    
    // Contadores para IDs automáticos - PROBLEMA: Lógica de ID na camada de controle
    private Long esporteIdCounter = 1L;
    private Long conteudoIdCounter = 1L;
    private Long professorIdCounter = 1L;
    private Long categoriaIdCounter = 1L;
    private Long aulaIdCounter = 1L;

    /**
     * Construtor que inicializa dados de exemplo
     * PROBLEMA: Lógica de inicialização misturada com controle
     */
    public SistemaEsportivoController() {
        inicializarDadosExemplo();
    }

    // ========================================================================
    // ENDPOINTS PARA ESPORTES
    // ========================================================================

    /**
     * POST /api/esportes - Cadastra um novo esporte
     * 
     * PROBLEMAS DESTA IMPLEMENTAÇÃO:
     * - Validação manual inline
     * - Lógica de negócio misturada com controle HTTP
     * - Persistência direta sem abstração
     */
    @PostMapping("/esportes")
    public ResponseEntity<?> criarEsporte(@RequestBody Esporte esporte) {
        try {
            // PROBLEMA: Validação manual repetitiva
            if (esporte.getNome() == null || esporte.getNome().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("Erro: Nome do esporte é obrigatório");
            }
            
            if (esporte.getCategoria() == null || esporte.getCategoria().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("Erro: Categoria do esporte é obrigatória");
            }

            // PROBLEMA: Lógica de negócio no controller
            // Verificar se já existe esporte com mesmo nome
            for (Esporte e : esportes.values()) {
                if (e.getNome().equalsIgnoreCase(esporte.getNome())) {
                    return ResponseEntity.badRequest()
                        .body("Erro: Já existe um esporte com este nome");
                }
            }

            // PROBLEMA: Geração de ID no controller
            esporte.setId(esporteIdCounter++);
            
            // PROBLEMA: Persistência direta sem abstração
            esportes.put(esporte.getId(), esporte);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(esporte);
            
        } catch (Exception e) {
            // PROBLEMA: Tratamento de erro genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno: " + e.getMessage());
        }
    }

    /**
     * GET /api/esportes - Lista todos os esportes
     */
    @GetMapping("/esportes")
    public ResponseEntity<List<Esporte>> listarEsportes() {
        return ResponseEntity.ok(new ArrayList<>(esportes.values()));
    }

    // ========================================================================
    // ENDPOINTS PARA CONTEÚDOS
    // ========================================================================

    /**
     * POST /api/conteudos - Adiciona conteúdo vinculado a um esporte
     * 
     * PROBLEMAS: Mesmas violações do método criarEsporte
     */
    @PostMapping("/conteudos")
    public ResponseEntity<?> criarConteudo(@RequestBody Conteudo conteudo) {
        try {
            // PROBLEMA: Validação manual repetitiva
            if (conteudo.getTitulo() == null || conteudo.getTitulo().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("Erro: Título do conteúdo é obrigatório");
            }
            
            if (conteudo.getUrl() == null || conteudo.getUrl().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("Erro: URL do conteúdo é obrigatória");
            }
            
            if (conteudo.getNivel() == null || conteudo.getNivel().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("Erro: Nível do conteúdo é obrigatório");
            }

            // PROBLEMA: Validação de relacionamento no controller
            if (conteudo.getEsporteId() == null || !esportes.containsKey(conteudo.getEsporteId())) {
                return ResponseEntity.badRequest()
                    .body("Erro: Esporte não encontrado");
            }

            // PROBLEMA: Validação de nível hardcoded
            if (!conteudo.getNivel().equals("Fundamental II") && !conteudo.getNivel().equals("Médio")) {
                return ResponseEntity.badRequest()
                    .body("Erro: Nível deve ser 'Fundamental II' ou 'Médio'");
            }

            conteudo.setId(conteudoIdCounter++);
            conteudos.put(conteudo.getId(), conteudo);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(conteudo);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno: " + e.getMessage());
        }
    }

    /**
     * GET /api/conteudos/esporte/{esporteId} - Lista conteúdos de um esporte
     * 
     * PROBLEMA: Lógica de filtro no controller
     */
    @GetMapping("/conteudos/esporte/{esporteId}")
    public ResponseEntity<?> listarConteudosPorEsporte(@PathVariable Long esporteId) {
        try {
            // PROBLEMA: Validação de existência no controller
            if (!esportes.containsKey(esporteId)) {
                return ResponseEntity.notFound().build();
            }

            // PROBLEMA: Lógica de filtro implementada diretamente no controller
            List<Conteudo> conteudosDoEsporte = conteudos.values().stream()
                .filter(conteudo -> conteudo.getEsporteId().equals(esporteId))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(conteudosDoEsporte);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno: " + e.getMessage());
        }
    }

    // ========================================================================
    // ENDPOINTS PARA PROFESSORES
    // ========================================================================

    /**
     * POST /api/professores - Cadastra um novo professor
     */
    @PostMapping("/professores")
    public ResponseEntity<?> criarProfessor(@RequestBody Professor professor) {
        try {
            // PROBLEMA: Validação repetitiva
            if (professor.getNome() == null || professor.getNome().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("Erro: Nome do professor é obrigatório");
            }
            
            if (professor.getEspecializacao() == null || professor.getEspecializacao().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("Erro: Especialização do professor é obrigatória");
            }

            professor.setId(professorIdCounter++);
            professores.put(professor.getId(), professor);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(professor);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno: " + e.getMessage());
        }
    }

    /**
     * GET /api/professores/especializacao/{especializacao} - Lista professores por especialização
     * 
     * PROBLEMA: Lógica de filtro no controller
     */
    @GetMapping("/professores/especializacao/{especializacao}")
    public ResponseEntity<List<Professor>> listarProfessoresPorEspecializacao(@PathVariable String especializacao) {
        // PROBLEMA: Lógica de filtro implementada diretamente no controller
        List<Professor> professoresFiltrados = professores.values().stream()
            .filter(professor -> professor.getEspecializacao().equalsIgnoreCase(especializacao))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(professoresFiltrados);
    }

    // ========================================================================
    // ENDPOINTS PARA AULAS
    // ========================================================================

    /**
     * POST /api/aulas - Cria uma aula com lista de conteúdos e professor
     * 
     * PROBLEMA: Lógica de negócio complexa no controller
     */
    @PostMapping("/aulas")
    public ResponseEntity<?> criarAula(@RequestBody Aula aula) {
        try {
            // PROBLEMA: Validações complexas no controller
            if (aula.getData() == null) {
                return ResponseEntity.badRequest()
                    .body("Erro: Data da aula é obrigatória");
            }
            
            if (aula.getDuracao() == null || aula.getDuracao() <= 0) {
                return ResponseEntity.badRequest()
                    .body("Erro: Duração deve ser maior que zero");
            }
            
            if (aula.getProfessorId() == null || !professores.containsKey(aula.getProfessorId())) {
                return ResponseEntity.badRequest()
                    .body("Erro: Professor não encontrado");
            }

            // PROBLEMA: Validação complexa de relacionamentos no controller
            if (aula.getConteudos() == null || aula.getConteudos().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("Erro: Aula deve ter pelo menos um conteúdo");
            }

            // PROBLEMA: Lógica de negócio - verificar se todos os conteúdos existem
            for (Long conteudoId : aula.getConteudos()) {
                if (!conteudos.containsKey(conteudoId)) {
                    return ResponseEntity.badRequest()
                        .body("Erro: Conteúdo com ID " + conteudoId + " não encontrado");
                }
            }

            aula.setId(aulaIdCounter++);
            aulas.put(aula.getId(), aula);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(aula);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno: " + e.getMessage());
        }
    }

    /**
     * GET /api/aulas/filtrar?categoria={categoria} - Filtra aulas por categoria do esporte
     * 
     * PROBLEMA: Lógica de filtro complexa no controller
     */
    @GetMapping("/aulas/filtrar")
    public ResponseEntity<List<Aula>> filtrarAulasPorCategoria(@RequestParam String categoria) {
        // PROBLEMA: Lógica de negócio complexa implementada diretamente no controller
        // Esta consulta requer:
        // 1. Buscar aulas
        // 2. Para cada aula, buscar seus conteúdos
        // 3. Para cada conteúdo, buscar o esporte
        // 4. Verificar se a categoria do esporte corresponde ao filtro
        
        List<Aula> aulasMatched = new ArrayList<>();
        
        for (Aula aula : aulas.values()) {
            boolean aulaCorrespondeCategoria = false;
            
            // Para cada conteúdo da aula
            for (Long conteudoId : aula.getConteudos()) {
                Conteudo conteudo = conteudos.get(conteudoId);
                if (conteudo != null) {
                    Esporte esporte = esportes.get(conteudo.getEsporteId());
                    if (esporte != null && esporte.getCategoria().equalsIgnoreCase(categoria)) {
                        aulaCorrespondeCategoria = true;
                        break;
                    }
                }
            }
            
            if (aulaCorrespondeCategoria) {
                aulasMatched.add(aula);
            }
        }
        
        return ResponseEntity.ok(aulasMatched);
    }

    // ========================================================================
    // MÉTODOS AUXILIARES - TAMBÉM PROBLEMÁTICOS
    // ========================================================================

    /**
     * PROBLEMA: Inicialização de dados no controller
     * Deveria estar em uma classe de configuração ou service
     */
    private void inicializarDadosExemplo() {
        // Criando esportes de exemplo
        Esporte volei = new Esporte(esporteIdCounter++, "Vôlei", "Coletivo");
        Esporte basquete = new Esporte(esporteIdCounter++, "Basquete", "Coletivo");
        Esporte natacao = new Esporte(esporteIdCounter++, "Natação", "Individual");
        
        esportes.put(volei.getId(), volei);
        esportes.put(basquete.getId(), basquete);
        esportes.put(natacao.getId(), natacao);
        
        // Criando professores de exemplo
        Professor prof1 = new Professor(professorIdCounter++, "João Silva", "Esportes Coletivos");
        Professor prof2 = new Professor(professorIdCounter++, "Maria Santos", "Esportes Aquáticos");
        
        professores.put(prof1.getId(), prof1);
        professores.put(prof2.getId(), prof2);
        
        // Criando conteúdos de exemplo
        Conteudo conteudo1 = new Conteudo(conteudoIdCounter++, 
            "Fundamentos do Vôlei", 
            "https://example.com/volei-fundamentos.mp4", 
            "Fundamental II", 
            volei.getId());
        
        Conteudo conteudo2 = new Conteudo(conteudoIdCounter++, 
            "Técnicas de Saque no Vôlei", 
            "https://example.com/volei-saque.pdf", 
            "Médio", 
            volei.getId());
            
        conteudos.put(conteudo1.getId(), conteudo1);
        conteudos.put(conteudo2.getId(), conteudo2);
        
        // Criando aula de exemplo
        Aula aula1 = new Aula(aulaIdCounter++, 
            LocalDate.now().plusDays(1), 
            90, 
            Arrays.asList(conteudo1.getId(), conteudo2.getId()), 
            prof1.getId());
            
        aulas.put(aula1.getId(), aula1);
    }

    // ========================================================================
    // ENDPOINTS ADICIONAIS PARA DEMONSTRAR OS PROBLEMAS
    // ========================================================================

    /**
     * GET /api/debug/status - Endpoint para debug (PROBLEMA: responsabilidade inadequada)
     */
    @GetMapping("/debug/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("totalEsportes", esportes.size());
        status.put("totalConteudos", conteudos.size());
        status.put("totalProfessores", professores.size());
        status.put("totalAulas", aulas.size());
        status.put("ultimoEsporteId", esporteIdCounter - 1);
        
        return ResponseEntity.ok(status);
    }
}
