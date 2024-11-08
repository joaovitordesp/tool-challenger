package br.com.toolschallenge.model.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "transacao")
@Getter
@Setter
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cartao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "descricao_id", referencedColumnName = "id")
    private Descricao descricao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forma_pagamento_id", referencedColumnName = "id")
    private FormaPagamento formaPagamento;
}


