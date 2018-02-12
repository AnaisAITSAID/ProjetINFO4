package Element;

import java.awt.Graphics2D;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourForte extends Tour{
	private int coef = 4;
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
			degats = (degats + (100 *getNiveau()*2));
	}



	@Override
	public int calculDegat() {

		return (100*getNiveau()*2);
	}



	@Override
	public void setPrix() {
		this.prix += this.calculDegat();
	}
	
	
	
	
}
