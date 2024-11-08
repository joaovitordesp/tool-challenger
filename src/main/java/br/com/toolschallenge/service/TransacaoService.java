package br.com.toolschallenge.service;

import br.com.toolschallenge.domain.exception.AutorizacaoRecusadaException;
import br.com.toolschallenge.domain.exception.TransacaoJaEstornadaException;
import br.com.toolschallenge.domain.exception.TransacaoNaoEncontradaException;
import br.com.toolschallenge.dto.input.TransacaoInputDTO;
import br.com.toolschallenge.dto.output.EstornoOutputDTO;
import br.com.toolschallenge.dto.output.TransacaoOutputDTO;
import br.com.toolschallenge.model.enums.StatusPagamento;
import br.com.toolschallenge.model.model.Transacao;
import br.com.toolschallenge.model.repository.TransacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public TransacaoOutputDTO processarPagamento(TransacaoInputDTO dto) {
        TransacaoOutputDTO transacao = new TransacaoOutputDTO();

        transacao.setTransacao(dto.getTransacao());
        transacao.getTransacao().setFormaPagamento(dto.getTransacao().getFormaPagamento());
        transacao.getTransacao().setDescricao(dto.getTransacao().getDescricao());

        String nsu = gerarNsu();
        String codigoAutorizacao = gerarCodigoAutorizacao();

        StatusPagamento status = autorizarTransacao(dto.getTransacao().getDescricao().getValor())
                ? StatusPagamento.AUTORIZADO
                : StatusPagamento.NEGADO;

        transacao.getTransacao().getDescricao().setNsu(nsu);
        transacao.getTransacao().getDescricao().setCodAutorizacao(codigoAutorizacao);
        transacao.getTransacao().getDescricao().setStatus(status);

        transacao.getTransacao().setCartao(mascararCartao(dto.getTransacao().getCartao()));

        transacaoRepository.save(transacao.getTransacao());

        return transacao;
    }

    public Optional<Transacao> consultarTransacao(Long id) {
        return Optional.ofNullable(transacaoRepository.findById(id)
                .orElseThrow(() -> new TransacaoNaoEncontradaException(id) {
                }));
    }

    @Transactional
    public EstornoOutputDTO estornarPagamento(Long id) {
        Transacao transacao = consultarTransacao(id)
                .orElseThrow(() -> new TransacaoNaoEncontradaException(id));

        if(transacao.getDescricao().getStatus().equals(StatusPagamento.CANCELADO)){
            throw new TransacaoJaEstornadaException(id);
        }

        transacao.getDescricao().setStatus(StatusPagamento.CANCELADO);

        transacaoRepository.save(transacao);

        EstornoOutputDTO estornoOutput = new EstornoOutputDTO();
        estornoOutput.setTransacao(transacao);
        estornoOutput.getTransacao().setDescricao(transacao.getDescricao());
        estornoOutput.getTransacao().setFormaPagamento(transacao.getFormaPagamento());

        return estornoOutput;
    }

    private String gerarNsu() {
        return String.valueOf((int) (Math.random() * 1000000));
    }

    private String gerarCodigoAutorizacao() {
        return String.valueOf((int) (Math.random() * 1000000));
    }

    private boolean autorizarTransacao(double valor) {
        if(valor <= 1000.00){
            return true;
        }else{
            throw new AutorizacaoRecusadaException();
        }
    }

    private String mascararCartao(String cartao) {
        return cartao.substring(0, 3) + "*******" + cartao.substring(cartao.length() - 2);
    }
}


