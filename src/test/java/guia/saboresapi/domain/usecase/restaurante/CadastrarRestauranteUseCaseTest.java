package guia.saboresapi.domain.usecase.restaurante;


import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.CadastrarRestauranteInterface;
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

class CadastrarRestauranteUseCaseTest {

    private CadastrarRestauranteUseCase useCase;

    @Mock
    private CadastrarRestauranteInterface cadastrarRestauranteInterface;

    @Mock
    private ConsultarEnderecoPorCepInterface consultarEnderecoPorCepInterface;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new CadastrarRestauranteUseCase(cadastrarRestauranteInterface, consultarEnderecoPorCepInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCadastrarRestaurante() {
        // Arrange
        Restaurante entidade = RestauranteHelper.gerarRestauranteValido();

        when(consultarEnderecoPorCepInterface.consultaPorCep(anyString())).thenReturn(RestauranteHelper.enderecoBuilder());
        when(cadastrarRestauranteInterface.cadastrarRestaurante(any(Restaurante.class))).thenReturn(entidade);

        // Act
        Restaurante restauranteSalvo = useCase.cadastrarRestaurante(entidade);

        // Assert
        assertThat(restauranteSalvo)
                .isNotNull()
                .isInstanceOf(Restaurante.class)
                .isEqualTo(entidade);

        assertThat(restauranteSalvo.getNome())
                .isEqualTo(entidade.getNome());

        verify(cadastrarRestauranteInterface, times(1)).cadastrarRestaurante(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarRestaurante_CepNaoEncontrado() {
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var enderecoSemCep = Endereco.builder().cep(null).build();
        var mensagemException = "CEP inexistente.";
        when(consultarEnderecoPorCepInterface.consultaPorCep(anyString())).thenReturn(enderecoSemCep);

        assertThatThrownBy(() -> useCase.cadastrarRestaurante(restaurante))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);

        verify(consultarEnderecoPorCepInterface, times(1)).consultaPorCep(anyString());
        verify(cadastrarRestauranteInterface, never()).cadastrarRestaurante(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarRestaurante_NomeNaoInformado() {
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        restaurante.setNome(null);
        var mensagemException = "O nome do restaurante deve ser informado.";
        when(consultarEnderecoPorCepInterface.consultaPorCep(anyString())).thenReturn(RestauranteHelper.enderecoBuilder());

        assertThatThrownBy(() -> useCase.cadastrarRestaurante(restaurante))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);

        verify(consultarEnderecoPorCepInterface, times(1)).consultaPorCep(anyString());
        verify(cadastrarRestauranteInterface, never()).cadastrarRestaurante(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarRestaurante_CapacidadeNaoInformado() {
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        restaurante.setCapacidade(null);
        var mensagemException = "A capacidade do restaurante deve ser informada.";
        when(consultarEnderecoPorCepInterface.consultaPorCep(anyString())).thenReturn(RestauranteHelper.enderecoBuilder());

        assertThatThrownBy(() -> useCase.cadastrarRestaurante(restaurante))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);

        verify(consultarEnderecoPorCepInterface, times(1)).consultaPorCep(anyString());
        verify(cadastrarRestauranteInterface, never()).cadastrarRestaurante(any(Restaurante.class));
    }
}