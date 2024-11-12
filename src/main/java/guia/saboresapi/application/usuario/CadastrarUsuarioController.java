package guia.saboresapi.application.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.input.usuario.CadastrarUsuarioRequest;
import guia.saboresapi.domain.mapper.usuario.UsuarioMapper;
import guia.saboresapi.domain.output.usuario.UsuarioResponse;
import guia.saboresapi.domain.usecase.usuario.CadastrarUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
  public class CadastrarUsuarioController {

    private final UsuarioMapper usuarioMapper;
    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody CadastrarUsuarioRequest cadastrarUsuarioRequest) {
      Usuario usuario = usuarioMapper.toUsuario(cadastrarUsuarioRequest);

      Usuario usuarioCadastrado = cadastrarUsuarioUseCase.cadastrarUsuario(usuario);

      UsuarioResponse usuarioResponse = usuarioMapper.toUsuarioResponse(usuarioCadastrado);

      URI uri = ServletUriComponentsBuilder
          .fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(usuarioResponse.usuarioId())
          .toUri();

      return ResponseEntity.created(uri).body(usuarioResponse);
    }
  }
