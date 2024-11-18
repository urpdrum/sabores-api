package guia.saboresapi.infra.repository.mapper;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.infra.entity.UsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T15:12:13-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UsuarioEntityMapperImpl implements UsuarioEntityMapper {

    @Override
    public UsuarioEntity toUsuarioEntity(Usuario usuario) {
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

    @Override
    public Usuario toUsuario(UsuarioEntity usuarioEntity) {
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
}
