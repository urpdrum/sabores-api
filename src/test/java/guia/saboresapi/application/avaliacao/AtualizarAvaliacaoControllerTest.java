package guia.saboresapi.application.avaliacao;


import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import guia.saboresapi.utils.generic.JsonStringHelper;
import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.input.avaliacao.AtualizarAvaliacaoRequest;
import guia.saboresapi.domain.mapper.avaliacao.AvaliacaoMapper;
import guia.saboresapi.domain.usecase.avaliacao.AtualizarAvaliacaoUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AtualizarAvaliacaoControllerTest {

    @Mock
    private AtualizarAvaliacaoUseCase atualizarAvaliacaoUseCase;

    @Mock
    private AvaliacaoMapper mapper;

    private MockMvc mockMvc;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        AtualizarAvaliacaoController controller = new AtualizarAvaliacaoController(mapper, atualizarAvaliacaoUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirAtualizarAvaliacao() throws Exception {
        Long id = 1L;
        var avaliacaoNova = AvaliacaoHelper.gerarAvaliacao();
        avaliacaoNova.setNota(1);
        avaliacaoNova.setComentario("Horrivel");
        var avaliacaoResponse = AvaliacaoHelper.gerarAvaliacaoResponse();
        var avaliacaoRequest = AvaliacaoHelper.gerarAtualizarAvaliacaoRequest();
        when(atualizarAvaliacaoUseCase.atualizarAvaliacao(anyLong(), any(Avaliacao.class)))
                .thenAnswer(answer -> answer.getArgument(1));
        when(mapper.toAvaliacao(any(AtualizarAvaliacaoRequest.class))).thenReturn(avaliacaoNova);
        when(mapper.toAvaliacaoResponse(any(Avaliacao.class))).thenReturn(avaliacaoResponse);

        mockMvc.perform(put("/avaliacoes/{id}", id)
                .content(JsonStringHelper.asJsonString(avaliacaoRequest))
                .contentType(MediaType.APPLICATION_JSON))
                
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.avaliacaoId").value(id))
                .andExpect(jsonPath("$.usuarioId").value(avaliacaoResponse.usuarioId()))
                .andExpect(jsonPath("$.restauranteId").value(avaliacaoResponse.restauranteId()))
                .andExpect(jsonPath("$.nota").value(avaliacaoResponse.nota()))
                .andExpect(jsonPath("$.comentario").value(avaliacaoResponse.comentario()));

        verify(atualizarAvaliacaoUseCase, times(1)).atualizarAvaliacao(anyLong(), any(Avaliacao.class));
        verify(mapper, times(1)).toAvaliacao(any(AtualizarAvaliacaoRequest.class));
        verify(mapper, times(1)).toAvaliacaoResponse(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarAvaliacao_IdNaoEncontrado() throws Exception {
        Long id = 1L;
        var avaliacaoNova = AvaliacaoHelper.gerarAvaliacao();
        avaliacaoNova.setNota(1);
        avaliacaoNova.setComentario("Horrivel");
        var avaliacaoResponse = AvaliacaoHelper.gerarAvaliacaoResponse();
        var avaliacaoRequest = AvaliacaoHelper.gerarAtualizarAvaliacaoRequest();
        var mensagemException = "Avaliação de id: " + id + " não encontrada.";
        when(atualizarAvaliacaoUseCase.atualizarAvaliacao(anyLong(), any(Avaliacao.class)))
                .thenThrow(new AvaliacaoNotFoundException(mensagemException));
        when(mapper.toAvaliacao(any(AtualizarAvaliacaoRequest.class))).thenReturn(avaliacaoNova);
        when(mapper.toAvaliacaoResponse(any(Avaliacao.class))).thenReturn(avaliacaoResponse);

        mockMvc.perform(put("/avaliacoes/{id}", id)
                        .content(JsonStringHelper.asJsonString(avaliacaoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/avaliacoes/" + id));

        verify(atualizarAvaliacaoUseCase, times(1)).atualizarAvaliacao(anyLong(), any(Avaliacao.class));
        verify(mapper, times(1)).toAvaliacao(any(AtualizarAvaliacaoRequest.class));
        verify(mapper, never()).toAvaliacaoResponse(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarAvaliacao_NotaInvalida() throws Exception {
        Long id = 1L;
        var avaliacaoNova = AvaliacaoHelper.gerarAvaliacao();
        avaliacaoNova.setNota(200);
        avaliacaoNova.setComentario("Horrivel");
        var avaliacaoResponse = AvaliacaoHelper.gerarAvaliacaoResponse();
        var avaliacaoRequest = AvaliacaoHelper.gerarAtualizarAvaliacaoRequest();
        var mensagemException = "A Nota deve ser entre 0 e 5";
        when(atualizarAvaliacaoUseCase.atualizarAvaliacao(anyLong(), any(Avaliacao.class)))
                .thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toAvaliacao(any(AtualizarAvaliacaoRequest.class))).thenReturn(avaliacaoNova);
        when(mapper.toAvaliacaoResponse(any(Avaliacao.class))).thenReturn(avaliacaoResponse);

        mockMvc.perform(put("/avaliacoes/{id}", id)
                        .content(JsonStringHelper.asJsonString(avaliacaoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/avaliacoes/" + id));

        verify(atualizarAvaliacaoUseCase, times(1)).atualizarAvaliacao(anyLong(), any(Avaliacao.class));
        verify(mapper, times(1)).toAvaliacao(any(AtualizarAvaliacaoRequest.class));
        verify(mapper, never()).toAvaliacaoResponse(any(Avaliacao.class));
    }

}
