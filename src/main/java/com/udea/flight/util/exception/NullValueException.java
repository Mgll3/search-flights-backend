package com.udea.flight.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NullValueException extends RuntimeException{
    public NullValueException(String message) {
        super(message);
    }

    public NullValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
