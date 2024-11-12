package guia.saboresapi.infra.adapter.repository.reserva;

import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.gateway.reserva.AtualizarReservaInterface;
import guia.saboresapi.infra.entity.ReservaEntity;
import guia.saboresapi.infra.repository.ReservaRepository;
import guia.saboresapi.infra.repository.mapper.ReservaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AtualizarReservaAdapter implements AtualizarReservaInterface {

    private final ReservaRepository reservaRepository;
    private final ReservaEntityMapper mapper;

    @Override
    public Reserva atualizarReserva(Reserva reserva) {
        ReservaEntity entity = mapper.toReservaEntity(reserva);

        return mapper.toReserva(reservaRepository.save(entity));
    }
}
