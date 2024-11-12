package guia.saboresapi.domain.usecase.restaurante;


import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.gateway.restaurante.AtualizarRestauranteInterface;
import guia.saboresapi.domain.gateway.restaurante.ConsultarEnderecoPorCepInterface;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AtualizarRestauranteUseCaseTest {

    private AtualizarRestauranteUseCase useCase;

    @Mock
    private AtualizarRestauranteInterface atualizarRestauranteInterface;

    @Mock
    private ConsultarEnderecoPorCepInterface consultarEnderecoPorCepInterface;

    @Mock
    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    AutoCloseable openMocks;


    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new AtualizarRestauranteUseCase(atualizarRestauranteInterface, consultarEnderecoPorCepInterface, buscarRestaurantePorIdUseCase);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirAtualizarRestaurante() {
        // Arrange
        var restauranteAntigo = RestauranteHelper.gerarRestauranteValido();
        var restauranteNovo = RestauranteHelper.gerarRestauranteValido();
        var id = 1L;
        restauranteAntigo.setRestauranteId(id);
        restauranteNovo.getEndereco().setCep("83010680");
        restauranteNovo.setCapacidade(150);

        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(restauranteAntigo);
        when(consultarEnderecoPorCepInterface.consultaPorCep(anyString())).thenReturn(RestauranteHelper.enderecoBuilder());
        when(atualizarRestauranteInterface.atualizarRestaurante(any(Restaurante.class))).thenReturn(restauranteAntigo);

        // Act
        Restaurante restauranteSalvo = useCase.atualizarRestaurante(id, restauranteNovo);

        // Assert
        assertThat(restauranteSalvo)
                .isNotNull()
                .isInstanceOf(Restaurante.class)
                .isEqualTo(restauranteAntigo);

        assertThat(restauranteSalvo.getNome())
                .isEqualTo(restauranteAntigo.getNome());

        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(consultarEnderecoPorCepInterface, times(1)).consultaPorCep(anyString());
        verify(atualizarRestauranteInterface, times(1)).atualizarRestaurante(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_IdNaoEncontrado() {
        Restaurante entidade = RestauranteHelper.gerarRestauranteValido();
        var id = 1L;
        entidade.setRestauranteId(id);
        var mensagemException = "Restaurante de id: " + id + " não encontrado.";
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenThrow(new RestauranteNotFoundException(mensagemException));

        assertThatThrownBy(() -> useCase.atualizarRestaurante(id, entidade))
                .isInstanceOf(RestauranteNotFoundException.class)
                .hasMessage(mensagemException);
        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(consultarEnderecoPorCepInterface, never()).consultaPorCep(anyString());
        verify(atualizarRestauranteInterface, never()).atualizarRestaurante(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_CepNaoEncontrado() {
        var restauranteAntigo = RestauranteHelper.gerarRestauranteValido();
        var restauranteNovo = RestauranteHelper.gerarRestauranteValido();
        var id = 1L;
        restauranteAntigo.setRestauranteId(id);
        restauranteNovo.getEndereco().setCep("83010680");
        restauranteNovo.setCapacidade(150);
        var enderecoSemCep = Endereco.builder().cep(null).build();
        var mensagemException = "CEP inexistente.";
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(restauranteAntigo);
        when(consultarEnderecoPorCepInterface.consultaPorCep(anyString())).thenReturn(enderecoSemCep);

        assertThatThrownBy(() -> useCase.atualizarRestaurante(id, restauranteNovo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);

        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(consultarEnderecoPorCepInterface, times(1)).consultaPorCep(anyString());
        verify(atualizarRestauranteInterface, never()).atualizarRestaurante(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_NomeNaoInformado() {
        var restauranteAntigo = RestauranteHelper.gerarRestauranteValido();
        var restauranteNovo = RestauranteHelper.gerarRestauranteValido();
        var id = 1L;
        restauranteAntigo.setRestauranteId(id);
        restauranteNovo.getEndereco().setCep("83010680");
        restauranteNovo.setCapacidade(150);
        restauranteNovo.setNome(null);
        var mensagemException = "O nome do restaurante deve ser informado.";
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(restauranteAntigo);
        when(consultarEnderecoPorCepInterface.consultaPorCep(anyString())).thenReturn(RestauranteHelper.enderecoBuilder());

        assertThatThrownBy(() -> useCase.atualizarRestaurante(id, restauranteNovo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);

        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(consultarEnderecoPorCepInterface, times(1)).consultaPorCep(anyString());
        verify(atualizarRestauranteInterface, never()).atualizarRestaurante(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_CapacidadeNaoInformado() {
        var restauranteAntigo = RestauranteHelper.gerarRestauranteValido();
        var restauranteNovo = RestauranteHelper.gerarRestauranteValido();
        var id = 1L;
        restauranteAntigo.setRestauranteId(id);
        restauranteNovo.getEndereco().setCep("83010680");
        restauranteNovo.setCapacidade(null);
        var mensagemException = "A capacidade do restaurante deve ser informada.";
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(restauranteAntigo);
        when(consultarEnderecoPorCepInterface.consultaPorCep(anyString())).thenReturn(RestauranteHelper.enderecoBuilder());

        assertThatThrownBy(() -> useCase.atualizarRestaurante(id, restauranteNovo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);

        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(consultarEnderecoPorCepInterface, times(1)).consultaPorCep(anyString());
        verify(atualizarRestauranteInterface, never()).atualizarRestaurante(any(Restaurante.class));
    }

}