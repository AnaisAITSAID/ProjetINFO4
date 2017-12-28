package carte;

import java.util.ArrayList;

public class Chemin {
	private static ArrayList<CaseChemin> chemin;
	
	public Chemin (){
		this.chemin = new ArrayList<CaseChemin>();
	}
	
	public void inserer (int posX, int posY){
		this.chemin.add(new CaseChemin(posX, posY));
	}

	public static CaseChemin getPos(int i) {
		return chemin.get(i);
	}
	
	public static CaseChemin getArrive(){
		int taille = chemin.size();
		return chemin.get(taille-1);	
	}
	
	public int tailleChemin () {
		return chemin.size();
	}
	
	public static int orientationCaseSuivante (int i) {
		int value = 1;  

		if (i != chemin.size() - 1) {
			CaseChemin caseCourante = chemin.get(i);
			CaseChemin caseSuivante = chemin.get(i+1);
			if (caseCourante.getX() < caseSuivante.getX()) { // droite 
				value = 1;
			} else if (caseCourante.getY() < caseSuivante.getY()){ // haut
				value = 2;
			} else if (caseCourante.getX() > caseSuivante.getX()){ // gauche 
				value = 3;
			} else { // bas
				value = 4;
			}	
		}
		return value;
	}
}
