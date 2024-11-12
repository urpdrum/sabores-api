package guia.saboresapi.domain.entity;

import java.time.LocalDateTime;

public class Avaliacao {

    private Long avaliacaoId;
    private Restaurante restaurante;
    private Usuario usuario;
    private Integer nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;

    public Avaliacao(Long avaliacaoId, Restaurante restaurante, Usuario usuario, Integer nota, String comentario, LocalDateTime dataAvaliacao) {
        this.avaliacaoId = avaliacaoId;
        this.restaurante = restaurante;
        this.usuario = usuario;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    public Avaliacao() {
    }

    public Long getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(Long avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}
