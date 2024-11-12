package guia.saboresapi.domain.mapper.endereco;

import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.output.endereco.EnderecoResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T15:25:29-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class EnderecoResponseMapperImpl implements EnderecoResponseMapper {

    @Override
    public Endereco toEndereco(EnderecoResponse enderecoResponse) {
        if ( enderecoResponse == null ) {
            return null;
        }

        Endereco.EnderecoBuilder endereco = Endereco.builder();

        endereco.cidade( enderecoResponse.localidade() );
        endereco.logradouro( enderecoResponse.logradouro() );
        endereco.numero( enderecoResponse.numero() );
        endereco.complemento( enderecoResponse.complemento() );
        endereco.bairro( enderecoResponse.bairro() );
        endereco.cep( enderecoResponse.cep() );
        endereco.uf( enderecoResponse.uf() );

        return endereco.build();
    }
}
