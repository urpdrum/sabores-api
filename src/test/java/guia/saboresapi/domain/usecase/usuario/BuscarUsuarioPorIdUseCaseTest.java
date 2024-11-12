package guia.saboresapi.domain.usecase.usuario;

import com.fiap.tc.restaurantes.domain.entity.Usuario;
import com.fiap.tc.restaurantes.domain.exception.usuario.UsuarioNotFoundException;
import com.fiap.tc.restaurantes.domain.gateway.usuario.BuscarUsuarioPorIdInterface;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarUsuarioPorIdUseCaseTest {
  private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

  @Mock
  private BuscarUsuarioPorIdInterface buscarUsuarioPorIdInterface;

  @BeforeEach
  public void setUp() {
    this.buscarUsuarioPorIdUseCase = new BuscarUsuarioPorIdUseCase(buscarUsuarioPorIdInterface);
  }

  @Test
  void deveBuscarUsuarioPorId() {
    // Arrange
    Usuario usuario = UsuarioHelper.gerarUsuarioValido();
    Long id = 1L;
    usuario.setUsuarioId(id);

    when(buscarUsuarioPorIdInterface.buscarUsuarioPorId(id)).thenReturn(usuario);

    // Act
    Usuario usuarioBuscado = buscarUsuarioPorIdUseCase.buscarUsuarioPorId(id);

    // Assert
    assertThat(usuarioBuscado)
        .isNotNull()
        .isInstanceOf(Usuario.class)
        .isEqualTo(usuario);
  }

  @Test
  void deveGerarExcecao_QuandoBuscarUsuario_IdNaoExiste() {
    // Arrange
    Long id = 100L;

    when(buscarUsuarioPorIdInterface.buscarUsuarioPorId(any(Long.class))).thenThrow(new UsuarioNotFoundException("Usuário de id: " + id + " não encontrado."));

    // Act
    assertThatThrownBy(() -> buscarUsuarioPorIdUseCase.buscarUsuarioPorId(id))
        .isNotNull()
        .isInstanceOf(UsuarioNotFoundException.class)
        .hasMessage("Usuário de id: " + id + " não encontrado.");

    verify(buscarUsuarioPorIdInterface, times(1)).buscarUsuarioPorId(any(Long.class));
  }
}
