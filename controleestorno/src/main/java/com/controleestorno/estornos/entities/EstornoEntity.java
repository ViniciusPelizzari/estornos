package com.controleestorno.estornos.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Classe que representa um estorno de produto.
 * Entidade mapeada para a tabela "controle_estorno" no banco de dados.
 */
@Entity
@Table(name = "controle-estorno")
@Data
public class EstornoEntity {

    //Campos da entidade - EstornoEntity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricaoProduto;
    private int codigoProduto;
    private Long saldoAnterior;
    private Long saldoAtualizado;
    private String descricaoEstorno;
    private String nomeResponsavel;
    @DateTimeFormat
    private Date dateEstorno;
    @DateTimeFormat
    private Date dateAtualizacaoEstorno;

    @PrePersist
    public void prePersist() {
        this.dateEstorno = new Date();
        this.dateAtualizacaoEstorno = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateAtualizacaoEstorno = new Date();
    }
}

