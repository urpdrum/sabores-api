package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.exception.reserva.ReservaNotFoundException;
import guia.saboresapi.domain.gateway.reserva.BuscarReservaPorIdInterface;

public class BuscarReservaPorIdUseCase {

    private final BuscarReservaPorIdInterface buscarReservaPorIdInterface;

    public BuscarReservaPorIdUseCase(BuscarReservaPorIdInterface buscarReservaPorIdInterface) {
        this.buscarReservaPorIdInterface = buscarReservaPorIdInterface;
    }

    public Reserva buscarReservaPorId(Long id) {
        Reserva reserva = buscarReservaPorIdInterface.buscarReservaPorId(id);
        if(reserva == null) {
            throw new ReservaNotFoundException("Reserva de id: " + id + " não encontrada.");
        }
        return reserva;
    }
}
