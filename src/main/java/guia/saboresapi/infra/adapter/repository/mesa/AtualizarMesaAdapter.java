package guia.saboresapi.infra.adapter.repository.mesa;



import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.gateway.mesa.AtualizarMesaInterface;
import guia.saboresapi.infra.entity.MesaEntity;
import guia.saboresapi.infra.repository.MesaRepository;
import guia.saboresapi.infra.repository.mapper.MesaEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarMesaAdapter implements AtualizarMesaInterface {

    private final MesaRepository mesaRepository;
    private final MesaEntityMapper mesaEntityMapper;

    @Transactional
    @Override
    public Mesa atualizarMesa(Mesa mesa) {
        MesaEntity mesaEntity = mesaEntityMapper.toMesaEntity(mesa);

        return mesaEntityMapper.toMesa(mesaRepository.save(mesaEntity));
    }
}