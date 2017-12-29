package carte;

import java.awt.Graphics;

import utils.Constantes.Type;

public class CaseJouable extends Case{
	private boolean estPrise;	//booléen permettant de savoir si la case est occupée
								//c'est-à-dire si le joueur y a déjà placé une tour ou non
	
	public CaseJouable(int x, int y) {
		super(Type.CaseJouable, x, y);
		this.estPrise = false;
	}
	@Override
	public void dessiner(Graphics g) {
		g.drawImage(sprite.getSpriteCaseJouable(), getX(), getY(), null);
	}
	
}
