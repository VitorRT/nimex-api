package br.com.nimex.api.APIRestNimex.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.nimex.api.APIRestNimex.exception.RestNotFoundException;
import br.com.nimex.api.APIRestNimex.exception.dto.FullRestValidationError;
import br.com.nimex.api.APIRestNimex.exception.dto.RestException;
import br.com.nimex.api.APIRestNimex.exception.dto.RestMethodError;
import br.com.nimex.api.APIRestNimex.exception.dto.RestShortException;
import br.com.nimex.api.APIRestNimex.exception.dto.RestValidationError;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionRender {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<FullRestValidationError> ConstraintViolationExceptionHandler(ConstraintViolationException e) {

        List<RestValidationError> errors = new ArrayList<>();

        e.getConstraintViolations().forEach((v) -> {
            errors.add(new RestValidationError(v.getPropertyPath().toString(), v.getMessage()));
        });

        FullRestValidationError error = new FullRestValidationError(HttpStatus.BAD_REQUEST.value(), errors);

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FullRestValidationError> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        List<RestValidationError> errors = new ArrayList<>();
        
        e.getFieldErrors().forEach(v -> errors.add(new RestValidationError(v.getField(), v.getDefaultMessage())));

        FullRestValidationError error = new FullRestValidationError(HttpStatus.BAD_REQUEST.value(), errors);

        return ResponseEntity.badRequest().body(error);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestMethodError> HttpRequestMethodNotSupportedExceptionHadler(HttpRequestMethodNotSupportedException e) {
        var error = new RestMethodError(e.getStatusCode().value(),e.getMethod(), e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestException> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        var error = new RestException(HttpStatus.BAD_REQUEST.value(),e.getCause().getMessage(), e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(RestNotFoundException.class) 
    public ResponseEntity<RestShortException> RestNotFoundExceptionHandler(RestNotFoundException e) {
        var error = new RestShortException(e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class) 
    public ResponseEntity<RestShortException> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        var error = new RestShortException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<RestShortException> InvalidDataAccessResourceUsageExceptionHandler(InvalidDataAccessResourceUsageException e) {
        var error = new RestShortException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Dados de acesso ao banco inválidos.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<RestShortException> HttpMessageConversionExceptionHandler(HttpMessageConversionException e) {
        var error = new RestShortException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

}
