package guia.saboresapi.domain.usecase.reserva.integracao;

import com.fiap.tc.restaurantes.domain.entity.Reserva;
import com.fiap.tc.restaurantes.domain.exception.mesa.MesaNotFoundException;
import com.fiap.tc.restaurantes.domain.exception.usuario.UsuarioNotFoundException;
import com.fiap.tc.restaurantes.domain.usecase.reserva.CadastrarReservaUseCase;
import guia.saboresapi.utils.reserva.ReservaHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class CadastrarReservaUseCaseIT {

    @Autowired
    private CadastrarReservaUseCase cadastrarReservaUseCase;

    @Test
    void devePermitirCadastrarReserva() {
        var reserva = ReservaHelper.gerarReserva();

        var reservaObtida = cadastrarReservaUseCase.cadastrarReserva(reserva, reserva.getUsuario().getUsuarioId(), reserva.getMesa().getMesaId());

        assertThat(reservaObtida)
                .isNotNull()
                .isInstanceOf(Reserva.class);
        assertThat(reservaObtida.getReservaId()).isPositive();
        assertThat(reservaObtida.getUsuario().getUsuarioId()).isEqualTo(reserva.getUsuario().getUsuarioId());
        assertThat(reservaObtida.getMesa().getMesaId()).isEqualTo(reserva.getMesa().getMesaId());
        assertThat(reservaObtida.getStatus()).isEqualTo(reserva.getStatus());
        assertThat(reservaObtida.getDataInicio()).isEqualTo(reserva.getDataInicio());
        assertThat(reservaObtida.getDataFim()).isEqualTo(reserva.getDataFim());
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_UsuarioNaoEncontrado() {
        var reserva = ReservaHelper.gerarReserva();
        var usuarioId = 1234567L;
        var mesaId = reserva.getMesa().getMesaId();
        var mensagemException = "Usuário de id: " + usuarioId + " não encontrado.";
        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(UsuarioNotFoundException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_MesaNaoEncontrada() {
        var reserva = ReservaHelper.gerarReserva();
        var usuarioId = reserva.getUsuario().getUsuarioId();
        var mesaId = 1234567L;
        var mensagemException = "Mesa de id: " + mesaId + " não encontrada";
        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(MesaNotFoundException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataInicioAntesDeHoje() {
        var reserva = ReservaHelper.gerarReserva();
        var mesaId = reserva.getMesa().getMesaId();
        var usuarioId = reserva.getUsuario().getUsuarioId();
        reserva.setDataInicio(LocalDateTime.now().minusDays(1));
        var mensagemException = "Só é possível reservar para datas futuras.";

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataFimAntesDeHoje() {
        var reserva = ReservaHelper.gerarReserva();
        var mesaId = reserva.getMesa().getMesaId();
        var usuarioId = reserva.getUsuario().getUsuarioId();
        reserva.setDataFim(LocalDateTime.now().minusDays(1));
        var mensagemException = "Só é possível reservar para datas futuras.";

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataInicioMaiorQueDataFim() {
        var reserva = ReservaHelper.gerarReserva();
        var mesaId = reserva.getMesa().getMesaId();
        var usuarioId = reserva.getUsuario().getUsuarioId();
        reserva.setDataInicio(LocalDateTime.now().plusDays(2));
        reserva.setDataFim(LocalDateTime.now().plusDays(1));
        var mensagemException = "A Data inicio da reserva deve ser anterior a data fim.";

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_MesaJaReservada() {
        var reserva = ReservaHelper.gerarReserva();
        var mesaId = reserva.getMesa().getMesaId();
        var usuarioId = reserva.getUsuario().getUsuarioId();
        reserva.setDataInicio(LocalDateTime.of(2030,9,10,11,47,37));
        reserva.setDataFim(LocalDateTime.of(2030,9,10,12,47,37));
        var mensagemException = "A Mesa de id: " + reserva.getMesa().getMesaId() + " já está reservada no período selecionado.";

        assertThatThrownBy(() -> cadastrarReservaUseCase.cadastrarReserva(reserva, usuarioId, mesaId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(mensagemException);
    }
}
