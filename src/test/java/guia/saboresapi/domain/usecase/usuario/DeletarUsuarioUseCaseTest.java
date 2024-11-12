package guia.saboresapi.domain.usecase.usuario;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.exception.usuario.UsuarioNotFoundException;
import guia.saboresapi.domain.gateway.usuario.DeletarUsuarioInterface;
import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacoesPorUsuarioUseCase;
import guia.saboresapi.domain.usecase.avaliacao.DeletarAvaliacaoUseCase;
import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorUsuarioUseCase;
import guia.saboresapi.domain.usecase.reserva.DeletarReservaUseCase;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import guia.saboresapi.utils.reserva.ReservaHelper;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarUsuarioUseCaseTest {
  private DeletarUsuarioUseCase deletarUsuarioUseCase;

  @Mock
  private DeletarUsuarioInterface deletarUsuarioInterface;
  @Mock
  private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
  @Mock
  private BuscarAvaliacoesPorUsuarioUseCase buscarAvaliacoesPorUsuarioUseCase;
  @Mock
  private DeletarAvaliacaoUseCase deletarAvaliacaoUseCase;
  @Mock
  private BuscarReservasPorUsuarioUseCase buscarReservasPorUsuarioUseCase;
  @Mock
  private DeletarReservaUseCase deletarReservaUseCase;

  @BeforeEach
  public void setUp() {
    this.deletarUsuarioUseCase = new DeletarUsuarioUseCase(
        deletarUsuarioInterface,
        buscarUsuarioPorIdUseCase,
        buscarAvaliacoesPorUsuarioUseCase,
        deletarAvaliacaoUseCase,
        buscarReservasPorUsuarioUseCase,
        deletarReservaUseCase
    );
  }

  @Test
  void deveDeletarUsuario_ComReserva() {
    Reserva reserva1 = ReservaHelper.gerarReserva();
    Reserva reserva2 = ReservaHelper.gerarReserva();
    Reserva reserva3 = ReservaHelper.gerarReserva();
    reserva1.setReservaId(1L);
    reserva2.setReservaId(2L);
    reserva3.setReservaId(3L);
    List<Reserva> reservas = List.of(reserva1, reserva2, reserva3);

    Long id = 1L;
    Usuario usuarioBuscado = UsuarioHelper.gerarUsuarioValido();
    usuarioBuscado.setUsuarioId(id);

    when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(any(Long.class))).thenReturn(usuarioBuscado);
    when(deletarReservaUseCase.deletarReserva(any(Long.class))).thenReturn(true);
    when(buscarReservasPorUsuarioUseCase.buscarReservasPorUsuario(any(Long.class))).thenReturn(reservas);
    when(deletarUsuarioInterface.deletarUsuario(any(Long.class))).thenReturn(true);

    boolean usuarioDeletado = deletarUsuarioUseCase.deletarUsuario(id);

    assertThat(usuarioDeletado).isTrue();

    verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(any(Long.class));
    verify(buscarReservasPorUsuarioUseCase, times(1)).buscarReservasPorUsuario(any(Long.class));
    verify(deletarReservaUseCase, times(3)).deletarReserva(any(Long.class));
    verify(deletarUsuarioInterface, times(1)).deletarUsuario(any(Long.class));

  }

  @Test
  void deveDeletarUsuario_ComAvaliacao() {
    Avaliacao avaliacao1 = AvaliacaoHelper.gerarAvaliacao();
    Avaliacao avaliacao2 = AvaliacaoHelper.gerarAvaliacao();
    Avaliacao avaliacao3 = AvaliacaoHelper.gerarAvaliacao();
    avaliacao1.setAvaliacaoId(1L);
    avaliacao2.setAvaliacaoId(2L);
    avaliacao3.setAvaliacaoId(3L);
    List<Avaliacao> avaliacoes = List.of(avaliacao1, avaliacao2, avaliacao3);
    Long id = 1L;
    Usuario usuarioBuscado = UsuarioHelper.gerarUsuarioValido();
    usuarioBuscado.setUsuarioId(id);

    when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(any(Long.class))).thenReturn(usuarioBuscado);
    when(deletarUsuarioInterface.deletarUsuario(any(Long.class))).thenReturn(true);
    when(buscarAvaliacoesPorUsuarioUseCase.buscarAvaliacoesPorUsuario(any(Long.class))).thenReturn(avaliacoes);
    when(deletarAvaliacaoUseCase.deletarAvaliacao(any(Long.class))).thenReturn(true);

    //Act
    boolean usuarioDeletado = deletarUsuarioUseCase.deletarUsuario(id);

    // Assert
    assertThat(usuarioDeletado).isTrue();

    verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(any(Long.class));
    verify(deletarUsuarioInterface, times(1)).deletarUsuario(any(Long.class));
    verify(buscarAvaliacoesPorUsuarioUseCase, times(1)).buscarAvaliacoesPorUsuario(any(Long.class));
    verify(deletarAvaliacaoUseCase, times(3)).deletarAvaliacao(any(Long.class));
  }

  @Test
  void deveDeletarUsuario_SemAvaliacao() {
    // Arrange
    List<Avaliacao> avaliacoes = List.of();
    Long id = 1L;
    Usuario usuarioBuscado = UsuarioHelper.gerarUsuarioValido();
    usuarioBuscado.setUsuarioId(id);

    when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(any(Long.class))).thenReturn(usuarioBuscado);
    when(deletarUsuarioInterface.deletarUsuario(any(Long.class))).thenReturn(true);
    when(buscarAvaliacoesPorUsuarioUseCase.buscarAvaliacoesPorUsuario(any(Long.class))).thenReturn(avaliacoes);

    //Act
    boolean usuarioDeletado = deletarUsuarioUseCase.deletarUsuario(id);

    // Assert
    assertThat(usuarioDeletado).isTrue();

    verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(any(Long.class));
    verify(deletarUsuarioInterface, times(1)).deletarUsuario(any(Long.class));
    verify(buscarAvaliacoesPorUsuarioUseCase, times(1)).buscarAvaliacoesPorUsuario(any(Long.class));
    verify(deletarAvaliacaoUseCase, never()).deletarAvaliacao(any(Long.class));
  }

  @Test
  void deveGerarExcecao_QuandoExcluirUsuario_IdDoUsuarioNaoExiste() {
    // Arrange
    Long id = 2L;

    when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(any(Long.class)))
        .thenThrow(new UsuarioNotFoundException("Usuário de id: " + id + " não encontrado."));

    // Act && Assert
    assertThatThrownBy(() -> deletarUsuarioUseCase.deletarUsuario(id))
        .isInstanceOf(UsuarioNotFoundException.class)
        .hasMessage("Usuário de id: " + id + " não encontrado.");

    verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(any(Long.class));
    verify(buscarAvaliacoesPorUsuarioUseCase, never()).buscarAvaliacoesPorUsuario(any(Long.class));
    verify(deletarAvaliacaoUseCase, never()).deletarAvaliacao(any(Long.class));
    verify(deletarUsuarioInterface, never()).deletarUsuario(any(Long.class));
  }
}
