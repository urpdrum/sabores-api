package guia.saboresapi.application.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.mapper.usuario.UsuarioMapper;
import guia.saboresapi.domain.output.usuario.UsuarioResponse;
import guia.saboresapi.domain.usecase.usuario.ListarUsuariosUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class ListarUsuariosController {

    private final UsuarioMapper usuarioMapper;
    private final ListarUsuariosUseCase listarUsuariosUseCase;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        List<Usuario> usuarios = listarUsuariosUseCase.listarUsuarios();
        List<UsuarioResponse> listaUsuarioResponse = usuarios.stream().map(usuarioMapper::toUsuarioResponse).toList();

        return ResponseEntity.ok(listaUsuarioResponse);
    }

}
