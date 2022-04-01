package com.jdb.personal.acc.api.exception;

public class NotCategoryException extends RuntimeException{

	private static final long serialVersionUID = -3055340673139702611L;

	public NotCategoryException() {
        super();
    }

    public NotCategoryException(String message) {
        super(message);
    }
}
