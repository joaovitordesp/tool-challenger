package br.com.toolschallenge.domain.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    DADOS_INVALIDOS("/dados-invalidos", "Os dados passados estão inválidos"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private final String title;
    private final String uri;

    ProblemType(String path, String title){
        this.uri = "https://apipagamentos.com.br"+path;
        this.title = title;
    }
}