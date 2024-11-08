package br.com.toolschallenge.model.model;

import br.com.toolschallenge.model.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "forma_pagamento")
public class FormaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoPagamento tipo;

    @JsonProperty("parcelas")
    private String numeroParcelas;
}
