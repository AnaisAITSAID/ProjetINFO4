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

}