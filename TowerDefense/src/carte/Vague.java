package carte;

import java.util.ArrayList;

public class Vague {
	static int num_vague=0;
	static int nb_ennemis=4;   //est ce qu'on le met en "final" ou pas la peine?
	static int pts_vie=100;    //si on part du principe qu'on augmente les points de vie des ennemis à chaque vague
	static int argent_donne=pts_vie;
	private ArrayList collec_ennemi;
	
	public Vague() {
		num_vague=num_vague+1;
		pts_vie=pts_vie+10;
		collec_ennemi=new ArrayList();
		
		for(int i=0;i<nb_ennemis;i++) {
			Ennemi e= new Ennemi(pts_vie,argent_donne);
			collec_ennemi.add(e);
		}
	}
	
	public Ennemi getEnnemi(int j) {
		return (Ennemi) collec_ennemi.get(j);
	}
	
}
