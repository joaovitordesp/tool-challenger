package br.com.toolschallenge.dto.input;

import br.com.toolschallenge.model.model.Descricao;
import br.com.toolschallenge.model.model.FormaPagamento;
import br.com.toolschallenge.model.model.Transacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransacaoInputDTO {
    private Transacao transacao;
}
