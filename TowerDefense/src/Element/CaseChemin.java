package Element;

import java.awt.Graphics;

import utils.Constantes.Type;

public class CaseChemin extends Case {
	private Ennemi ennemi;

	public CaseChemin(int x, int y){
		super(Type.CaseChemin, x, y);
		this.ennemi = null;
	}

	@Override
	public void dessiner(Graphics g) {
		g.drawImage(sprite.getSpriteCaseChemin(), getX(), getY(), null);
	}
	
	public Ennemi getEnnemi() {
		return ennemi;
	}

	public void setEnnemi(Ennemi ennemi) {
		this.ennemi = ennemi;
	}
	
}