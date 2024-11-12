package guia.saboresapi.infra.adapter.repository.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.gateway.reserva.CadastrarReservaInterface;
import guia.saboresapi.infra.repository.ReservaRepository;
import guia.saboresapi.infra.repository.mapper.ReservaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CadastrarReservaAdapter implements CadastrarReservaInterface {

    private final ReservaRepository reservaRepository;
    private final ReservaEntityMapper mapper;

    @Override
    public Reserva cadastrarReserva(Reserva reserva) {
        return mapper.toReserva(reservaRepository.save(mapper.toReservaEntity(reserva)));
    }
}
