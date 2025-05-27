package com.educacao.esportiva.application.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

/**
 * DTO para criação de Aula.
 * Demonstra aplicação do ISP - interface específica para criação de Aula.
 */
public class CriarAulaDTO {
    
    @NotNull(message = "Data e hora são obrigatórias")
    @Future(message = "Data e hora devem ser futuras")
    private LocalDateTime dataHora;
    
    @NotNull(message = "ID do professor é obrigatório")
    @Positive(message = "ID do professor deve ser positivo")
    private Long professorId;
    
    @NotNull(message = "ID do conteúdo é obrigatório")
    @Positive(message = "ID do conteúdo deve ser positivo")
    private Long conteudoId;
    
    public CriarAulaDTO() {}
    
    public CriarAulaDTO(LocalDateTime dataHora, Long professorId, Long conteudoId) {
        this.dataHora = dataHora;
        this.professorId = professorId;
        this.conteudoId = conteudoId;
    }
    
    // Getters
    public LocalDateTime getDataHora() { return dataHora; }
    public Long getProfessorId() { return professorId; }
    public Long getConteudoId() { return conteudoId; }
    
    // Setters
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public void setProfessorId(Long professorId) { this.professorId = professorId; }
    public void setConteudoId(Long conteudoId) { this.conteudoId = conteudoId; }
}
