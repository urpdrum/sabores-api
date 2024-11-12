package guia.saboresapi.infra.repository.integracao;



import guia.saboresapi.infra.entity.UsuarioEntity;
import guia.saboresapi.infra.repository.UsuarioRepository;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UsuarioRepositoryIT {
  @Autowired
  UsuarioRepository repository;

  @Nested
  class CadastraUsuario {
    @Test
    void devePermitirCadastrarUsuarioEntity() {
      // Arrange
      UsuarioEntity entidade = UsuarioHelper.gerarUsuarioEntity();


      // Act
      UsuarioEntity usuarioSalvo = repository.save(entidade);

      // Assert
      assertThat(usuarioSalvo)
          .isNotNull()
          .isInstanceOf(UsuarioEntity.class)
          .isEqualTo(entidade);

      assertThat(usuarioSalvo.getNome())
          .isEqualTo(entidade.getNome());

      assertThat(usuarioSalvo.getSenha())
          .isEqualTo(entidade.getSenha());


      assertThat(usuarioSalvo.getEmail())
          .isEqualTo(entidade.getEmail());

    }
  }

  @Nested
  class BuscarUsuario {
    @Test
    void devePermitirBuscarUsuarioEntityPorId() {
      // Arrange
      Long id = 2L;

      // Act
      Optional<UsuarioEntity> usuarioEntityOptional = repository.findById(id);

      // Assert

      assertThat(usuarioEntityOptional).isPresent();

      usuarioEntityOptional.ifPresent(usuarioEntity -> {
        assertThat(usuarioEntity.getUsuarioId())
            .isEqualTo(id);
      });
    }
  }

  @Nested
  class DeletarUsuario {
    @Test
    void devePermitirDeletarUsuarioEntity() {
      // Arrange
      Long id = 3L;

      // Act
      repository.deleteById(id);
      Optional<UsuarioEntity> usuarioExcluido = repository.findById(id);

      // Assert
      assertThat(usuarioExcluido).isEmpty();
    }
  }

  @Nested
  class ListarUsuario {
    @Test
    void devePermitirListarUsuarioEntity() {
      // Act
      List<UsuarioEntity> usuarioEntityList = repository.findAll();

      // Assert
      assertThat(usuarioEntityList)
          .hasSizeGreaterThan(9);

    }
  }
}
