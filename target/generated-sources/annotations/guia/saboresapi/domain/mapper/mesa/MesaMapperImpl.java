package guia.saboresapi.domain.mapper.mesa;

import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.input.mesa.AtualizarMesaRequest;
import guia.saboresapi.domain.input.mesa.CadastrarMesaRequest;
import guia.saboresapi.domain.output.mesa.MesaResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T15:25:29-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class MesaMapperImpl implements MesaMapper {

    @Override
    public Mesa toMesa(CadastrarMesaRequest cadastrarMesaRequest) {
        if ( cadastrarMesaRequest == null ) {
            return null;
        }

        Mesa mesa = new Mesa();

        mesa.setRestaurante( cadastrarMesaRequestToRestaurante( cadastrarMesaRequest ) );
        mesa.setQuantidadeAssentos( cadastrarMesaRequest.quantidadeAssentos() );

        return mesa;
    }

    @Override
    public Mesa toMesa(AtualizarMesaRequest atualizarMesaRequest) {
        if ( atualizarMesaRequest == null ) {
            return null;
        }

        Mesa mesa = new Mesa();

        mesa.setQuantidadeAssentos( atualizarMesaRequest.quantidadeAssentos() );

        return mesa;
    }

    @Override
    public MesaResponse toMesaResponse(Mesa mesa) {
        if ( mesa == null ) {
            return null;
        }

        Long id = null;
        int capacidade = 0;
        Long restauranteId = null;

        id = mesa.getMesaId();
        if ( mesa.getQuantidadeAssentos() != null ) {
            capacidade = mesa.getQuantidadeAssentos();
        }
        restauranteId = mesaRestauranteRestauranteId( mesa );

        MesaResponse mesaResponse = new MesaResponse( id, restauranteId, capacidade );

        return mesaResponse;
    }

    protected Restaurante cadastrarMesaRequestToRestaurante(CadastrarMesaRequest cadastrarMesaRequest) {
        if ( cadastrarMesaRequest == null ) {
            return null;
        }

        Restaurante.RestauranteBuilder restaurante = Restaurante.builder();

        restaurante.restauranteId( cadastrarMesaRequest.restauranteId() );

        return restaurante.build();
    }

    private Long mesaRestauranteRestauranteId(Mesa mesa) {
        if ( mesa == null ) {
            return null;
        }
        Restaurante restaurante = mesa.getRestaurante();
        if ( restaurante == null ) {
            return null;
        }
        Long restauranteId = restaurante.getRestauranteId();
        if ( restauranteId == null ) {
            return null;
        }
        return restauranteId;
    }
}
