package guia.saboresapi.application.avaliacao;


import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.mapper.avaliacao.AvaliacaoMapper;
import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacoesPorUsuarioUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BuscarAvaliacoesPorUsuarioControllerTest {

    @Mock
    private BuscarAvaliacoesPorUsuarioUseCase buscarAvaliacoesPorUsuarioUseCase;

    @Mock
    private AvaliacaoMapper mapper;

    private MockMvc mockMvc;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        BuscarAvaliacoesPorUsuarioController controller = new BuscarAvaliacoesPorUsuarioController(mapper, buscarAvaliacoesPorUsuarioUseCase);
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
    void devePermitirBuscarAvaliacoesPorUsuario() throws Exception {
        var usuarioId = 1L;
        var avaliacao1 = AvaliacaoHelper.gerarAvaliacao();
        var avaliacao2 = AvaliacaoHelper.gerarAvaliacao();
        var listAvaliacoes = Arrays.asList(avaliacao1, avaliacao2);
        var avaliacaoResponse = AvaliacaoHelper.gerarAvaliacaoResponse();
        when(buscarAvaliacoesPorUsuarioUseCase.buscarAvaliacoesPorUsuario(anyLong())).thenReturn(listAvaliacoes);
        when(mapper.toAvaliacaoResponse(any(Avaliacao.class))).thenReturn(avaliacaoResponse);

        mockMvc.perform(get("/avaliacoes/usuario")
                        .param("usuarioId", String.valueOf(usuarioId)))
                
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(listAvaliacoes.size()))
                .andExpect(jsonPath("$[0].usuarioId").value(avaliacaoResponse.usuarioId()))
                .andExpect(jsonPath("$[0].restauranteId").value(avaliacaoResponse.restauranteId()))
                .andExpect(jsonPath("$[0].nota").value(avaliacaoResponse.nota()))
                .andExpect(jsonPath("$[0].comentario").value(avaliacaoResponse.comentario()));
        verify(buscarAvaliacoesPorUsuarioUseCase, times(1)).buscarAvaliacoesPorUsuario(anyLong());
        verify(mapper, times(2)).toAvaliacaoResponse(any(Avaliacao.class));
    }
}
