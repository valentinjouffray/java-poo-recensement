package fr.diginamic.recensement.services;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.exceptions.PossibleExceptions;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 *
 * @author DIGINAMIC
 */
public class RecherchePopulationBorneService extends MenuService {

    @Override
    public void traiter(Recensement rec, Scanner scanner) throws IllegalArgumentException {

        System.out.println("Quel est le code du département recherché ? ");
        String choix = scanner.nextLine();
        if (choix.length() != 2) {
            throw PossibleExceptions.DEPARTEMENT.getException();
        }
        if (!choix.matches("[0-9]+")) {
            throw PossibleExceptions.DIGITSONLY.getException();
        }

        System.out.println("Choississez une population minimum (en milliers d'habitants): ");
        String saisieMin = getAnswer(scanner);

        System.out.println("Choississez une population maximum (en milliers d'habitants): ");
        String saisieMax = getAnswer(scanner);

        int min = Integer.parseInt(saisieMin) * 1000;
        int max = Integer.parseInt(saisieMax) * 1000;

        List<Ville> villes = rec.getVilles();
        List<Ville> foundVilles = villes.stream().filter(v -> v.getCodeDepartement().equals(choix) && v.getPopulation() >= min && v.getPopulation() <= max).collect(Collectors.toList());
        // On vérifie si des villes ont été trouvées
        if (foundVilles.isEmpty()) {
            throw PossibleExceptions.NOTFOUND.getException();
        }
        foundVilles.forEach(v -> System.out.println(v.getNom() + " - " + v.getPopulation() + " habitants"));
    }

    private String getAnswer(Scanner scanner) {
        String saisieMin = scanner.nextLine();
        if (!saisieMin.matches("[0-9]+")) {
            throw PossibleExceptions.DIGITSONLY.getException();
        }
        if (Integer.parseInt(saisieMin) < 0) {
            throw PossibleExceptions.UNDERZERO.getException();
        }
        if (Integer.parseInt(saisieMin) > 1000) {
            throw PossibleExceptions.OVERMAX.getException();
        }
        return saisieMin;
    }

}
