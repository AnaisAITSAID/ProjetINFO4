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
}
