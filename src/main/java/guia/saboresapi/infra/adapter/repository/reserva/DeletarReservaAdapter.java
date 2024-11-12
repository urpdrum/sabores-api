package guia.saboresapi.infra.adapter.repository.reserva;


import guia.saboresapi.domain.gateway.reserva.DeletarReservaInterface;
import guia.saboresapi.infra.repository.ReservaRepository;
import guia.saboresapi.infra.repository.mapper.ReservaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletarReservaAdapter implements DeletarReservaInterface {

    private final ReservaRepository reservaRepository;
    private final ReservaEntityMapper mapper;

    @Override
    public boolean deletarReserva(Long reservaId) {
        reservaRepository.deleteById(reservaId);
        return true;
    }
}
