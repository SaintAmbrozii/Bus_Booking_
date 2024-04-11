package com.example.busbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONTINUE)
public class BookingException extends RuntimeException{

    public BookingException(String message) {
        super(message);
    }
}

