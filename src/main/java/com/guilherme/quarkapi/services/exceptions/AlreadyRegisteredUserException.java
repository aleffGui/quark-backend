package com.guilherme.quarkapi.services.exceptions;

public class AlreadyRegisteredUserException  extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AlreadyRegisteredUserException(String msg) {
		super(msg);
	}
	
	public AlreadyRegisteredUserException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
