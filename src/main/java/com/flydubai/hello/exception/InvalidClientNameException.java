package com.flydubai.hello.exception;

/**
 * The Class InvalidClientNameException.
 * 
 * @author ashwanipratap
 */
public class InvalidClientNameException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6556784101400202988L;

	/**
	 * Instantiates a new invalid client name exception.
	 *
	 * @param message the message
	 */
	public InvalidClientNameException(String message) {
		super(message);
	}
}
