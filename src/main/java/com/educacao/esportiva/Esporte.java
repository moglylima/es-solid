package com.educacao.esportiva;

/**
 * ENTIDADE ESPORTE
 * 
 * Esta classe representa a entidade Esporte no sistema de gestão educacional.
 * Contém informações básicas sobre cada modalidade esportiva disponível.
 * 
 * Atributos:
 * - id: Identificador único do esporte
 * - nome: Nome da modalidade (ex: Vôlei, Basquete, Futebol)
 * - categoria: Tipo de esporte (ex: Coletivo, Individual, Aquático)
 */
public class Esporte {
    private Long id;
    private String nome;
    private String categoria;

    // Construtores
    public Esporte() {}

    /**
     * Construtor completo para criação de um esporte
     * @param id Identificador único
     * @param nome Nome da modalidade esportiva
     * @param categoria Categoria do esporte (Coletivo/Individual/etc)
     */
    public Esporte(Long id, String nome, String categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

    // Getters e Setters com documentação
    
    /**
     * @return Identificador único do esporte
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Nome da modalidade esportiva
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Categoria do esporte (ex: Coletivo, Individual)
     */
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Esporte{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
