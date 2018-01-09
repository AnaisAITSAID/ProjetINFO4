package Element;

import java.awt.Graphics;

import utils.Sprites;

public abstract class AffichageSprite {
	Sprites sprite = Sprites.getInstance();
	public abstract void dessiner(Graphics g);
}
