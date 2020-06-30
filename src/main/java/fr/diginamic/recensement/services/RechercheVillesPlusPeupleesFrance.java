package fr.diginamic.recensement.services;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.FunctionalException;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées de France
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesFrance extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) throws FunctionalException {

		System.out.println("Veuillez saisir un nombre de villes:");
		String nbVillesStr = scanner.nextLine();
		if (!NumberUtils.isDigits(nbVillesStr)) {
			throw new FunctionalException("Veuillez saisir un nombre entier de villes.");
		}
		int nbVilles = Integer.parseInt(nbVillesStr);

		List<Ville> villes = recensement.getVilles();
		System.out.println("Les " + nbVilles + " villes les plus peuplées de France sont :");
		Collections.sort(villes, new EnsemblePopComparateur(false));
		for (int i = 0; i < nbVilles; i++) {
			Ville ville = villes.get(i);
			System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
		}
	}

}
