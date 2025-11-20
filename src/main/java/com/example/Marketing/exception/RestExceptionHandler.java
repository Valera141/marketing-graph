package com.example.Marketing.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.EntityNotFoundException;

// Importa las excepciones de JWT
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class RestExceptionHandler {

    // --- ESTE BLOQUE ES EL IMPORTANTE ---
    // Atrapa las excepciones comunes de JWT y las convierte en un 401
    @ExceptionHandler({ 
        ExpiredJwtException.class, 
        SignatureException.class, 
        MalformedJwtException.class,
        IllegalArgumentException.class // Atrapa errores de token nulo/vacío
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleJwtExceptions(Exception ex) {
        return Map.of(
            "status", 401,
            "error", "Token JWT inválido o expirado",
            "message", ex.getMessage()
        );
    }
    // ------------------------------------

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(EntityNotFoundException ex) {
        return Map.of(
            "status", 404,
            "error", ex.getMessage()
        );
    }
    
    // (Puedes añadir más manejadores de MethodArgumentNotValidException, etc.)
}