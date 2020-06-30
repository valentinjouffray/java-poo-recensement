package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.FunctionalException;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées d'une département
 * donné
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesDepartement extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) throws FunctionalException {

		System.out.println("Veuillez saisir un numéro de département:");
		String nomDept = scanner.nextLine();

		if (StringUtils.isEmpty(nomDept)) {
			throw new FunctionalException("Veuillez saisir le numéro d'un département.");
		}
		System.out.println("Veuillez saisir un nombre de villes:");
		String nbVillesStr = scanner.nextLine();
		if (!NumberUtils.isDigits(nbVillesStr)) {
			throw new FunctionalException("Veuillez saisir un nombre entier de villes.");
		}
		int nbVilles = Integer.parseInt(nbVillesStr);

		List<Ville> villesDept = new ArrayList<Ville>();

		List<Ville> villes = recensement.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(nomDept)) {
				villesDept.add(ville);
			}
		}

		Collections.sort(villesDept, new EnsemblePopComparateur(false));

		if (villesDept.size() > 0) {
			System.out.println("Les " + nbVilles + " villes les plus peuplées du département " + nomDept + " :");
			for (int i = 0; i < nbVilles; i++) {
				Ville ville = villesDept.get(i);
				System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
			}
		} else {
			throw new FunctionalException("Département " + nomDept + " non trouvé.");
		}
	}

}
