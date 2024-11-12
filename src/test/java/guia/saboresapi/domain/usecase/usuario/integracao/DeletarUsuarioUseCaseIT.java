package guia.saboresapi.domain.usecase.usuario.integracao;

import com.fiap.tc.restaurantes.domain.exception.usuario.UsuarioNotFoundException;
import com.fiap.tc.restaurantes.domain.usecase.usuario.DeletarUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DeletarUsuarioUseCaseIT {
  @Autowired
  private DeletarUsuarioUseCase deletarUsuarioUseCase;

  @Test
  void devePermitirDeletarUsuario() {
    // Arrange
    Long id = 1L;

    // Act
    boolean usuarioFoiDeletado = deletarUsuarioUseCase.deletarUsuario(id);

    // Assert
    assertThat(usuarioFoiDeletado).isTrue();
  }

  @Test
  void deveGerarExcecao_QuandoDeletarUsuario_IdNaoEncontrado() {
    // Arrange
    Long id = 100L;

    // Act & Assert
    assertThatThrownBy(() -> deletarUsuarioUseCase.deletarUsuario(id))
        .isInstanceOf(UsuarioNotFoundException.class)
        .hasMessage("Usuário de id: " + id + " não encontrado.");

  }
}
