package br.com.nimex.api.APIRestNimex.exception.dto;
import java.util.List;

public record FullRestValidationError(Integer status, List<RestValidationError> errors) {
    
}
