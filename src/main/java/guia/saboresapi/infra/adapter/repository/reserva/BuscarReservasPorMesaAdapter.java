package guia.saboresapi.infra.adapter.repository.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorMesaInterface;
import guia.saboresapi.infra.entity.ReservaEntity;
import guia.saboresapi.infra.repository.ReservaRepository;
import guia.saboresapi.infra.repository.mapper.ReservaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarReservasPorMesaAdapter implements BuscarReservasPorMesaInterface {

    private final ReservaRepository reservaRepository;
    private final ReservaEntityMapper mapper;

    @Override
    public List<Reserva> buscarReservasPorMesa(Long mesaId) {
        List<ReservaEntity> reservaEntityList = reservaRepository.buscarReservasPorMesa(mesaId);
        return reservaEntityList.stream().map(mapper::toReserva).toList();
    }
}
