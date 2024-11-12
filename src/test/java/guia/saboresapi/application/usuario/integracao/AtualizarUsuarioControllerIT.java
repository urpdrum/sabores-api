package guia.saboresapi.application.usuario.integracao;


import guia.saboresapi.domain.input.usuario.AtualizarUsuarioRequest;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class AtualizarUsuarioControllerIT {
  @LocalServerPort
  private int port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  void deveAtualizarUsuario() {
    AtualizarUsuarioRequest usuario = UsuarioHelper.gerarAtualizarUsuarioRequest();
    Long id = 4L;

    given()
        .contentType("application/json")
        .body(usuario)
        
    .when()
        .put("/usuarios/{id}", id)
    .then()
        .statusCode(HttpStatus.OK.value())
        .body(matchesJsonSchemaInClasspath("schemas/usuario/usuario.schema.json"))
        ;

  }

  @Test
  void deveGerarExcecao_QuandoAtualizaUsuario_IdNaoExiste() {
    AtualizarUsuarioRequest usuario = UsuarioHelper.gerarAtualizarUsuarioRequest();
    Long id = 300L;

    given()
        .contentType("application/json")
        .body(usuario)
        
    .when()
        .put("/usuarios/{id}", id)
    .then()
        .statusCode(HttpStatus.NOT_FOUND.value())
        .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
        ;
  }
}
