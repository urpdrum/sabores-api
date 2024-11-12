package guia.saboresapi.infra.repository.mapper;

import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.infra.entity.MesaEntity;
import guia.saboresapi.infra.entity.ReservaEntity;
import guia.saboresapi.infra.entity.UsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T11:23:57-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class ReservaEntityMapperImpl implements ReservaEntityMapper {

    @Override
    public Reserva toReserva(ReservaEntity reservaEntity) {
        if ( reservaEntity == null ) {
            return null;
        }

        Reserva reserva = new Reserva();

        reserva.setUsuario( usuarioEntityToUsuario( reservaEntity.getUsuarioEntity() ) );
        reserva.setMesa( mesaEntityToMesa( reservaEntity.getMesaEntity() ) );
        reserva.setReservaId( reservaEntity.getReservaId() );
        reserva.setStatus( reservaEntity.getStatus() );
        reserva.setDataInicio( reservaEntity.getDataInicio() );
        reserva.setDataFim( reservaEntity.getDataFim() );

        return reserva;
    }

    @Override
    public ReservaEntity toReservaEntity(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }

        ReservaEntity.ReservaEntityBuilder reservaEntity = ReservaEntity.builder();

        reservaEntity.usuarioEntity( usuarioToUsuarioEntity( reserva.getUsuario() ) );
        reservaEntity.mesaEntity( mesaToMesaEntity( reserva.getMesa() ) );
        reservaEntity.reservaId( reserva.getReservaId() );
        reservaEntity.status( reserva.getStatus() );
        reservaEntity.dataInicio( reserva.getDataInicio() );
        reservaEntity.dataFim( reserva.getDataFim() );

        return reservaEntity.build();
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

    protected Mesa mesaEntityToMesa(MesaEntity mesaEntity) {
        if ( mesaEntity == null ) {
            return null;
        }

        Mesa mesa = new Mesa();

        mesa.setMesaId( mesaEntity.getMesaId() );
        mesa.setQuantidadeAssentos( mesaEntity.getQuantidadeAssentos() );

        return mesa;
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

    protected MesaEntity mesaToMesaEntity(Mesa mesa) {
        if ( mesa == null ) {
            return null;
        }

        MesaEntity mesaEntity = new MesaEntity();

        mesaEntity.setMesaId( mesa.getMesaId() );
        mesaEntity.setQuantidadeAssentos( mesa.getQuantidadeAssentos() );

        return mesaEntity;
    }
}
