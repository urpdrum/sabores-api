package guia.saboresapi.domain.usecase.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.exception.mesa.MesaNotFoundException;
import guia.saboresapi.domain.gateway.mesa.AtualizarMesaInterface;
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

class AtualizarMesaUseCaseTest {
  private AtualizarMesaUseCase atualizarMesaUseCase;

  @Mock
  private AtualizarMesaInterface atualizarMesaInterface;

  @Mock
  private BuscarMesaPorIdUseCase buscarMesaPorId;

  AutoCloseable openMocks;

  @BeforeEach
  void setUp() {
    openMocks = MockitoAnnotations.openMocks(this);
    atualizarMesaUseCase = new AtualizarMesaUseCase(atualizarMesaInterface, buscarMesaPorId);
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Test
  void devePermitirAltualizarMesa() {
    // Arrange
    Long id = 1L;
    Mesa mesa = MesaHelper.gerarMesa();
    Mesa mesaNova = MesaHelper.gerarMesa();
    mesaNova.setQuantidadeAssentos(8);

    when(buscarMesaPorId.buscarMesaPorId(any(Long.class))).thenReturn(mesa);
    when(atualizarMesaInterface.atualizarMesa(any(Mesa.class))).thenReturn(mesaNova);

    // Act
    Mesa mesaAtualizada = atualizarMesaUseCase.atualizarMesa(id, mesa);

    // Assert
    assertThat(mesaAtualizada)
        .isNotNull()
        .isInstanceOf(Mesa.class);

    assertThat(mesaAtualizada.getQuantidadeAssentos())
        .isEqualTo(mesaNova.getQuantidadeAssentos());

    verify(buscarMesaPorId, times(1)).buscarMesaPorId(any(Long.class));
    verify(atualizarMesaInterface, times(1)).atualizarMesa(mesa);
  }

  @Test
  void deveGerarExcecao_QuandoAtualizarMesa_IdDaMesaNaoEncontrado() {
    // Arrange
    Long id = 1L;
    Mesa mesa = MesaHelper.gerarMesa();
    Mesa mesaNova = MesaHelper.gerarMesa();
    mesaNova.setQuantidadeAssentos(8);

    when(buscarMesaPorId.buscarMesaPorId(any(Long.class))).thenThrow(new MesaNotFoundException("Mesa de id: " + id + " não encontrada"));

    // Act && Assert
    assertThatThrownBy(() -> atualizarMesaUseCase.atualizarMesa(id, mesa))
        .isInstanceOf(MesaNotFoundException.class)
        .hasMessage("Mesa de id: " + id + " não encontrada");

    verify(buscarMesaPorId, times(1)).buscarMesaPorId(any(Long.class));
    verify(atualizarMesaInterface, never()).atualizarMesa(any(Mesa.class));
  }
}
