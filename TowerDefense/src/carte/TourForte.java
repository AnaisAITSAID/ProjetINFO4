package carte;

import java.awt.Graphics;

import utils.Constantes.Type_tour;

public class TourForte extends Tour{

	public TourForte(Case case_position) {
		super(case_position,Type_tour.TourForte);
	}

	@Override
	public void dessiner(Graphics g) {
		g.drawImage(sprite.getSpriteTour1(),getXcaseposition(), getYcaseposition(),null);
		
	}
}
