package com.example.demo.models.errors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error(HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<Error> handleEntityNotFound(EntityNotFoundException ex) {
        Error error = new Error(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<Error> handleRuntimeException(RuntimeException re) {
        Error error = new Error(HttpStatus.SERVICE_UNAVAILABLE, "Validation Error", re.getMessage());
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
