package fr.diginamic.recensement.exceptions;

public class NombreIncorrectException extends FunctionalException {

	public NombreIncorrectException() {
		super("La population doit être un chiffre.");
	}
}
