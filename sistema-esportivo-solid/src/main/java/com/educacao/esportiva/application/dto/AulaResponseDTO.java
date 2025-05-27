package com.educacao.esportiva.application.dto;

import java.time.LocalDateTime;

/**
 * DTO para resposta de Aula.
 * Demonstra aplicação do ISP - interface específica para retorno de dados de Aula.
 */
public class AulaResponseDTO {
    
    private Long id;
    private LocalDateTime dataHora;
    private String nomeProfessor;
    private Long professorId;
    private String tituloConteudo;
    private Long conteudoId;
    private String nomeEsporte;
    
    public AulaResponseDTO() {}
    
    public AulaResponseDTO(Long id, LocalDateTime dataHora, String nomeProfessor, Long professorId,
                          String tituloConteudo, Long conteudoId, String nomeEsporte) {
        this.id = id;
        this.dataHora = dataHora;
        this.nomeProfessor = nomeProfessor;
        this.professorId = professorId;
        this.tituloConteudo = tituloConteudo;
        this.conteudoId = conteudoId;
        this.nomeEsporte = nomeEsporte;
    }
    
    // Getters
    public Long getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getNomeProfessor() { return nomeProfessor; }
    public Long getProfessorId() { return professorId; }
    public String getTituloConteudo() { return tituloConteudo; }
    public Long getConteudoId() { return conteudoId; }
    public String getNomeEsporte() { return nomeEsporte; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public void setNomeProfessor(String nomeProfessor) { this.nomeProfessor = nomeProfessor; }
    public void setProfessorId(Long professorId) { this.professorId = professorId; }
    public void setTituloConteudo(String tituloConteudo) { this.tituloConteudo = tituloConteudo; }
    public void setConteudoId(Long conteudoId) { this.conteudoId = conteudoId; }
    public void setNomeEsporte(String nomeEsporte) { this.nomeEsporte = nomeEsporte; }
}
