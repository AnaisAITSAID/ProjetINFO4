package Element;

import java.awt.Graphics2D;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourForte extends Tour{

	public TourForte(Case case_position) {
		super(case_position, Type_tour.TourForte, 2*Constantes.tailleCase, 2000, 1, 5);
	}


	
	/* place l'image au centre de la case */
	@Override
	public void dessiner(Graphics2D g) {
		g.drawImage(sprite.getspriteTourForte(),getXcaseposition() + Constantes.tailleCase/4, getYcaseposition() + Constantes.tailleCase/4,null);
	}
	

}
