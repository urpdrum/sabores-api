package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.gateway.reserva.CadastrarReservaInterface;
import guia.saboresapi.domain.usecase.mesa.BuscarMesaPorIdUseCase;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;
import guia.saboresapi.utils.mesa.MesaHelper;
import guia.saboresapi.utils.reserva.ReservaHelper;
import guia.saboresapi.utils.usuario.UsuarioHelper;
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

class CadastrarReservaUseCaseTest {

    private CadastrarReservaUseCase cadastrarReservaUseCase;

    @Mock
    private CadastrarReservaInterface cadastrarReservaInterface;

    @Mock
    private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    @Mock
    private BuscarMesaPorIdUseCase buscarMesaPorIdUseCase;

    @Mock
    private BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        cadastrarReservaUseCase = new CadastrarReservaUseCase(cadastrarReservaInterface,
                buscarUsuarioPorIdUseCase,
                buscarMesaPorIdUseCase,
                buscarReservasPorMesaEPeriodoUseCase);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirCadastrarReserva() {
        var reserva = ReservaHelper.gerarReserva();
        var usuario = UsuarioHelper.gerarUsuarioValidoComId();
        var mesa = MesaHelper.gerarMesaComId();
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenReturn(usuario);
        when(buscarMesaPorIdUseCase.buscarMesaPorId(anyLong())).thenReturn(mesa);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());
        when(cadastrarReservaInterface.cadastrarReserva(any(Reserva.class))).thenReturn(reserva);

        var reservaCadastrada = cadastrarReservaUseCase.cadastrarReserva(reserva, usuario.getUsuarioId(), mesa.getMesaId());

        assertThat(reservaCadastrada)
                .isInstanceOf(Reserva.class)
                .isNotNull();
        assertThat(reservaCadastrada.getUsuario().getUsuarioId()).isEqualTo(usuario.getUsuarioId());
        assertThat(reservaCadastrada.getMesa().getMesaId()).isEqualTo(mesa.getMesaId());
        assertThat(reservaCadastrada.getStatus()).isEqualTo(reserva.getStatus());
        assertThat(reservaCadastrada.getDataInicio()).isEqualTo(reserva.getDataInicio());
        assertThat(reservaCadastrada.getDataFim()).isEqualTo(reserva.getDataFim());

        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarMesaPorIdUseCase, times(1)).buscarMesaPorId(anyLong());
        verify(cadastrarReservaInterface, times(1)).cadastrarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_UsuarioNaoEncontrado() {
        var reserva = ReservaHelper.gerarReserva();
        var usuarioId = 1L;
        var mensagemException = "Usuário de id: " + usuarioId + " não encontrado.";
        var mesa = MesaHelper.gerarMesaComId();
        var mesaId = mesa.getMesaId();
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenThrow(new UsuarioNotFoundException(mensagemException));
        when(buscarMesaPorIdUseCase.buscarMesaPorId(anyLong())).thenReturn(mesa);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());
        when(cadastrarReservaInterface.cadastrarReserva(any(Reserva.class))).thenReturn(reserva);

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(UsuarioNotFoundException.class)
                .hasMessage(mensagemException);
        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarMesaPorIdUseCase, never()).buscarMesaPorId(anyLong());
        verify(cadastrarReservaInterface, never()).cadastrarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_MesaNaoEncontrada() {
        var reserva = ReservaHelper.gerarReserva();
        var mesaId = 1L;
        var mensagemException = "Mesa de id: " + mesaId + " não encontrada";
        var usuario = UsuarioHelper.gerarUsuarioValidoComId();
        var usuarioId = usuario.getUsuarioId();
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenReturn(usuario);
        when(buscarMesaPorIdUseCase.buscarMesaPorId(anyLong())).thenThrow(new MesaNotFoundException(mensagemException));
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());
        when(cadastrarReservaInterface.cadastrarReserva(any(Reserva.class))).thenReturn(reserva);

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(MesaNotFoundException.class)
                .hasMessage(mensagemException);
        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarMesaPorIdUseCase, times(1)).buscarMesaPorId(anyLong());
        verify(cadastrarReservaInterface, never()).cadastrarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataInicioAntesDeHoje() {
        var reserva = ReservaHelper.gerarReserva();
        var usuario = UsuarioHelper.gerarUsuarioValidoComId();
        var mesa = MesaHelper.gerarMesaComId();
        var mesaId = mesa.getMesaId();
        var usuarioId = usuario.getUsuarioId();
        var mensagemException = "Só é possível reservar para datas futuras.";
        reserva.setDataInicio(LocalDateTime.now().minusDays(1));
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenReturn(usuario);
        when(buscarMesaPorIdUseCase.buscarMesaPorId(anyLong())).thenReturn(mesa);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());
        when(cadastrarReservaInterface.cadastrarReserva(any(Reserva.class))).thenReturn(reserva);

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);

        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarMesaPorIdUseCase, times(1)).buscarMesaPorId(anyLong());
        verify(cadastrarReservaInterface, never()).cadastrarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataFimAntesDeHoje() {
        var reserva = ReservaHelper.gerarReserva();
        var usuario = UsuarioHelper.gerarUsuarioValidoComId();
        var mesa = MesaHelper.gerarMesaComId();
        var mesaId = mesa.getMesaId();
        var usuarioId = usuario.getUsuarioId();
        var mensagemException = "Só é possível reservar para datas futuras.";
        reserva.setDataFim(LocalDateTime.now().minusDays(1));
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenReturn(usuario);
        when(buscarMesaPorIdUseCase.buscarMesaPorId(anyLong())).thenReturn(mesa);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());
        when(cadastrarReservaInterface.cadastrarReserva(any(Reserva.class))).thenReturn(reserva);

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);

        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarMesaPorIdUseCase, times(1)).buscarMesaPorId(anyLong());
        verify(cadastrarReservaInterface, never()).cadastrarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataInicioMaiorQueDataFim() {
        var reserva = ReservaHelper.gerarReserva();
        var usuario = UsuarioHelper.gerarUsuarioValidoComId();
        var mesa = MesaHelper.gerarMesaComId();
        var mesaId = mesa.getMesaId();
        var usuarioId = usuario.getUsuarioId();
        var mensagemException = "A Data inicio da reserva deve ser anterior a data fim.";
        reserva.setDataInicio(LocalDateTime.now().plusDays(2));
        reserva.setDataFim(LocalDateTime.now().plusDays(1));
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenReturn(usuario);
        when(buscarMesaPorIdUseCase.buscarMesaPorId(anyLong())).thenReturn(mesa);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());
        when(cadastrarReservaInterface.cadastrarReserva(any(Reserva.class))).thenReturn(reserva);

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);

        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarMesaPorIdUseCase, times(1)).buscarMesaPorId(anyLong());
        verify(cadastrarReservaInterface, never()).cadastrarReserva(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_MesaJaReservada() {
        var reserva = ReservaHelper.gerarReserva();
        var usuario = UsuarioHelper.gerarUsuarioValidoComId();
        var mesa = MesaHelper.gerarMesaComId();
        var mesaId = mesa.getMesaId();
        var usuarioId = usuario.getUsuarioId();
        var mensagemException = "A Mesa de id: " + mesa.getMesaId() + " já está reservada no período selecionado.";
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenReturn(usuario);
        when(buscarMesaPorIdUseCase.buscarMesaPorId(anyLong())).thenReturn(mesa);
        when(buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Arrays.asList(ReservaHelper.gerarReserva(), ReservaHelper.gerarReserva()));
        when(cadastrarReservaInterface.cadastrarReserva(any(Reserva.class))).thenReturn(reserva);

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(mensagemException);

        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarMesaPorIdUseCase, times(1)).buscarMesaPorId(anyLong());
        verify(cadastrarReservaInterface, never()).cadastrarReserva(any(Reserva.class));
    }
}
