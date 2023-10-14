package com.guilherme.quarkapi.services.exceptions;

public class TaskCompletedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public TaskCompletedException(String msg) {
		super(msg);
	}
	
	public TaskCompletedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
