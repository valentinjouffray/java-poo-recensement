package fr.diginamic.recensement.services.exceptions;

public enum PossibleExceptions {
    DEPARTEMENT(new IllegalArgumentException("Le code département doit être composé de 2 chiffres.")), DIGITSONLY(new IllegalArgumentException("Doit être composé de chiffres.")), UNDERZERO(new IllegalArgumentException("Doit être supérieur à 0.")), OVERMAX(new IllegalArgumentException("Doit être inférieur à 1000.")), NOTFOUND(new IllegalArgumentException("Département non trouvé."));

    private final IllegalArgumentException exception;

    PossibleExceptions(IllegalArgumentException exception) {
        this.exception = exception;
    }

    public IllegalArgumentException getException() {
        return exception;
    }
}
