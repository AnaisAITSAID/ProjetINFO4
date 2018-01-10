package Element;

import java.awt.Graphics;

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
	public void dessiner(Graphics g) {
		g.drawImage(sprite.getSpriteCaseJouable(), getX(), getY(), null);
	}
	
}
