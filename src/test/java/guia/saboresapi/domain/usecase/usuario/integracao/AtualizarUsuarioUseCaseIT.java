package guia.saboresapi.domain.usecase.usuario.integracao;


import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.exception.usuario.UsuarioNotFoundException;
import guia.saboresapi.domain.usecase.usuario.AtualizarUsuarioUseCase;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AtualizarUsuarioUseCaseIT {
  @Autowired
  private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

  @Test
  void atualizarUsuario() {
    // Arrange
    Usuario usuarioBody = UsuarioHelper.gerarUsuarioValido();
    Long id = 5L;

    // Act
    Usuario usuarioAtualizado = atualizarUsuarioUseCase.atualizarUsuario(id, usuarioBody);

    // Assert
    assertThat(usuarioAtualizado)
        .isNotNull()
        .isInstanceOf(Usuario.class);

    assertThat(usuarioAtualizado.getNome())
        .isEqualTo(usuarioBody.getNome());

    assertThat(usuarioAtualizado.getSenha())
        .isEqualTo(usuarioBody.getSenha());

    assertThat(usuarioAtualizado.getTelefone())
        .isEqualTo(usuarioBody.getTelefone());

    assertThat(usuarioAtualizado.getUsuarioId())
        .isNotNull()
        .isPositive()
        .isEqualTo(id);

    assertThat(usuarioAtualizado.getEmail())
        .isEqualTo("maria.souza@email.com");
  }

  @Test
  void deveGerarExcecao_QuandoAtualizarUsuario_IdUsuarioNaoExiste() {
    // Arrange
    Long id = 100L;
    Usuario usuario = UsuarioHelper.gerarUsuarioValido();

    // Act & Assert
    assertThatThrownBy(() -> atualizarUsuarioUseCase.atualizarUsuario(id, usuario))
        .isNotNull()
        .isInstanceOf(UsuarioNotFoundException.class)
            .hasMessage("Usuário de id: " + id + " não encontrado.");
  }
}
