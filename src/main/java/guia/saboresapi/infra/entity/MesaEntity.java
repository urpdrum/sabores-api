package guia.saboresapi.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mesa")
public class MesaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mesaId;

  @ManyToOne
  @JoinColumn(name = "restaurante_id", nullable = false)
  private RestauranteEntity restauranteEntity;

  @Column(nullable = false)
  private Integer quantidadeAssentos;
}
