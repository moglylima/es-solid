package com.educacao.esportiva;

/**
 * ENTIDADE CONTEÚDO
 * 
 * Esta classe representa os materiais educacionais disponíveis no sistema.
 * Cada conteúdo está vinculado a um esporte específico e possui um nível educacional.
 * 
 * Atributos:
 * - id: Identificador único do conteúdo
 * - titulo: Título do material educacional
 * - url: Link para o recurso (vídeo, PDF, etc.)
 * - nivel: Nível educacional (Fundamental II, Médio)
 * - esporteId: Referência ao esporte relacionado
 */
public class Conteudo {
    private Long id;
    private String titulo;
    private String url;
    private String nivel; // Fundamental II, Médio
    private Long esporteId; // Relacionamento com Esporte

    // Construtores
    public Conteudo() {}

    /**
     * Construtor completo para criação de conteúdo educacional
     * @param id Identificador único
     * @param titulo Título do conteúdo
     * @param url Link para o recurso (vídeo/PDF)
     * @param nivel Nível educacional alvo
     * @param esporteId ID do esporte relacionado
     */
    public Conteudo(Long id, String titulo, String url, String nivel, Long esporteId) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
        this.nivel = nivel;
        this.esporteId = esporteId;
    }

    // Getters e Setters com documentação

    /**
     * @return Identificador único do conteúdo
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Título do material educacional
     */
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return URL do recurso (vídeo, PDF, etc.)
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return Nível educacional (Fundamental II, Médio)
     */
    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return ID do esporte relacionado
     */
    public Long getEsporteId() {
        return esporteId;
    }

    public void setEsporteId(Long esporteId) {
        this.esporteId = esporteId;
    }

    @Override
    public String toString() {
        return "Conteudo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", url='" + url + '\'' +
                ", nivel='" + nivel + '\'' +
                ", esporteId=" + esporteId +
                '}';
    }
}
