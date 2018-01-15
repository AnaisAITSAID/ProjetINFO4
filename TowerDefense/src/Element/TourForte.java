package Element;

import java.awt.Graphics;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourForte extends Tour{

	public TourForte(Case case_position) {
		super(case_position, Type_tour.TourForte, 2*Constantes.tailleCase, 5, 1, 5);
	}

	public TourForte(int x, int y) {
		super(x,y,Type_tour.TourForte);
	}
	
	/* place l'image au centre de la case */
	@Override
	public void dessiner(Graphics g) {
		g.drawImage(sprite.getspriteTourForte(),getXcaseposition() + Constantes.tailleCase/4, getYcaseposition() + Constantes.tailleCase/4,null);
	}
	

}
