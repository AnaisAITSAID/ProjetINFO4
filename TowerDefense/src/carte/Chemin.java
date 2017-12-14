package carte;

import java.util.ArrayList;

public class Chemin {
	private ArrayList<CaseChemin> chemin;
	
	public Chemin (){
		this.chemin = new ArrayList<CaseChemin>();
	}
	
	public void inserer (int posX, int posY){
		this.chemin.add(new CaseChemin(posX, posY));
	}

	public CaseChemin getPos(int i) {
		return this.chemin.get(i);
	}
	
	public CaseChemin getArrive(){
		int taille = this.chemin.size();
		return this.chemin.get(taille-1);	
	}
}
