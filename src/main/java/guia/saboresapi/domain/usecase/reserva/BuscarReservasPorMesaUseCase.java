package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorMesaInterface;

import java.util.List;

public class BuscarReservasPorMesaUseCase {

    private final BuscarReservasPorMesaInterface buscarReservasPorMesaInterface;

    public BuscarReservasPorMesaUseCase(BuscarReservasPorMesaInterface buscarReservasPorMesaInterface) {
        this.buscarReservasPorMesaInterface = buscarReservasPorMesaInterface;
    }

    public List<Reserva> buscarReservasPorMesa(Long mesaId) {
        return buscarReservasPorMesaInterface.buscarReservasPorMesa(mesaId);
    }
}
