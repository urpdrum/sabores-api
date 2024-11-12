package guia.saboresapi.domain.usecase.restaurante.integracao;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorLocalidadeUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
@ExtendWith(SpringExtension.class)
class BuscarRestaurantePorLocalidadeUseCaseIT {

    @Autowired
    private BuscarRestaurantePorLocalidadeUseCase useCase;

    @Test
    void devePermitirbuscarRestaurantePorLocalidade() {
        String logradouro = "logradouro teste";

        //Act
        List<Restaurante> resultado = useCase.buscarRestaurantePorLocalidade(logradouro);

        // Assert
        assertThat(resultado)
                .hasSizeGreaterThan(1);
    }
}