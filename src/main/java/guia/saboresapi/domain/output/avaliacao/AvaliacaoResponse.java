package guia.saboresapi.domain.output.avaliacao;

public record AvaliacaoResponse(
        Long avaliacaoId,
        Long restauranteId,
        Long usuarioId,
        Integer nota,
        String comentario
) {

}
