package com.educacao.esportiva;

import java.time.LocalDate;
import java.util.List;

/**
 * ENTIDADE AULA
 * 
 * Esta classe representa uma aula no sistema educacional esportivo.
 * Cada aula possui um professor responsável e uma lista de conteúdos a serem abordados.
 * 
 * Atributos:
 * - id: Identificador único da aula
 * - data: Data da realização da aula
 * - duracao: Duração da aula em minutos
 * - conteudos: Lista de IDs dos conteúdos que serão abordados
 * - professorId: ID do professor responsável pela aula
 */
public class Aula {
    private Long id;
    private LocalDate data;
    private Integer duracao; // Em minutos
    private List<Long> conteudos; // Lista de IDs de conteúdos
    private Long professorId; // Relacionamento com Professor

    // Construtores
    public Aula() {}

    /**
     * Construtor completo para criação de aula
     * @param id Identificador único
     * @param data Data da aula
     * @param duracao Duração em minutos
     * @param conteudos Lista de IDs dos conteúdos
     * @param professorId ID do professor responsável
     */
    public Aula(Long id, LocalDate data, Integer duracao, List<Long> conteudos, Long professorId) {
        this.id = id;
        this.data = data;
        this.duracao = duracao;
        this.conteudos = conteudos;
        this.professorId = professorId;
    }

    // Getters e Setters com documentação

    /**
     * @return Identificador único da aula
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Data da realização da aula
     */
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * @return Duração da aula em minutos
     */
    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    /**
     * @return Lista de IDs dos conteúdos da aula
     */
    public List<Long> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Long> conteudos) {
        this.conteudos = conteudos;
    }

    /**
     * @return ID do professor responsável
     */
    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", data=" + data +
                ", duracao=" + duracao +
                ", conteudos=" + conteudos +
                ", professorId=" + professorId +
                '}';
    }
}
