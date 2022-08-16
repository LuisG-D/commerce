package com.commerce.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ChatroomsNotExistException extends RuntimeException{
    public ChatroomsNotExistException(String message){
        super(String.format("No se encontraron ChatRooms"));
    }
}