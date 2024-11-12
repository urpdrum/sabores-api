package guia.saboresapi.domain.usecase.reserva.integracao;


import guia.saboresapi.domain.exception.reserva.ReservaNotFoundException;
import guia.saboresapi.domain.usecase.reserva.DeletarReservaUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class DeletarReservaUseCaseIT {

    @Autowired
    private DeletarReservaUseCase deletarReservaUseCase;

    @Test
    void devePermitirDeletarReserva() {
        var id = 4L;

        var reservaDeletada = deletarReservaUseCase.deletarReserva(id);
        assertThat(reservaDeletada).isTrue();
    }

    @Test
    void deveGerarExcecao_QuandoDeletarReserva_IdNaoEncontrado() {
        var id = 123456798L;
        var mensagemException = "Reserva de id: " + id + " não encontrada.";

        assertThatThrownBy(() -> deletarReservaUseCase.deletarReserva(id))
                .isInstanceOf(ReservaNotFoundException.class)
                .hasMessage(mensagemException);
    }
}
