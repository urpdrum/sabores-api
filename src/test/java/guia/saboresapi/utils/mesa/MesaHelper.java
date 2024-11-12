package guia.saboresapi.utils.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.input.mesa.AtualizarMesaRequest;
import guia.saboresapi.domain.input.mesa.CadastrarMesaRequest;
import guia.saboresapi.domain.output.mesa.MesaResponse;
import guia.saboresapi.infra.entity.MesaEntity;
import guia.saboresapi.utils.restaurante.RestauranteHelper;

public class MesaHelper {
    public static AtualizarMesaRequest gerarAtualizarMesaRequest(Integer quantidadeAssentos) {
        return new AtualizarMesaRequest(quantidadeAssentos);
    }

    public static CadastrarMesaRequest gerarMesaCadastroRequest(Long restauranteId, Integer quantidadeAssentos) {
        return new CadastrarMesaRequest(restauranteId, quantidadeAssentos);
    }

    public static MesaResponse gerarMesaResponse(Mesa mesa) {
        return new MesaResponse(
            mesa.getMesaId(),
            mesa.getRestaurante().getRestauranteId(),
            mesa.getQuantidadeAssentos()
        );
    }

    public static MesaEntity gerarMesaEntity() {
        MesaEntity mesa = new MesaEntity();

        mesa.setRestauranteEntity(RestauranteHelper.gerarRestauranteEntityValido());
        mesa.setQuantidadeAssentos(4);

        return mesa;
    }

    public static Mesa gerarMesa() {
        Mesa mesa = new Mesa();

        mesa.setRestaurante(RestauranteHelper.gerarRestauranteValido());
        mesa.setQuantidadeAssentos(4);

        return mesa;
    }

    public static Mesa gerarMesaComId() {
        Mesa mesa = new Mesa();

        mesa.setMesaId(1L);
        mesa.setRestaurante(RestauranteHelper.gerarRestauranteValido());
        mesa.setQuantidadeAssentos(4);

        return mesa;
    }
}
