package Element;

import java.awt.Graphics2D;

import utils.Constantes;
import utils.Constantes.Type_tour;

public class BoutonsProduits extends AffichageSprite{
	private int x; 
	private int y;
	private Constantes.Type_tour tour;
	
	public BoutonsProduits(int x, int y, Constantes.Type_tour tour) {
		super();
		this.x = x;
		this.y = y;
		this.tour = tour;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	@Override
	public void dessiner(Graphics2D g) {
		switch (this.tour) {
			case TourForte:
				g.drawImage(sprite.getspriteTourForteAchat(), this.x, this.y,null);			
				break;
			case TourRapide:
				g.drawImage(sprite.getspriteTourRapideAchat(), this.x, this.y,null);			
				break;
			default:
				break;
		}
	}
	
	public boolean contain(int x, int y) {
		if (x <= this.x + Constantes.tailleCase && x >= this.x &&
		    	y <= this.y + Constantes.tailleCase && y >= this.y)
		    	return true;
		    return false;
	}
}
