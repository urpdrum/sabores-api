package guia.saboresapi.infra.repository;


import guia.saboresapi.infra.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
  List<RestauranteEntity> findByNomeContaining(String nome);
  List<RestauranteEntity> findByTipoDeCozinha(String tipoCozinhaEnum);

  @Query("SELECT r FROM RestauranteEntity r WHERE r.endereco.logradouro LIKE %:logradouro%")
  List<RestauranteEntity> findByLocalidade(@Param("logradouro") String logradouro);
}
