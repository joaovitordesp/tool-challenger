package br.com.toolschallenge.dto.output;

import br.com.toolschallenge.model.model.Descricao;
import br.com.toolschallenge.model.model.FormaPagamento;
import br.com.toolschallenge.model.model.Transacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstornoOutputDTO {
    private Transacao transacao;
}

