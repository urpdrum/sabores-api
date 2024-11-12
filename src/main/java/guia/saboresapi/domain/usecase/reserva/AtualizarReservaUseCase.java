package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.entity.validation.reserva.ReservaValidator;
import guia.saboresapi.domain.gateway.reserva.AtualizarReservaInterface;

import java.util.List;

public class AtualizarReservaUseCase {

    private final BuscarReservaPorIdUseCase buscarReservaPorIdUseCase;
    private final AtualizarReservaInterface atualizarReservaInterface;
    private final BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase;

    public AtualizarReservaUseCase(BuscarReservaPorIdUseCase buscarReservaPorIdUseCase, AtualizarReservaInterface atualizarReservaInterface, BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase) {
        this.buscarReservaPorIdUseCase = buscarReservaPorIdUseCase;
        this.atualizarReservaInterface = atualizarReservaInterface;
        this.buscarReservasPorMesaEPeriodoUseCase = buscarReservasPorMesaEPeriodoUseCase;
    }

    public Reserva atualizarReserva(Long reservaId, Reserva reservaNova) {
        Reserva reserva = buscarReservaPorIdUseCase.buscarReservaPorId(reservaId);

        if (reservaNova.getDataInicio() != null) {
            reserva.setDataInicio(reservaNova.getDataInicio());
        }
        if (reservaNova.getDataFim() != null) {
            reserva.setDataFim(reservaNova.getDataFim());
        }
        if (reservaNova.getStatus() != null) {
            reserva.setStatus(reservaNova.getStatus());
        }

        ReservaValidator.validate(reserva);

        List<Reserva> reservaList = buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(reserva.getMesa().getMesaId(),
                reserva.getDataInicio(),
                reserva.getDataFim());


        if (!reservaList.isEmpty() &&
                (reservaList.size() > 1 || // Se há mais de uma reserva para o horario, jogamos a exceção
                !reservaList.get(0).getReservaId().equals(reserva.getReservaId()))) { // Se só retornar 1 reserva e for ela mesma, não jogamos exceção
            throw new IllegalStateException("A Mesa de id: " + reserva.getMesa().getMesaId() + " já está reservada no período selecionado.");
        }


        return atualizarReservaInterface.atualizarReserva(reserva);
    }
}
