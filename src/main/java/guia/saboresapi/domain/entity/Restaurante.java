package guia.saboresapi.domain.entity;


import guia.saboresapi.domain.enums.TipoCozinhaEnum;
import lombok.Builder;

import java.util.List;

@Builder
public class Restaurante {

    private Long restauranteId;
    private String nome;
    private Endereco endereco;
    private TipoCozinhaEnum tipoDeCozinha;
    private Integer capacidade;
    private String horarioFuncionamento;
    private List<Mesa> mesas;

    public Restaurante(Long restauranteId, String nome, Endereco endereco, TipoCozinhaEnum tipoDeCozinha, Integer capacidade, String horarioFuncionamento, List<Mesa> mesas) {
        this.restauranteId = restauranteId;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoDeCozinha = tipoDeCozinha;
        this.capacidade = capacidade;
        this.horarioFuncionamento = horarioFuncionamento;
        this.mesas = mesas;
    }


    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoCozinhaEnum getTipoDeCozinha() {
        return tipoDeCozinha;
    }

    public void setTipoDeCozinha(TipoCozinhaEnum tipoDeCozinha) {
        this.tipoDeCozinha = tipoDeCozinha;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
}
