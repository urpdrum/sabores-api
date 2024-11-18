package guia.saboresapi.domain.mapper.avaliacao;

import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.input.avaliacao.AtualizarAvaliacaoRequest;
import guia.saboresapi.domain.input.avaliacao.CadastrarAvaliacaoRequest;
import guia.saboresapi.domain.output.avaliacao.AvaliacaoResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T12:35:53-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class AvaliacaoMapperImpl implements AvaliacaoMapper {

    @Override
    public Avaliacao toAvaliacao(CadastrarAvaliacaoRequest avaliacaoRequest) {
        if ( avaliacaoRequest == null ) {
            return null;
        }

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setNota( avaliacaoRequest.nota() );
        avaliacao.setComentario( avaliacaoRequest.comentario() );

        return avaliacao;
    }

    @Override
    public Avaliacao toAvaliacao(AtualizarAvaliacaoRequest avaliacaoRequest) {
        if ( avaliacaoRequest == null ) {
            return null;
        }

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setNota( avaliacaoRequest.nota() );
        avaliacao.setComentario( avaliacaoRequest.comentario() );

        return avaliacao;
    }

    @Override
    public AvaliacaoResponse toAvaliacaoResponse(Avaliacao avaliacao) {
        if ( avaliacao == null ) {
            return null;
        }

        Long restauranteId = null;
        Long usuarioId = null;
        Long avaliacaoId = null;
        Integer nota = null;
        String comentario = null;

        restauranteId = avaliacaoRestauranteRestauranteId( avaliacao );
        usuarioId = avaliacaoUsuarioUsuarioId( avaliacao );
        avaliacaoId = avaliacao.getAvaliacaoId();
        nota = avaliacao.getNota();
        comentario = avaliacao.getComentario();

        AvaliacaoResponse avaliacaoResponse = new AvaliacaoResponse( avaliacaoId, restauranteId, usuarioId, nota, comentario );

        return avaliacaoResponse;
    }

    private Long avaliacaoRestauranteRestauranteId(Avaliacao avaliacao) {
        if ( avaliacao == null ) {
            return null;
        }
        Restaurante restaurante = avaliacao.getRestaurante();
        if ( restaurante == null ) {
            return null;
        }
        Long restauranteId = restaurante.getRestauranteId();
        if ( restauranteId == null ) {
            return null;
        }
        return restauranteId;
    }

    private Long avaliacaoUsuarioUsuarioId(Avaliacao avaliacao) {
        if ( avaliacao == null ) {
            return null;
        }
        Usuario usuario = avaliacao.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Long usuarioId = usuario.getUsuarioId();
        if ( usuarioId == null ) {
            return null;
        }
        return usuarioId;
    }
}
