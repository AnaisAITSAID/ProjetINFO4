package Element;

import java.awt.Graphics2D;

import utils.Constantes;

public class Chateau extends AffichageSprite {

	private int vieChateau;		//correspond aux points de vie du joueur
	private int argent;
	
	


	/**
	 * Constructeur de la classe château
	 */
	public Chateau() {
		super();
		this.vieChateau = Constantes.vieChateau;
		this.argent= Constantes.argent;
	}



	@Override
	/**
	 * Fonction permettant de dessiner le château sur la dernière case du chemin
	 */
	public void dessiner(Graphics2D g) {
		CaseChemin arrive = Chemin.getArrive();
		g.drawImage(sprite.getSpriteChateau(), arrive.getX(), arrive.getY(), null);
	}
	
	/**
	 * getter pour les points de vie du joueur
	 * @return valeur des points de vie du joueur
	 */
	public int getVieChateau() {
		return vieChateau;
	}
	
	/**
	 * setter pour les points de vie du château. Cette fonction nous permettra de mettre à jour
	 * les points de vie du joueur lorsqu'il se fera attaquer.
	 * @param degat
	 */
	public void setVieChateau(int degat) {
		this.vieChateau -= degat;
	}
	
	/**
	 * Fonction permettant de déterminer si le joueur a perdu (plus de points de vie) ou pas
	 * @return un booléen qui sera à true si le joueur a perdu
	 */
	public boolean gameOver() {
		if (this.vieChateau == 0)
			return true;
		return false;
	}
	
	public int getArgent() {
		return argent;
	}
	
	public void setArgent(int argent) {
		this.argent = argent;
	}

}
