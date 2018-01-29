package Element;

import java.awt.Graphics2D;

import utils.Constantes;

public class BoutonsProduits extends AffichageSprite{
	private int x; 
	private int y;
	
	
	public BoutonsProduits(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	@Override
	public void dessiner(Graphics2D g) {
		g.drawImage(sprite.getspriteTourForteAchat(), this.x, this.y,null);
	}
	
	public boolean contain(int x, int y) {
		if (x <= this.x + Constantes.tailleCase && x >= this.x &&
		    	y <= this.y + Constantes.tailleCase && y >= this.y)
		    	return true;
		    return false;
	}
}
