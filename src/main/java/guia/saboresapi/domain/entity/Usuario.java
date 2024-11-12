package guia.saboresapi.domain.entity;


import guia.saboresapi.domain.entity.validation.usuario.EmailValidator;
import guia.saboresapi.domain.entity.validation.usuario.PasswordValidator;

public class Usuario {
  private Long usuarioId;
  private String nome;
  private String email;
  private String senha;
  private String telefone;

  public Usuario(String nome, String email, String senha, String telefone) {
    if(nome == null || nome.isEmpty()) {
      throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
    }
    if(email == null || email.isEmpty() || !EmailValidator.isValid(email)) {
      throw new IllegalArgumentException("Email deve ser válido");
    }
    if(senha == null || senha.isEmpty() || !PasswordValidator.isValid(senha)) {
      throw new IllegalArgumentException("A senha não corresponde aos padrões de segurança");
    }
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.telefone = telefone;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
}
