package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.enums.StatusReservaEnum;
import guia.saboresapi.domain.exception.reserva.ReservaNotFoundException;
import guia.saboresapi.domain.gateway.reserva.AtualizarReservaInterface;
import guia.saboresapi.utils.reserva.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AtualizarReservaUseCaseTest {

    private AtualizarReservaUseCase atualizarReservaUseCase;

    @Mock
    private AtualizarReservaInterface atualizarReservaInterface;

    @Mock
    private BuscarReservaPorIdUseCase buscarReservaPorIdUseCase;

    @Mock
    private BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        atualizarReservaUseCase = new AtualizarReservaUseCase(buscarReservaPorIdUseCase,
                atualizarReservaInterface,
                buscarReservasPorMesaEPeriodoUseCase);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirAtualizarReserva() {
        var id = 1L;
        var reservaVelha = ReservaHelper.gerarReserva();
        reservaVelha.setReservaId(id);
        var reservaNova = ReservaHelper.gerarReserva();
        reservaNova.setStatus(StatusReservaEnum.INATIVA);

        when(atualizarReservaInterface.atualizarReserva(any(Reserva.class))).thenAnswer(answer -> answer.getArgument(0));
        when(buscarReservaPorIdUseCase.buscarReservaPorId(any())).thenReturn(reservaVelha);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());

        var reservaAtualizada = atualizarReservaUseCase.atualizarReserva(id, reservaNova);

        assertThat(reservaAtualizada)
                .isInstanceOf(Reserva.class)
                .isNotNull();
        assertThat(reservaAtualizada.getReservaId()).isEqualTo(id);
        assertThat(reservaAtualizada.getStatus()).isEqualTo(reservaNova.getStatus());
        assertThat(reservaAtualizada.getMesa().getMesaId()).isEqualTo(reservaVelha.getMesa().getMesaId());
        assertThat(reservaAtualizada.getUsuario().getUsuarioId()).isEqualTo(reservaVelha.getUsuario().getUsuarioId());
        assertThat(reservaAtualizada.getDataInicio()).isEqualTo(reservaNova.getDataInicio());
        assertThat(reservaAtualizada.getDataFim()).isEqualTo(reservaNova.getDataFim());

        verify(buscarReservaPorIdUseCase,times(1)).buscarReservaPorId(anyLong());
        verify(buscarReservasPorMesaEPeriodoUseCase, times(1)).buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
        verify(atualizarReservaInterface, times(1)).atualizarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_IdNaoEncontrado() {
        var reservaNova = ReservaHelper.gerarReserva();
        reservaNova.setStatus(StatusReservaEnum.INATIVA);
        var id = 1L;
        var mensagemException = "Reserva de id: " + id + " não encontrada.";
        when(atualizarReservaInterface.atualizarReserva(any(Reserva.class))).thenAnswer(answer -> answer.getArgument(0));
        when(buscarReservaPorIdUseCase.buscarReservaPorId(any())).thenThrow(new ReservaNotFoundException(mensagemException));
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reservaNova))
                .hasMessage(mensagemException)
                        .isInstanceOf(ReservaNotFoundException.class);

        verify(buscarReservaPorIdUseCase,times(1)).buscarReservaPorId(anyLong());
        verify(buscarReservasPorMesaEPeriodoUseCase, never()).buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
        verify(atualizarReservaInterface, never()).atualizarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataInicioAntesDeHoje() {
        var reservaVelha = ReservaHelper.gerarReserva();
        var reservaNova = ReservaHelper.gerarReserva();
        reservaNova.setStatus(StatusReservaEnum.INATIVA);
        reservaNova.setDataInicio(LocalDateTime.now().minusDays(1));
        var id = 1L;
        var mensagemException = "Só é possível reservar para datas futuras.";
        when(atualizarReservaInterface.atualizarReserva(any(Reserva.class))).thenAnswer(answer -> answer.getArgument(0));
        when(buscarReservaPorIdUseCase.buscarReservaPorId(any())).thenReturn(reservaVelha);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reservaNova))
                .hasMessage(mensagemException)
                .isInstanceOf(IllegalArgumentException.class);

        verify(buscarReservaPorIdUseCase,times(1)).buscarReservaPorId(anyLong());
        verify(buscarReservasPorMesaEPeriodoUseCase, never()).buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
        verify(atualizarReservaInterface, never()).atualizarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataFimAntesDeHoje() {
        var reservaVelha = ReservaHelper.gerarReserva();
        var reservaNova = ReservaHelper.gerarReserva();
        reservaNova.setStatus(StatusReservaEnum.INATIVA);
        reservaNova.setDataFim(LocalDateTime.now().minusDays(1));
        var id = 1L;
        var mensagemException = "Só é possível reservar para datas futuras.";
        when(atualizarReservaInterface.atualizarReserva(any(Reserva.class))).thenAnswer(answer -> answer.getArgument(0));
        when(buscarReservaPorIdUseCase.buscarReservaPorId(any())).thenReturn(reservaVelha);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reservaNova))
                .hasMessage(mensagemException)
                .isInstanceOf(IllegalArgumentException.class);

        verify(buscarReservaPorIdUseCase,times(1)).buscarReservaPorId(anyLong());
        verify(buscarReservasPorMesaEPeriodoUseCase, never()).buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
        verify(atualizarReservaInterface, never()).atualizarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataInicioMaiorQueDataFim() {
        var reservaVelha = ReservaHelper.gerarReserva();
        var reservaNova = ReservaHelper.gerarReserva();
        reservaNova.setStatus(StatusReservaEnum.INATIVA);
        reservaNova.setDataInicio(LocalDateTime.now().plusDays(2));
        reservaNova.setDataFim(LocalDateTime.now().plusDays(1));
        var id = 1L;
        var mensagemException = "A Data inicio da reserva deve ser anterior a data fim.";
        when(atualizarReservaInterface.atualizarReserva(any(Reserva.class))).thenAnswer(answer -> answer.getArgument(0));
        when(buscarReservaPorIdUseCase.buscarReservaPorId(any())).thenReturn(reservaVelha);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reservaNova))
                .hasMessage(mensagemException)
                .isInstanceOf(IllegalArgumentException.class);

        verify(buscarReservaPorIdUseCase,times(1)).buscarReservaPorId(anyLong());
        verify(buscarReservasPorMesaEPeriodoUseCase, never()).buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
        verify(atualizarReservaInterface, never()).atualizarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_MesaJaReservada() {
        var reservaVelha = ReservaHelper.gerarReserva();
        var reservaNova = ReservaHelper.gerarReserva();
        reservaNova.setStatus(StatusReservaEnum.INATIVA);

        var id = 1L;
        var mensagemException = "A Mesa de id: " + reservaVelha.getMesa().getMesaId() + " já está reservada no período selecionado.";
        when(atualizarReservaInterface.atualizarReserva(any(Reserva.class))).thenAnswer(answer -> answer.getArgument(0));
        when(buscarReservaPorIdUseCase.buscarReservaPorId(any())).thenReturn(reservaVelha);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Arrays.asList(ReservaHelper.gerarReserva(), ReservaHelper.gerarReserva()));

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reservaNova))
                .hasMessage(mensagemException)
                .isInstanceOf(IllegalStateException.class);

        verify(buscarReservaPorIdUseCase,times(1)).buscarReservaPorId(anyLong());
        verify(buscarReservasPorMesaEPeriodoUseCase, times(1)).buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
        verify(atualizarReservaInterface, never()).atualizarReserva(any(Reserva.class));
    }
}
