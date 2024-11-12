package guia.saboresapi.application.restaurante;



import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.input.restaurante.CadastrarRestauranteRequest;
import guia.saboresapi.domain.mapper.restaurante.RestauranteMapper;
import guia.saboresapi.domain.usecase.restaurante.CadastrarRestauranteUseCase;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class CadastrarRestauranteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @Mock
    private RestauranteMapper mapper;


    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        CadastrarRestauranteController controller = new CadastrarRestauranteController(mapper, cadastrarRestauranteUseCase);
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
    void devePermitirCadastrarRestaurante() throws Exception {
        // Arrange
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var request = RestauranteHelper.gerarCadastrarRestauranteRequest();
        var response = RestauranteHelper.gerarRestauranteResponse();

        when(cadastrarRestauranteUseCase.cadastrarRestaurante(any(Restaurante.class))).thenAnswer(answer -> answer.getArgument(0));
        when(mapper.toRestaurante(any(CadastrarRestauranteRequest.class))).thenReturn(restaurante);
        when(mapper.toRestauranteResponse(any(Restaurante.class))).thenReturn(response);

        // Act
        mockMvc.perform(post("/restaurantes")
                .content(JsonStringHelper.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.restauranteId").value(response.restauranteId()))
            .andExpect(jsonPath("$.nome").value(response.nome()))
            .andExpect(jsonPath("$.tipoDeCozinha").value(response.tipoDeCozinha()))
            .andExpect(jsonPath("$.endereco.logradouro").value(response.endereco().logradouro()))
            .andExpect(jsonPath("$.capacidade").value(response.capacidade()))
            .andExpect(jsonPath("$.horarioFuncionamento").value(response.horarioFuncionamento()));


        verify(cadastrarRestauranteUseCase, times(1)).cadastrarRestaurante(any(Restaurante.class));
        verify(mapper, times(1)).toRestaurante(any(CadastrarRestauranteRequest.class));
        verify(mapper, times(1)).toRestauranteResponse(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarRestaurante_CepNaoEncontrado() throws Exception {
        // Arrange
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var request = RestauranteHelper.gerarCadastrarRestauranteRequestComCepInexistente();
        var response = RestauranteHelper.gerarRestauranteResponse();
        var mensagemException = "CEP inexistente.";

        when(cadastrarRestauranteUseCase.cadastrarRestaurante(any(Restaurante.class))).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toRestaurante(any(CadastrarRestauranteRequest.class))).thenReturn(restaurante);
        when(mapper.toRestauranteResponse(any(Restaurante.class))).thenReturn(response);

        // Act
        mockMvc.perform(post("/restaurantes")
                        .content(JsonStringHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/restaurantes"));


        verify(cadastrarRestauranteUseCase, times(1)).cadastrarRestaurante(any(Restaurante.class));
        verify(mapper, times(1)).toRestaurante(any(CadastrarRestauranteRequest.class));
        verify(mapper, never()).toRestauranteResponse(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarRestaurante_NomeNaoInformado() throws Exception {
        // Arrange
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var request = RestauranteHelper.gerarCadastrarRestauranteRequestComNomeNulo();
        var response = RestauranteHelper.gerarRestauranteResponse();
        var mensagemException = "O nome do restaurante deve ser informado.";

        when(cadastrarRestauranteUseCase.cadastrarRestaurante(any(Restaurante.class))).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toRestaurante(any(CadastrarRestauranteRequest.class))).thenReturn(restaurante);
        when(mapper.toRestauranteResponse(any(Restaurante.class))).thenReturn(response);

        // Act
        mockMvc.perform(post("/restaurantes")
                        .content(JsonStringHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/restaurantes"));


        verify(cadastrarRestauranteUseCase, times(1)).cadastrarRestaurante(any(Restaurante.class));
        verify(mapper, times(1)).toRestaurante(any(CadastrarRestauranteRequest.class));
        verify(mapper, never()).toRestauranteResponse(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarRestaurante_CapacidadeNaoInformado() throws Exception {
        // Arrange
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var request = RestauranteHelper.gerarCadastrarRestauranteRequestComCapacidadeNula();
        var response = RestauranteHelper.gerarRestauranteResponse();
        var mensagemException = "A capacidade do restaurante deve ser informada.";

        when(cadastrarRestauranteUseCase.cadastrarRestaurante(any(Restaurante.class))).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toRestaurante(any(CadastrarRestauranteRequest.class))).thenReturn(restaurante);
        when(mapper.toRestauranteResponse(any(Restaurante.class))).thenReturn(response);

        // Act
        mockMvc.perform(post("/restaurantes")
                        .content(JsonStringHelper.asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/restaurantes"));


        verify(cadastrarRestauranteUseCase, times(1)).cadastrarRestaurante(any(Restaurante.class));
        verify(mapper, times(1)).toRestaurante(any(CadastrarRestauranteRequest.class));
        verify(mapper, never()).toRestauranteResponse(any(Restaurante.class));
    }
}