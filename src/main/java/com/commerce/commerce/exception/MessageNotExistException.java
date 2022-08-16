package com.commerce.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MessageNotExistException extends RuntimeException{
    public MessageNotExistException(String message){
        super(String.format("No se encontraron mensajes"));
    }
}
