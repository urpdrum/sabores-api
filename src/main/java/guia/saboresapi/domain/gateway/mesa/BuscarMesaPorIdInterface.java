package guia.saboresapi.domain.gateway.mesa;


import guia.saboresapi.domain.entity.Mesa;

public interface BuscarMesaPorIdInterface {
    Mesa buscarMesaPorId(Long mesaId);
}