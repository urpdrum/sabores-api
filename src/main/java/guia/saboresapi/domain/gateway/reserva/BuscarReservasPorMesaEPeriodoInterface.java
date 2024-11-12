package guia.saboresapi.domain.gateway.reserva;



import guia.saboresapi.domain.entity.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface BuscarReservasPorMesaEPeriodoInterface {

    List<Reserva> buscarReservasPorMesaEPeriodo(Long mesaId, LocalDateTime dataInicio, LocalDateTime dataFim);
}
