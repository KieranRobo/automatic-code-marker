package com.kieranrobertson.project.model;

/**
 * Respond with these errors as opposed to HTTP errors/exceptions when
 * the user didn't necesarrily do anything wrong, but the request can't
 * be completed.
 */
public class APIErrorResponse extends APIResponse {

    public APIErrorResponse(String errorCode, String message) {
        super(errorCode, message);
    }
}
