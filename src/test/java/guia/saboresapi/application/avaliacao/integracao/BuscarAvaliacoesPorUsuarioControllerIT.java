package guia.saboresapi.application.avaliacao.integracao;

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
class BuscarAvaliacoesPorUsuarioControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirBuscarAvaliacoesPorUsuario() {
        var id = 1L;

        given()
                .param("usuarioId", id)
                
        .when()
                .get("/avaliacoes/usuario")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/avaliacao/avaliacaoResponseList.schema.json"))
                ;
    }
}
