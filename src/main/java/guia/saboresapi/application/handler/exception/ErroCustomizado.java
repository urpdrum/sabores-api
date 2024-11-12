package guia.saboresapi.application.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ErroCustomizado {
  private final Instant horario;
  private final String erro;
  private final Integer status;
  private final String rota;

}
