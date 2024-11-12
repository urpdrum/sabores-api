package guia.saboresapi.domain.usecase.avaliacao;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.entity.validation.avaliacao.AvaliacaoValidator;
import guia.saboresapi.domain.gateway.avaliacao.CadastrarAvaliacaoInterface;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorIdUseCase;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;

public class CadastrarAvaliacaoUseCase {

    private final CadastrarAvaliacaoInterface cadastrarAvaliacaoInterface;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    public CadastrarAvaliacaoUseCase(CadastrarAvaliacaoInterface cadastrarAvaliacaoInterface, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase, BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase) {
        this.cadastrarAvaliacaoInterface = cadastrarAvaliacaoInterface;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.buscarRestaurantePorIdUseCase = buscarRestaurantePorIdUseCase;
    }

    public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao, Long restauranteId, Long usuarioId) {

        //Valida se Usuario existe
        Usuario usuario = buscarUsuarioPorIdUseCase.buscarUsuarioPorId(usuarioId);
        //Valida se Restaurate existe
        Restaurante restaurante = buscarRestaurantePorIdUseCase.buscarRestaurantePorId(restauranteId);

        avaliacao.setUsuario(usuario);
        avaliacao.setRestaurante(restaurante);

        AvaliacaoValidator.validate(avaliacao);

        return cadastrarAvaliacaoInterface.cadastraAvaliacao(avaliacao);
    }
}
