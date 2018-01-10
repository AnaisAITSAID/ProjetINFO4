package utils;

public class Constantes {
	public static final int taille = 15;
	public static final int tailleCase = 40;
	public static final int vieChateau = 10;
	public static final int argent = 150;
	public static enum Type{
		CaseJouable,
		CaseChemin;
	}
	
	public static enum Type_tour{
		TourForte,
		TourRapide;
	}
	
	public static enum Orientation{
		Inconnu,
		Droite,
		Haut,
		Gauche,
		Bas
	}
}
