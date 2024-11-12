package guia.saboresapi.domain.mapper.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.input.usuario.AtualizarUsuarioRequest;
import guia.saboresapi.domain.input.usuario.CadastrarUsuarioRequest;
import guia.saboresapi.domain.output.usuario.UsuarioResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T11:23:57-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toUsuario(CadastrarUsuarioRequest cadastrarUsuarioRequest) {
        if ( cadastrarUsuarioRequest == null ) {
            return null;
        }

        String nome = null;
        String email = null;
        String senha = null;
        String telefone = null;

        nome = cadastrarUsuarioRequest.nome();
        email = cadastrarUsuarioRequest.email();
        senha = cadastrarUsuarioRequest.senha();
        telefone = cadastrarUsuarioRequest.telefone();

        Usuario usuario = new Usuario( nome, email, senha, telefone );

        return usuario;
    }

    @Override
    public Usuario toUsuario(AtualizarUsuarioRequest atualizarUsuarioRequest) {
        if ( atualizarUsuarioRequest == null ) {
            return null;
        }

        String nome = null;
        String email = null;
        String senha = null;
        String telefone = null;

        nome = atualizarUsuarioRequest.nome();
        email = atualizarUsuarioRequest.email();
        senha = atualizarUsuarioRequest.senha();
        telefone = atualizarUsuarioRequest.telefone();

        Usuario usuario = new Usuario( nome, email, senha, telefone );

        return usuario;
    }

    @Override
    public UsuarioResponse toUsuarioResponse(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        Long usuarioId = null;
        String nome = null;
        String email = null;
        String senha = null;
        String telefone = null;

        usuarioId = usuario.getUsuarioId();
        nome = usuario.getNome();
        email = usuario.getEmail();
        senha = usuario.getSenha();
        telefone = usuario.getTelefone();

        UsuarioResponse usuarioResponse = new UsuarioResponse( usuarioId, nome, email, senha, telefone );

        return usuarioResponse;
    }
}
