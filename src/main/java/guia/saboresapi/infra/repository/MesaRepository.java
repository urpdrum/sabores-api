package guia.saboresapi.infra.repository;


import guia.saboresapi.infra.entity.MesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<MesaEntity, Long> {
    @Query("SELECT m FROM MesaEntity m WHERE m.restauranteEntity.restauranteId = :restauranteId")
    List<MesaEntity> findByRestaurante(Long restauranteId);
}
