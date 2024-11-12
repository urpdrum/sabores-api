package guia.saboresapi.infra.repository.integracao;



import guia.saboresapi.infra.entity.MesaEntity;
import guia.saboresapi.infra.repository.MesaRepository;
import guia.saboresapi.utils.mesa.MesaHelper;
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
class MesaRepositoryIT {
  @Autowired
  private MesaRepository mesaRepository;

  @Test
  void devePermitirCadastrarMesaEntity() {
    // Arrange
    MesaEntity mesa = MesaHelper.gerarMesaEntity();

    // Act
    MesaEntity mesaSalva = mesaRepository.save(mesa);

    // Assert
    assertThat(mesaSalva)
        .isNotNull()
        .isInstanceOf(MesaEntity.class)
        .isEqualTo(mesa);

    assertThat(mesaSalva.getQuantidadeAssentos())
        .isEqualTo(mesa.getQuantidadeAssentos());

    assertThat(mesaSalva.getRestauranteEntity().getRestauranteId())
        .isEqualTo(mesa.getRestauranteEntity().getRestauranteId());
  }

  @Test
  void devePermitirDeletarMesaEntity() {
    // Arrange
    Long id = 4L;

    // Act
    mesaRepository.deleteById(id);
    Optional<MesaEntity> mesa = mesaRepository.findById(id);

    // Assert
     assertThat(mesa)
         .isEmpty();

  }

  @Test
  void devePermitirBuscarMesaPorId() {
    // Arrange
    Long id = 1L;

    // Act
    Optional<MesaEntity> mesaBuscada = mesaRepository.findById(id);

    // Assert
    assertThat(mesaBuscada)
        .isPresent();

    mesaBuscada.ifPresent(mesaPresente -> {
      assertThat(mesaPresente.getQuantidadeAssentos())
          .isEqualTo(4);

      assertThat(mesaPresente.getRestauranteEntity().getRestauranteId())
          .isEqualTo(1);
    });

  }

  @Test
  void devePermitirListarMesasPorRestaurante() {
    // Arrange
    Long restauranteId = 1L;

    // Act
    List<MesaEntity> mesas = mesaRepository.findByRestaurante(restauranteId);

    // Assert
    assertThat(mesas)
        .isNotEmpty()
        .hasSize(5);

    mesas.forEach(mesa -> assertThat(mesa.getRestauranteEntity().getRestauranteId()).isEqualTo(restauranteId));
  }
}
