package com.educacao.esportiva.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * DTO para criação de Conteúdo.
 * Demonstra aplicação do ISP - interface específica para criação de Conteúdo.
 */
public class CriarConteudoDTO {
    
    @NotBlank(message = "Título é obrigatório")
    @Size(min = 5, max = 200, message = "Título deve ter entre 5 e 200 caracteres")
    private String titulo;
    
    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 10, max = 1000, message = "Descrição deve ter entre 10 e 1000 caracteres")
    private String descricao;
    
    @NotBlank(message = "Nível é obrigatório")
    private String nivel;
    
    @NotNull(message = "Duração é obrigatória")
    @Positive(message = "Duração deve ser positiva")
    private Integer duracao;
    
    @NotNull(message = "ID do esporte é obrigatório")
    @Positive(message = "ID do esporte deve ser positivo")
    private Long esporteId;
    
    public CriarConteudoDTO() {}
    
    public CriarConteudoDTO(String titulo, String descricao, String nivel, Integer duracao, Long esporteId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nivel = nivel;
        this.duracao = duracao;
        this.esporteId = esporteId;
    }
    
    // Getters
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getNivel() { return nivel; }
    public Integer getDuracao() { return duracao; }
    public Long getEsporteId() { return esporteId; }
    
    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
    public void setEsporteId(Long esporteId) { this.esporteId = esporteId; }
}
