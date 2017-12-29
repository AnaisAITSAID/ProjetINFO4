package carte;

import java.util.ArrayList;

import utils.Constantes;

public class Chemin {
	private static ArrayList<CaseChemin> chemin;	//le chemin correspond � une liste cha�n�e de cases
	
	/**
	 * constructeur de classe chemin
	 */
	public Chemin (){
		this.chemin = new ArrayList<CaseChemin>();
	}
	
	/**
	 * Fonction permettant d'ins�rer une case au chemin
	 * @param posX correspond � la coordonn�e selon l'axe des X de la case
	 * @param posY correspond � la coordonn�e selon l'axe des Y de la case
	 */
	public void inserer (int posX, int posY){
		this.chemin.add(new CaseChemin(posX, posY));
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public static CaseChemin getPos(int i) {
		return chemin.get(i);
	}
	
	/**
	 * Fonction permettant de r�cup�rer la case o� se trouve la ch�teau
	 * @return case ch�teau qui correspond � la zone � prot�ger par le joueur
	 */
	public static CaseChemin getArrive(){
		int taille = chemin.size();
		return chemin.get(taille-1);	
	}
	
	/**
	 * Fonction permettant de r�cup�rer la taille du chemin
	 * @return la taille du chemin
	 */
	public int tailleChemin () {
		return chemin.size();
	}
	
	/*
	 * 
	 */
	public static Constantes.Orientation orientationCaseSuivante (int i) {
		Constantes.Orientation value = Constantes.Orientation.Droite;  

		if (i != chemin.size() - 1) {
			CaseChemin caseCourante = chemin.get(i);
			CaseChemin caseSuivante = chemin.get(i+1);
			if (caseCourante.getX() < caseSuivante.getX()) { // droite 
				value = Constantes.Orientation.Droite;
			} else if (caseCourante.getY() < caseSuivante.getY()){ // haut
				value = Constantes.Orientation.Haut;
			} else if (caseCourante.getX() > caseSuivante.getX()){ // gauche 
				value = Constantes.Orientation.Gauche;
			} else { // bas
				value = Constantes.Orientation.Bas;
			}	
		}
		return value;
	}
}
