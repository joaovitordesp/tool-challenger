package br.com.toolschallenge.controller;

import br.com.toolschallenge.dto.input.TransacaoInputDTO;
import br.com.toolschallenge.dto.output.EstornoOutputDTO;
import br.com.toolschallenge.dto.output.TransacaoOutputDTO;
import br.com.toolschallenge.model.model.Transacao;
import br.com.toolschallenge.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pagamentos")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/pagamento")
    public ResponseEntity<TransacaoOutputDTO> realizarPagamento(@RequestBody TransacaoInputDTO pagamentoDto) {
        TransacaoOutputDTO transacao = transacaoService.processarPagamento(pagamentoDto);
        return ResponseEntity.ok(transacao);
    }

    @GetMapping("/consulta/{id}")
    public ResponseEntity<Transacao> consultarTransacao(@PathVariable Long id) {
        Optional<Transacao> transacao = transacaoService.consultarTransacao(id);
        return transacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/estorno/{id}")
    public ResponseEntity<EstornoOutputDTO> estornarPagamento(@PathVariable Long id) {
        EstornoOutputDTO estorno = transacaoService.estornarPagamento(id);
        return ResponseEntity.ok(estorno);
    }
}
