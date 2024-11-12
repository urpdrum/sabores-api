package guia.saboresapi.infra.repository;

=
import guia.saboresapi.infra.entity.MesaEntity;
import guia.saboresapi.utils.mesa.MesaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class MesaRepositoryTest {
    @Mock
    private MesaRepository mesaRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
            openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCadastrarMesa() {
        // Arrange
        MesaEntity mesa = MesaHelper.gerarMesaEntity();

        when(mesaRepository.save(any(MesaEntity.class))).thenAnswer(answer -> answer.getArgument(0));

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

        verify(mesaRepository, times(1)).save(any(MesaEntity.class));
    }

    @Test
    void devePermitirBuscarMesaPorId() {
        // Arrange
        Long id = 1L;
        MesaEntity mesa = MesaHelper.gerarMesaEntity();
        mesa.setMesaId(id);

        when(mesaRepository.findById(any(Long.class))).thenReturn(Optional.of(mesa));

        // Act
        Optional<MesaEntity> mesaBuscada = mesaRepository.findById(id);

        // Assert
        assertThat(mesaBuscada)
                .isPresent();

        mesaBuscada.ifPresent(mesaPresente -> {
          assertThat(mesaPresente.getQuantidadeAssentos())
              .isEqualTo(mesa.getQuantidadeAssentos());

          assertThat(mesaPresente.getRestauranteEntity().getRestauranteId())
              .isEqualTo(mesa.getRestauranteEntity().getRestauranteId());
        });

        verify(mesaRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void devePermitirListarMesasPorRestaurante() {
        // Arrange
        Long restauranteId = 1L;
        MesaEntity mesa = MesaHelper.gerarMesaEntity();
        MesaEntity mesa2 = MesaHelper.gerarMesaEntity();
        mesa.getRestauranteEntity().setRestauranteId(restauranteId);
        mesa2.getRestauranteEntity().setRestauranteId(restauranteId);
        List<MesaEntity> listaMesas = List.of(mesa, mesa2);

        when(mesaRepository.findByRestaurante(any(Long.class)))
                .thenReturn(listaMesas);

        // Act
        List<MesaEntity> mesas = mesaRepository.findByRestaurante(restauranteId);

        // Arrange
        assertThat(mesas)
                .isNotEmpty()
                .hasSize(2)
                .containsExactly(mesa, mesa2);

        assertThat(mesas.get(0).getRestauranteEntity().getRestauranteId())
                .isEqualTo(mesa.getRestauranteEntity().getRestauranteId());

        assertThat(mesas.get(0).getQuantidadeAssentos())
                .isEqualTo(mesa.getQuantidadeAssentos());

        assertThat(mesas.get(1).getRestauranteEntity().getRestauranteId())
                .isEqualTo(mesa2.getRestauranteEntity().getRestauranteId());

        assertThat(mesas.get(1).getQuantidadeAssentos())
                .isEqualTo(mesa2.getQuantidadeAssentos());

        verify(mesaRepository, times(1)).findByRestaurante(any(Long.class));
    }

    @Test
    void devePermitirDeletarMesa() {
        // Arrange
        Long id = 1L;

        doNothing().when(mesaRepository).deleteById(any(Long.class));

        // Act
        mesaRepository.deleteById(id);

        // Assert
        verify(mesaRepository, times(1)).deleteById(any(Long.class));

    }
}
