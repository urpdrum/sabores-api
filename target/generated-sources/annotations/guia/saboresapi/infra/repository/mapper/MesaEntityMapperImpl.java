package guia.saboresapi.infra.repository.mapper;

import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.enums.TipoCozinhaEnum;
import guia.saboresapi.infra.entity.EnderecoEntity;
import guia.saboresapi.infra.entity.MesaEntity;
import guia.saboresapi.infra.entity.RestauranteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T12:35:53-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class MesaEntityMapperImpl implements MesaEntityMapper {

    @Override
    public MesaEntity toMesaEntity(Mesa mesa) {
        if ( mesa == null ) {
            return null;
        }

        MesaEntity mesaEntity = new MesaEntity();

        mesaEntity.setRestauranteEntity( restauranteToRestauranteEntity( mesa.getRestaurante() ) );
        mesaEntity.setMesaId( mesa.getMesaId() );
        mesaEntity.setQuantidadeAssentos( mesa.getQuantidadeAssentos() );

        return mesaEntity;
    }

    @Override
    public Mesa toMesa(MesaEntity mesaEntity) {
        if ( mesaEntity == null ) {
            return null;
        }

        Mesa mesa = new Mesa();

        mesa.setRestaurante( restauranteEntityToRestaurante( mesaEntity.getRestauranteEntity() ) );
        mesa.setMesaId( mesaEntity.getMesaId() );
        mesa.setQuantidadeAssentos( mesaEntity.getQuantidadeAssentos() );

        return mesa;
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

    protected RestauranteEntity restauranteToRestauranteEntity(Restaurante restaurante) {
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

    protected Restaurante restauranteEntityToRestaurante(RestauranteEntity restauranteEntity) {
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
}
