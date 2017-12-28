package carte;

import java.awt.Graphics;

import utils.Constantes;

public class Chateau extends AffichageSprite {

	private int vieChateau;
	


	public Chateau() {
		super();
		this.vieChateau = Constantes.vieChateau;
	}

	@Override
	public void dessiner(Graphics g) {
		CaseChemin arrive = Chemin.getArrive();
		g.drawImage(sprite.getSpriteChateau(), arrive.getX(), arrive.getY(), null);
	}
	public int getVieChateau() {
		return vieChateau;
	}

	public void setVieChateau(int degat) {
		this.vieChateau -= degat;
	}
	
	public boolean gameOver() {
		if (this.vieChateau == 0)
			return true;
		return false;
	}
}
