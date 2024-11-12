package guia.saboresapi.application.handler;

import guia.saboresapi.application.handler.exception.ErroCustomizado;
import guia.saboresapi.domain.exception.avaliacao.AvaliacaoNotFoundException;
import guia.saboresapi.domain.exception.mesa.MesaNotFoundException;
import guia.saboresapi.domain.exception.reserva.ReservaNotFoundException;
import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.exception.usuario.UsuarioNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestauranteNotFoundException.class)
    public ResponseEntity<ErroCustomizado> restauranteNotFoundException(RestauranteNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroCustomizado erro = new ErroCustomizado(
                Instant.now(),
                ex.getMessage(),
                status.value(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(erro, status);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ErroCustomizado> handleUsuarioNotFoundException(UsuarioNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroCustomizado erro = new ErroCustomizado(
                Instant.now(),
                ex.getMessage(),
                status.value(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(erro, status);
    }

    @ExceptionHandler(AvaliacaoNotFoundException.class)
    public ResponseEntity<ErroCustomizado> handleAvaliacaoNotFoundException(AvaliacaoNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroCustomizado erro = new ErroCustomizado(
                Instant.now(),
                ex.getMessage(),
                status.value(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(erro, status);
    }

    @ExceptionHandler(MesaNotFoundException.class)
    public ResponseEntity<ErroCustomizado> handleMesaNotFoundException(MesaNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroCustomizado erro = new ErroCustomizado(
                Instant.now(),
                ex.getMessage(),
                status.value(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(erro, status);
    }

    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<ErroCustomizado> handleReservaNotFoundException(ReservaNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroCustomizado erro = new ErroCustomizado(
                Instant.now(),
                ex.getMessage(),
                status.value(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(erro, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroCustomizado> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroCustomizado erro = new ErroCustomizado(
                Instant.now(),
                ex.getMessage(),
                status.value(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(erro, status);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErroCustomizado> handleIllegalStateException(IllegalStateException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroCustomizado erro = new ErroCustomizado(
                Instant.now(),
                ex.getMessage(),
                status.value(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(erro, status);
    }
}
