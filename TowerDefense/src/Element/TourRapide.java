package Element;

import java.awt.Graphics;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourRapide extends Tour{
	
	public TourRapide(Case case_position) {
		super(case_position,Type_tour.TourRapide, 2*Constantes.tailleCase, 5, 1, 5);
	}
	
	public TourRapide(int x, int y) {
		super(x,y,Type_tour.TourRapide);
	}
	
	@Override
	public void dessiner(Graphics g) {
	//	g.drawImage(sprite.getSpriteTour1(),getX(), getY(),null);
		
	}
}
