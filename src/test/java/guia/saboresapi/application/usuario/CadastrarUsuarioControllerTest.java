package guia.saboresapi.application.usuario;

import com.fiap.tc.restaurantes.domain.entity.Usuario;
import com.fiap.tc.restaurantes.domain.input.usuario.CadastrarUsuarioRequest;
import com.fiap.tc.restaurantes.domain.mapper.usuario.UsuarioMapper;
import com.fiap.tc.restaurantes.domain.output.usuario.UsuarioResponse;
import com.fiap.tc.restaurantes.domain.usecase.usuario.CadastrarUsuarioUseCase;
import guia.saboresapi.utils.generic.JsonStringHelper;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CadastrarUsuarioControllerTest {
  @Mock
  private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
  @Mock
  private UsuarioMapper usuarioMapper;

  private MockMvc mockMvc;

  private AutoCloseable mock;

  @BeforeEach
  public void setUp() {
    mock = MockitoAnnotations.openMocks(this);
    CadastrarUsuarioController controller = new CadastrarUsuarioController(usuarioMapper, cadastrarUsuarioUseCase);
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .addFilter((request, response, chain) -> {
          response.setCharacterEncoding("UTF-8");
          chain.doFilter(request, response);
        })
        .build();
  }

  @AfterEach
  void tearDown() throws Exception {
    mock.close();
  }

  @Test
  void deveCadastrarUsuario() throws Exception {
    // Arrange
    Usuario usuario = UsuarioHelper.gerarUsuarioValido();
    UsuarioResponse usuarioResponse = UsuarioHelper.gerarUsuarioResponse();
    CadastrarUsuarioRequest usuarioRequest = UsuarioHelper.gerarCadastrarUsuarioRequest();

    URI uri = URI.create("http://localhost/usuarios/" + usuarioResponse.usuarioId());

    when(usuarioMapper.toUsuario(any(CadastrarUsuarioRequest.class))).thenReturn(usuario);
    when(cadastrarUsuarioUseCase.cadastrarUsuario(any(Usuario.class))).thenAnswer(answer -> answer.getArgument(0));
    when(usuarioMapper.toUsuarioResponse(any(Usuario.class))).thenReturn(usuarioResponse);

    // Act & Assert
    mockMvc.perform(post("/usuarios")
        .content(JsonStringHelper.asJsonString(usuarioRequest))
        .contentType(MediaType.APPLICATION_JSON)
    )
        
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", uri.toString()))
        .andExpect(jsonPath("$.usuarioId").value(usuarioResponse.usuarioId()))
        .andExpect(jsonPath("$.nome").value(usuarioResponse.nome()))
        .andExpect(jsonPath("$.email").value(usuarioResponse.email()))
        .andExpect(jsonPath("$.telefone").value(usuarioResponse.telefone()))
        .andExpect(jsonPath("$.senha").value(usuarioResponse.senha()));

    verify(usuarioMapper, times(1)).toUsuario(any(CadastrarUsuarioRequest.class));
    verify(cadastrarUsuarioUseCase, times(1)).cadastrarUsuario(any(Usuario.class));
    verify(usuarioMapper, times(1)).toUsuarioResponse(any(Usuario.class));
  }

}
