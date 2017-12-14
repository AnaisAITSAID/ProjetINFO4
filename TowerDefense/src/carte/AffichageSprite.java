package carte;

import java.awt.Graphics;

public abstract class AffichageSprite {
	Sprites sprite = Sprites.getInstance();
	public abstract void dessiner(Graphics g);
}
