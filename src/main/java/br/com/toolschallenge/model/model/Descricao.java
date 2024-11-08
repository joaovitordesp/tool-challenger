package br.com.toolschallenge.model.model;

import br.com.toolschallenge.model.enums.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "descricao")
public class Descricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataHora;
    private String estabelecimento;
    private String nsu;
    private String codAutorizacao;
    private StatusPagamento status;
}
