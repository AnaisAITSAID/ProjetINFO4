package Element;

public class Vague {
	public static int num_vague;
	public int nb_ennemis;  

	private int pts_vie;
	private int argent_donne;
	public Ennemi collec_ennemi [];
	private int coef;
	
	public Vague () {
		num_vague = 0;
		nb_ennemis = 6;  

		pts_vie = 33;
		argent_donne = 10;
		coef = 10;
	}
	public int getPointDeVie(){
		return pts_vie;
		
	}
	
	/**
	 * Cette fonction permet de v�rifier si tous les ennemis sont morts.
	 * Si tel est la cas, la vague est arr�t�e. 
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
		if (nb_ennemis == 0) collec_ennemi = null; // le garbage libere la ressource
		if (stop) {
			pts_vie += 70 + this.coef*num_vague;
			argent_donne = 30 * num_vague;
		}

		return stop;
	}
	
	/**
	 * Fonction permettant de cr�er une vague. 
	 * Tous les ennemis sont cr��s un � un. 
	 */
	public void lancer_Vague() {
		this.nb_ennemis = 6;
		num_vague = num_vague + 1;
		collec_ennemi = new Ennemi [this.nb_ennemis];
		int caseCourante = 0; 
		if (num_vague %3 == 0) this.coef = this.coef * 5;
		System.out.println(this.coef);
		
		for(int i=0 ; i < nb_ennemis; i++) {
			Ennemi e= new Ennemi(pts_vie, argent_donne, caseCourante);
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
	 * Fonction qui renvoie l'ennemi � une position donn�e dans la vague.
	 * @param i
	 * @return ennemi � la position i dans la vague
	 */
	public Ennemi getEnnemi(int i) {
		return collec_ennemi[i];
	}
	
	public void supprimerEnnemi(Ennemi e) {
		int ind = 0;
		boolean trouve=false;
		for(int i = 0; i < nb_ennemis; i++ ) {
			if(collec_ennemi[i] == e) {
				ind = i;
				trouve = true;
			}
		}
		if(trouve == true) {
			for(int j = ind; j<nb_ennemis-1;j++) {
				collec_ennemi[j] = collec_ennemi[j+1];
			}
			nb_ennemis--;
		}

	}
	public int getNb_ennemis() {
		return nb_ennemis;
	}

}
