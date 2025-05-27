package com.educacao.esportiva.domain;

import jakarta.persistence.*;

/**
 * ========================================================================
 * ENTIDADE DE DOMÍNIO PROFESSOR - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    ✅ Responsabilidade única: representar um Professor
 *    ✅ Contém apenas dados e comportamentos relacionados ao domínio Professor
 * 
 * 2. OPEN/CLOSED PRINCIPLE (OCP):
 *    ✅ Aberto para extensão através de herança ou composição
 *    ✅ Fechado para modificação dos métodos base
 * 
 * MELHORIAS APLICADAS:
 * - Validações de domínio encapsuladas
 * - Métodos de negócio específicos do domínio
 * - Separação clara entre entidade e persistência
 */
@Entity
@Table(name = "professores")
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    
    @Column(name = "especializacao", nullable = false, length = 100)
    private String especializacao;

    // Construtores
    protected Professor() {
        // Construtor protegido para JPA
    }

    /**
     * Construtor de domínio - valida regras de negócio
     */
    public Professor(String nome, String especializacao) {
        this.setNome(nome);
        this.setEspecializacao(especializacao);
    }

    // Getters e Setters com validação de domínio

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    /**
     * SOLUÇÃO: Validação de domínio encapsulada
     */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do professor não pode ser nulo ou vazio");
        }
        if (nome.trim().length() < 2 || nome.trim().length() > 200) {
            throw new IllegalArgumentException("Nome do professor deve ter entre 2 e 200 caracteres");
        }
        this.nome = nome.trim();
    }

    public String getEspecializacao() {
        return especializacao;
    }

    /**
     * SOLUÇÃO: Validação de especialização com regras de domínio
     */
    public void setEspecializacao(String especializacao) {
        if (especializacao == null || especializacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialização do professor não pode ser nula ou vazia");
        }
        if (especializacao.trim().length() < 5 || especializacao.trim().length() > 100) {
            throw new IllegalArgumentException("Especialização deve ter entre 5 e 100 caracteres");
        }
        this.especializacao = especializacao.trim();
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se especialização contém termo específico
     */
    public boolean temEspecializacaoEm(String termo) {
        return this.especializacao.toLowerCase().contains(termo.toLowerCase());
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se é especialista em esportes coletivos
     */
    public boolean isEspecialistaColetivos() {
        return temEspecializacaoEm("coletivo");
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se é especialista em esportes individuais
     */
    public boolean isEspecialistaIndividuais() {
        return temEspecializacaoEm("individual");
    }

    /**
     * MÉTODO DE DOMÍNIO: Obter nome abreviado (primeiro e último nome)
     */
    public String getNomeAbreviado() {
        String[] partes = this.nome.split(" ");
        if (partes.length == 1) {
            return partes[0];
        }
        return partes[0] + " " + partes[partes.length - 1];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Professor professor = (Professor) obj;
        return id != null ? id.equals(professor.id) : professor.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", especializacao='" + especializacao + '\'' +
                '}';
    }
}