package com.jdb.personal.acc.api.exception;

public class NotCycleException extends RuntimeException{

	private static final long serialVersionUID = -4267026250391046879L;

	public NotCycleException() {
		super();
	}

	public NotCycleException(String message) {
		super(message);
	}
}
