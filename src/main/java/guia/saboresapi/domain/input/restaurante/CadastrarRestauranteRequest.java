package guia.saboresapi.domain.input.restaurante;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastrarRestauranteRequest(
        @NotBlank String nome,
        @NotBlank Endereco endereco,
        @NotBlank String tipoDeCozinha,
        @NotNull Integer capacidade,
        @NotBlank String horarioFuncionamento
) {
    public record Endereco(
            @NotNull Integer numero,
            @NotBlank String complemento,
            @NotBlank @Pattern(regexp = "\\d{8}",
                    message = "O CEP deve conter somente número com 8 posições") String cep
            ) {
    }
}
