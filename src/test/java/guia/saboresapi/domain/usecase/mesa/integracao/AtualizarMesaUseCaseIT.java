package guia.saboresapi.domain.usecase.mesa.integracao;

import com.fiap.tc.restaurantes.domain.entity.Mesa;
import com.fiap.tc.restaurantes.domain.exception.mesa.MesaNotFoundException;
import com.fiap.tc.restaurantes.domain.usecase.mesa.AtualizarMesaUseCase;
import guia.saboresapi.utils.mesa.MesaHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AtualizarMesaUseCaseIT {
  @Autowired
  private AtualizarMesaUseCase atualizarMesaUseCase;

  @Test
  void devePermitirAtualizarMesa() {
    Long id = 6L;
    Mesa mesa = MesaHelper.gerarMesa();
    mesa.setQuantidadeAssentos(8);

    Mesa mesaAtualizada = atualizarMesaUseCase.atualizarMesa(id, mesa);

    assertThat(mesaAtualizada)
        .isNotNull()
        .isInstanceOf(Mesa.class);

    assertThat(mesaAtualizada.getQuantidadeAssentos())
        .isEqualTo(mesa.getQuantidadeAssentos());
  }

  @Test
  void deveGerarExcecao_QuandoAtualizarMesa_IdDaMesaNaoExiste() {
    Long id = 60L;
    Mesa mesa = MesaHelper.gerarMesa();
    mesa.setQuantidadeAssentos(8);

    assertThatThrownBy(() -> atualizarMesaUseCase.atualizarMesa(id, mesa))
        .isInstanceOf(MesaNotFoundException.class)
        .hasMessage("Mesa de id: " + id + " não encontrada");
  }
}
