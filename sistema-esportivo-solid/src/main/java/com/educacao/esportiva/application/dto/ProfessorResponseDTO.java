package com.educacao.esportiva.application.dto;

/**
 * DTO para resposta de Professor.
 * Demonstra aplicação do ISP - interface específica para retorno de dados de Professor.
 */
public class ProfessorResponseDTO {
    
    private Long id;
    private String nome;
    private String email;
    private String especializacao;
    private boolean ativo;
    
    public ProfessorResponseDTO() {}
    
    public ProfessorResponseDTO(Long id, String nome, String email, String especializacao, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.especializacao = especializacao;
        this.ativo = ativo;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getEspecializacao() { return especializacao; }
    public boolean isAtivo() { return ativo; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setEspecializacao(String especializacao) { this.especializacao = especializacao; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
