package com.example.CalencareApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeJaExisteException extends RuntimeException{
    public EntidadeJaExisteException() {
        super("Entidade já existe!");
    }
}
