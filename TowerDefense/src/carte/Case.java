package carte;

import java.awt.Graphics;
import java.awt.Rectangle;

import utils.Constantes.Type;

public abstract class Case extends AffichageSprite {
	private Type type; 
	private int x;
	private int y;
	
	public Case (Type type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
	public Type getType() {
		return this.type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
