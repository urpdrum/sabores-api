package guia.saboresapi.domain.usecase.avaliacao;


import guia.saboresapi.domain.gateway.avaliacao.CadastrarAvaliacaoInterface;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorIdUseCase;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CadastrarAvaliacaoUseCaseTest {

    private CadastrarAvaliacaoUseCase cadastrarAvaliacaoUseCase;

    @Mock
    private CadastrarAvaliacaoInterface cadastrarAvaliacaoInterface;

    @Mock
    private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    @Mock
    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        cadastrarAvaliacaoUseCase = new CadastrarAvaliacaoUseCase(cadastrarAvaliacaoInterface, buscarUsuarioPorIdUseCase, buscarRestaurantePorIdUseCase);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirCadastrarAvaliacao() {
        var avaliacao = AvaliacaoHelper.gerarAvaliacao();
        avaliacao.setAvaliacaoId(1L);
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(avaliacao.getRestaurante());
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenReturn(avaliacao.getUsuario());
        when(cadastrarAvaliacaoInterface.cadastraAvaliacao(any(Avaliacao.class))).thenAnswer(answer -> answer.getArgument(0));

        var avaliacaoArmazenada = cadastrarAvaliacaoUseCase.cadastrarAvaliacao(avaliacao, avaliacao.getRestaurante().getRestauranteId(), avaliacao.getUsuario().getUsuarioId());

        assertThat(avaliacaoArmazenada)
                .isInstanceOf(Avaliacao.class)
                .isNotNull();
        assertThat(avaliacaoArmazenada.getAvaliacaoId()).isEqualTo(avaliacao.getAvaliacaoId());
        assertThat(avaliacaoArmazenada.getNota()).isEqualTo(avaliacao.getNota());
        assertThat(avaliacaoArmazenada.getComentario()).isEqualTo(avaliacao.getComentario());
        assertThat(avaliacaoArmazenada.getDataAvaliacao()).isEqualTo(avaliacao.getDataAvaliacao());
        assertThat(avaliacaoArmazenada.getRestaurante()).isEqualTo(avaliacao.getRestaurante());
        assertThat(avaliacaoArmazenada.getUsuario()).isEqualTo(avaliacao.getUsuario());
        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(cadastrarAvaliacaoInterface, times(1)).cadastraAvaliacao(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarAvaliacao_UsuarioNaoEncontrado() {
        var avaliacao = AvaliacaoHelper.gerarAvaliacao();
        var restauranteId = avaliacao.getRestaurante().getRestauranteId();
        var usuarioId = avaliacao.getUsuario().getUsuarioId();
        avaliacao.setAvaliacaoId(1L);
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(avaliacao.getRestaurante());
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenThrow(new UsuarioNotFoundException("Usuário não encontrado"));
        when(cadastrarAvaliacaoInterface.cadastraAvaliacao(any(Avaliacao.class))).thenAnswer(answer -> answer.getArgument(0));

        assertThatThrownBy(() -> cadastrarAvaliacaoUseCase.cadastrarAvaliacao(avaliacao, restauranteId, usuarioId))
                .isInstanceOf(UsuarioNotFoundException.class)
                .hasMessage("Usuário não encontrado");
        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarRestaurantePorIdUseCase, never()).buscarRestaurantePorId(anyLong());
        verify(cadastrarAvaliacaoInterface, never()).cadastraAvaliacao(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarAvaliacao_RestauranteNaoEncontrado() {
        var avaliacao = AvaliacaoHelper.gerarAvaliacao();
        var restauranteId = avaliacao.getRestaurante().getRestauranteId();
        var usuarioId = avaliacao.getUsuario().getUsuarioId();
        avaliacao.setAvaliacaoId(1L);
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenThrow(new RestauranteNotFoundException("Restaurante não encontrado"));
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenReturn(avaliacao.getUsuario());
        when(cadastrarAvaliacaoInterface.cadastraAvaliacao(any(Avaliacao.class))).thenAnswer(answer -> answer.getArgument(0));

        assertThatThrownBy(() -> cadastrarAvaliacaoUseCase.cadastrarAvaliacao(avaliacao, restauranteId, usuarioId))
                .isInstanceOf(RestauranteNotFoundException.class)
                .hasMessage("Restaurante não encontrado");
        verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(anyLong());
        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(cadastrarAvaliacaoInterface, never()).cadastraAvaliacao(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarAvaliacao_NotaInvalida() {
        var avaliacao = AvaliacaoHelper.gerarAvaliacao();
        var restauranteId = avaliacao.getRestaurante().getRestauranteId();
        var usuarioId = avaliacao.getUsuario().getUsuarioId();
        avaliacao.setAvaliacaoId(1L);
        avaliacao.setNota(1000);
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(avaliacao.getRestaurante());
        when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(anyLong())).thenReturn(avaliacao.getUsuario());
        when(cadastrarAvaliacaoInterface.cadastraAvaliacao(any(Avaliacao.class))).thenAnswer(answer -> answer.getArgument(0));

        assertThatThrownBy(() -> cadastrarAvaliacaoUseCase.cadastrarAvaliacao(avaliacao, restauranteId, usuarioId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("A Nota deve ser entre 0 e 5");
    }
}
