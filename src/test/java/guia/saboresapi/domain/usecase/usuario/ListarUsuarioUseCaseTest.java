package guia.saboresapi.domain.usecase.usuario;

import com.fiap.tc.restaurantes.domain.entity.Usuario;
import com.fiap.tc.restaurantes.domain.gateway.usuario.ListarUsuariosInterface;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarUsuarioUseCaseTest {
  private ListarUsuariosUseCase listarUsuariosUseCase;

  @Mock
  private ListarUsuariosInterface listarUsuariosInterface;

  @BeforeEach
  public void setUp() {
    listarUsuariosUseCase = new ListarUsuariosUseCase(listarUsuariosInterface);
  }

  @Test
  void deveListarUsuarios() {
    // Arrange
    Usuario usuario1 = UsuarioHelper.gerarUsuarioValido();
    Usuario usuario2 = UsuarioHelper.gerarUsuarioValido();
    List<Usuario> listaDeUsuario = List.of(usuario1, usuario2);

    when(listarUsuariosInterface.listarUsuarios()).thenReturn(listaDeUsuario);

    // Act
    List<Usuario> usuarios = listarUsuariosUseCase.listarUsuarios();

    // Assert
    assertThat(usuarios)
        .isNotNull()
        .isNotEmpty()
        .hasSizeGreaterThan(1)
        .containsExactlyInAnyOrder(usuario1, usuario2);

    verify(listarUsuariosInterface, times(1)).listarUsuarios();
  }
}
