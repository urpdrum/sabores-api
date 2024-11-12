package guia.saboresapi.domain.entity.validation.restaurante;


import guia.saboresapi.domain.entity.Restaurante;

public class RestauranteValidator {

    public static void validateRestaurante(Restaurante restaurante) {
        if (restaurante.getNome() == null || restaurante.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do restaurante deve ser informado.");
        }
        if (restaurante.getCapacidade() == null || restaurante.getCapacidade() < 0) {
            throw new IllegalArgumentException("A capacidade do restaurante deve ser informada.");
        }
    }

    private RestauranteValidator() {
    }
}
