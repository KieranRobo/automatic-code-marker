package com.kieranrobertson.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Challenge is invalid")
public class InvalidChallengeException extends RuntimeException {
    public InvalidChallengeException(String message) {
        super(message);
    }
}
