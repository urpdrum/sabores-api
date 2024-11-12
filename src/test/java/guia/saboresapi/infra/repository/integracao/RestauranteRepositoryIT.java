package guia.saboresapi.infra.repository.integracao;


import guia.saboresapi.infra.entity.RestauranteEntity;
import guia.saboresapi.infra.repository.RestauranteRepository;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RestauranteRepositoryIT {

    @Autowired
    RestauranteRepository repository;

    @Nested
    class CadastrarRestaurante {
        @Test
        void devePermitirCadastrarRestaurante(){
            // Arrange
            RestauranteEntity entidade = RestauranteHelper.gerarRestauranteEntityValido();

            // Act
            RestauranteEntity restauranteSalvo = repository.save(entidade);

            // Assert
            assertThat(restauranteSalvo)
                    .isNotNull()
                    .isInstanceOf(RestauranteEntity.class);

            assertThat(restauranteSalvo.getNome())
                    .isEqualTo(entidade.getNome());
            assertThat(restauranteSalvo.getEndereco().getLogradouro())
                    .isEqualTo(entidade.getEndereco().getLogradouro());
        }
    }

    @Nested
    class BuscarRestaurantes{

        @Test
        void devePermitirBuscarRestauranteEntityPorId() {
            var id = 1L;

            var restauranteObtidoOpt = repository.findById(id);

            assertThat(restauranteObtidoOpt).isPresent();
            restauranteObtidoOpt.ifPresent(restaurante -> {
                assertThat(restaurante)
                        .isNotNull()
                        .isInstanceOf(RestauranteEntity.class);
            });
        }

        @Test
        void devePermitirListarRestauranteEntity() {
            // Act
            List<RestauranteEntity> restauranteEntityList = repository.findAll();

            // Assert
            assertThat(restauranteEntityList)
                    .hasSizeGreaterThan(2);
        }

        @Test
        void devePermitirBuscarRestauranteEntityPorNome() {
            // Arrange
            String nome = "restaurante teste 2";

            // Act
            List<RestauranteEntity> restaurantes = repository.findByNomeContaining(nome);

            // Assert
            assertThat(restaurantes).isNotNull();
            assertThat(nome).isEqualTo(restaurantes.get(0).getNome());
        }

        @Test
        void findByTipoDeCozinha() {
            var tipoCozinha = "MEXICANA";

            List<RestauranteEntity> restauranteEntities = repository.findByTipoDeCozinha(tipoCozinha);

            assertThat(restauranteEntities)
                .isNotEmpty()
                    .hasSize(2);
        }

        @Test
        void findByLocalidade() {
            var logradouro = "logradouro teste 2";

            List<RestauranteEntity> restauranteEntities = repository.findByLocalidade(logradouro);

            assertThat(restauranteEntities)
                .isNotEmpty()
                .hasSize(1);
        }
    }

    @Nested
    class DeletarRestaurante{
        @Test
        void devePermitirDeletarRestauranteEntity() {
            // Arrange
            Long id = 3L;

            // Act
            repository.deleteById(id);
            Optional<RestauranteEntity> restauranteExcluido = repository.findById(id);

            assertThat(restauranteExcluido).isNotPresent();
        }
    }
}
