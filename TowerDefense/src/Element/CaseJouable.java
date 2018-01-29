package Element;

import java.awt.Graphics2D;

import utils.Constantes.Type;

public class CaseJouable extends Case{
	private Tour tour;
	
	public CaseJouable(int x, int y) {
		super(Type.CaseJouable, x, y);
		this.tour = null;
	}
	
	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	@Override
	public void dessiner(Graphics2D g) {
		g.drawImage(sprite.getSpriteCaseJouable(), getX(), getY(), null);
	}
	
}
