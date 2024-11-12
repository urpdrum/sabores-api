package guia.saboresapi.infra.adapter.repository.reserva;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorUsuarioInterface;
import guia.saboresapi.infra.entity.ReservaEntity;
import guia.saboresapi.infra.repository.ReservaRepository;
import guia.saboresapi.infra.repository.mapper.ReservaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarReservasPorUsuarioAdapter implements BuscarReservasPorUsuarioInterface {

    private final ReservaRepository reservaRepository;
    private final ReservaEntityMapper mapper;

    @Override
    public List<Reserva> buscarReservasPorUsuario(Long usuarioId) {
        List<ReservaEntity> reservaEntityList = reservaRepository.buscarReservasPorUsuario(usuarioId);
        return reservaEntityList.stream().map(mapper::toReserva).toList();
    }
}
