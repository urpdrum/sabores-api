package guia.saboresapi.domain.input.mesa;

public record CadastrarMesaRequest(
    Long restauranteId,
    Integer quantidadeAssentos
) {
}
