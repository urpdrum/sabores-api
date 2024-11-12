package guia.saboresapi.domain.usecase.usuario.integracao;

import com.fiap.tc.restaurantes.domain.entity.Usuario;
import com.fiap.tc.restaurantes.domain.usecase.usuario.ListarUsuariosUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ListarUsuarioUseCaseIT {
  @Autowired
  private ListarUsuariosUseCase listarUsuariosUseCase;

  @Test
  void devePermitirListarUsuarios() {
    // Act
    List<Usuario> listaUsuarios = listarUsuariosUseCase.listarUsuarios();

    // Assert
    assertThat(listaUsuarios)
        .isNotNull()
        .isNotEmpty()
        .hasSizeGreaterThan(9);
  }
}
