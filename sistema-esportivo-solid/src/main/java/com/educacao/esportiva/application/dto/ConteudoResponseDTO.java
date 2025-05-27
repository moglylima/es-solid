package com.educacao.esportiva.application.dto;

/**
 * DTO para resposta de Conteúdo.
 * Demonstra aplicação do ISP - interface específica para retorno de dados de Conteúdo.
 */
public class ConteudoResponseDTO {
    
    private Long id;
    private String titulo;
    private String descricao;
    private String nivel;
    private int duracao;
    private String nomeEsporte;
    private Long esporteId;
    
    public ConteudoResponseDTO() {}
    
    public ConteudoResponseDTO(Long id, String titulo, String descricao, String nivel, 
                              int duracao, String nomeEsporte, Long esporteId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nivel = nivel;
        this.duracao = duracao;
        this.nomeEsporte = nomeEsporte;
        this.esporteId = esporteId;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getNivel() { return nivel; }
    public int getDuracao() { return duracao; }
    public String getNomeEsporte() { return nomeEsporte; }
    public Long getEsporteId() { return esporteId; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
    public void setNomeEsporte(String nomeEsporte) { this.nomeEsporte = nomeEsporte; }
    public void setEsporteId(Long esporteId) { this.esporteId = esporteId; }
}
