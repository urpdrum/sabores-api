package guia.saboresapi.domain.usecase.usuario;

import com.fiap.tc.restaurantes.domain.entity.Usuario;
import com.fiap.tc.restaurantes.domain.gateway.usuario.CadastrarUsuarioInterface;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarUsuarioUseCaseTest {
  private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

  @Mock
  private CadastrarUsuarioInterface cadastrarUsuarioInterface;

  @BeforeEach
  public void setUp() {
    cadastrarUsuarioUseCase = new CadastrarUsuarioUseCase(cadastrarUsuarioInterface);
  }

  @Test
  void deveCadastrarUsuario() {
    // Arrange
    Usuario usuario = UsuarioHelper.gerarUsuarioValido();

    when(cadastrarUsuarioInterface.cadastrarUsuario(any(Usuario.class))).thenReturn(usuario);

    // Act
    Usuario usuarioCadastrado = cadastrarUsuarioUseCase.cadastrarUsuario(usuario);

    // Assert
    assertThat(usuarioCadastrado)
        .isNotNull()
        .isInstanceOf(Usuario.class);

    assertThat(usuarioCadastrado.getNome())
        .isEqualTo(usuario.getNome());
    assertThat(usuarioCadastrado.getEmail())
        .isEqualTo(usuario.getEmail());
    assertThat(usuarioCadastrado.getSenha())
        .isEqualTo(usuario.getSenha());
    assertThat(usuarioCadastrado.getTelefone())
        .isEqualTo(usuario.getTelefone());

    verify(cadastrarUsuarioInterface, times(1)).cadastrarUsuario(any(Usuario.class));
  }
}
