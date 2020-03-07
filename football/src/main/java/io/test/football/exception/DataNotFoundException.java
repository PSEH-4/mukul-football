package io.test.football.exception;

import io.test.football.exception.util.ErrorMessages;

public class DataNotFoundException extends Exception{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new data not found exception
	 */
	public DataNotFoundException() {
		super();
	}

	/**
	 * Instantiates a new data not found exception
	 *
	 * @param message the message
	 */
	public DataNotFoundException(String message) {
		super(ErrorMessages.DATA_NOT_FOUND.getErrorCode() + message);
	}

	/**
	 * Instantiates a new data not found exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DataNotFoundException(String message, Throwable cause) {
		super(ErrorMessages.DATA_NOT_FOUND.getErrorCode() + message, cause);
	}
}
