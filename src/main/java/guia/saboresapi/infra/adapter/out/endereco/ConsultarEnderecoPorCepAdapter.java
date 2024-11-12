package guia.saboresapi.infra.adapter.out.endereco;


import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.gateway.restaurante.ConsultarEnderecoPorCepInterface;
import guia.saboresapi.domain.mapper.endereco.EnderecoResponseMapper;
import guia.saboresapi.infra.feign.ConsultarEnderecoPorCepClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class ConsultarEnderecoPorCepAdapter implements ConsultarEnderecoPorCepInterface {

    private final ConsultarEnderecoPorCepClient consultarEnderecoPorCepClient;

    private final EnderecoResponseMapper enderecoResponseMapper;

    @Override
    public Endereco consultaPorCep(String cep) {
        var enderecoResponse = consultarEnderecoPorCepClient.consultaPorCep(cep);
        return enderecoResponseMapper.toEndereco(enderecoResponse);
    }

}
