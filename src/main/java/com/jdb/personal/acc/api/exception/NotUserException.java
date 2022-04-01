package com.jdb.personal.acc.api.exception;

public class NotUserException extends RuntimeException{

	private static final long serialVersionUID = -1576927294990272382L;

	public NotUserException() {
        super();
    }

    public NotUserException(String message) {
        super(message);
    }
}
