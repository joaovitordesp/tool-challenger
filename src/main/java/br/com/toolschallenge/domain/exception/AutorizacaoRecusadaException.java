package br.com.toolschallenge.domain.exception;

public class AutorizacaoRecusadaException extends EntidadeNaoEncontradaException{
    public AutorizacaoRecusadaException(String mensagem) {
        super(mensagem);
    }

    public AutorizacaoRecusadaException() {
        this(String.format("A transação foi recusada pois excedeu o valor"));
    }
}
