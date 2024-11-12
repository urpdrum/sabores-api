package guia.saboresapi.domain.output.restaurante;


public record RestauranteResponse(
        Long restauranteId,
        String nome,
           Endereco endereco,
           String tipoDeCozinha,
           Integer capacidade,
           String horarioFuncionamento) {
    public record Endereco(
            String logradouro,
            Integer numero,
            String complemento,
            String bairro,
            String cep,
            String cidade,
            String uf
            ) {
    }
}
