package guia.saboresapi.domain.usecase.usuario;

import com.fiap.tc.restaurantes.domain.entity.Usuario;
import com.fiap.tc.restaurantes.domain.exception.usuario.UsuarioNotFoundException;
import com.fiap.tc.restaurantes.domain.gateway.usuario.AtualizarUsuarioInterface;
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
class AtualizarUsuarioUseCaseTest {

  private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

  @Mock
  private AtualizarUsuarioInterface atualizarUsuarioInterface;

  @Mock
  private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

  @BeforeEach
  void setUp() {
    this.atualizarUsuarioUseCase = new AtualizarUsuarioUseCase(atualizarUsuarioInterface, buscarUsuarioPorIdUseCase);
  }

  @Test
  void deveAtualizarUsuario() {
    // Arrange
    Usuario usuarioBuscado = UsuarioHelper.gerarUsuarioValido();
    Usuario usuarioBody = UsuarioHelper.gerarUsuarioValido();
    Long id = 1L;

    usuarioBody.setTelefone("00000000000");
    usuarioBody.setNome("Exemplo");
    usuarioBody.setSenha("Aa@12345");

    doReturn(usuarioBuscado)
        .when(buscarUsuarioPorIdUseCase).buscarUsuarioPorId(any(Long.class));
    when(atualizarUsuarioInterface.atualizarUsuario(any(Long.class), any(Usuario.class))).thenAnswer(answer -> answer.getArgument(1));

    // Act
    Usuario usuarioAtualizado = atualizarUsuarioUseCase.atualizarUsuario(id, usuarioBody);

    // Assert
    assertThat(usuarioAtualizado)
        .isNotNull()
        .isInstanceOf(Usuario.class);

    assertThat(usuarioAtualizado.getTelefone())
        .isNotEmpty()
        .isEqualTo("00000000000");

    assertThat(usuarioAtualizado.getNome())
        .isNotEmpty()
        .isEqualTo("Exemplo");

    assertThat(usuarioAtualizado.getSenha())
        .isNotEmpty()
        .isEqualTo("Aa@12345");

    assertThat(usuarioAtualizado.getUsuarioId())
        .isEqualTo(usuarioBuscado.getUsuarioId());

    assertThat(usuarioAtualizado.getEmail())
        .isEqualTo(usuarioBuscado.getEmail());

    verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(any(Long.class));
    verify(atualizarUsuarioInterface, times(1)).atualizarUsuario(any(Long.class), any(Usuario.class));
  }

  @Test
  void deveGerarExcecao_QuandoAtualizarUsuario_IdDoUsuarioNaoExiste() {
    // Arrange
    Usuario usuarioBody = UsuarioHelper.gerarUsuarioValido();
    Long id = 2L;

    usuarioBody.setTelefone("00000000000");
    usuarioBody.setNome("Exemplo");
    usuarioBody.setSenha("Aa@12345");

    when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(any(Long.class)))
        .thenThrow(new UsuarioNotFoundException("Usuário de id: " + id + " não encontrado."));

    // Act & Assert
    assertThatThrownBy(() -> atualizarUsuarioUseCase.atualizarUsuario(id, usuarioBody))
        .isInstanceOf(UsuarioNotFoundException.class)
        .hasMessage("Usuário de id: " + id + " não encontrado.");

    verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(any(Long.class));
    verify(atualizarUsuarioInterface, never()).atualizarUsuario(any(Long.class), any(Usuario.class));
  }
}
