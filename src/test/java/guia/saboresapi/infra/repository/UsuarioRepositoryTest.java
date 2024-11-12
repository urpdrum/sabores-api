package guia.saboresapi.infra.repository;


import guia.saboresapi.infra.entity.UsuarioEntity;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UsuarioRepositoryTest {
  @Mock
  public UsuarioRepository repository;

  @Nested
  class CadastraUsuario {
    @Test
    void devePermitirCadastrarUsuarioEntity() {
      // Arrange
      UsuarioEntity entidade = UsuarioHelper.gerarUsuarioEntity();

      when(repository.save(any(UsuarioEntity.class)))
          .thenAnswer(answer -> answer.getArgument(0));


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

      verify(repository, times(1)).save(any(UsuarioEntity.class));
    }
  }

  @Nested
  class BuscarUsuario {
    @Test
    void devePermitirBuscarUsuarioEntityPorId() {
      // Arrange
      Long id = 1L;

      UsuarioEntity entidade = UsuarioHelper.gerarUsuarioEntity();
      entidade.setUsuarioId(id);

      when(repository.findById(id))
          .thenReturn(Optional.of(entidade));

      // Act
      Optional<UsuarioEntity> usuarioEntityOptional = repository.findById(id);

      // Assert
      assertThat(usuarioEntityOptional)
          .isPresent()
          .containsSame(entidade);

      usuarioEntityOptional.ifPresent(usuarioEntity -> {
        assertThat(usuarioEntity.getUsuarioId())
            .isPositive();

        assertThat(usuarioEntity.getNome())
            .isEqualTo(entidade.getNome());

        assertThat(usuarioEntity.getSenha())
            .isEqualTo(entidade.getSenha());

        assertThat(usuarioEntity.getEmail())
            .isEqualTo(entidade.getEmail());
      });

      verify(repository, times(1)).findById(any(Long.class));
    }
  }

  @Nested
  class DeletarUsuario {
    @Test
    void devePermitirDeletarUsuarioEntity() {
      // Arrange
      Long id = 1L;

      doNothing().when(repository).deleteById(any(Long.class));

      // Act
      repository.deleteById(id);

      // Assert
      verify(repository, times(1)).deleteById(any(Long.class));
    }
  }


  @Nested
  class ListarUsuario {
    @Test
    void devePermitirListarUsuarioEntity() {
      // Arrange
      UsuarioEntity entidade1 = UsuarioHelper.gerarUsuarioEntity();
      UsuarioEntity entidade2 = UsuarioHelper.gerarUsuarioEntity();

      List<UsuarioEntity> usuarioEntities = Arrays.asList(entidade1, entidade2);

      when(repository.findAll())
          .thenReturn(usuarioEntities);

      // Act
      List<UsuarioEntity> usuarioEntityList = repository.findAll();

      // Assert
      assertThat(usuarioEntityList)
          .hasSize(2)
          .containsExactlyInAnyOrder(entidade1, entidade2);

      verify(repository, times(1)).findAll();
    }
  }
}
