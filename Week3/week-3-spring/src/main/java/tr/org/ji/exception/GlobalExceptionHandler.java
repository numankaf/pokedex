package tr.org.ji.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(HttpServletRequest servletRequest, MethodArgumentNotValidException ex){
        logger.error(ex.getMessage(),ex);
        var map = Map.of("message","Validator error occured");
        return  new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
