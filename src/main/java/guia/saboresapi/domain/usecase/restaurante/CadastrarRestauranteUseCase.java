package guia.saboresapi.domain.usecase.restaurante;

import feign.FeignException;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.entity.validation.restaurante.RestauranteValidator;
import guia.saboresapi.domain.gateway.restaurante.CadastrarRestauranteInterface;
import guia.saboresapi.domain.gateway.restaurante.ConsultarEnderecoPorCepInterface;


public class CadastrarRestauranteUseCase {

    private final CadastrarRestauranteInterface cadastrarRestauranteInterface;

    private final ConsultarEnderecoPorCepInterface consultarEnderecoPorCepInterface;

    public CadastrarRestauranteUseCase(CadastrarRestauranteInterface cadastrarRestauranteInterface, ConsultarEnderecoPorCepInterface consultarEnderecoPorCepInterface) {
        this.cadastrarRestauranteInterface = cadastrarRestauranteInterface;
        this.consultarEnderecoPorCepInterface = consultarEnderecoPorCepInterface;
    }

    public Restaurante cadastrarRestaurante(Restaurante restaurante) {

        try {
            var endereco = consultarEnderecoPorCepInterface.consultaPorCep(restaurante.getEndereco().getCep());
            if (endereco.getCep() == null)
                throw new IllegalArgumentException("CEP inexistente.");

            restaurante.getEndereco().setCidade(endereco.getCidade());
            restaurante.getEndereco().setUf(endereco.getUf());
            restaurante.getEndereco().setBairro(endereco.getBairro());
            restaurante.getEndereco().setLogradouro(endereco.getLogradouro());
        } catch (FeignException e) {
            throw new IllegalArgumentException("CEP inexistente.");
        }

        RestauranteValidator.validateRestaurante(restaurante);

        return cadastrarRestauranteInterface.cadastrarRestaurante(restaurante);
    }
}
