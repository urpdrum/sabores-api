package guia.saboresapi.domain.entity;



import guia.saboresapi.domain.enums.StatusReservaEnum;

import java.time.LocalDateTime;

public class Reserva {

    private Long reservaId;
    private Mesa mesa;
    private Usuario usuario;
    private StatusReservaEnum status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public Reserva(Long reservaId, Mesa mesa, Usuario usuario, StatusReservaEnum status, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.reservaId = reservaId;
        this.mesa = mesa;
        this.usuario = usuario;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Reserva() {
    }

    public Long getReservaId() {
        return reservaId;
    }

    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusReservaEnum getStatus() {
        return status;
    }

    public void setStatus(StatusReservaEnum status) {
        this.status = status;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
}
