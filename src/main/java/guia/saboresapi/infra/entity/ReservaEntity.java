package guia.saboresapi.infra.entity;


import guia.saboresapi.domain.enums.StatusReservaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "reserva")
public class ReservaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reservaId;

  @ManyToOne
  @JoinColumn(name = "mesaId")
  private MesaEntity mesaEntity;

  @ManyToOne
  @JoinColumn(name = "usuarioId")
  private UsuarioEntity usuarioEntity;

  @Enumerated(EnumType.STRING)
  private StatusReservaEnum status;

  private LocalDateTime dataInicio;

  private LocalDateTime dataFim;
}
