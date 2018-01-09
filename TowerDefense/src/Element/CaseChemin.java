package Element;

import java.awt.Graphics;

import utils.Constantes.Type;

public class CaseChemin extends Case {
	
	public CaseChemin(int x, int y){
		super(Type.CaseChemin, x, y);
	}

	@Override
	public void dessiner(Graphics g) {
		g.drawImage(sprite.getSpriteCaseChemin(), getX(), getY(), null);
	}
	
}
