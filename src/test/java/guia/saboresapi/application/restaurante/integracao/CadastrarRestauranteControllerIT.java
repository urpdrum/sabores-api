package guia.saboresapi.application.restaurante.integracao;

import com.fiap.tc.restaurantes.domain.input.restaurante.CadastrarRestauranteRequest;
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
class CadastrarRestauranteControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveCadastrarRestaurante() {
        CadastrarRestauranteRequest restauranteRequest = RestauranteHelper.gerarCadastrarRestauranteRequest();

        given()
                .contentType("application/json")
                .body(restauranteRequest)
                
        .when()
                .post("/restaurantes")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("schemas/restaurante/restaurante.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarRestaurante_CepNaoEncontrado() {
        CadastrarRestauranteRequest restauranteRequest = RestauranteHelper.gerarCadastrarRestauranteRequestComCepInexistente();

        given()
                .contentType("application/json")
                .body(restauranteRequest)
                
        .when()
                .post("/restaurantes")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarRestaurante_NomeNaoInformado() {
        CadastrarRestauranteRequest restauranteRequest = RestauranteHelper.gerarCadastrarRestauranteRequestComNomeNulo();

        given()
                .contentType("application/json")
                .body(restauranteRequest)
                
        .when()
                .post("/restaurantes")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarRestaurante_CapacidadeNaoInformado() {
        CadastrarRestauranteRequest restauranteRequest = RestauranteHelper.gerarCadastrarRestauranteRequestComCapacidadeNula();

        given()
                .contentType("application/json")
                .body(restauranteRequest)
                
        .when()
                .post("/restaurantes")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }
}
