package guia.saboresapi.application.avaliacao;

import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.exception.usuario.UsuarioNotFoundException;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import guia.saboresapi.utils.generic.JsonStringHelper;
import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.input.avaliacao.CadastrarAvaliacaoRequest;
import guia.saboresapi.domain.mapper.avaliacao.AvaliacaoMapper;
import guia.saboresapi.domain.usecase.avaliacao.CadastrarAvaliacaoUseCase;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CadastrarAvaliacaoControllerTest {

    @Mock
    private CadastrarAvaliacaoUseCase cadastrarAvaliacaoUseCase;

    @Mock
    private AvaliacaoMapper mapper;

    private MockMvc mockMvc;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        CadastrarAvaliacaoController controller = new CadastrarAvaliacaoController(mapper, cadastrarAvaliacaoUseCase);
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
    void devePermitirCadastrarAvaliacao() throws Exception {
        var avaliacao = AvaliacaoHelper.gerarAvaliacao();
        var request = AvaliacaoHelper.gerarCadastrarAvaliacaoRequest();
        var response = AvaliacaoHelper.gerarAvaliacaoResponse();
        when(cadastrarAvaliacaoUseCase.cadastrarAvaliacao(any(Avaliacao.class), anyLong(), anyLong())).thenAnswer(answer -> answer.getArgument(0));
        when(mapper.toAvaliacao(any(CadastrarAvaliacaoRequest.class))).thenReturn(avaliacao);
        when(mapper.toAvaliacaoResponse(any(Avaliacao.class))).thenReturn(response);

        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuarioId").value(response.usuarioId()))
                .andExpect(jsonPath("$.restauranteId").value(response.restauranteId()))
                .andExpect(jsonPath("$.nota").value(response.nota()))
                .andExpect(jsonPath("$.comentario").value(response.comentario()));

        verify(cadastrarAvaliacaoUseCase, times(1)).cadastrarAvaliacao(any(Avaliacao.class), anyLong(), anyLong());
        verify(mapper, times(1)).toAvaliacao(any(CadastrarAvaliacaoRequest.class));
        verify(mapper, times(1)).toAvaliacaoResponse(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarAvaliacao_RestauranteNaoEncontrado() throws Exception {
        var avaliacao = AvaliacaoHelper.gerarAvaliacao();
        var request = AvaliacaoHelper.gerarCadastrarAvaliacaoRequest();
        var response = AvaliacaoHelper.gerarAvaliacaoResponse();
        var mensagemException = "Restaurante de id: " + avaliacao.getRestaurante().getRestauranteId() + " não encontrado.";
        when(cadastrarAvaliacaoUseCase.cadastrarAvaliacao(any(Avaliacao.class), anyLong(), anyLong())).thenThrow(new RestauranteNotFoundException(mensagemException));
        when(mapper.toAvaliacao(any(CadastrarAvaliacaoRequest.class))).thenReturn(avaliacao);
        when(mapper.toAvaliacaoResponse(any(Avaliacao.class))).thenReturn(response);

        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/avaliacoes"));

        verify(cadastrarAvaliacaoUseCase, times(1)).cadastrarAvaliacao(any(Avaliacao.class), anyLong(), anyLong());
        verify(mapper, times(1)).toAvaliacao(any(CadastrarAvaliacaoRequest.class));
        verify(mapper, never()).toAvaliacaoResponse(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarAvaliacao_UsuarioNaoEncontrado() throws Exception {
        var avaliacao = AvaliacaoHelper.gerarAvaliacao();
        var request = AvaliacaoHelper.gerarCadastrarAvaliacaoRequest();
        var response = AvaliacaoHelper.gerarAvaliacaoResponse();
        var mensagemException = "Usuário de id: " + avaliacao.getUsuario().getUsuarioId() + " não encontrado.";
        when(cadastrarAvaliacaoUseCase.cadastrarAvaliacao(any(Avaliacao.class), anyLong(), anyLong())).thenThrow(new UsuarioNotFoundException(mensagemException));
        when(mapper.toAvaliacao(any(CadastrarAvaliacaoRequest.class))).thenReturn(avaliacao);
        when(mapper.toAvaliacaoResponse(any(Avaliacao.class))).thenReturn(response);

        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/avaliacoes"));

        verify(cadastrarAvaliacaoUseCase, times(1)).cadastrarAvaliacao(any(Avaliacao.class), anyLong(), anyLong());
        verify(mapper, times(1)).toAvaliacao(any(CadastrarAvaliacaoRequest.class));
        verify(mapper, never()).toAvaliacaoResponse(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarAvaliacao_NotaInvalida() throws Exception {
        var avaliacao = AvaliacaoHelper.gerarAvaliacao();
        var request = AvaliacaoHelper.gerarCadastrarAvaliacaoRequest();
        var response = AvaliacaoHelper.gerarAvaliacaoResponse();
        var mensagemException = "A Nota deve ser entre 0 e 5";
        when(cadastrarAvaliacaoUseCase.cadastrarAvaliacao(any(Avaliacao.class), anyLong(), anyLong())).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toAvaliacao(any(CadastrarAvaliacaoRequest.class))).thenReturn(avaliacao);
        when(mapper.toAvaliacaoResponse(any(Avaliacao.class))).thenReturn(response);

        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/avaliacoes"));

        verify(cadastrarAvaliacaoUseCase, times(1)).cadastrarAvaliacao(any(Avaliacao.class), anyLong(), anyLong());
        verify(mapper, times(1)).toAvaliacao(any(CadastrarAvaliacaoRequest.class));
        verify(mapper, never()).toAvaliacaoResponse(any(Avaliacao.class));
    }
}
