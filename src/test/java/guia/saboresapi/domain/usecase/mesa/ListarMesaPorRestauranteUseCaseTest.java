package guia.saboresapi.domain.usecase.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.gateway.mesa.ListarMesasPorRestauranteInterface;
import guia.saboresapi.utils.mesa.MesaHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ListarMesaPorRestauranteUseCaseTest {
  private ListarMesasPorRestauranteUseCase listarMesasPorRestauranteUseCase;

  @Mock
  private ListarMesasPorRestauranteInterface listarMesasPorRestauranteInterface;

  AutoCloseable openMocks;

  @BeforeEach
  void setUp() {
    openMocks = MockitoAnnotations.openMocks(this);
    listarMesasPorRestauranteUseCase = new ListarMesasPorRestauranteUseCase(listarMesasPorRestauranteInterface);
  }

  @Test
  void devePermitirListarMesasPorRestaurante() {
    // Arrange
    Long restauranteId = 1L;
    Mesa mesa = MesaHelper.gerarMesa();
    Mesa mesa2 = MesaHelper.gerarMesa();
    mesa.getRestaurante().setRestauranteId(restauranteId);
    mesa2.getRestaurante().setRestauranteId(restauranteId);
    List<Mesa> listaMesas = List.of(mesa, mesa2);

    when(listarMesasPorRestauranteInterface.listarMesasPorRestaurante(any(Long.class))).thenReturn(listaMesas);

    // Act
    List<Mesa> mesasPorRestaurantes = listarMesasPorRestauranteUseCase.listarMesasPorRestaurante(restauranteId);

    // Assert

    assertThat(mesasPorRestaurantes)
        .isNotEmpty()
        .hasSizeGreaterThan(1)
        .containsExactlyInAnyOrder(mesa, mesa2);

    assertThat(mesasPorRestaurantes.get(0).getRestaurante().getRestauranteId())
        .isEqualTo(mesa.getRestaurante().getRestauranteId());

    assertThat(mesasPorRestaurantes.get(0).getQuantidadeAssentos())
        .isEqualTo(mesa.getQuantidadeAssentos());

    assertThat(mesasPorRestaurantes.get(1).getRestaurante().getRestauranteId())
        .isEqualTo(mesa2.getRestaurante().getRestauranteId());

    assertThat(mesasPorRestaurantes.get(1).getQuantidadeAssentos())
        .isEqualTo(mesa2.getQuantidadeAssentos());

    verify(listarMesasPorRestauranteInterface, times(1)).listarMesasPorRestaurante(any(Long.class));
  }

  @Test
  void devePermitirMesaPorRestaurante_IdRestauranteNaoExiste() {
    // Arrange
    Long restauranteId = 1L;
    List<Mesa> listaMesas = List.of();

    when(listarMesasPorRestauranteInterface.listarMesasPorRestaurante(any(Long.class))).thenReturn(listaMesas);

    // Act
    List<Mesa> mesasPorRestaurantes = listarMesasPorRestauranteUseCase.listarMesasPorRestaurante(restauranteId);

    assertThat(mesasPorRestaurantes)
        .isEmpty();

    verify(listarMesasPorRestauranteInterface, times(1)).listarMesasPorRestaurante(any(Long.class));
  }
}
