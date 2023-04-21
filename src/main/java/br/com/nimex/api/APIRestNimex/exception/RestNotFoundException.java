package br.com.nimex.api.APIRestNimex.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestNotFoundException extends RuntimeException {
    private Integer status;

    public RestNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND.value();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
