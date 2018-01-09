package Element;

import utils.Constantes;
import utils.Constantes.Type;

public abstract class Case extends AffichageSprite {
	private Type type; 	//il existe 2 types de cases: jouable ou chemin
	private int x;		//x et y correspondent aux coordonnées de la case
	private int y;
	
	/*
	 * Constructeur de la classe case
	 */
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
	
	public boolean contain(int x, int y) {
		if (x <= this.x + Constantes.tailleCase && x >= this.x &&
		    	y <= this.y + Constantes.tailleCase && y >= this.y)
		    	return true;
		    return false;
	}
}
