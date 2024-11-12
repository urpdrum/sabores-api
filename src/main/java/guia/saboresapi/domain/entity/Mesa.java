package guia.saboresapi.domain.entity;

public class Mesa {
    private Long mesaId;
    private Restaurante restaurante;
    private Integer quantidadeAssentos;


    public Mesa(Long mesaId, Restaurante restaurante, Integer quantidadeAssentos) {
        this.mesaId = mesaId;
        this.restaurante = restaurante;
        this.quantidadeAssentos = quantidadeAssentos;
    }

    public Mesa() {
    }

    public Long getMesaId() {
        return mesaId;
    }

    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Integer getQuantidadeAssentos() {
        return quantidadeAssentos;
    }

    public void setQuantidadeAssentos(Integer quantidadeAssentos) {
        this.quantidadeAssentos = quantidadeAssentos;
    }
}

