package com.jdb.personal.acc.api.exception;

public class NotUserException extends RuntimeException{

    public NotUserException() {
        super();
    }

    public NotUserException(String message) {
        super(message);
    }
}
