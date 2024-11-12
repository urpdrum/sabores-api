package guia.saboresapi.domain.input.avaliacao;

public record AtualizarAvaliacaoRequest(
        Integer nota,
        String comentario
) {
}
