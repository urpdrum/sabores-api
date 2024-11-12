package guia.saboresapi.domain.usecase.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.ListarRestauranteInterface;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ListarRestauranteUseCaseTest {

    private ListarRestauranteUseCase useCase;

    @Mock
    private ListarRestauranteInterface listarRestauranteInterface;

    AutoCloseable openMocks;


    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new ListarRestauranteUseCase(listarRestauranteInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirListarRestaurantes() {
        // Arrange
        Restaurante entidade1 = RestauranteHelper.gerarRestauranteValido();
        Restaurante entidade2 = RestauranteHelper.gerarRestauranteValido();

        List<Restaurante> restaurantes = Arrays.asList(entidade1, entidade2);

        when(listarRestauranteInterface.listarRestaurantes()).thenReturn(restaurantes);

        //Act
        List<Restaurante> listaRestaurantes = useCase.listarRestaurantes();

        // Assert
        assertThat(listaRestaurantes)
                .hasSize(2)
                .containsExactlyInAnyOrder(entidade1, entidade2);

        verify(listarRestauranteInterface, times(1)).listarRestaurantes();
    }
}