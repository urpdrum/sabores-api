package guia.saboresapi.infra.repository;


import guia.saboresapi.infra.entity.ReservaEntity;
import guia.saboresapi.utils.reserva.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservaRepositoryTest {

    @Mock
    private ReservaRepository reservaRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class CadastrarReserva {

        @Test
        void devePermitirCadastrarReserva() {
            var reserva = ReservaHelper.gerarReservaEntity();
            when(reservaRepository.save(any())).thenReturn(reserva);

            var reservaCadastrada = reservaRepository.save(reserva);
            assertThat(reservaCadastrada)
                    .isNotNull()
                    .isInstanceOf(ReservaEntity.class)
                    .isEqualTo(reserva);
            verify(reservaRepository, times(1)).save(reserva);
        }

    }

    @Nested
    class BuscarReserva {

        @Test
        void devePermitirBuscarReservaPorId() {
            var reserva = ReservaHelper.gerarReservaEntity();
            when(reservaRepository.findById(anyLong())).thenReturn(Optional.of(reserva));
            var reservaObtidaOpt = reservaRepository.findById(1L);

            assertThat(reservaObtidaOpt)
                    .isPresent()
                    .contains(reserva);
            verify(reservaRepository, times(1)).findById(anyLong());
        }

        @Test
        void devePermitirBuscarReservasPorMesa() {
            var reserva = ReservaHelper.gerarReservaEntity();
            when(reservaRepository.buscarReservasPorMesa(anyLong())).thenReturn(Arrays.asList(reserva));

            var reservaObtida = reservaRepository.buscarReservasPorMesa(1L);
            assertThat(reservaObtida)
                    .hasSize(1)
                    .containsExactlyInAnyOrder(reserva);
            verify(reservaRepository,times(1)).buscarReservasPorMesa(anyLong());
        }

        @Test
        void devePermitirBuscarReservasPorMesaEPeriodo() {
            var reserva = ReservaHelper.gerarReservaEntity();
            when(reservaRepository.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(Arrays.asList(reserva));

            var reservaObtida = reservaRepository.buscarReservasPorMesaEPeriodo(1L, LocalDateTime.now(), LocalDateTime.now());
            assertThat(reservaObtida)
                    .hasSize(1)
                    .containsExactlyInAnyOrder(reserva);
            verify(reservaRepository, times(1)).buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
        }

        @Test
        void devePermitirBuscarReservasPorUsuario() {
            var reserva = ReservaHelper.gerarReservaEntity();
            when(reservaRepository.buscarReservasPorUsuario(anyLong())).thenReturn(Arrays.asList(reserva));

            var reservaObtida = reservaRepository.buscarReservasPorUsuario(1L);
            assertThat(reservaObtida)
                    .hasSize(1)
                    .containsExactlyInAnyOrder(reserva);
            verify(reservaRepository,times(1)).buscarReservasPorUsuario(anyLong());
        }

    }

    @Nested
    class DeletarReserva {

        @Test
        void devePermitirDeletarReserva() {
            doNothing().when(reservaRepository).deleteById(anyLong());
            reservaRepository.deleteById(1L);
            verify(reservaRepository, times(1)).deleteById(anyLong());
        }

    }

}
