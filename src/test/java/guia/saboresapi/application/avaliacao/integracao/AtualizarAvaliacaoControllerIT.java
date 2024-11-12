package guia.saboresapi.application.avaliacao.integracao;

import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
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
class AtualizarAvaliacaoControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirAtualizarAvaliacao() {
        var id = 1L;
        var request = AvaliacaoHelper.gerarAtualizarAvaliacaoRequest();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/avaliacoes/{id}", id)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/avaliacao/avaliacaoResponse.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarAvaliacao_IdNaoEncontrado() {
        var id = 1502825L;
        var request = AvaliacaoHelper.gerarAtualizarAvaliacaoRequest();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/avaliacoes/{id}", id)
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarAvaliacao_NotaInvalida() {
        var id = 1L;
        var request = AvaliacaoHelper.gerarAtualizarAvaliacaoRequestComNotaInvalida();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/avaliacoes/{id}", id)
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

}
