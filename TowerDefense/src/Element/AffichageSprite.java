package Element;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import utils.Sprites;

public abstract class AffichageSprite {
	Sprites sprite = Sprites.getInstance();
	public abstract void dessiner(Graphics2D g);
}
