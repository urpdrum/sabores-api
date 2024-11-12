package guia.saboresapi.domain.usecase.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.gateway.mesa.CadastrarMesaInterface;

public class CadastrarMesaUseCase {

  private final CadastrarMesaInterface cadastrarMesaInterface;

  public CadastrarMesaUseCase(CadastrarMesaInterface cadastrarMesaInterface) {
    this.cadastrarMesaInterface = cadastrarMesaInterface;
  }

  public Mesa cadastrarMesa(Mesa mesa) {
    return cadastrarMesaInterface.cadastrarMesa(mesa);
  }
}