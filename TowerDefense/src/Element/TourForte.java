package Element;

import java.awt.Graphics2D;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourForte extends Tour{

	public TourForte(Case case_position) {
		super(case_position, Type_tour.TourForte, 2*Constantes.tailleCase, 2, 1, 35,100);
	}


	
	/* place l'image au centre de la case */
	@Override
	public void dessiner(Graphics2D g) {
		g.drawImage(sprite.getspriteTourForte(),getXcaseposition() + Constantes.tailleCase/4, getYcaseposition() + Constantes.tailleCase/4,null);
	}



	@Override
	public void setDegats() {
		degats += Math.pow(5, getNiveau()+2); 
	}



	@Override
	public int calculDegat() {
		return 	(int) (degats + Math.pow(5, getNiveau()+2)); 
	}
	

}
