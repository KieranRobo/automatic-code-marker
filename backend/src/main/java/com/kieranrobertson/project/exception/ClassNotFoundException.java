package com.kieranrobertson.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Class does not exist")
public class ClassNotFoundException extends RuntimeException {
    public ClassNotFoundException(String message) {
        super(message);
    }
}
