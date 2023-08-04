package com.pokedex.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(HttpServletRequest servletRequest, RuntimeException runtimeException){
        logger.error(runtimeException.getMessage(),runtimeException);
        var map = Map.of("message","Unknown error occured");
        return  new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {

        var map = Map.of("message","Username or password is wrong!");
        return  new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PokedexDatabaseException.class)
    public ResponseEntity<?> handlePokedexDatabaseException(HttpServletRequest servletRequest, PokedexDatabaseException runtimeException){
        logger.error(runtimeException.getMessage(),runtimeException);
        var map = Map.of("message",runtimeException.getMessage());
        return  new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
