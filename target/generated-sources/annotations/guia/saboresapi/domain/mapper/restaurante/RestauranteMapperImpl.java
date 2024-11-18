package guia.saboresapi.domain.mapper.restaurante;

import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.enums.TipoCozinhaEnum;
import guia.saboresapi.domain.input.restaurante.AtualizarRestauranteRequest;
import guia.saboresapi.domain.input.restaurante.CadastrarRestauranteRequest;
import guia.saboresapi.domain.output.restaurante.RestauranteResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T12:14:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class RestauranteMapperImpl implements RestauranteMapper {

    @Override
    public Restaurante toRestaurante(CadastrarRestauranteRequest restauranteRequest) {
        if ( restauranteRequest == null ) {
            return null;
        }

        Restaurante.RestauranteBuilder restaurante = Restaurante.builder();

        restaurante.nome( restauranteRequest.nome() );
        restaurante.endereco( enderecoToEndereco( restauranteRequest.endereco() ) );
        if ( restauranteRequest.tipoDeCozinha() != null ) {
            restaurante.tipoDeCozinha( Enum.valueOf( TipoCozinhaEnum.class, restauranteRequest.tipoDeCozinha() ) );
        }
        restaurante.capacidade( restauranteRequest.capacidade() );
        restaurante.horarioFuncionamento( restauranteRequest.horarioFuncionamento() );

        return restaurante.build();
    }

    @Override
    public Restaurante toRestaurante(AtualizarRestauranteRequest restauranteRequest) {
        if ( restauranteRequest == null ) {
            return null;
        }

        Restaurante.RestauranteBuilder restaurante = Restaurante.builder();

        restaurante.nome( restauranteRequest.nome() );
        restaurante.endereco( enderecoToEndereco1( restauranteRequest.endereco() ) );
        if ( restauranteRequest.tipoDeCozinha() != null ) {
            restaurante.tipoDeCozinha( Enum.valueOf( TipoCozinhaEnum.class, restauranteRequest.tipoDeCozinha() ) );
        }
        restaurante.capacidade( restauranteRequest.capacidade() );
        restaurante.horarioFuncionamento( restauranteRequest.horarioFuncionamento() );

        return restaurante.build();
    }

    @Override
    public RestauranteResponse toRestauranteResponse(Restaurante restaurante) {
        if ( restaurante == null ) {
            return null;
        }

        Long restauranteId = null;
        String nome = null;
        RestauranteResponse.Endereco endereco = null;
        String tipoDeCozinha = null;
        Integer capacidade = null;
        String horarioFuncionamento = null;

        restauranteId = restaurante.getRestauranteId();
        nome = restaurante.getNome();
        endereco = enderecoToEndereco2( restaurante.getEndereco() );
        if ( restaurante.getTipoDeCozinha() != null ) {
            tipoDeCozinha = restaurante.getTipoDeCozinha().name();
        }
        capacidade = restaurante.getCapacidade();
        horarioFuncionamento = restaurante.getHorarioFuncionamento();

        RestauranteResponse restauranteResponse = new RestauranteResponse( restauranteId, nome, endereco, tipoDeCozinha, capacidade, horarioFuncionamento );

        return restauranteResponse;
    }

    protected Endereco enderecoToEndereco(CadastrarRestauranteRequest.Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        Endereco.EnderecoBuilder endereco1 = Endereco.builder();

        endereco1.numero( endereco.numero() );
        endereco1.complemento( endereco.complemento() );
        endereco1.cep( endereco.cep() );

        return endereco1.build();
    }

    protected Endereco enderecoToEndereco1(AtualizarRestauranteRequest.Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        Endereco.EnderecoBuilder endereco1 = Endereco.builder();

        endereco1.numero( endereco.numero() );
        endereco1.complemento( endereco.complemento() );
        endereco1.cep( endereco.cep() );

        return endereco1.build();
    }

    protected RestauranteResponse.Endereco enderecoToEndereco2(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        String logradouro = null;
        Integer numero = null;
        String complemento = null;
        String bairro = null;
        String cep = null;
        String cidade = null;
        String uf = null;

        logradouro = endereco.getLogradouro();
        numero = endereco.getNumero();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        cep = endereco.getCep();
        cidade = endereco.getCidade();
        uf = endereco.getUf();

        RestauranteResponse.Endereco endereco1 = new RestauranteResponse.Endereco( logradouro, numero, complemento, bairro, cep, cidade, uf );

        return endereco1;
    }
}
