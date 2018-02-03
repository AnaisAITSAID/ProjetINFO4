package Element;

import java.awt.Graphics2D;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourRapide extends Tour{
	
	public TourRapide(Case case_position) {
		super(case_position,Type_tour.TourRapide, 2*Constantes.tailleCase, 5, 1, 5);
	}
	
	
	@Override
	public void dessiner(Graphics2D g) {
		g.drawImage(sprite.getspriteTourRapide(), getXcaseposition() + Constantes.tailleCase/4, getYcaseposition() + Constantes.tailleCase/4,null);
		
	}
}
