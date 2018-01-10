package Element;

import java.util.ArrayList;

public class Vague {
	public static int num_vague = 0;
	private int nb_ennemis = 4;  
	private int pts_vie = 100;
	private int argent_donne = pts_vie;
	private Ennemi collec_ennemi [];

	/**
	 * Constructeur de la classe vague
	 */
	public Vague() {
		num_vague = num_vague + 1;
		pts_vie = pts_vie + 10;
		
	}
	
	/**
	 * Cette fonction permet de vérifier si tous les ennemis sont morts.
	 * Si tel est la cas, la vague est arrêtée. 
	 * @return
	 */
	public boolean ennemisMorts() {
		boolean stop = false;
		
		for (Ennemi ennemi : this.collec_ennemi) {
			if (ennemi.isBouge()) {
				stop = false; 
				break;
			} else {
				stop = true;
			}
		}
		return stop;
	}
	
	/**
	 * Fonction permettant de créer une vague. 
	 * Tous les ennemis sont créés un à un. 
	 */
	public void lancer_Vague() {
		collec_ennemi = new Ennemi [this.nb_ennemis];
		int caseCourante = 0; 
		for(int i=0 ; i < nb_ennemis; i++) {
			Ennemi e= new Ennemi(pts_vie,argent_donne, caseCourante);
			collec_ennemi[i] = e;
			--caseCourante;
		}
	}
	
	/**
	 * Fonction qui renvoie un tableau d'ennemis (la vague)
	 * @return
	 */
	public Ennemi [] getCollec_ennemi() {
		return collec_ennemi;
	}

	/**
	 * Fonction qui renvoie l'ennemi à une position donnée dans la vague.
	 * @param i
	 * @return ennemi à la position i dans la vague
	 */
	public Ennemi getEnnemi(int i) {
		return this.collec_ennemi[i];
	}
	
}
