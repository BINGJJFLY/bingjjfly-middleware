package com.wjz.message;

public class MessageException extends RuntimeException {

	private static final long serialVersionUID = 7334133481154806071L;

	public MessageException() {
		super();
	}

	public MessageException(String message) {
		super(message);
	}

	public MessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageException(Throwable cause) {
		super(cause);
	}
}
