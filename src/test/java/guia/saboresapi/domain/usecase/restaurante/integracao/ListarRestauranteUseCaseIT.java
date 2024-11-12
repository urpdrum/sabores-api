package guia.saboresapi.domain.usecase.restaurante.integracao;

import com.fiap.tc.restaurantes.domain.entity.Restaurante;
import com.fiap.tc.restaurantes.domain.usecase.restaurante.ListarRestauranteUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ListarRestauranteUseCaseIT {

    @Autowired
    private ListarRestauranteUseCase listarRestauranteUseCase;

    @Test
    void deveListarRestaurantes() {
        // Act
        List<Restaurante> listaRestaurantes = listarRestauranteUseCase.listarRestaurantes();

        // Assert
        assertThat(listaRestaurantes)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(1);
    }
}
