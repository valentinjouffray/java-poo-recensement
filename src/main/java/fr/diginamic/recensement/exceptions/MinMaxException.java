package fr.diginamic.recensement.exceptions;

public class MinMaxException extends FunctionalException {

	public MinMaxException() {
		super("Le maximum doit être supérieur au minimum.");
	}
}
