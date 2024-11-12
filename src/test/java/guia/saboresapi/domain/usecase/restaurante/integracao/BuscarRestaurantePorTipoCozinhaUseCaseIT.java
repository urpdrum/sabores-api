package guia.saboresapi.domain.usecase.restaurante.integracao;

import com.fiap.tc.restaurantes.domain.entity.Restaurante;
import com.fiap.tc.restaurantes.domain.usecase.restaurante.BuscarRestaurantePorTipoCozinhaUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BuscarRestaurantePorTipoCozinhaUseCaseIT {

    @Autowired
    private BuscarRestaurantePorTipoCozinhaUseCase useCase;

    @Test
    void devePermitirbuscarRestaurantePorTipoCozinha() {

        List<Restaurante> resultado = useCase.buscarPorTipoCozinha("MEXICANA");

        // Assert
        assertThat(resultado)
                .hasSizeGreaterThan(1);
    }
}