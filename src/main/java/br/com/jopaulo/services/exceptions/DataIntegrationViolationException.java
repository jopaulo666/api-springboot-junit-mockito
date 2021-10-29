package br.com.jopaulo.services.exceptions;

public class DataIntegrationViolationException extends RuntimeException {

	public DataIntegrationViolationException(String message) {
		super(message);
	}
}
