package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.entity.validation.reserva.ReservaValidator;
import guia.saboresapi.domain.enums.StatusReservaEnum;
import guia.saboresapi.domain.gateway.reserva.CadastrarReservaInterface;
import guia.saboresapi.domain.usecase.mesa.BuscarMesaPorIdUseCase;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;

import java.util.List;

public class CadastrarReservaUseCase {

    private final CadastrarReservaInterface cadastrarReservaInterface;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final BuscarMesaPorIdUseCase buscarMesaPorIdUseCase;
    private final BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase;

    public CadastrarReservaUseCase(CadastrarReservaInterface cadastrarReservaInterface,
                                   BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
                                   BuscarMesaPorIdUseCase buscarMesaPorIdUseCase,
                                   BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase) {
        this.cadastrarReservaInterface = cadastrarReservaInterface;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.buscarMesaPorIdUseCase = buscarMesaPorIdUseCase;
        this.buscarReservasPorMesaEPeriodoUseCase = buscarReservasPorMesaEPeriodoUseCase;
    }

    public Reserva cadastrarReserva(Reserva reserva, Long usuarioId, Long mesaId) {

        Usuario usuario = buscarUsuarioPorIdUseCase.buscarUsuarioPorId(usuarioId);
        Mesa mesa = buscarMesaPorIdUseCase.buscarMesaPorId(mesaId);

        reserva.setUsuario(usuario);
        reserva.setMesa(mesa);
        reserva.setStatus(StatusReservaEnum.ATIVA);

        ReservaValidator.validate(reserva);

        List<Reserva> reservaList = buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(mesaId,
                reserva.getDataInicio(),
                reserva.getDataFim());

        if (!reservaList.isEmpty()) {
            throw new IllegalStateException("A Mesa de id: " + mesaId + " já está reservada no período selecionado.");
        }

        return cadastrarReservaInterface.cadastrarReserva(reserva);
    }
}
