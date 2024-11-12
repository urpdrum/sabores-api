package guia.saboresapi.infra.config.reserva;


import guia.saboresapi.domain.gateway.reserva.CadastrarReservaInterface;
import guia.saboresapi.domain.usecase.mesa.BuscarMesaPorIdUseCase;
import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorMesaEPeriodoUseCase;
import guia.saboresapi.domain.usecase.reserva.CadastrarReservaUseCase;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastrarReservaConfig {
    @Bean
    CadastrarReservaUseCase cadastrarReservaUseCase(CadastrarReservaInterface cadastrarReservaInterface,
                                                    BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
                                                    BuscarMesaPorIdUseCase buscarMesaPorIdUseCase,
                                                    BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase) {
        return new CadastrarReservaUseCase(cadastrarReservaInterface, buscarUsuarioPorIdUseCase, buscarMesaPorIdUseCase, buscarReservasPorMesaEPeriodoUseCase);
    }
}
