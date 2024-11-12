package guia.saboresapi.utils.restaurante;



import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.enums.TipoCozinhaEnum;
import guia.saboresapi.domain.input.restaurante.AtualizarRestauranteRequest;
import guia.saboresapi.domain.input.restaurante.CadastrarRestauranteRequest;
import guia.saboresapi.domain.output.restaurante.RestauranteResponse;
import guia.saboresapi.infra.entity.EnderecoEntity;
import guia.saboresapi.infra.entity.RestauranteEntity;

import java.util.List;

public class RestauranteHelper {
    public static CadastrarRestauranteRequest.Endereco gerarCadastrarEnderecoRequest(){
        return new CadastrarRestauranteRequest.Endereco(
                1,
            "apto 1",
                "01001001"
        );
    }

    public static CadastrarRestauranteRequest.Endereco gerarCadastrarEnderecoRequestComCepInexistente(){
        return new CadastrarRestauranteRequest.Endereco(
                1,
                "apto 1",
                "12345678"
        );
    }

    public static AtualizarRestauranteRequest.Endereco gerarAtualizarEnderecoRequest(){
        return new AtualizarRestauranteRequest.Endereco(
                1,
                "apto 1",
                "01001001"
        );
    }

    public static AtualizarRestauranteRequest.Endereco gerarAtualizarEnderecoRequestComCepInexistente(){
        return new AtualizarRestauranteRequest.Endereco(
                1,
                "apto 1",
                "12345678"
        );
    }

    public static RestauranteResponse.Endereco gerarEnderecoResponse() {
        return new RestauranteResponse.Endereco(
                "Logradouro teste",
                1,
                "apto 1",
                "Bairro teste",
                "01001001",
                "São Paulo",
                "SP"
        );
    }

    public static CadastrarRestauranteRequest gerarCadastrarRestauranteRequest() {
        String nome = "Nome Teste";
        Integer capacidade = 100;
        String horarioFuncionamento = "10 as 11";
        return new CadastrarRestauranteRequest(
            nome,
                gerarCadastrarEnderecoRequest(),
            "MEXICANA",
            capacidade,
            horarioFuncionamento);
    }

    public static CadastrarRestauranteRequest gerarCadastrarRestauranteRequestComNomeNulo() {
        String nome = null;
        Integer capacidade = 100;
        String horarioFuncionamento = "10 as 11";
        return new CadastrarRestauranteRequest(
                nome,
                gerarCadastrarEnderecoRequest(),
                "MEXICANA",
                capacidade,
                horarioFuncionamento);
    }

    public static CadastrarRestauranteRequest gerarCadastrarRestauranteRequestComCapacidadeNula() {
        String nome = "Teste";
        Integer capacidade = null;
        String horarioFuncionamento = "10 as 11";
        return new CadastrarRestauranteRequest(
                nome,
                gerarCadastrarEnderecoRequest(),
                "MEXICANA",
                capacidade,
                horarioFuncionamento);
    }

    public static CadastrarRestauranteRequest gerarCadastrarRestauranteRequestComCepInexistente() {
        String nome = "Nome Teste";
        Integer capacidade = 100;
        String horarioFuncionamento = "10 as 11";
        return new CadastrarRestauranteRequest(
                nome,
                gerarCadastrarEnderecoRequestComCepInexistente(),
                "MEXICANA",
                capacidade,
                horarioFuncionamento);
    }

    public static AtualizarRestauranteRequest gerarAtualizarRestauranteRequest() {
        String nome = "Nome Teste";
        Integer capacidade = 100;
        String horarioFuncionamento = "10 as 11";
        return new AtualizarRestauranteRequest(
            nome,
                gerarAtualizarEnderecoRequest(),
            "MEXICANA",
            capacidade,
            horarioFuncionamento);
    }

    public static AtualizarRestauranteRequest gerarAtualizarRestauranteRequestComNomeNulo() {
        String nome = null;
        Integer capacidade = 100;
        String horarioFuncionamento = "10 as 11";
        return new AtualizarRestauranteRequest(
                nome,
                gerarAtualizarEnderecoRequest(),
                "MEXICANA",
                capacidade,
                horarioFuncionamento);
    }

    public static AtualizarRestauranteRequest gerarAtualizarRestauranteRequestComCapacidadeNula() {
        String nome = "Teste";
        Integer capacidade = null;
        String horarioFuncionamento = "10 as 11";
        return new AtualizarRestauranteRequest(
                nome,
                gerarAtualizarEnderecoRequest(),
                "MEXICANA",
                capacidade,
                horarioFuncionamento);
    }

    public static AtualizarRestauranteRequest gerarAtualizarRestauranteRequestComCepInexistente() {
        String nome = "Nome Teste";
        Integer capacidade = 100;
        String horarioFuncionamento = "10 as 11";
        return new AtualizarRestauranteRequest(
                nome,
                gerarAtualizarEnderecoRequestComCepInexistente(),
                "MEXICANA",
                capacidade,
                horarioFuncionamento);
    }

    public static RestauranteEntity gerarRestauranteEntityValido() {
        String nome = "Nome Teste";
        Integer capacidade = 100;
        String tipoCozinhaEnum = "MEXICANA";
        String horarioFuncionamento = "10 as 11";
        return new RestauranteEntity(1L, nome,
            enderecoEntityBuilder(),
            tipoCozinhaEnum, capacidade, horarioFuncionamento);
    }

    public static Restaurante gerarRestauranteValido() {
        String nome = "Nome Teste";
        Integer capacidade = 100;
        String horarioFuncionamento = "10 as 11";
        return new Restaurante(1L, nome,
            enderecoBuilder(),
            TipoCozinhaEnum.MEXICANA, capacidade, horarioFuncionamento, List.of());
    }

    public static RestauranteResponse gerarRestauranteResponse(){
        String nome = "Nome Teste";
        Integer capacidade = 100;
        String tipoCozinhaEnum = "MEXICANA";
        String horarioFuncionamento = "10 as 11";

        return new RestauranteResponse(
            1L, nome, gerarEnderecoResponse(), tipoCozinhaEnum
            , capacidade, horarioFuncionamento);
    }

    public static EnderecoEntity enderecoEntityBuilder(){
        return EnderecoEntity.builder()
            .bairro("bairro teste")
            .cidade("cidade teste")
            .complemento("apto 1")
            .cep("00000000")
            .logradouro("logradouro teste")
            .numero(1)
            .uf("IT")
            .build();
    }

    public static Endereco enderecoBuilder(){
        return Endereco.builder()
            .bairro("bairro teste")
            .cidade("cidade teste")
            .complemento("apto 1")
            .cep("01001001")
            .logradouro("logradouro teste")
            .numero(1)
            .uf("IT")
            .build();
    }
}
