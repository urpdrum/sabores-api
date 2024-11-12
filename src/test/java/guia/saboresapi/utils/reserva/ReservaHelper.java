package guia.saboresapi.utils.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.enums.StatusReservaEnum;
import guia.saboresapi.domain.input.reserva.AtualizarReservaRequest;
import guia.saboresapi.domain.input.reserva.CadastrarReservaRequest;
import guia.saboresapi.domain.output.reserva.ReservaResponse;
import guia.saboresapi.infra.entity.ReservaEntity;
import guia.saboresapi.utils.mesa.MesaHelper;
import guia.saboresapi.utils.usuario.UsuarioHelper;

import java.time.LocalDateTime;

public class ReservaHelper {

    public static ReservaEntity gerarReservaEntity() {
        ReservaEntity reserva = new ReservaEntity();

        reserva.setUsuarioEntity(UsuarioHelper.gerarUsuarioEntity());
        reserva.setMesaEntity(MesaHelper.gerarMesaEntity());

        reserva.setStatus(StatusReservaEnum.ATIVA);
        reserva.setDataInicio(LocalDateTime.of(2030, 1,1,0,0));
        reserva.setDataFim(LocalDateTime.of(2030, 1,1,2,0));

        return reserva;
    }

    public static Reserva gerarReserva() {

        Reserva reserva = new Reserva();

        reserva.setUsuario(UsuarioHelper.gerarUsuarioValidoComId());
        reserva.setMesa(MesaHelper.gerarMesaComId());

        reserva.setStatus(StatusReservaEnum.ATIVA);
        reserva.setDataInicio(LocalDateTime.now().plusDays(1));
        reserva.setDataFim(LocalDateTime.now().plusDays(1).plusHours(1));

        return reserva;
    }

    public static AtualizarReservaRequest gerarAtualizarReservaRequest() {
        return new AtualizarReservaRequest(LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(10).plusHours(1),
                StatusReservaEnum.INATIVA);
    }

    public static AtualizarReservaRequest gerarAtualizarReservaRequestComDataInicioAntesDeHoje() {
        return new AtualizarReservaRequest(LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(1),
                StatusReservaEnum.ATIVA);
    }

    public static AtualizarReservaRequest gerarAtualizarReservaRequestComDataFimAntesDeHoje() {
        return new AtualizarReservaRequest(LocalDateTime.now().plusDays(1),
                LocalDateTime.now().minusDays(1).plusHours(1),
                StatusReservaEnum.ATIVA);
    }

    public static AtualizarReservaRequest gerarAtualizarReservaRequestComDataFimAntesDataInicio() {
        return new AtualizarReservaRequest(LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).minusHours(1),
                StatusReservaEnum.ATIVA);
    }

    public static AtualizarReservaRequest gerarAtualizarReservaRequestComMesaJaReservada() {
        return new AtualizarReservaRequest(LocalDateTime.of(2030,9,10,11,47,37),
                LocalDateTime.of(2030,9,10,12,47,37),
                StatusReservaEnum.ATIVA);
    }

    public static CadastrarReservaRequest gerarCadastrarReservaRequest() {
        return new CadastrarReservaRequest(
                1L,
                1L,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(1)
        );
    }

    public static CadastrarReservaRequest gerarCadastrarReservaRequest(Long mesaId) {
        return new CadastrarReservaRequest(
            1L,
            mesaId,
            LocalDateTime.now().plusDays(1),
            LocalDateTime.now().plusDays(1).plusHours(1)
        );
    }

    public static CadastrarReservaRequest gerarCadastrarReservaRequestComUsuarioNaoEncontrado() {
        return new CadastrarReservaRequest(
                1151565L,
                1L,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(1)
        );
    }

    public static CadastrarReservaRequest gerarCadastrarReservaRequestComMesaNaoEncontrada() {
        return new CadastrarReservaRequest(
                1L,
                11561564654L,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(1)
        );
    }

    public static CadastrarReservaRequest gerarCadastrarReservaRequestComDataInicioAntesDeHoje() {
        return new CadastrarReservaRequest(
                1L,
                1L,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(1)
        );
    }

    public static CadastrarReservaRequest gerarCadastrarReservaRequestComDataFimAntesDeHoje() {
        return new CadastrarReservaRequest(
                1L,
                1L,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().minusDays(1).plusHours(1)
        );
    }

    public static CadastrarReservaRequest gerarCadastrarReservaRequestComDataFimAntesDeDataInicio() {
        return new CadastrarReservaRequest(
                1L,
                1L,
                LocalDateTime.now().plusDays(1).plusHours(2),
                LocalDateTime.now().plusDays(1).plusHours(1)
        );
    }

    public static CadastrarReservaRequest gerarCadastrarReservaRequestComMesaJaReservada() {
        return new CadastrarReservaRequest(
                1L,
                1L,
                LocalDateTime.of(2030,9,10,11,47,37),
                LocalDateTime.of(2030,9,10,12,47,37)
        );
    }

    public static ReservaResponse gerarReservaResponse() {
        return new ReservaResponse(1L,
                1L,
                1L,
                StatusReservaEnum.ATIVA,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2));
    }
}
