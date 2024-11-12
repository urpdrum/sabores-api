package guia.saboresapi.domain.usecase.avaliacao.integracao;


import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacoesPorUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class BuscarAvaliacoesPorUsuarioUseCaseIT {

    @Autowired
    private BuscarAvaliacoesPorUsuarioUseCase buscarAvaliacoesPorUsuarioUseCase;

    @Test
    void devePermitirBuscarAvaliacoesPorUsuario() {
        var usuarioId = 1L;

        var listAvaliacoes = buscarAvaliacoesPorUsuarioUseCase.buscarAvaliacoesPorUsuario(usuarioId);

        assertThat(listAvaliacoes)
                .isNotEmpty()
                .hasSize(2);
    }
}
