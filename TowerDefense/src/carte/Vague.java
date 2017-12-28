package carte;

import java.util.ArrayList;

public class Vague {
	private static int num_vague = 0;
	private int nb_ennemis = 4;  
	private int pts_vie = 100;
	private int argent_donne = pts_vie;
	private Ennemi collec_ennemi [];

	public Vague() {
		num_vague = num_vague + 1;
		pts_vie = pts_vie + 10;
		
	}
	
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
	
	public void lancer_Vague() {
		collec_ennemi = new Ennemi [this.nb_ennemis];
		int caseCourante = 0; 
		for(int i=0 ; i < nb_ennemis; i++) {
			Ennemi e= new Ennemi(pts_vie,argent_donne, caseCourante);
			collec_ennemi[i] = e;
			--caseCourante;
		}
	}

	public Ennemi [] getCollec_ennemi() {
		return collec_ennemi;
	}

	public Ennemi getEnnemi(int i) {
		return this.collec_ennemi[i];
	}
	
}
