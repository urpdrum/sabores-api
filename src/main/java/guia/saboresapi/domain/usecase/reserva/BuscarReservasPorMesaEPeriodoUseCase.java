package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorMesaEPeriodoInterface;

import java.time.LocalDateTime;
import java.util.List;

public class BuscarReservasPorMesaEPeriodoUseCase {

    private BuscarReservasPorMesaEPeriodoInterface buscarReservasPorMesaEPeriodoInterface;

    public BuscarReservasPorMesaEPeriodoUseCase(BuscarReservasPorMesaEPeriodoInterface buscarReservasPorMesaEPeriodoInterface) {
        this.buscarReservasPorMesaEPeriodoInterface = buscarReservasPorMesaEPeriodoInterface;
    }

    public List<Reserva> buscarReservasPorMesaEPeriodo(Long mesaId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return buscarReservasPorMesaEPeriodoInterface.buscarReservasPorMesaEPeriodo(mesaId, dataInicio, dataFim);
    }
}
