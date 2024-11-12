package guia.saboresapi.domain.gateway.reserva;


import guia.saboresapi.domain.entity.Reserva;

public interface BuscarReservaPorIdInterface {

    Reserva buscarReservaPorId(Long reservaId);
}
