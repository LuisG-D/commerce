package com.commerce.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreateRoomException extends RuntimeException {
    public CreateRoomException(String resource){
        super(String.format("Chat ya existente %s", resource));
    }
}



