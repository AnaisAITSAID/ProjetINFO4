package carte;

import java.awt.Graphics;

import utils.Constantes;

public class Ennemi extends AffichageSprite {

	private int pointsDeVie;
	private int monnaiesGenere;	//pi�ces que le joueur gagne lorsque l'ennemi est tu�
	private int caseCourante;	

	private boolean bouge; // un ennemi ne bouge plus si il est mort ou si il est arriv� 
	private int vitesse;
	private int frame;
	
	/**
	 * Constructeur de la classe ennemi
	 * @param pointsDeVie 
	 * @param monnaiesGenere
	 * @param caseCourante
	 */
	public Ennemi(int pointsDeVie, int monnaiesGenere, int caseCourante) {
		super();
		this.pointsDeVie = pointsDeVie;
		this.monnaiesGenere = monnaiesGenere;
		this.caseCourante = caseCourante;
		this.bouge = true;
		this.vitesse = 20;
		this.frame = 0;
	}

	/**
	 * Fonction repr�sentant l'attaque d'un ennemi
	 * @return la valeur des d�g�ts caus�s 
	 */
	public int attaquer () {
		this.bouge = false;
		int degat = 1;
		return degat;
	}
	
	/**
	 * Fonction permettant de savoir si un ennemi est en mouvement ou non
	 * @return un bool�en qui sera � true si l'ennemi bouge
	 */
	public boolean isBouge() {
		return this.bouge;
	}


	public void deplacer () {
		this.frame += vitesse;
		if (this.frame == 40) {
			++this.caseCourante;
			this.frame = 0;
		}
	}
	
	@Override
	/**
	 * Fonction permettant de dessiner les ennemis tout au long du chemin.
	 * Cette fonction fait appel � la fonction orientationCaseSuivante afin de savoir dans quelle 
	 * direction vont les ennemis et donc sur quelle case les dessiner.
	 */
	public void dessiner(Graphics g) {
		if (this.caseCourante >= 0) {
			CaseChemin caseChemin = Chemin.getPos(caseCourante);
			if (Chemin.orientationCaseSuivante(this.caseCourante) == Constantes.Orientation.Droite) {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX() + this.frame, caseChemin.getY(), null);							
			} else if (Chemin.orientationCaseSuivante(this.caseCourante) == Constantes.Orientation.Haut) {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX(), caseChemin.getY() + this.frame, null);							
			} else if (Chemin.orientationCaseSuivante(this.caseCourante) == Constantes.Orientation.Gauche) {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX() - this.frame, caseChemin.getY(), null);							
			} else {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX(), caseChemin.getY() - this.frame, null);											
			}
		}
	}

	/**
	 * Fonction permettant de savoir si un ennemi est arriv� au ch�teau ou non.
	 * Cela est d�termin� par une comparaison de la case courante de l'ennemi avec la 
	 * case du ch�teau.
	 * @return un bool�en qui est � true si l'ennemi est arriv�
	 */
	public boolean estArrive () {
		boolean estArrive = false;
		if (this.caseCourante >=0 ) {
			
			CaseChemin caseChemin = Chemin.getPos(this.caseCourante);
			if (caseChemin == Chemin.getArrive()){
				estArrive = true;
			} 
		} else {
			estArrive = false;
		}
		return estArrive;
	}

	
}
