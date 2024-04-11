package com.example.busbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONTINUE)
public class NoSuchMoneyException extends RuntimeException{

    public NoSuchMoneyException(String message) {
        super(message);
    }
}
