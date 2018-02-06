package Element;

public class Vague {
	public static int num_vague = 0;
	public int nb_ennemis = 6;  

	private int pts_vie = 30;
	private int argent_donne = 10;
	public Ennemi collec_ennemi [];

	/**
	 * Constructeur de la classe vague
	 */
	public Vague() {
		
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
		if (nb_ennemis == 0) collec_ennemi = null; // le garbage libere la ressource
		return stop;
	}
	
	/**
	 * Fonction permettant de créer une vague. 
	 * Tous les ennemis sont créés un à un. 
	 */
	public void lancer_Vague() {
		this.nb_ennemis = 6;
		num_vague = num_vague + 1;
		collec_ennemi = new Ennemi [this.nb_ennemis];
		int caseCourante = 0; 
		
		for(int i=0 ; i < nb_ennemis; i++) {
			Ennemi e= new Ennemi(pts_vie + Math.pow(3, num_vague), argent_donne + Math.pow(3, num_vague)/3, caseCourante);
			collec_ennemi[i] = e;
			--caseCourante;
		}
		pts_vie += 30;
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
