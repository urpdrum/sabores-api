package guia.saboresapi.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "avaliacao")
public class AvaliacaoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long avaliacaoId;

  @ManyToOne
  @JoinColumn(name = "restauranteId")
  private RestauranteEntity restauranteEntity;

  @ManyToOne
  @JoinColumn(name = "usuarioId")
  private UsuarioEntity usuarioEntity;

  private Integer nota;
  private String comentario;

  @Builder.Default
  private LocalDateTime dataAvaliacao = LocalDateTime.now();
}
