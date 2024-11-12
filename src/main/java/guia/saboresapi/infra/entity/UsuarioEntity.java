package guia.saboresapi.infra.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class UsuarioEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long usuarioId;
  @NonNull
  private String nome;
  @NonNull
  private String email;
  @NonNull
  private String senha;
  @NonNull
  private String telefone;
}
