package carte;

import java.awt.Graphics;

import utils.Constantes.Type;

public class CaseJouable extends Case{
	private boolean estPrise;
	
	public CaseJouable(int x, int y) {
		super(Type.CaseJouable, x, y);
		this.estPrise = false;
	}
	@Override
	public void dessiner(Graphics g) {
		/* dessine l'image de la case */
	}
	
}
