package guia.saboresapi.utils.usuario;


import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.input.usuario.AtualizarUsuarioRequest;
import guia.saboresapi.domain.input.usuario.CadastrarUsuarioRequest;
import guia.saboresapi.domain.output.usuario.UsuarioDeletadoResponse;
import guia.saboresapi.domain.output.usuario.UsuarioResponse;
import guia.saboresapi.infra.entity.UsuarioEntity;

public class UsuarioHelper {
  public static UsuarioDeletadoResponse gerarUsuarioDeletadoReponse() {
    return new UsuarioDeletadoResponse(true);
  }

  public static CadastrarUsuarioRequest gerarCadastrarUsuarioRequest() {
    String nome = "Lucas";
    String email = "lucas@mail.com";
    String telefone = "000000000";
    String senha = "aA@4b7c8";
    return new CadastrarUsuarioRequest(nome, email, senha, telefone);
  }

  public static UsuarioResponse gerarUsuarioResponse() {
    Long id = 1L;
    String nome = "Lucas";
    String email = "lucas@mail.com";
    String telefone = "000000000";
    String senha = "aA@4b7c8";
    return new UsuarioResponse(id, nome, email, senha, telefone);
  }

  public static UsuarioResponse gerarUsuarioResponseAtualizado() {
    Long id = 1L;
    String nome = "João";
    String email = "lucas@mail.com";
    String telefone = "00000000001";
    String senha = "bB@7aw85";
    return new UsuarioResponse(id, nome, email, senha, telefone);
  }

  public static AtualizarUsuarioRequest gerarAtualizarUsuarioRequest() {
    String nome = "João";
    String email = "lucas@mail.com";
    String telefone = "00000000001";
    String senha = "bB@7aw85";
    return new AtualizarUsuarioRequest(nome, email, senha, telefone);
  }


  public static Usuario gerarUsuarioValidoComId() {
    Long id = 1L;
    String nome = "Lucas";
    String email = "lucas@mail.com";
    String telefone = "000000000";
    String senha = "aA@4b7c8";
    Usuario usuario = new Usuario(nome, email, senha, telefone);
    usuario.setUsuarioId(id);
    return usuario;
  }

  public static Usuario gerarUsuarioValido() {
    String nome = "Lucas";
    String email = "lucas@mail.com";
    String telefone = "000000000";
    String senha = "aA@4b7c8";
    return  new Usuario(nome, email, senha, telefone);
  }

  public static UsuarioEntity gerarUsuarioEntity() {
    String nome = "Lucas";
    String email = "lucas@mail.com";
    String telefone = "000000000";
    String senha = "aA@4b7c8";
    return  new UsuarioEntity(nome, email, senha, telefone);
  }

  public static Usuario gerarUsuarioComNomeVazio() {
    String nome = "";
    String email = "lucas@mail.com";
    String telefone = "000000000";
    String senha = "aA@4b7c";
    return  new Usuario(nome, email, senha, telefone);
  }

  public static Usuario gerarUsuarioComEmailInvalido() {
    String nome = "Lucas";
    String email = "lucas.com";
    String telefone = "000000000";
    String senha = "aA@4b7c";
    return  new Usuario(nome, email, senha, telefone);
  }

  public static Usuario gerarUsuarioComSenhaInvalida() {
    String nome = "Lucas";
    String email = "lucas@mail.com";
    String telefone = "000000000";
    String senha = "123senhainvalida";
    return  new Usuario(nome, email, senha, telefone);
  }

  public static Usuario gerarUsuarioComTelefoneVazio() {
    String nome = "Lucas";
    String email = "lucas@mail.com";
    String telefone = "";
    String senha = "123senhainvalida";
    return  new Usuario(nome, email, senha, telefone);
  }
}
