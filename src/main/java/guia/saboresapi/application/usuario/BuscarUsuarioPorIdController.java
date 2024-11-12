package guia.saboresapi.application.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.mapper.usuario.UsuarioMapper;
import guia.saboresapi.domain.output.usuario.UsuarioResponse;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class BuscarUsuarioPorIdController {

    private final UsuarioMapper usuarioMapper;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuario(@PathVariable Long id) {
        Usuario usuarioBuscado = buscarUsuarioPorIdUseCase.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioMapper.toUsuarioResponse(usuarioBuscado));
    }
}
