package guia.saboresapi.domain.usecase.avaliacao.integracao;


import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacoesPorRestauranteUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class BuscarAvaliacoesPorRestauranteUseCaseIT {

    @Autowired
    private BuscarAvaliacoesPorRestauranteUseCase buscarAvaliacoesPorRestauranteUseCase;

    @Test
    void devePermitirBuscarAvaliacoesPorRestaurante() {
        var restauranteId = 1L;

        var listAvaliacoes = buscarAvaliacoesPorRestauranteUseCase.buscarAvaliacoesPorRestaurante(restauranteId);

        assertThat(listAvaliacoes)
                .isNotEmpty()
                .hasSize(2);
    }
}
