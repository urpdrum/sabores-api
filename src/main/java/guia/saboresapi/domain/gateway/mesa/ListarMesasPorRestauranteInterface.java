package guia.saboresapi.domain.gateway.mesa;


import guia.saboresapi.domain.entity.Mesa;

import java.util.List;

public interface ListarMesasPorRestauranteInterface {
    List<Mesa> listarMesasPorRestaurante(Long restauranteId);
}