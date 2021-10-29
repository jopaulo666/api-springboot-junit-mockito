package br.com.jopaulo.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(String message) {
		super(message);
	}
}
