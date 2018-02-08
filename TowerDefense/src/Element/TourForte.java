package Element;

import java.awt.Graphics2D;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourForte extends Tour{
	private int coef = 2;
	public TourForte(Case case_position) {
		super(case_position, Type_tour.TourForte, 3*Constantes.tailleCase, 1, 1, 100,100);
	}


	
	/* place l'image au centre de la case */
	@Override
	public void dessiner(Graphics2D g) {
		g.drawImage(sprite.getspriteTourForte(),getXcaseposition() -5 + Constantes.tailleCase/4, getYcaseposition() - 10 + Constantes.tailleCase/4,null);
	}



	@Override
	public void setDegats() {
		if (getNiveau() %4 == 0) this.coef *= 4;
		degats += 30 + this.coef*getNiveau();
	}



	@Override
	public int calculDegat() {
		return degats +30 + this.coef*getNiveau();
	}



	
	

}
