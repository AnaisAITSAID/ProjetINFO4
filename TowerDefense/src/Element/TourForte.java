package Element;

import java.awt.Graphics2D;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourForte extends Tour{

	public TourForte(Case case_position) {
		super(case_position, Type_tour.TourForte, 2*Constantes.tailleCase, 1, 1, 100,100);
	}


	
	/* place l'image au centre de la case */
	@Override
	public void dessiner(Graphics2D g) {
		g.drawImage(sprite.getspriteTourForte(),getXcaseposition() + Constantes.tailleCase/4, getYcaseposition() + Constantes.tailleCase/4,null);
	}



	@Override
	public void setDegats() {
		if (getNiveau() == 1) {
			degats += 300;
		} else {
			degats += 1200;
		}
	}



	@Override
	public int calculDegat() {
		if (getNiveau() == 1) {
			return  300;
		} else {
			return  1200;
		}
	}
	

}
