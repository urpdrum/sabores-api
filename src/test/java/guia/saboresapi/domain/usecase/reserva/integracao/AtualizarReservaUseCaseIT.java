package guia.saboresapi.domain.usecase.reserva.integracao;

import com.fiap.tc.restaurantes.domain.entity.Reserva;
import com.fiap.tc.restaurantes.domain.enums.StatusReservaEnum;
import com.fiap.tc.restaurantes.domain.exception.reserva.ReservaNotFoundException;
import com.fiap.tc.restaurantes.domain.usecase.reserva.AtualizarReservaUseCase;
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
class AtualizarReservaUseCaseIT {

    @Autowired
    private AtualizarReservaUseCase atualizarReservaUseCase;

    @Test
    void devePermitirAtualizarReserva() {
        var reserva = ReservaHelper.gerarReserva();
        var id = 1L;
        reserva.setStatus(StatusReservaEnum.INATIVA);

        var reservaAtualizada = atualizarReservaUseCase.atualizarReserva(id, reserva);

        assertThat(reservaAtualizada)
                .isNotNull()
                .isInstanceOf(Reserva.class);
        assertThat(reservaAtualizada.getReservaId()).isEqualTo(id);
        assertThat(reservaAtualizada.getStatus()).isEqualTo(reserva.getStatus());
        assertThat(reservaAtualizada.getDataInicio()).isEqualTo(reserva.getDataInicio());
        assertThat(reservaAtualizada.getDataFim()).isEqualTo(reserva.getDataFim());
        assertThat(reservaAtualizada.getUsuario().getUsuarioId()).isEqualTo(reserva.getUsuario().getUsuarioId());
        assertThat(reservaAtualizada.getMesa().getMesaId()).isEqualTo(reserva.getMesa().getMesaId());
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_IdNaoEncontrado() {
        var reserva = ReservaHelper.gerarReserva();
        var id = 1234568541L;
        var mensagemException = "Reserva de id: " + id + " não encontrada.";

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reserva))
                .isInstanceOf(ReservaNotFoundException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataInicioAntesDeHoje() {
        var reserva = ReservaHelper.gerarReserva();
        var id = 1L;
        reserva.setDataInicio(LocalDateTime.now().minusDays(1));
        var mensagemException = "Só é possível reservar para datas futuras.";

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reserva))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataFimAntesDeHoje() {
        var reserva = ReservaHelper.gerarReserva();
        var id = 1L;
        reserva.setDataFim(LocalDateTime.now().minusDays(1));
        var mensagemException = "Só é possível reservar para datas futuras.";

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reserva))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataInicioMaiorQueDataFim() {
        var reserva = ReservaHelper.gerarReserva();
        var id = 1L;
        reserva.setDataInicio(LocalDateTime.now().plusDays(2));
        reserva.setDataFim(LocalDateTime.now().plusDays(1));
        var mensagemException = "A Data inicio da reserva deve ser anterior a data fim.";

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reserva))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_MesaJaReservada() {

        var reserva = ReservaHelper.gerarReserva();
        var id = 2L;
        reserva.setDataInicio(LocalDateTime.of(2030,9,10,11,47,37));
        reserva.setDataFim(LocalDateTime.of(2030,9,10,12,47,37));
        var mensagemException = "A Mesa de id: " + reserva.getMesa().getMesaId() + " já está reservada no período selecionado.";

        assertThatThrownBy(() -> atualizarReservaUseCase.atualizarReserva(id, reserva))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(mensagemException);
    }
}
