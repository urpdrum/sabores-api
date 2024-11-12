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
class CadastrarAvaliacaoControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirCadastrarAvaliacao() {
        var request = AvaliacaoHelper.gerarCadastrarAvaliacaoRequest();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/avaliacoes")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("schemas/avaliacao/avaliacaoResponse.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarAvaliacao_RestauranteNaoEncontrado() {
        var request = AvaliacaoHelper.gerarCadastrarAvaliacaoRequestRestauranteInexistente();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/avaliacoes")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarAvaliacao_UsuarioNaoEncontrado() {
        var request = AvaliacaoHelper.gerarCadastrarAvaliacaoRequestUsuarioInexistente();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/avaliacoes")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarAvaliacao_NotaInvalida() {
        var request = AvaliacaoHelper.gerarCadastrarAvaliacaoRequestNotaInvalida();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/avaliacoes")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }
}
