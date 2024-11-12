package guia.saboresapi.domain.gateway.restaurante;


import guia.saboresapi.domain.entity.Restaurante;

public interface BuscarRestaurantePorIdInterface {

    Restaurante buscarRestaurantePorId(Long id);
}
