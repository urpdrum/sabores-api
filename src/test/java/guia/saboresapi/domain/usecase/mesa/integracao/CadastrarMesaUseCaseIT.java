package guia.saboresapi.domain.usecase.mesa.integracao;

import com.fiap.tc.restaurantes.domain.entity.Mesa;
import com.fiap.tc.restaurantes.domain.usecase.mesa.CadastrarMesaUseCase;
import guia.saboresapi.utils.mesa.MesaHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CadastrarMesaUseCaseIT {
  @Autowired
  private CadastrarMesaUseCase cadastrarMesaUseCase;

  @Test
  void deveCadastrarMesa() {
    Mesa mesa = MesaHelper.gerarMesa();

    Mesa mesaCadastrada = cadastrarMesaUseCase.cadastrarMesa(mesa);

    assertThat(mesaCadastrada)
        .isNotNull()
        .isInstanceOf(Mesa.class);

    assertThat(mesaCadastrada.getQuantidadeAssentos())
        .isEqualTo(mesa.getQuantidadeAssentos());

    assertThat(mesaCadastrada.getRestaurante().getRestauranteId())
        .isEqualTo(mesa.getRestaurante().getRestauranteId());

  }
}
