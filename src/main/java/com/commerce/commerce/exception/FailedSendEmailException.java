package com.commerce.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FailedSendEmailException extends ResponseStatusException {
    public FailedSendEmailException(String message){
        super(HttpStatus.NOT_ACCEPTABLE, message);
    }
}
