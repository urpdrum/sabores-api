package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.exception.reserva.ReservaNotFoundException;
import guia.saboresapi.domain.gateway.reserva.DeletarReservaInterface;
import guia.saboresapi.utils.reserva.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DeletarReservaUseCaseTest {

    private DeletarReservaUseCase deletarReservaUseCase;

    @Mock
    private DeletarReservaInterface deletarReservaInterface;

    @Mock
    private BuscarReservaPorIdUseCase buscarReservaPorIdUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        deletarReservaUseCase = new DeletarReservaUseCase(buscarReservaPorIdUseCase, deletarReservaInterface);
    }

    @AfterEach
    void tearDown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirDeletarReserva() {
        var id = 1L;
        when(buscarReservaPorIdUseCase.buscarReservaPorId(anyLong())).thenReturn(ReservaHelper.gerarReserva());
        when(deletarReservaInterface.deletarReserva(anyLong())).thenReturn(true);

        var reservaDeletada = deletarReservaUseCase.deletarReserva(id);

        assertThat(reservaDeletada).isTrue();
        verify(buscarReservaPorIdUseCase, times(1)).buscarReservaPorId(anyLong());
        verify(deletarReservaInterface, times(1)).deletarReserva(anyLong());
    }

    @Test
    void deveGerarExcecao_QuandoDeletarReserva_IdNaoEncontrado() {
        var id = 1L;
        var mensagemException = "Reserva de id: " + id + " não encontrada.";
        when(buscarReservaPorIdUseCase.buscarReservaPorId(anyLong())).thenThrow(new ReservaNotFoundException(mensagemException));
        when(deletarReservaInterface.deletarReserva(anyLong())).thenReturn(true);

        assertThatThrownBy(() -> deletarReservaUseCase.deletarReserva(id))
                .isInstanceOf(ReservaNotFoundException.class)
                        .hasMessage(mensagemException);

        verify(buscarReservaPorIdUseCase, times(1)).buscarReservaPorId(anyLong());
        verify(deletarReservaInterface, never()).deletarReserva(anyLong());
    }
}
