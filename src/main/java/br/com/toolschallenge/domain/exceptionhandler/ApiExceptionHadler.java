package br.com.toolschallenge.domain.exceptionhandler;

import br.com.toolschallenge.domain.exception.EntidadeNaoEncontradaException;
import br.com.toolschallenge.domain.exception.NegocioException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;

@ControllerAdvice // ponto central de tratamento de exceptions handlers
public class ApiExceptionHadler extends ResponseEntityExceptionHandler {

    //caso precise de uma futura implementação
    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
            + "o problema persistir, entre em contato com o administrador do sistema.";

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

        return Problem.builder().timestamp(OffsetDateTime.now()).status(status.value()).type(problemType.getUri())
                .title(problemType.getTitle()).detail(detail);
    }

    private String joinPath(List<Reference> references) {
        return references.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
    }
}

