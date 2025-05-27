package com.educacao.esportiva.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * ========================================================================
 * ENTIDADE DE DOMÍNIO AULA - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    ✅ Responsabilidade única: representar uma Aula de esporte
 *    ✅ Contém apenas dados e comportamentos relacionados ao domínio Aula
 *    ✅ Validações de domínio encapsuladas na própria entidade
 * 
 * 2. OPEN/CLOSED PRINCIPLE (OCP):
 *    ✅ Aberto para extensão através de herança
 *    ✅ Fechado para modificação dos métodos fundamentais
 * 
 * 3. LISKOV SUBSTITUTION PRINCIPLE (LSP):
 *    ✅ Pode ser estendida sem quebrar contratos base
 * 
 * MELHORIAS EM RELAÇÃO À VERSÃO MONOLÍTICA:
 * - Separação clara entre entidade de domínio e persistência
 * - Validações de domínio encapsuladas
 * - Métodos de negócio específicos do domínio
 * - Relacionamentos bem definidos com outras entidades
 */
@Entity
@Table(name = "aulas")
public class Aula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;
    
    @Column(name = "data_aula", nullable = false)
    private LocalDate dataAula;
    
    @Column(name = "horario_inicio", nullable = false)
    private LocalTime horarioInicio;
    
    @Column(name = "duracao_minutos", nullable = false)
    private Integer duracaoMinutos;
    
    @Column(name = "local", nullable = false, length = 100)
    private String local;
    
    @Column(name = "esporte_id", nullable = false)
    private Long esporteId;
    
    @Column(name = "professor_id", nullable = false)
    private Long professorId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", insertable = false, updatable = false)
    private Professor professor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "esporte_id", insertable = false, updatable = false)
    private Esporte esporte;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conteudo_id")
    private Conteudo conteudo;

    // Construtores
    protected Aula() {
        // Construtor protegido para JPA
    }

    /**
     * Construtor de domínio - APLICAÇÃO DO SRP
     * 
     * SOLUÇÃO: Validação de domínio encapsulada na criação
     * - Garante que nenhum objeto Aula seja criado em estado inválido
     * - Centraliza regras de negócio na entidade
     */
    public Aula(String titulo, LocalDate dataAula, LocalTime horarioInicio, 
                Integer duracaoMinutos, String local, Long esporteId, Long professorId) {
        this.setTitulo(titulo);
        this.setDataAula(dataAula);
        this.setHorarioInicio(horarioInicio);
        this.setDuracaoMinutos(duracaoMinutos);
        this.setLocal(local);
        this.setEsporteId(esporteId);
        this.setProfessorId(professorId);
    }
    
    /**
     * Construtor alternativo para criação com objetos relacionados
     */
    public Aula(LocalDateTime dataHora, Professor professor, Conteudo conteudo) {
        // Extrair data e hora
        this.setDataAula(dataHora.toLocalDate());
        this.setHorarioInicio(dataHora.toLocalTime());
        this.setProfessor(professor);
        this.setConteudo(conteudo);
        this.setProfessorId(professor.getId());
        this.setEsporteId(conteudo.getEsporteId());
        this.setTitulo("Aula de " + conteudo.getTitulo());
        this.setLocal("Local padrão");
        this.setDuracaoMinutos(conteudo.getDuracao() != null ? conteudo.getDuracao() : 60);
    }

    // Getters e Setters com validação de domínio

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    /**
     * SOLUÇÃO SRP: Validação de domínio encapsulada
     * 
     * PROBLEMA RESOLVIDO: Na versão monolítica, a validação estava no controller
     * SOLUÇÃO APLICADA: Validação movida para onde realmente pertence - a entidade
     */
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título da aula não pode ser nulo ou vazio");
        }
        if (titulo.trim().length() < 5 || titulo.trim().length() > 200) {
            throw new IllegalArgumentException("Título da aula deve ter entre 5 e 200 caracteres");
        }
        this.titulo = titulo.trim();
    }

    public LocalDate getDataAula() {
        return dataAula;
    }

    /**
     * SOLUÇÃO SRP: Validação de data com regras de domínio
     */
    public void setDataAula(LocalDate dataAula) {
        if (dataAula == null) {
            throw new IllegalArgumentException("Data da aula não pode ser nula");
        }
        if (dataAula.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data da aula não pode ser no passado");
        }
        this.dataAula = dataAula;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    /**
     * SOLUÇÃO SRP: Validação de horário com regras de domínio
     */
    public void setHorarioInicio(LocalTime horarioInicio) {
        if (horarioInicio == null) {
            throw new IllegalArgumentException("Horário de início não pode ser nulo");
        }
        // Horário deve ser entre 6h e 22h
        if (horarioInicio.isBefore(LocalTime.of(6, 0)) || 
            horarioInicio.isAfter(LocalTime.of(22, 0))) {
            throw new IllegalArgumentException("Horário de início deve ser entre 06:00 e 22:00");
        }
        this.horarioInicio = horarioInicio;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    /**
     * SOLUÇÃO SRP: Validação de duração com regras de domínio
     */
    public void setDuracaoMinutos(Integer duracaoMinutos) {
        if (duracaoMinutos == null || duracaoMinutos <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        }
        if (duracaoMinutos < 30 || duracaoMinutos > 240) {
            throw new IllegalArgumentException("Duração deve ser entre 30 e 240 minutos");
        }
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getLocal() {
        return local;
    }

    /**
     * SOLUÇÃO SRP: Validação de local com regras de domínio
     */
    public void setLocal(String local) {
        if (local == null || local.trim().isEmpty()) {
            throw new IllegalArgumentException("Local da aula não pode ser nulo ou vazio");
        }
        if (local.trim().length() < 3 || local.trim().length() > 100) {
            throw new IllegalArgumentException("Local da aula deve ter entre 3 e 100 caracteres");
        }
        this.local = local.trim();
    }

    public Long getEsporteId() {
        return esporteId;
    }

    /**
     * SOLUÇÃO SRP: Validação de referência para esporte
     */
    public void setEsporteId(Long esporteId) {
        if (esporteId == null || esporteId <= 0) {
            throw new IllegalArgumentException("ID do esporte deve ser válido");
        }
        this.esporteId = esporteId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    /**
     * SOLUÇÃO SRP: Validação de referência para professor
     */
    public void setProfessorId(Long professorId) {
        if (professorId == null || professorId <= 0) {
            throw new IllegalArgumentException("ID do professor deve ser válido");
        }
        this.professorId = professorId;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Esporte getEsporte() {
        return esporte;
    }

    public void setEsporte(Esporte esporte) {
        this.esporte = esporte;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * MÉTODO DE DOMÍNIO - APLICAÇÃO DO SRP
     * 
     * SOLUÇÃO: Comportamento específico do domínio Aula
     * - Encapsula lógica de cálculo de horário final
     */
    public LocalTime getHorarioFim() {
        return horarioInicio.plusMinutes(duracaoMinutos);
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se aula é longa (mais de 2 horas)
     */
    public boolean isAulaLonga() {
        return duracaoMinutos > 120;
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se aula é no período matutino
     */
    public boolean isMatutina() {
        return horarioInicio.isBefore(LocalTime.of(12, 0));
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se aula é no período vespertino
     */
    public boolean isVespertina() {
        return horarioInicio.isBefore(LocalTime.of(18, 0)) && 
               !isMatutina();
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se aula é no período noturno
     */
    public boolean isNoturna() {
        return horarioInicio.isAfter(LocalTime.of(17, 59));
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se há conflito de horário com outra aula
     */
    public boolean temConflitoHorario(Aula outraAula) {
        if (!this.dataAula.equals(outraAula.dataAula)) {
            return false; // Datas diferentes, sem conflito
        }
        
        LocalTime inicioEsta = this.horarioInicio;
        LocalTime fimEsta = this.getHorarioFim();
        LocalTime inicioOutra = outraAula.horarioInicio;
        LocalTime fimOutra = outraAula.getHorarioFim();
        
        return !(fimEsta.isBefore(inicioOutra) || inicioEsta.isAfter(fimOutra));
    }

    /**
     * MÉTODO DE DOMÍNIO: Obter descrição completa da aula
     */
    public String getDescricaoCompleta() {
        return String.format("%s - %s às %s (%d min) em %s", 
                           titulo, 
                           dataAula, 
                           horarioInicio, 
                           duracaoMinutos, 
                           local);
    }

    /**
     * MÉTODO DE DOMÍNIO: Obter data e hora como LocalDateTime
     */
    public LocalDateTime getDataHora() {
        return LocalDateTime.of(dataAula, horarioInicio);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Aula aula = (Aula) obj;
        return id != null ? id.equals(aula.id) : aula.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", dataAula=" + dataAula +
                ", horarioInicio=" + horarioInicio +
                ", duracaoMinutos=" + duracaoMinutos +
                ", local='" + local + '\'' +
                ", esporteId=" + esporteId +
                ", professorId=" + professorId +
                '}';
    }
}
