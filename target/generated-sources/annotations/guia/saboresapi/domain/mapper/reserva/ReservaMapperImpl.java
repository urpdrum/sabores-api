package guia.saboresapi.domain.mapper.reserva;

import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.enums.StatusReservaEnum;
import guia.saboresapi.domain.input.reserva.AtualizarReservaRequest;
import guia.saboresapi.domain.input.reserva.CadastrarReservaRequest;
import guia.saboresapi.domain.output.reserva.ReservaResponse;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T12:35:54-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class ReservaMapperImpl implements ReservaMapper {

    @Override
    public Reserva toReserva(CadastrarReservaRequest request) {
        if ( request == null ) {
            return null;
        }

        Reserva reserva = new Reserva();

        reserva.setDataInicio( request.dataInicio() );
        reserva.setDataFim( request.dataFim() );

        return reserva;
    }

    @Override
    public Reserva toReserva(AtualizarReservaRequest request) {
        if ( request == null ) {
            return null;
        }

        Reserva reserva = new Reserva();

        reserva.setStatus( request.status() );
        reserva.setDataInicio( request.dataInicio() );
        reserva.setDataFim( request.dataFim() );

        return reserva;
    }

    @Override
    public ReservaResponse toReservaResponse(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }

        Long usuarioId = null;
        Long mesaId = null;
        Long reservaId = null;
        StatusReservaEnum status = null;
        LocalDateTime dataInicio = null;
        LocalDateTime dataFim = null;

        usuarioId = reservaUsuarioUsuarioId( reserva );
        mesaId = reservaMesaMesaId( reserva );
        reservaId = reserva.getReservaId();
        status = reserva.getStatus();
        dataInicio = reserva.getDataInicio();
        dataFim = reserva.getDataFim();

        ReservaResponse reservaResponse = new ReservaResponse( reservaId, mesaId, usuarioId, status, dataInicio, dataFim );

        return reservaResponse;
    }

    private Long reservaUsuarioUsuarioId(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }
        Usuario usuario = reserva.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Long usuarioId = usuario.getUsuarioId();
        if ( usuarioId == null ) {
            return null;
        }
        return usuarioId;
    }

    private Long reservaMesaMesaId(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }
        Mesa mesa = reserva.getMesa();
        if ( mesa == null ) {
            return null;
        }
        Long mesaId = mesa.getMesaId();
        if ( mesaId == null ) {
            return null;
        }
        return mesaId;
    }
}
