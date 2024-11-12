package guia.saboresapi.domain.gateway.restaurante;


import guia.saboresapi.domain.entity.Endereco;

public interface ConsultarEnderecoPorCepInterface {

    /**
     * @param cep
     * @return
     */
    Endereco consultaPorCep(final String cep);

}
