package Element;

import java.awt.Graphics2D;

import utils.Constantes;

public class Chateau extends AffichageSprite {

	private int vieChateau;		//correspond aux points de vie du joueur
	private int argent;
	
	


	/**
	 * Constructeur de la classe ch�teau
	 */
	public Chateau() {
		super();
		this.vieChateau = Constantes.vieChateau;
		this.argent= Constantes.argent;
	}



	@Override
	/**
	 * Fonction permettant de dessiner le ch�teau sur la derni�re case du chemin
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
	 * setter pour les points de vie du ch�teau. Cette fonction nous permettra de mettre � jour
	 * les points de vie du joueur lorsqu'il se fera attaquer.
	 * @param degat
	 */
	public void setVieChateau(int degat) {
		this.vieChateau -= degat;
	}
	
	/**
	 * Fonction permettant de d�terminer si le joueur a perdu (plus de points de vie) ou pas
	 * @return un bool�en qui sera � true si le joueur a perdu
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
