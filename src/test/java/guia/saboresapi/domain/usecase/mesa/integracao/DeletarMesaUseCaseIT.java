package guia.saboresapi.domain.usecase.mesa.integracao;

import com.fiap.tc.restaurantes.domain.exception.mesa.MesaNotFoundException;
import com.fiap.tc.restaurantes.domain.usecase.mesa.DeletarMesaUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DeletarMesaUseCaseIT {
  @Autowired
  private DeletarMesaUseCase deletarMesaUseCase;

  @Test
  void devePermitirDeletarMesa() {
    Long id = 5L;

    boolean mesaDeletada = deletarMesaUseCase.deletarMesa(id);

    assertThat(mesaDeletada).isTrue();
  }

  @Test
  void deveGerarExcecao_QuandoDeletarMesa_IdDaMesaNaoExiste() {
    Long id = 50L;

    assertThatThrownBy(() -> deletarMesaUseCase.deletarMesa(id))
        .isInstanceOf(MesaNotFoundException.class)
        .hasMessage("Mesa de id: " + id + " não encontrada");
  }
}
