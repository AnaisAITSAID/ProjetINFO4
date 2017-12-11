package carte;

import java.awt.Graphics;
import java.awt.Rectangle;

import utils.Constantes.Type;

public abstract class Case  {
	private Type type; 
	private int x;
	private int y;
	Sprites sprite = Sprites.getInstance();
	
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
	public abstract void dessiner(Graphics g);
}
