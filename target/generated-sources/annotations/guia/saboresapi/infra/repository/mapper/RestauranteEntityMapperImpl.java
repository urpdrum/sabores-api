package guia.saboresapi.infra.repository.mapper;

import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.enums.TipoCozinhaEnum;
import guia.saboresapi.infra.entity.EnderecoEntity;
import guia.saboresapi.infra.entity.RestauranteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T12:14:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class RestauranteEntityMapperImpl implements RestauranteEntityMapper {

    @Override
    public RestauranteEntity toRestauranteEntity(Restaurante restaurante) {
        if ( restaurante == null ) {
            return null;
        }

        RestauranteEntity restauranteEntity = new RestauranteEntity();

        restauranteEntity.setRestauranteId( restaurante.getRestauranteId() );
        restauranteEntity.setNome( restaurante.getNome() );
        restauranteEntity.setEndereco( enderecoToEnderecoEntity( restaurante.getEndereco() ) );
        if ( restaurante.getTipoDeCozinha() != null ) {
            restauranteEntity.setTipoDeCozinha( restaurante.getTipoDeCozinha().name() );
        }
        restauranteEntity.setCapacidade( restaurante.getCapacidade() );
        restauranteEntity.setHorarioFuncionamento( restaurante.getHorarioFuncionamento() );

        return restauranteEntity;
    }

    @Override
    public Restaurante toRestaurante(RestauranteEntity restauranteEntity) {
        if ( restauranteEntity == null ) {
            return null;
        }

        Restaurante.RestauranteBuilder restaurante = Restaurante.builder();

        restaurante.restauranteId( restauranteEntity.getRestauranteId() );
        restaurante.nome( restauranteEntity.getNome() );
        restaurante.endereco( enderecoEntityToEndereco( restauranteEntity.getEndereco() ) );
        if ( restauranteEntity.getTipoDeCozinha() != null ) {
            restaurante.tipoDeCozinha( Enum.valueOf( TipoCozinhaEnum.class, restauranteEntity.getTipoDeCozinha() ) );
        }
        restaurante.capacidade( restauranteEntity.getCapacidade() );
        restaurante.horarioFuncionamento( restauranteEntity.getHorarioFuncionamento() );

        return restaurante.build();
    }

    protected EnderecoEntity enderecoToEnderecoEntity(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoEntity.EnderecoEntityBuilder enderecoEntity = EnderecoEntity.builder();

        enderecoEntity.logradouro( endereco.getLogradouro() );
        enderecoEntity.numero( endereco.getNumero() );
        enderecoEntity.complemento( endereco.getComplemento() );
        enderecoEntity.bairro( endereco.getBairro() );
        enderecoEntity.cidade( endereco.getCidade() );
        enderecoEntity.cep( endereco.getCep() );
        enderecoEntity.uf( endereco.getUf() );

        return enderecoEntity.build();
    }

    protected Endereco enderecoEntityToEndereco(EnderecoEntity enderecoEntity) {
        if ( enderecoEntity == null ) {
            return null;
        }

        Endereco.EnderecoBuilder endereco = Endereco.builder();

        endereco.logradouro( enderecoEntity.getLogradouro() );
        endereco.numero( enderecoEntity.getNumero() );
        endereco.complemento( enderecoEntity.getComplemento() );
        endereco.bairro( enderecoEntity.getBairro() );
        endereco.cidade( enderecoEntity.getCidade() );
        endereco.cep( enderecoEntity.getCep() );
        endereco.uf( enderecoEntity.getUf() );

        return endereco.build();
    }
}
