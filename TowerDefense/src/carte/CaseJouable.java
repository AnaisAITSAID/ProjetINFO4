package carte;

import utils.Constantes.Type;

public class CaseJouable extends Case{
	private boolean estPrise;
	
	public CaseJouable(int x, int y) {
		super(Type.CaseChemin, x, y);
		this.estPrise = false;
	}
}
