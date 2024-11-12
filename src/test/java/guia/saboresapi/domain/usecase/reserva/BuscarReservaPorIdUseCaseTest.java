package guia.saboresapi.domain.usecase.reserva;

import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.exception.reserva.ReservaNotFoundException;
import guia.saboresapi.domain.gateway.reserva.BuscarReservaPorIdInterface;
import guia.saboresapi.utils.reserva.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class BuscarReservaPorIdUseCaseTest {

    private BuscarReservaPorIdUseCase buscarReservaPorIdUseCase;

    @Mock
    private BuscarReservaPorIdInterface buscarReservaPorIdInterface;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        buscarReservaPorIdUseCase = new BuscarReservaPorIdUseCase(buscarReservaPorIdInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirBuscarReservaPorId() {
        var reserva = ReservaHelper.gerarReserva();
        var id = 1L;
        reserva.setReservaId(id);
        when(buscarReservaPorIdInterface.buscarReservaPorId(anyLong())).thenReturn(reserva);

        var reservaObtida = buscarReservaPorIdUseCase.buscarReservaPorId(id);

        assertThat(reservaObtida)
                .isNotNull()
                .isInstanceOf(Reserva.class);
        assertThat(reservaObtida.getReservaId()).isEqualTo(id);
        assertThat(reservaObtida.getUsuario().getUsuarioId()).isEqualTo(reserva.getUsuario().getUsuarioId());
        assertThat(reservaObtida.getMesa().getMesaId()).isEqualTo(reserva.getMesa().getMesaId());
        assertThat(reservaObtida.getDataInicio()).isEqualTo(reserva.getDataInicio());
        assertThat(reservaObtida.getDataFim()).isEqualTo(reserva.getDataFim());
        assertThat(reservaObtida.getStatus()).isEqualTo(reserva.getStatus());
    }

    @Test
    void deveGerarExcecao_QuandoBuscarReservaPorId_IdNaoEncontrado() {
        var id = 1L;
        when(buscarReservaPorIdInterface.buscarReservaPorId(anyLong())).thenReturn(null);

        assertThatThrownBy(() -> buscarReservaPorIdUseCase.buscarReservaPorId(id))
                .isInstanceOf(ReservaNotFoundException.class)
                .hasMessage("Reserva de id: " + id + " não encontrada.");
    }
}
