package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorUsuarioInterface;

import java.util.List;

public class BuscarReservasPorUsuarioUseCase {

    private final BuscarReservasPorUsuarioInterface buscarReservasPorUsuarioInterface;

    public BuscarReservasPorUsuarioUseCase(BuscarReservasPorUsuarioInterface buscarReservasPorUsuarioInterface) {
        this.buscarReservasPorUsuarioInterface = buscarReservasPorUsuarioInterface;
    }

    public List<Reserva> buscarReservasPorUsuario(Long usuarioId) {
        return buscarReservasPorUsuarioInterface.buscarReservasPorUsuario(usuarioId);
    }
}
