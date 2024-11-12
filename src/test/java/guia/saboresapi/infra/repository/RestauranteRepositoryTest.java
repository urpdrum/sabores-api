package guia.saboresapi.infra.repository;


import guia.saboresapi.infra.entity.RestauranteEntity;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestauranteRepositoryTest {

    @Mock
    public RestauranteRepository repository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class CadastraRestaurante {

        @Test
        void devePermitirCadastrarRestauranteEntity() {
            // Arrange
            RestauranteEntity entidade = RestauranteHelper.gerarRestauranteEntityValido();

            when(repository.save(any(RestauranteEntity.class)))
                .thenAnswer(answer -> answer.getArgument(0));

            // Act
            RestauranteEntity restauranteSalvo = repository.save(entidade);

            // Assert
            assertThat(restauranteSalvo)
                .isNotNull()
                .isInstanceOf(RestauranteEntity.class)
                .isEqualTo(entidade);

            assertThat(restauranteSalvo.getNome())
                .isEqualTo(entidade.getNome());

            verify(repository, times(1)).save(any(RestauranteEntity.class));
        }
    }

    @Nested
    class BuscarRestaurante {

        @Test
        void devePermitirBuscarRestauranteEntityPorId() {
            // Arrange
            Long id = 1L;

            RestauranteEntity entidade = RestauranteHelper.gerarRestauranteEntityValido();
            entidade.setRestauranteId(id);

            when(repository.findById(id))
                .thenReturn(Optional.of(entidade));

            // Act
            Optional<RestauranteEntity> restauranteEntityOptional = repository.findById(id);

            // Assert
            assertThat(restauranteEntityOptional)
                .isPresent()
                .containsSame(entidade);

            restauranteEntityOptional.ifPresent(restauranteEntity -> {
                assertThat(restauranteEntity.getRestauranteId()).isEqualTo(id);

                assertThat(restauranteEntity.getNome())
                    .isEqualTo(entidade.getNome());
            });

            verify(repository, times(1)).findById(any(Long.class));
        }

        @Test
        void devePermitirListarRestauranteEntity() {
            // Arrange
            RestauranteEntity entidade1 = RestauranteHelper.gerarRestauranteEntityValido();
            RestauranteEntity entidade2 = RestauranteHelper.gerarRestauranteEntityValido();

            List<RestauranteEntity> restauranteEntities = Arrays.asList(entidade1, entidade2);

            when(repository.findAll())
                    .thenReturn(restauranteEntities);

            // Act
            List<RestauranteEntity> restauranteEntityList = repository.findAll();

            // Assert
            assertThat(restauranteEntityList)
                    .hasSize(2)
                    .containsExactlyInAnyOrder(entidade1, entidade2);

            verify(repository, times(1)).findAll();
        }

        @Test
        void findByNomeContaining() {
            // Arrange
            RestauranteEntity entidade1 = RestauranteHelper.gerarRestauranteEntityValido();
            RestauranteEntity entidade2 = RestauranteHelper.gerarRestauranteEntityValido();

            String nome = "Nome Teste";

            List<RestauranteEntity> restauranteEntities = Arrays.asList(entidade1, entidade2);

            when(repository.findByNomeContaining(any()))
                    .thenReturn(restauranteEntities);

            List<RestauranteEntity> restauranteEntityList = repository.findByNomeContaining(nome);

            // Assert
            assertThat(restauranteEntityList)
                    .hasSize(2)
                    .containsExactlyInAnyOrder(entidade1, entidade2);

            assertThat(restauranteEntityList.get(0).getNome())
                    .isEqualTo(entidade1.getNome());

            assertThat(restauranteEntityList.get(1).getNome())
                    .isEqualTo(entidade1.getNome());

            verify(repository, times(1)).findByNomeContaining(any());
        }

        @Test
        void findByTipoDeCozinha() {
            // Arrange
            RestauranteEntity entidade1 = RestauranteHelper.gerarRestauranteEntityValido();
            RestauranteEntity entidade2 = RestauranteHelper.gerarRestauranteEntityValido();

            String tipoCozinha = "MEXICANA";

            List<RestauranteEntity> restauranteEntities = Arrays.asList(entidade1, entidade2);

            when(repository.findByTipoDeCozinha(any()))
                    .thenReturn(restauranteEntities);

            List<RestauranteEntity> restauranteEntityList = repository.findByTipoDeCozinha(tipoCozinha);

            // Assert
            assertThat(restauranteEntityList)
                    .hasSize(2)
                    .containsExactlyInAnyOrder(entidade1, entidade2);

            assertThat(restauranteEntityList.get(0).getTipoDeCozinha())
                    .isEqualTo(entidade1.getTipoDeCozinha());

            assertThat(restauranteEntityList.get(1).getTipoDeCozinha())
                    .isEqualTo(entidade1.getTipoDeCozinha());

            verify(repository, times(1)).findByTipoDeCozinha(any());
        }

        @Test
        void findByLocalidade() {
            // Arrange
            RestauranteEntity entidade1 = RestauranteHelper.gerarRestauranteEntityValido();
            RestauranteEntity entidade2 = RestauranteHelper.gerarRestauranteEntityValido();

            String localidade = "logradouro teste";

            List<RestauranteEntity> restauranteEntities = Arrays.asList(entidade1, entidade2);

            when(repository.findByLocalidade(any()))
                    .thenReturn(restauranteEntities);

            List<RestauranteEntity> restauranteEntityList = repository.findByLocalidade(localidade);

            // Assert
            assertThat(restauranteEntityList)
                    .hasSize(2)
                    .containsExactlyInAnyOrder(entidade1, entidade2);

            assertThat(restauranteEntityList.get(0).getEndereco().getLogradouro()).isEqualTo(localidade);

            verify(repository, times(1)).findByLocalidade(any());
        }
    }

    @Nested
    class DeletarRestaurante {
        @Test
        void devePermitirDeletarRestauranteEntity() {
            // Arrange
            Long id = 1L;

            doNothing().when(repository).deleteById(any(Long.class));

            // Act
            repository.deleteById(id);

            // Assert
            verify(repository, times(1)).deleteById(any(Long.class));
        }
    }
}