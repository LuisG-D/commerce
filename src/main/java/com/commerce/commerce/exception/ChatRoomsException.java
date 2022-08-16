package com.commerce.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ChatRoomsException extends RuntimeException{

    public ChatRoomsException(long resource){
        super(String.format("No se ha encontrado chatrooms con id %s",resource));
    }

}
