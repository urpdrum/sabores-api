package guia.saboresapi.application.usuario.integracao;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class DeletarUsuarioControllerIT {
  @LocalServerPort
  private int port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  void deveDeletarUsuario() {
    Long id = 6L;
    when()
        .delete("/usuarios/{id}", id)
    .then()
        
        .statusCode(HttpStatus.OK.value())
        .body(matchesJsonSchemaInClasspath("schemas/usuario/usuarioDeletado.schema.json"));
  }

  @Test
  void deveGerarExcecao_QuandoDeletarUsuario_IdNaoExiste() {
    Long id = 300L;

    when()
        .delete("/usuarios/{id}", id)
    .then()
        .statusCode(HttpStatus.NOT_FOUND.value())
        .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
        ;
  }
}
