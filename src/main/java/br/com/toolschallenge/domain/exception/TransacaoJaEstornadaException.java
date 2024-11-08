package br.com.toolschallenge.domain.exception;

public class TransacaoJaEstornadaException extends EntidadeNaoEncontradaException{
    public TransacaoJaEstornadaException(String mensagem) {
        super(mensagem);
    }

    public TransacaoJaEstornadaException(Long transacaoId) {
        this(String.format("A transação com o ID %d já foi estornada", transacaoId));
    }
}
