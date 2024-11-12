package guia.saboresapi.infra.adapter.repository.reserva;

import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorMesaEPeriodoInterface;
import guia.saboresapi.infra.repository.ReservaRepository;
import guia.saboresapi.infra.repository.mapper.ReservaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarReservasPorMesaEPeriodoAdapter implements BuscarReservasPorMesaEPeriodoInterface {

    private final ReservaRepository reservaRepository;
    private final ReservaEntityMapper mapper;

    @Override
    public List<Reserva> buscarReservasPorMesaEPeriodo(Long mesaId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return reservaRepository.buscarReservasPorMesaEPeriodo(mesaId, dataInicio, dataFim)
                .stream()
                .map(mapper::toReserva)
                .toList();
    }
}
