package fr.diginamic.recensement.exceptions;

public class NombreNegatifException extends FunctionalException {

	public NombreNegatifException() {
		super("Il est interdit de saisir une population négative.");
	}
}
