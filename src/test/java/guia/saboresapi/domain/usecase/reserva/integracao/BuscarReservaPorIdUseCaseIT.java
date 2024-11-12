package guia.saboresapi.domain.usecase.reserva.integracao;

import com.fiap.tc.restaurantes.domain.entity.Reserva;
import com.fiap.tc.restaurantes.domain.exception.reserva.ReservaNotFoundException;
import com.fiap.tc.restaurantes.domain.usecase.reserva.BuscarReservaPorIdUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class BuscarReservaPorIdUseCaseIT {

    @Autowired
    private BuscarReservaPorIdUseCase buscarReservaPorIdUseCase;

    @Test
    void devePermitirBuscarReservaPorId() {
        var id = 1L;

        var reservaObtida = buscarReservaPorIdUseCase.buscarReservaPorId(id);

        assertThat(reservaObtida)
                .isNotNull()
                .isInstanceOf(Reserva.class);
        assertThat(reservaObtida.getReservaId()).isEqualTo(id);
    }

    @Test
    void deveGerarExcecao_QuandoBuscarReservaPorId_IdNaoEncontrado() {
        var id = 12345678L;
        var mensagemException = "Reserva de id: " + id + " não encontrada.";

        assertThatThrownBy(() -> buscarReservaPorIdUseCase.buscarReservaPorId(id))
                .isInstanceOf(ReservaNotFoundException.class)
                .hasMessage(mensagemException);
    }
}
