package io.sultanov.accountservice.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class PasswordException extends RuntimeException {

    public PasswordException() {
        super();
    }

    public PasswordException(String message) {
        super(message);
    }
}
