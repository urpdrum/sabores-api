package guia.saboresapi.infra.repository.integracao;

import guia.saboresapi.domain.enums.StatusReservaEnum;
import guia.saboresapi.infra.entity.ReservaEntity;
import guia.saboresapi.infra.repository.ReservaRepository;
import guia.saboresapi.utils.reserva.ReservaHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ReservaRespositoryIT {

    @Autowired
    private ReservaRepository reservaRepository;

    @Test
    void devePermitirCriarTabela() {
        var totalDeRegistros = reservaRepository.count();
        assertThat(totalDeRegistros).isPositive();
    }

    @Nested
    class CadastrarReserva {

        @Test
        void devePermitirCadastrarReserva() {
            var reserva = ReservaHelper.gerarReservaEntity();

            var reservaCadastrada = reservaRepository.save(reserva);
            assertThat(reservaCadastrada)
                    .isNotNull()
                    .isInstanceOf(ReservaEntity.class)
                    .isEqualTo(reserva);
            assertThat(reservaCadastrada.getReservaId())
                    .isPositive();
        }

    }

    @Nested
    class BuscarReserva {

        @Test
        void devePermitirBuscarReservaPorId() {
            var reservaObtidaOpt = reservaRepository.findById(1L);

            assertThat(reservaObtidaOpt)
                    .isPresent();
            assertThat(reservaObtidaOpt.get().getReservaId()).isEqualTo(1L);
            assertThat(reservaObtidaOpt.get().getUsuarioEntity().getUsuarioId()).isEqualTo(1L);
            assertThat(reservaObtidaOpt.get().getMesaEntity().getMesaId()).isEqualTo(1L);
            assertThat(reservaObtidaOpt.get().getStatus()).isEqualTo(StatusReservaEnum.ATIVA);
            assertThat(reservaObtidaOpt.get().getDataInicio()).isEqualTo("2030-09-10T11:47:37");
            assertThat(reservaObtidaOpt.get().getDataFim()).isEqualTo("2030-09-10T12:47:37");
        }

        @Test
        void devePermitirBuscarReservasPorMesa() {
            var reservaObtidaList = reservaRepository.buscarReservasPorMesa(1L);

            assertThat(reservaObtidaList)
                    .isNotEmpty()
                    .hasSize(2);
        }

        @Test
        void devePermitirBuscarReservasPorMesaEPeriodo() {
            var reservaObtidaList = reservaRepository.buscarReservasPorMesaEPeriodo(1L,
                    LocalDateTime.of(2030,9,10,11,47,37),
                    LocalDateTime.of(2030,9,10,12,47,37));

            assertThat(reservaObtidaList)
                    .isNotEmpty()
                    .hasSize(1);
        }

        @Test
        void devePermitirBuscarReservasPorUsuario() {
            var reservaObtidaList = reservaRepository.buscarReservasPorUsuario(1L);

            assertThat(reservaObtidaList)
                    .isNotEmpty()
                    .hasSize(4);
        }

    }

    @Nested
    class DeletarReserva {

        @Test
        void devePermitirDeletarReserva() {
            reservaRepository.deleteById(3L);
            var reservaObtidaOpt = reservaRepository.findById(3L);
            assertThat(reservaObtidaOpt).isNotPresent();
        }

    }
}
