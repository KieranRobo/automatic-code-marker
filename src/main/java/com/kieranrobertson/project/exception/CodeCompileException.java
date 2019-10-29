package com.kieranrobertson.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Code failed to compile")
public class CodeCompileException extends RuntimeException {
    public CodeCompileException(String message) {
        super(message);
    }
}