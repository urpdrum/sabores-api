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
class DeletarAvaliacaoControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirDeletarAvaliacao() {
        var id = 3L;

        given()
                
        .when()
                .delete("/avaliacoes/{id}", id)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/avaliacao/avaliacaoDeletadaResponse.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoDeletarAvaliacao_IdNaoEncontrado() {
        var id = 1502825L;

        given()
                
        .when()
                .delete("/avaliacoes/{id}", id)
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }
}
