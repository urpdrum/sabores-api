package guia.saboresapi.domain.usecase.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.exception.mesa.MesaNotFoundException;
import guia.saboresapi.domain.gateway.mesa.DeletarMesaInterface;
import guia.saboresapi.utils.mesa.MesaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeletarMesaUseCaseTest {
  private DeletarMesaUseCase deletarMesaUseCase;

  @Mock
  private DeletarMesaInterface deletarMesaInterface;

  @Mock
  private BuscarMesaPorIdUseCase buscarMesaPorIdUseCase;

  AutoCloseable openMocks;

  @BeforeEach
  void setUp() {
    openMocks = MockitoAnnotations.openMocks(this);
    deletarMesaUseCase = new DeletarMesaUseCase(deletarMesaInterface, buscarMesaPorIdUseCase);
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Test
  void devePermitirDeletarMesa() {
    // Arrange
    Long id = 1L;
    Mesa mesaBuscada = MesaHelper.gerarMesa();

    when(buscarMesaPorIdUseCase.buscarMesaPorId(any(Long.class))).thenReturn(mesaBuscada);
    when(deletarMesaInterface.deletarMesa(any(Long.class))).thenReturn(true);

    // Act
    boolean mesaDeletada = deletarMesaUseCase.deletarMesa(id);

    // Assert
    assertThat(mesaDeletada).isTrue();

    verify(buscarMesaPorIdUseCase, times(1)).buscarMesaPorId(any(Long.class));
    verify(deletarMesaInterface, times(1)).deletarMesa(any(Long.class));
  }

  @Test
  void deveGerarExcecao_QuandoDeletarMesa_IdDaMesaNaoExiste() {
    // Arrange
    Long id = 1L;

    when(buscarMesaPorIdUseCase.buscarMesaPorId(any(Long.class))).thenThrow(new MesaNotFoundException("Mesa de id: " + id + " não encontrada"));

    // Act && Assert
    assertThatThrownBy(() -> deletarMesaUseCase.deletarMesa(id))
        .isInstanceOf(MesaNotFoundException.class)
        .hasMessage("Mesa de id: " + id + " não encontrada");

    verify(deletarMesaInterface, never()).deletarMesa(any(Long.class));
    verify(buscarMesaPorIdUseCase, times(1)).buscarMesaPorId(any(Long.class));
  }
}
