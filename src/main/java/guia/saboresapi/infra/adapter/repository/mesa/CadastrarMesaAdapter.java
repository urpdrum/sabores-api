package guia.saboresapi.infra.adapter.repository.mesa;



import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.gateway.mesa.CadastrarMesaInterface;
import guia.saboresapi.infra.entity.MesaEntity;
import guia.saboresapi.infra.repository.MesaRepository;
import guia.saboresapi.infra.repository.mapper.MesaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarMesaAdapter implements CadastrarMesaInterface {

    private final MesaRepository mesaRepository;
    private final MesaEntityMapper mesaEntityMapper;

    @Override
    public Mesa cadastrarMesa(Mesa mesa) {
        MesaEntity mesaEntity = mesaEntityMapper.toMesaEntity(mesa);
        return mesaEntityMapper.toMesa(mesaRepository.save(mesaEntity));
    }
}