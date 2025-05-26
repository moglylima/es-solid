package com.educacao.esportiva;

/**
 * ENTIDADE PROFESSOR
 * 
 * Esta classe representa os professores do sistema educacional esportivo.
 * Cada professor possui uma especialização que define sua área de atuação.
 * 
 * Atributos:
 * - id: Identificador único do professor
 * - nome: Nome completo do professor
 * - especializacao: Área de especialização (ex: Esportes Coletivos, Individuais, Aquáticos)
 */
public class Professor {
    private Long id;
    private String nome;
    private String especializacao; // Ex: Esportes Coletivos, Individuais, Aquáticos

    // Construtores
    public Professor() {}

    /**
     * Construtor completo para criação de professor
     * @param id Identificador único
     * @param nome Nome completo do professor
     * @param especializacao Área de especialização do professor
     */
    public Professor(Long id, String nome, String especializacao) {
        this.id = id;
        this.nome = nome;
        this.especializacao = especializacao;
    }

    // Getters e Setters com documentação

    /**
     * @return Identificador único do professor
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Nome completo do professor
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Especialização do professor
     */
    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
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
