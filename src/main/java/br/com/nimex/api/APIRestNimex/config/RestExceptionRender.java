package br.com.nimex.api.APIRestNimex.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.nimex.api.APIRestNimex.exception.RestNotFoundException;
import br.com.nimex.api.APIRestNimex.exception.dto.RestException;
import br.com.nimex.api.APIRestNimex.exception.dto.RestMethodError;
import br.com.nimex.api.APIRestNimex.exception.dto.RestNotFoundError;
import br.com.nimex.api.APIRestNimex.exception.dto.RestValidationError;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionRender {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<RestValidationError>> ConstraintViolationExceptionHandler(ConstraintViolationException e) {

        List<RestValidationError> errors = new ArrayList<>();

        e.getConstraintViolations().forEach((v) -> {
            errors.add(new RestValidationError(v.getPropertyPath().toString(), v.getMessage()));
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RestValidationError>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<RestValidationError> errors = new ArrayList<>();
        e.getFieldErrors().forEach(v -> errors.add(new RestValidationError(v.getField(), v.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestMethodError> HttpRequestMethodNotSupportedExceptionHadler(HttpRequestMethodNotSupportedException e) {
        var error = new RestMethodError(e.getStatusCode().value(),e.getMethod(), e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestException> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        var error = new RestException(HttpStatus.BAD_REQUEST.value(),e.getCause().getMessage(), "erro de constraint.");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(RestNotFoundException.class) 
    public ResponseEntity<RestNotFoundError> RestNotFoundExceptionHandler(RestNotFoundException e) {
        var error = new RestNotFoundError(e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }

}
