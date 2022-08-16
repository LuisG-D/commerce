package com.commerce.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class EqualUserException extends RuntimeException {
    public EqualUserException(String resource){
        super(String.format("Imposible crear chat con %s", resource));
    }
}