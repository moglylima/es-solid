package com.educacao.esportiva.domain;

import jakarta.persistence.*;

/**
 * ========================================================================
 * ENTIDADE DE DOMÍNIO CONTEÚDO - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    ✅ Responsabilidade única: representar um Conteúdo educacional
 *    ✅ Contém apenas dados e comportamentos relacionados ao domínio Conteúdo
 * 
 * 2. OPEN/CLOSED PRINCIPLE (OCP):
 *    ✅ Aberto para extensão através de herança ou composição
 *    ✅ Fechado para modificação dos métodos base
 * 
 * MELHORIAS APLICADAS:
 * - Validações de domínio encapsuladas
 * - Métodos de negócio específicos do domínio
 * - Relacionamento bem definido com Esporte
 */
@Entity
@Table(name = "conteudos")
public class Conteudo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;
    
    @Column(name = "url", nullable = false, length = 500)
    private String url;
    
    @Column(name = "nivel", nullable = false, length = 50)
    private String nivel;
    
    @Column(name = "descricao", length = 1000)
    private String descricao;
    
    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;
    
    @Column(name = "esporte_id", nullable = false)
    private Long esporteId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "esporte_id", insertable = false, updatable = false)
    private Esporte esporte;

    // Construtores
    protected Conteudo() {
        // Construtor protegido para JPA
    }

    /**
     * Construtor de domínio - valida regras de negócio
     */
    public Conteudo(String titulo, String descricao, String tipo, Integer duracaoMinutos, Esporte esporte) {
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setUrl(tipo); // usando url como tipo temporariamente
        this.setDuracaoMinutos(duracaoMinutos);
        this.setEsporte(esporte);
        this.setEsporteId(esporte.getId());
        this.setNivel("Fundamental II"); // valor padrão
    }
    
    /**
     * Construtor alternativo
     */
    public Conteudo(String titulo, String url, String nivel, Long esporteId) {
        this.setTitulo(titulo);
        this.setUrl(url);
        this.setNivel(nivel);
        this.setEsporteId(esporteId);
    }

    // Getters e Setters com validação de domínio

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    /**
     * SOLUÇÃO: Validação de domínio encapsulada
     */
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título do conteúdo não pode ser nulo ou vazio");
        }
        if (titulo.trim().length() < 5 || titulo.trim().length() > 200) {
            throw new IllegalArgumentException("Título deve ter entre 5 e 200 caracteres");
        }
        this.titulo = titulo.trim();
    }

    public String getUrl() {
        return url;
    }

    /**
     * SOLUÇÃO: Validação de URL com regras de domínio
     */
    public void setUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL do conteúdo não pode ser nula ou vazia");
        }
        if (url.trim().length() > 500) {
            throw new IllegalArgumentException("URL deve ter no máximo 500 caracteres");
        }
        this.url = url.trim();
    }

    public String getNivel() {
        return nivel;
    }

    /**
     * SOLUÇÃO: Validação de nível com regras de negócio
     */
    public void setNivel(String nivel) {
        if (nivel == null || nivel.trim().isEmpty()) {
            throw new IllegalArgumentException("Nível do conteúdo não pode ser nulo ou vazio");
        }
        if (!nivel.equals("Fundamental II") && !nivel.equals("Médio")) {
            throw new IllegalArgumentException("Nível deve ser 'Fundamental II' ou 'Médio'");
        }
        this.nivel = nivel;
    }

    public Long getEsporteId() {
        return esporteId;
    }

    /**
     * SOLUÇÃO: Validação de relacionamento
     */
    public void setEsporteId(Long esporteId) {
        if (esporteId == null) {
            throw new IllegalArgumentException("ID do esporte não pode ser nulo");
        }
        this.esporteId = esporteId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }
    
    public Integer getDuracao() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        if (duracaoMinutos != null && duracaoMinutos <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        }
        this.duracaoMinutos = duracaoMinutos;
    }

    public Esporte getEsporte() {
        return esporte;
    }

    public void setEsporte(Esporte esporte) {
        this.esporte = esporte;
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se é para nível fundamental
     */
    public boolean isNivelFundamental() {
        return "Fundamental II".equals(this.nivel);
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se é para nível médio
     */
    public boolean isNivelMedio() {
        return "Médio".equals(this.nivel);
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se é conteúdo em vídeo
     */
    public boolean isVideo() {
        return this.url.toLowerCase().contains(".mp4") || 
               this.url.toLowerCase().contains("youtube") ||
               this.url.toLowerCase().contains("vimeo");
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se é conteúdo em PDF
     */
    public boolean isPdf() {
        return this.url.toLowerCase().contains(".pdf");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Conteudo conteudo = (Conteudo) obj;
        return id != null ? id.equals(conteudo.id) : conteudo.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
