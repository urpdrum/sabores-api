package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.gateway.reserva.DeletarReservaInterface;

public class DeletarReservaUseCase {

    private final BuscarReservaPorIdUseCase buscarReservaPorId;
    private final DeletarReservaInterface deletarReservaInterface;

    public DeletarReservaUseCase(BuscarReservaPorIdUseCase buscarReservaPorId, DeletarReservaInterface deletarReservaInterface) {
        this.buscarReservaPorId = buscarReservaPorId;
        this.deletarReservaInterface = deletarReservaInterface;
    }

    public boolean deletarReserva(Long reservaId) {

        buscarReservaPorId.buscarReservaPorId(reservaId);
        try {
            return deletarReservaInterface.deletarReserva(reservaId);
        } catch (Exception e) {
            return false;
        }
    }
}
