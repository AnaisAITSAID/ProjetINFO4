package Element;

import java.awt.Graphics2D;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class TourRapide extends Tour{
	private int coef = 2;
	public TourRapide(Case case_position) {
		super(case_position,Type_tour.TourRapide, 4*Constantes.tailleCase, 3, 1, 15,80);
	}
	
	
	@Override
	public void dessiner(Graphics2D g) {
		g.drawImage(sprite.getspriteTourRapide(), getXcaseposition() + Constantes.tailleCase/4, getYcaseposition()- 15 + Constantes.tailleCase/4,null);
		
	}
	@Override
	public void setDegats() {
			if (getNiveau() %4 == 0) this.coef *= 4;
			degats = (degats + (25 * getNiveau()));
	}


	@Override
	public int calculDegat() {
		// TODO Auto-generated method stub
		return (25*getNiveau());
	}


	@Override
	public void setPrix() {
		this.prix +=  getVitesse() * calculDegat() + this.degats;
		
	}
}
