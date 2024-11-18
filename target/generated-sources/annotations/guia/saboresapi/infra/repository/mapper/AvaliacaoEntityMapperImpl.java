package guia.saboresapi.infra.repository.mapper;

import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.enums.TipoCozinhaEnum;
import guia.saboresapi.infra.entity.AvaliacaoEntity;
import guia.saboresapi.infra.entity.EnderecoEntity;
import guia.saboresapi.infra.entity.MesaEntity;
import guia.saboresapi.infra.entity.RestauranteEntity;
import guia.saboresapi.infra.entity.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T11:57:59-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class AvaliacaoEntityMapperImpl implements AvaliacaoEntityMapper {

    @Override
    public Avaliacao toAvaliacao(AvaliacaoEntity avaliacaoEntity) {
        if ( avaliacaoEntity == null ) {
            return null;
        }

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setRestaurante( restauranteEntityToRestaurante( avaliacaoEntity.getRestauranteEntity() ) );
        avaliacao.setUsuario( usuarioEntityToUsuario( avaliacaoEntity.getUsuarioEntity() ) );
        avaliacao.setAvaliacaoId( avaliacaoEntity.getAvaliacaoId() );
        avaliacao.setNota( avaliacaoEntity.getNota() );
        avaliacao.setComentario( avaliacaoEntity.getComentario() );
        avaliacao.setDataAvaliacao( avaliacaoEntity.getDataAvaliacao() );

        return avaliacao;
    }

    @Override
    public AvaliacaoEntity toAvaliacaoEntity(Avaliacao avaliacao) {
        if ( avaliacao == null ) {
            return null;
        }

        AvaliacaoEntity.AvaliacaoEntityBuilder avaliacaoEntity = AvaliacaoEntity.builder();

        avaliacaoEntity.restauranteEntity( restauranteToRestauranteEntity( avaliacao.getRestaurante() ) );
        avaliacaoEntity.usuarioEntity( usuarioToUsuarioEntity( avaliacao.getUsuario() ) );
        avaliacaoEntity.avaliacaoId( avaliacao.getAvaliacaoId() );
        avaliacaoEntity.nota( avaliacao.getNota() );
        avaliacaoEntity.comentario( avaliacao.getComentario() );
        avaliacaoEntity.dataAvaliacao( avaliacao.getDataAvaliacao() );

        return avaliacaoEntity.build();
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

    protected Usuario usuarioEntityToUsuario(UsuarioEntity usuarioEntity) {
        if ( usuarioEntity == null ) {
            return null;
        }

        String nome = null;
        String email = null;
        String senha = null;
        String telefone = null;

        nome = usuarioEntity.getNome();
        email = usuarioEntity.getEmail();
        senha = usuarioEntity.getSenha();
        telefone = usuarioEntity.getTelefone();

        Usuario usuario = new Usuario( nome, email, senha, telefone );

        usuario.setUsuarioId( usuarioEntity.getUsuarioId() );

        return usuario;
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

    protected MesaEntity mesaToMesaEntity(Mesa mesa) {
        if ( mesa == null ) {
            return null;
        }

        MesaEntity mesaEntity = new MesaEntity();

        mesaEntity.setMesaId( mesa.getMesaId() );
        mesaEntity.setQuantidadeAssentos( mesa.getQuantidadeAssentos() );

        return mesaEntity;
    }

    protected List<MesaEntity> mesaListToMesaEntityList(List<Mesa> list) {
        if ( list == null ) {
            return null;
        }

        List<MesaEntity> list1 = new ArrayList<MesaEntity>( list.size() );
        for ( Mesa mesa : list ) {
            list1.add( mesaToMesaEntity( mesa ) );
        }

        return list1;
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
        restauranteEntity.setMesas( mesaListToMesaEntityList( restaurante.getMesas() ) );

        return restauranteEntity;
    }

    protected UsuarioEntity usuarioToUsuarioEntity(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setUsuarioId( usuario.getUsuarioId() );
        usuarioEntity.setNome( usuario.getNome() );
        usuarioEntity.setEmail( usuario.getEmail() );
        usuarioEntity.setSenha( usuario.getSenha() );
        usuarioEntity.setTelefone( usuario.getTelefone() );

        return usuarioEntity;
    }
}
