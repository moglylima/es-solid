package com.educacao.esportiva.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para criação de Professor.
 * Demonstra aplicação do ISP - interface específica para criação de Professor.
 */
public class CriarProfessorDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;
    
    @NotBlank(message = "Especialização é obrigatória")
    @Size(min = 2, max = 100, message = "Especialização deve ter entre 2 e 100 caracteres")
    private String especializacao;
    
    public CriarProfessorDTO() {}
    
    public CriarProfessorDTO(String nome, String email, String especializacao) {
        this.nome = nome;
        this.email = email;
        this.especializacao = especializacao;
    }
    
    // Getters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getEspecializacao() { return especializacao; }
    
    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setEspecializacao(String especializacao) { this.especializacao = especializacao; }
}
