package guia.saboresapi.domain.usecase.reserva.integracao;

import com.fiap.tc.restaurantes.domain.usecase.reserva.BuscarReservasPorUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class BuscarReservasPorUsuarioUseCaseIT {

    @Autowired
    private BuscarReservasPorUsuarioUseCase buscarReservasPorUsuarioUseCase;

    @Test
    void devePermitirBuscarReservasPorUsuario() {
        var usuarioId = 1L;

        var listObtida = buscarReservasPorUsuarioUseCase.buscarReservasPorUsuario(usuarioId);

        assertThat(listObtida)
                .isNotEmpty()
                .hasSize(4);
    }
}
