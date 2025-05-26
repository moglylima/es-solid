package com.educacao.esportiva;

/**
 * ENTIDADE CATEGORIA
 * 
 * Esta classe representa as categorias de esportes disponíveis no sistema.
 * Permite uma classificação mais específica dos esportes por ambiente ou tipo.
 * 
 * Atributos:
 * - id: Identificador único da categoria
 * - nome: Nome da categoria (ex: Aquáticos, Quadra, Academia, Campo)
 */
public class Categoria {
    private Long id;
    private String nome; // Ex: Aquáticos, Quadra, Academia, Campo

    // Construtores
    public Categoria() {}

    /**
     * Construtor completo para criação de categoria
     * @param id Identificador único
     * @param nome Nome da categoria esportiva
     */
    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters com documentação

    /**
     * @return Identificador único da categoria
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Nome da categoria esportiva
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
