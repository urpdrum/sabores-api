package guia.saboresapi.domain.gateway.reserva;



import guia.saboresapi.domain.entity.Reserva;

import java.util.List;

public interface BuscarReservasPorMesaInterface {

    List<Reserva> buscarReservasPorMesa(Long mesaId);
}
