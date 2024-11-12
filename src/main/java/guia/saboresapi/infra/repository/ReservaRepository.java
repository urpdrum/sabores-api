package guia.saboresapi.infra.repository;


import guia.saboresapi.infra.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    @Query("SELECT r FROM ReservaEntity r WHERE r.usuarioEntity.usuarioId = :usuarioId")
    List<ReservaEntity> buscarReservasPorUsuario(Long usuarioId);

    @Query("SELECT r FROM ReservaEntity r WHERE r.mesaEntity.mesaId = :mesaId")
    List<ReservaEntity> buscarReservasPorMesa(Long mesaId);

    @Query("SELECT r FROM ReservaEntity r WHERE r.mesaEntity.mesaId = :mesaId AND (r.dataInicio < :dataFim AND r.dataFim > :dataInicio) AND r.status = 'ATIVA'")
    List<ReservaEntity> buscarReservasPorMesaEPeriodo(Long mesaId, LocalDateTime dataInicio, LocalDateTime dataFim);
}
