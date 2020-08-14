package com.tb.app;

/**
 * 
 */

/**
 * @author Zouhair
 * 
 */
public class Joueur {

	/**
	 * Cette classe va nous permettre de stocker les donnees d'un joueur
	 */

	private String nom;
	private int score;
	/*
	 * C'est pour le caractère X ou O
	 */
	private String caractereChoisi;
	private boolean vainqueur;
	private boolean isIA;

	public Joueur() {
		// TODO Auto-generated constructor stub
	}

	public Joueur(String nom, int score, boolean vainqueur) {
		super();
		this.nom = nom;
		this.score = score;
		this.vainqueur = vainqueur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(boolean vainqueur) {
		this.vainqueur = vainqueur;
	}

	public String getCaractereChoisi() {
		return caractereChoisi;
	}

	public void setCaractereChoisi(String caractereChoisi) {
		this.caractereChoisi = caractereChoisi;
	}

	public boolean isIA() {
		return isIA;
	}

	public void setIA(boolean isIA) {
		this.isIA = isIA;
	}

	@Override
	public String toString() {
		return "Nom du joueur :" + this.nom + " Son score est :" + this.score + " il est vainqueur " + this.vainqueur;
	}
}
