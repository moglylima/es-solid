package com.educacao.esportiva.application.dto;

/**
 * DTO simples para criação de Esporte.
 */
public class CriarEsporteDTO {
    private String nome;
    private String categoria;
    
    public CriarEsporteDTO() {}
    
    public CriarEsporteDTO(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}