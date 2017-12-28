package carte;

import java.util.ArrayList;

import utils.Constantes;

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
