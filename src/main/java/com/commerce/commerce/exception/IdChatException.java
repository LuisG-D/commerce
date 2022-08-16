package com.commerce.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IdChatException extends RuntimeException{

    public IdChatException(long resource){
        super(String.format("No se ha encontrado el mensaje con id %s", resource));
    }
}