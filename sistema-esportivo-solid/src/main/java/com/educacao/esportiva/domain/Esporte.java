package com.educacao.esportiva.domain;

import jakarta.persistence.*;

/**
 * ========================================================================
 * ENTIDADE DE DOMÍNIO ESPORTE - VERSÃO SOLID
 * ========================================================================
 * 
 * APLICAÇÃO DOS PRINCÍPIOS SOLID:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    ✅ Responsabilidade única: representar um Esporte no domínio
 *    ✅ Contém apenas dados e comportamentos relacionados ao conceito Esporte
 *    ✅ Validações de domínio encapsuladas na própria entidade
 * 
 * 2. OPEN/CLOSED PRINCIPLE (OCP):
 *    ✅ Aberto para extensão através de herança
 *    ✅ Fechado para modificação dos métodos fundamentais
 * 
 * MELHORIAS EM RELAÇÃO À VERSÃO MONOLÍTICA:
 * - Separação clara entre entidade de domínio e persistência
 * - Validações de domínio encapsuladas
 * - Métodos de negócio específicos do domínio
 * - Uso de JPA para mapeamento objeto-relacional
 * - Construtores com validação automática
 */
@Entity
@Table(name = "esportes")
public class Esporte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, unique = true, length = 100)
    private String nome;
    
    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    // Construtores
    protected Esporte() {
        // Construtor protegido para JPA
    }

    /**
     * Construtor de domínio - APLICAÇÃO DO SRP
     * 
     * SOLUÇÃO: Validação de domínio encapsulada na criação
     * - Garante que nenhum objeto Esporte seja criado em estado inválido
     * - Centraliza regras de negócio na entidade
     */
    public Esporte(String nome, String categoria) {
        this.setNome(nome);
        this.setCategoria(categoria);
    }

    // Getters e Setters com validação de domínio

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    /**
     * SOLUÇÃO SRP: Validação de domínio encapsulada
     * 
     * PROBLEMA RESOLVIDO: Na versão monolítica, a validação estava no controller
     * SOLUÇÃO APLICADA: Validação movida para onde realmente pertence - a entidade
     */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do esporte não pode ser nulo ou vazio");
        }
        if (nome.trim().length() < 2 || nome.trim().length() > 100) {
            throw new IllegalArgumentException("Nome do esporte deve ter entre 2 e 100 caracteres");
        }
        this.nome = nome.trim();
    }

    public String getCategoria() {
        return categoria;
    }

    /**
     * SOLUÇÃO SRP: Validação de categoria com regras de domínio
     */
    public void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria do esporte não pode ser nula ou vazia");
        }
        if (categoria.trim().length() < 2 || categoria.trim().length() > 50) {
            throw new IllegalArgumentException("Categoria do esporte deve ter entre 2 e 50 caracteres");
        }
        this.categoria = categoria.trim();
    }

    /**
     * MÉTODO DE DOMÍNIO - APLICAÇÃO DO SRP
     * 
     * SOLUÇÃO: Comportamento específico do domínio Esporte
     * - Encapsula lógica de classificação
     * - Evita repetição de código em outras camadas
     */
    public boolean isColetivo() {
        return "Coletivo".equalsIgnoreCase(this.categoria);
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se é esporte individual
     */
    public boolean isIndividual() {
        return "Individual".equalsIgnoreCase(this.categoria);
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar se é esporte aquático
     */
    public boolean isAquatico() {
        return "Aquático".equalsIgnoreCase(this.categoria) || 
               this.nome.toLowerCase().contains("natação") ||
               this.nome.toLowerCase().contains("polo aquático");
    }

    /**
     * MÉTODO DE DOMÍNIO: Obter descrição completa
     */
    public String getDescricaoCompleta() {
        return String.format("%s (%s)", this.nome, this.categoria);
    }

    /**
     * MÉTODO DE DOMÍNIO: Verificar compatibilidade com nível educacional
     */
    public boolean isCompativel(String nivelEducacional) {
        // Todos os esportes são compatíveis com Fundamental II e Médio
        return "Fundamental II".equals(nivelEducacional) || "Médio".equals(nivelEducacional);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Esporte esporte = (Esporte) obj;
        return id != null ? id.equals(esporte.id) : esporte.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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