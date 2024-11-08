package br.com.toolschallenge;

import br.com.toolschallenge.controller.TransacaoController;
import br.com.toolschallenge.dto.input.TransacaoInputDTO;
import br.com.toolschallenge.dto.output.TransacaoOutputDTO;
import br.com.toolschallenge.model.model.Descricao;
import br.com.toolschallenge.model.model.FormaPagamento;
import br.com.toolschallenge.model.model.Transacao;
import br.com.toolschallenge.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransacaoControllerTest {
    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private TransacaoController transacaoController;

    private TransacaoInputDTO pagamentoDto;
    private TransacaoOutputDTO transacaoOutputDTO;
    private Transacao transacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pagamentoDto = new TransacaoInputDTO();
        // Aqui você configura o pagamentoDto com dados necessários, por exemplo:
        pagamentoDto.setTransacao(new Transacao());
        pagamentoDto.getTransacao().setCartao("1234567812345678");
        pagamentoDto.getTransacao().setDescricao(new Descricao());
        pagamentoDto.getTransacao().setFormaPagamento(new FormaPagamento());

        // Configuração do DTO de saída
        transacaoOutputDTO = new TransacaoOutputDTO();
        transacao = new Transacao();
        transacao.setCartao("1234567812345678");
        transacao.setDescricao(new Descricao());
        transacao.setFormaPagamento(new FormaPagamento());
        transacaoOutputDTO.setTransacao(transacao);
    }

    @Test
    void testRealizarPagamento_Success() {

        when(transacaoService.processarPagamento(any(TransacaoInputDTO.class)))
                .thenReturn(transacaoOutputDTO);

        ResponseEntity<TransacaoOutputDTO> response = transacaoController.realizarPagamento(pagamentoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(transacaoOutputDTO, response.getBody());

        verify(transacaoService, times(1)).processarPagamento(any(TransacaoInputDTO.class));
    }

    @Test
    void testRealizarPagamento_Fail() {

        when(transacaoService.processarPagamento(any(TransacaoInputDTO.class)))
                .thenThrow(new RuntimeException("Erro ao processar pagamento"));

        try {
            transacaoController.realizarPagamento(pagamentoDto);
            fail("Esperado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Erro ao processar pagamento", e.getMessage());
        }
    }
}
