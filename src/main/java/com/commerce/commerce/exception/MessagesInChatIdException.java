package com.commerce.commerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MessagesInChatIdException extends RuntimeException{
    public MessagesInChatIdException(long resource){
        super(String.format("No se han encontrado mensajes en el chat %s",resource));
    }
}
