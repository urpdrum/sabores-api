package guia.saboresapi.application.restaurante;

import com.fiap.tc.restaurantes.application.handler.GlobalExceptionHandler;
import com.fiap.tc.restaurantes.domain.entity.Restaurante;
import com.fiap.tc.restaurantes.domain.exception.restaurante.RestauranteNotFoundException;
import com.fiap.tc.restaurantes.domain.input.restaurante.AtualizarRestauranteRequest;
import com.fiap.tc.restaurantes.domain.mapper.restaurante.RestauranteMapper;
import com.fiap.tc.restaurantes.domain.usecase.restaurante.AtualizarRestauranteUseCase;
import guia.saboresapi.utils.generic.JsonStringHelper;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AtualizarRestauranteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @Mock
    private RestauranteMapper mapper;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        AtualizarRestauranteController controller = new AtualizarRestauranteController(mapper, atualizarRestauranteUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirAtualizarRestaurante() throws Exception {
        // Arrange
        Long id = 1L;
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var response = RestauranteHelper.gerarRestauranteResponse();
        var request = RestauranteHelper.gerarAtualizarRestauranteRequest();

        when(atualizarRestauranteUseCase.atualizarRestaurante(id, restaurante)).thenReturn(restaurante);
        when(mapper.toRestaurante(any(AtualizarRestauranteRequest.class))).thenReturn(restaurante);
        when(mapper.toRestauranteResponse(any(Restaurante.class))).thenReturn(response);

        // Act
        mockMvc.perform(put("/restaurantes/{id}", id)
                .content(JsonStringHelper.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restauranteId").value(id))
                .andExpect(jsonPath("$.nome").value(response.nome()))
                .andExpect(jsonPath("$.endereco.logradouro").value(response.endereco().logradouro()))
                .andExpect(jsonPath("$.tipoDeCozinha").value(response.tipoDeCozinha()))
                .andExpect(jsonPath("$.capacidade").value(response.capacidade()))
                .andExpect(jsonPath("$.horarioFuncionamento").value(response.horarioFuncionamento()));

        verify(mapper, times(1)).toRestaurante(any(AtualizarRestauranteRequest.class));
        verify(atualizarRestauranteUseCase, times(1)).atualizarRestaurante(any(Long.class), any(Restaurante.class));
        verify(mapper, times(1)).toRestauranteResponse(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_IdNaoEncontrado() throws Exception {
        Long id = 1L;
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var request = RestauranteHelper.gerarAtualizarRestauranteRequest();
        var mensagemException = "Restaurante de id: " + id + " não encontrado.";
        when(atualizarRestauranteUseCase.atualizarRestaurante(anyLong(), any(Restaurante.class))).thenThrow(new RestauranteNotFoundException(mensagemException));
        when(mapper.toRestaurante(any(AtualizarRestauranteRequest.class))).thenReturn(restaurante);

        mockMvc.perform(put("/restaurantes/{id}", id)
                        .content(JsonStringHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/restaurantes/" + id));


        verify(mapper, times(1)).toRestaurante(any(AtualizarRestauranteRequest.class));
        verify(atualizarRestauranteUseCase, times(1)).atualizarRestaurante(any(Long.class), any(Restaurante.class));
        verify(mapper, never()).toRestauranteResponse(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_CepNaoEncontrado() throws Exception {
        Long id = 1L;
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var request = RestauranteHelper.gerarAtualizarRestauranteRequestComCepInexistente();
        var mensagemException = "CEP inexistente.";
        when(atualizarRestauranteUseCase.atualizarRestaurante(anyLong(), any(Restaurante.class))).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toRestaurante(any(AtualizarRestauranteRequest.class))).thenReturn(restaurante);

        mockMvc.perform(put("/restaurantes/{id}", id)
                        .content(JsonStringHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/restaurantes/" + id));


        verify(mapper, times(1)).toRestaurante(any(AtualizarRestauranteRequest.class));
        verify(atualizarRestauranteUseCase, times(1)).atualizarRestaurante(any(Long.class), any(Restaurante.class));
        verify(mapper, never()).toRestauranteResponse(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_NomeNaoInformado() throws Exception {
        Long id = 1L;
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var request = RestauranteHelper.gerarAtualizarRestauranteRequestComNomeNulo();
        var mensagemException = "O nome do restaurante deve ser informado.";
        when(atualizarRestauranteUseCase.atualizarRestaurante(anyLong(), any(Restaurante.class))).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toRestaurante(any(AtualizarRestauranteRequest.class))).thenReturn(restaurante);

        mockMvc.perform(put("/restaurantes/{id}", id)
                        .content(JsonStringHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/restaurantes/" + id));


        verify(mapper, times(1)).toRestaurante(any(AtualizarRestauranteRequest.class));
        verify(atualizarRestauranteUseCase, times(1)).atualizarRestaurante(any(Long.class), any(Restaurante.class));
        verify(mapper, never()).toRestauranteResponse(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_CapacidadeNaoInformado() throws Exception {
        Long id = 1L;
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var request = RestauranteHelper.gerarAtualizarRestauranteRequestComCapacidadeNula();
        var mensagemException = "A capacidade do restaurante deve ser informada.";
        when(atualizarRestauranteUseCase.atualizarRestaurante(anyLong(), any(Restaurante.class))).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toRestaurante(any(AtualizarRestauranteRequest.class))).thenReturn(restaurante);

        mockMvc.perform(put("/restaurantes/{id}", id)
                        .content(JsonStringHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/restaurantes/" + id));


        verify(mapper, times(1)).toRestaurante(any(AtualizarRestauranteRequest.class));
        verify(atualizarRestauranteUseCase, times(1)).atualizarRestaurante(any(Long.class), any(Restaurante.class));
        verify(mapper, never()).toRestauranteResponse(any(Restaurante.class));
    }

}