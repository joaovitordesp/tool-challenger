package br.com.toolschallenge.domain.exception;

public class TransacaoNaoEncontradaException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = 1L;

    public TransacaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public TransacaoNaoEncontradaException(Long transacaoId) {
        this(String.format("Não existe uma transação com o id: %d", transacaoId));
    }
}
