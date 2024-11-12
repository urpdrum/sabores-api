package guia.saboresapi.domain.entity;

import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsuarioTest {
  @Test
  void deveCriarUsuario() {
    Usuario usuario = UsuarioHelper.gerarUsuarioValido();

    assertThat(usuario)
        .isNotNull()
        .isInstanceOf(Usuario.class);

    assertThat(usuario.getNome())
        .isNotEmpty()
        .isNotNull();

    assertThat(usuario.getEmail())
        .isNotEmpty()
        .isNotNull();

    assertThat(usuario.getTelefone())
        .isNotNull();

    assertThat(usuario.getSenha())
        .isNotEmpty()
        .isNotNull();
  }

  @Test
  void deveGerarExcecao_QuandoNomeVazio() {
    assertThatThrownBy(UsuarioHelper::gerarUsuarioComNomeVazio)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Nome não pode ser nulo ou vazio");
  }

  @Test
  void deveGerarExcecao_QuandoEmailForInvalido() {
    assertThatThrownBy(UsuarioHelper::gerarUsuarioComEmailInvalido)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Email deve ser válido");
  }

  @Test
  void deveGerarExcecao_QuandoSenhaForInvalida() {
    assertThatThrownBy(UsuarioHelper::gerarUsuarioComSenhaInvalida)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("A senha não corresponde aos padrões de segurança");
  }

  @Test
  void deveGerarUsuario_QuandoTelefoneForVazio() {
    assertThatThrownBy(UsuarioHelper::gerarUsuarioComSenhaInvalida)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("A senha não corresponde aos padrões de segurança");
  }
}
