package com.tb.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Classe qui permet de lancer le Jeux Morpion en java 
 * @Author  Zouhair KASMI
 * 
 */
public class Morpion {

	private static Scanner sc;
	private static Scanner sc2;
	private static 		List<Joueur> joueurs = new ArrayList<>();


	public Morpion() {
		
	}

	public static void main(String[] args) {


		morpion(joueurs);
	}

	public static void morpion(List<Joueur> joueurs) {
		System.out.println("======================== (Jeux morpion) ========================");
		String[][] x = new String[3][3];
		final int max = 1; // Si on ajoute un coupple de données il faut incrémenter
		// ce nombre
		final int min = 0; // il faut jamais changer
		int range = max - min + 1; // pour initialiser le random limiter à max
		/*
		 * generer un random entre min et max à l'aide de la fonction random de la
		 * classe biblio Math
		 */
		int rand = (int) (Math.random() * range) + min;
		int role = rand;

		/*
		 * La saisie des joueurs et les ajouter à la liste
		 */

		saisieJoueursAjoutList(joueurs);

		/*
		 * Remplissage des indices
		 */
		remplissageIndex(x);
		/*
		 * Affecter les caractères au joueurs
		 */
		joueurs.get(role).setCaractereChoisi("X");
		joueurs.get(Math.abs(1 - role)).setCaractereChoisi("O");
		do {
			/*
			 * Affichage du matrice
			 */
			affichageMatrice(joueurs, x, role);
			/*
			 * pour stocker les valeurs de saisies par l'utilisateur
			 */
			role = stockerValeursUtilisateur(joueurs, x, role);
			/*
			 * A chaque fois q'un joueur joue on vérifier si il y'a un vainqeur
			 */
		} while (!verifierGangant(x, joueurs.get(0), joueurs.get(1)));
	}

	private static int stockerValeursUtilisateur(List<Joueur> joueurs, String[][] x, int role) {
		String[] valeur = { "", "" };
		role = bonneSaisie(joueurs, x, role);
		return role;
	}

	private static int bonneSaisie(List<Joueur> joueurs, String[][] x, int role) {
		String[] valeur;
		do {
			/*
			 * La saisie
			 */
			valeur = recolerSaisieDesIndices();
			/*
			 * On vérifier si cette case choisie est bien vide ?? ou c'est déjà jouer par
			 * l'autre joueur
			 */
			if (verifierQueLeChampsEstSaisieCorrectement(x, valeur)) {
				affectationValeurJoue(joueurs, x, role, valeur);

				role = tourDeRole(role);
			}
			/*
			 * tantque la case est pas rempli on repete
			 */
		} while (verifierQueLeChampsEstSaisieCorrectement(x, valeur));
		return role;
	}

	private static void affectationValeurJoue(List<Joueur> joueurs, String[][] x, int role, String[] valeur) {
		x[Integer.parseInt(valeur[0])][Integer.parseInt(valeur[1])] = "   " + joueurs.get(role).getCaractereChoisi()
				+ "       ";
	}

	private static boolean verifierQueLeChampsEstSaisieCorrectement(String[][] x, String[] valeur) {
		return !x[Integer.parseInt(valeur[0])][Integer.parseInt(valeur[1])].trim().toUpperCase().equals("X")
				&& !x[Integer.parseInt(valeur[0])][Integer.parseInt(valeur[1])].trim().toUpperCase().equals("O");
	}

	private static String[] recolerSaisieDesIndices() {
		String[] valeur;
		Scanner sc = new Scanner(System.in);
		String v = sc.nextLine();
		valeur = v.split("");
		return valeur;
	}

	private static int tourDeRole(int role) {
		role = Math.abs(1 - role);
		return role;
	}

	private static void affichageMatrice(List<Joueur> joueurs, String[][] x, int role) {
		System.out.println("____________________________________");
		System.out.println("|" + x[0][0] + "|" + x[0][1] + "|" + x[0][2] + "|");
		System.out.println("|___________|___________|___________|");
		System.out.println("|" + x[1][0] + "|" + x[1][1] + "|" + x[1][2] + "|");
		System.out.println("|___________|___________|___________|");
		System.out.println("|" + x[2][0] + "|" + x[2][1] + "|" + x[2][2] + "|");
		System.out.println("|___________|___________|___________|");
		System.out.println("Les premier joueur est choisi aléatoirement par le système ");
		System.out.println("pour désigner la case choisie il faut juste ecrire le numéro dans la case");
		/*
		 * affectation des premier joueur d'une façon aléatoire
		 */
		System.out.println("C'est à " + joueurs.get(role).getNom() + " de jouer vous avez "
				+ joueurs.get(role).getCaractereChoisi());
		System.out.println("Le joueur " + joueurs.get(Math.abs(1 - role)).getNom() + "à le caractère "
				+ joueurs.get(Math.abs(1 - role)).getCaractereChoisi());
	}

	private static void remplissageIndex(String[][] x) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				x[i][j] = "   " + i + j + "      ";
			// x[i][j]=" X ";
		}
	}

	private static void saisieJoueursAjoutList(List<Joueur> joueurs) {
		System.out.println("Entrez le nom du joueur 1");
		sc = new Scanner(System.in);
		joueurs.add(new Joueur(sc.nextLine(), 0, false));
		System.out.println("Entrez le nom du joueur 2");
		sc2 = new Scanner(System.in);
		joueurs.add(new Joueur(sc2.nextLine(), 0, false));
		afficher(joueurs);
	}

	/*
	 * Fonction qui permet d'afficher les joueurs remplis pour le teste
	 */
	public static void afficher(List<Joueur> joueurs) {
		for (Joueur j : joueurs)
			System.out.println(j.toString());
	}

	/*
	 * Fonction qui permet de vérifier et jouer en même temps Pour IA
	 */

	public static boolean verifierGangantIA(String[][] x, Joueur j1, Joueur j2, int role) {
		/*
		 * on boucle sur tout les elt de tableau
		 */
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				/*
				 * On vérifier toutes les lignes qui contients les trois caractères égaux
				 */

				if (j + 1 < 3 && x[i][j].equals(x[i][j + 1]) && !x[i][j].trim().equals("")) {
					if (j + 2 < 3 && x[i][j].equals(x[i][j + 2])) {
						/*
						 * On affiche le gangant
						 */
						afficherVainqueur(x, j1, j2, i, j);

						return true;
					}else {
						//TODO : affectation 
						/*x[Integer.parseInt(
								x[0])][Integer.parseInt(x[1])] = "   " + joueurs.get(role).getCaractereChoisi()
								+ "       ";				*/	}
						
					/*
					 * SI les lignes sont pas égaux allons voir les colonnes
					 */
				}
				if (i + 1 < 3 && x[i][j].equals(x[i + 1][j]) && !x[i][j].trim().equals("")) {
					if (i + 2 < 3 && x[i][j].equals(x[i + 2][j])) {
						afficherVainqueur(x, j1, j2, i, j);
						return true;
					}
					/*
					 * Sinon on verra la première diagonale
					 */
				}
				if (i + 1 < 3 && j + 1 < 3 && x[i][j].equals(x[i + 1][j + 1]) && !x[i][j].trim().equals("")) {
					if (i + 2 < 3 && j + 2 < 3)
						if (x[i][j].equals(x[i + 2][j + 2])) {
							/*
							 * On affiche le gangant
							 */
							afficherVainqueur(x, j1, j2, i, j);
							return true;
						}
					/*
					 * La dernière diagonale
					 */
				}
				if (x[0][2].equals(x[1][1]) && !x[0][2].trim().equals("")) {
					if (x[0][2].equals(x[2][0])) {
						/*
						 * On affiche le gangant
						 */

						afficherVainqueur(x, j1, j2);
						return true;
					}
				}
			}
		return false;

	}

	private static void afficherVainqueur(String[][] x, Joueur j1, Joueur j2, int i, int j) {
		if (x[i][j].trim().toUpperCase().equals(j1.getCaractereChoisi().trim().toUpperCase())) {
			System.out.println("****************FIN DE LA PARTIE*************");
			System.out.println("****************LE JOUEUR =>" + j1.getNom() + " EST VAINQEUR*************");
		} else {
			System.out.println("****************FIN DE LA PARTIE*************");
			System.out.println("****************LE JOUEUR => " + j2.getNom() + " EST VAINQEUR*************");
		}
	}

	private static void afficherVainqueur(String[][] x, Joueur j1, Joueur j2) {
		if (x[0][2].trim().toUpperCase().equals(j1.getCaractereChoisi().trim().toUpperCase())) {
			System.out.println("****************FIN DE LA PARTIE*************");
			System.out.println("****************LE JOUEUR =>" + j1.getNom() + " EST VAINQEUR*************");
		} else {
			System.out.println("****************FIN DE LA PARTIE*************");
			System.out.println("****************LE JOUEUR =>" + j2.getNom() + " EST VAINQEUR*************");
		}
	}

	/*
	 * Fonction qui permet de vérifier si il y'a un vainqeur
	 */
	public static boolean verifierGangant(String[][] x, Joueur j1, Joueur j2) {
		/*
		 * on boucle sur tout les elt de tableau
		 */
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				/*
				 * On vérifier toutes les lignes qui contients les trois caractères égaux
				 */

				if (j + 1 < 3 && x[i][j].equals(x[i][j + 1]) && !x[i][j].trim().equals("")) {
					if (j + 2 < 3 && x[i][j].equals(x[i][j + 2])) {
						afficherVainqueur(x, j1, j2, i, j);

						return true;
					}
					/*
					 * SI les lignes sont pas égaux allons voir les colonnes
					 */
				}
				if (i + 1 < 3 && x[i][j].equals(x[i + 1][j]) && !x[i][j].trim().equals("")) {
					if (i + 2 < 3 && x[i][j].equals(x[i + 2][j])) {
						afficherVainqueur(x, j1, j2, i, j);
						return true;
					}
					/*
					 * Sinon on verra la première diagonale
					 */
				}
				if (i + 1 < 3 && j + 1 < 3 && x[i][j].equals(x[i + 1][j + 1]) && !x[i][j].trim().equals("")) {
					if (i + 2 < 3 && j + 2 < 3)
						if (x[i][j].equals(x[i + 2][j + 2])) {
							afficherVainqueur(x, j1, j2, i, j);
							return true;
						}
					/*
					 * La dernière diagonale
					 */
				}
				if (x[0][2].equals(x[1][1]) && !x[0][2].trim().equals("")) {
					if (x[0][2].equals(x[2][0])) {
						/*
						 * On affiche le gangant
						 */

						afficherVainqueur(x, j1, j2);
						return true;
					}
				}
			}
		return false;

	}
}
