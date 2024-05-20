package com.theara.User.exception;

public class ExceptionNotFound extends RuntimeException{
    private final String message;
    private final int statusCode;

    public ExceptionNotFound(String message, int statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
