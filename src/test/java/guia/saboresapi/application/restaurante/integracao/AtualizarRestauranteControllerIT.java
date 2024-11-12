package guia.saboresapi.application.restaurante.integracao;

import com.fiap.tc.restaurantes.domain.input.restaurante.AtualizarRestauranteRequest;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
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
class AtualizarRestauranteControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveAtualizarRestaurante() {
        AtualizarRestauranteRequest restauranteRequest = RestauranteHelper.gerarAtualizarRestauranteRequest();
        Long id = 2L;

        given()
                .contentType("application/json")
                .body(restauranteRequest)
                
        .when()
                .put("/restaurantes/{id}", id)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/restaurante/restaurante.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_IdNaoEncontrado() {
        Long id = 116515L;
        var request = RestauranteHelper.gerarAtualizarRestauranteRequest();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/restaurantes/{id}", id)
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_CepNaoEncontrado() {
        Long id = 1L;
        var request = RestauranteHelper.gerarAtualizarRestauranteRequestComCepInexistente();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/restaurantes/{id}", id)
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_NomeNaoInformado() {
        Long id = 1L;
        var request = RestauranteHelper.gerarAtualizarRestauranteRequestComNomeNulo();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/restaurantes/{id}", id)
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_CapacidadeNaoInformado() {
        Long id = 1L;
        var request = RestauranteHelper.gerarAtualizarRestauranteRequestComCapacidadeNula();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/restaurantes/{id}", id)
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }
}
