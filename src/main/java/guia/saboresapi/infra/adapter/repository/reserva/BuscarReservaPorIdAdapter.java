package guia.saboresapi.infra.adapter.repository.reserva;

import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.gateway.reserva.BuscarReservaPorIdInterface;
import guia.saboresapi.infra.repository.ReservaRepository;
import guia.saboresapi.infra.repository.mapper.ReservaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BuscarReservaPorIdAdapter implements BuscarReservaPorIdInterface {

    private final ReservaRepository reservaRepository;
    private final ReservaEntityMapper mapper;

    @Override
    public Reserva buscarReservaPorId(Long reservaId) {
        return mapper.toReserva(reservaRepository.findById(reservaId).orElse(null));
    }
}
