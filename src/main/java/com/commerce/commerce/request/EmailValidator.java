package com.commerce.commerce.request;


import com.commerce.commerce.exception.EmailAlreadyExistsException;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

@Service
@AllArgsConstructor


public class EmailValidator implements Predicate<String> {


    @Override
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public boolean test(String email) {
        if (email.contains("@") && email.contains(".")){
            return true;}


        throw new EmailAlreadyExistsException("Invalid email address");
    }
}