package Element;

import java.awt.Graphics2D;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourRapide extends Tour{
	
	public TourRapide(Case case_position) {
		super(case_position,Type_tour.TourRapide, 4*Constantes.tailleCase, 3, 1, 15,80);
	}
	
	
	@Override
	public void dessiner(Graphics2D g) {
		g.drawImage(sprite.getspriteTourRapide(), getXcaseposition() + Constantes.tailleCase/4, getYcaseposition() + Constantes.tailleCase/4,null);
		
	}
	
	@Override
	public void setDegats() {
		if (getNiveau() == 1) {
			degats += 75;
		} else {
			degats += 230;
		}
	}



	@Override
	public int calculDegat() {
		if (getNiveau() == 1) {
			return  75;
		} else {
			return  230;
		}
	}
	
	
}
