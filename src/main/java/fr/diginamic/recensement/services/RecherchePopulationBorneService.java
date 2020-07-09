package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.RecensementException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws RecensementException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		if (!NumberUtils.isDigits(saisieMin)) {
			throw new RecensementException("la population min doit être un entier.");
		}
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		if (!NumberUtils.isDigits(saisieMax)) {
			throw new RecensementException("la population max doit être un entier.");
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		if (min>max || min<0 || max<0) {
			throw new RecensementException("Les valeurs de min et max saisies ne sont pas cohérentes.");
		}
		
		boolean departementTrouve = false;

		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				departementTrouve = true;
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
		if (!departementTrouve) {
			throw new RecensementException("le département choisi n'existe pas.");
		}
	}

}
