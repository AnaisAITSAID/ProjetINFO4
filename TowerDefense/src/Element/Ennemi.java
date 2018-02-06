package Element;

import java.awt.Graphics2D;

import utils.Constantes;

public class Ennemi extends AffichageSprite {

	private int pointsDeVie;
	private int monnaiesGenere;	//pièces que le joueur gagne lorsque l'ennemi est tué


	private int caseCourante;	

	private boolean bouge; // un ennemi ne bouge plus si il est mort ou si il est arrivé 
	private int vitesse;
	private int frame;
	private int realX;
	private int realY;
	
	/**
	 * Constructeur de la classe ennemi
	 * @param pointsDeVie 
	 * @param monnaiesGenere
	 * @param caseCourante
	 */
	public Ennemi(double pointsDeVie, double monnaiesGenere, int caseCourante) {
		super();
		this.pointsDeVie = (int)pointsDeVie;
		this.monnaiesGenere = (int)monnaiesGenere;
		this.caseCourante = caseCourante;
		this.bouge = true;
		this.vitesse = 20;
		this.frame = 0;
		this.realX = 0;
		this.realY = 0;
	}


	/**
	 * Fonction représentant l'attaque d'un ennemi
	 * @return la valeur des dégâts causés 
	 */
	public int attaquer () {
		this.bouge = false;
		int degat = 1;
		return degat;
	}
	
	/**
	 * Fonction permettant de savoir si un ennemi est en mouvement ou non
	 * @return un booléen qui sera à true si l'ennemi bouge
	 */
	public boolean isBouge() {
		return this.bouge;
	}


	public void deplacer () {
		this.frame += vitesse;
		if (this.frame >= 40) {
			++this.caseCourante;
			this.frame = 0;
		}

	}
	
	@Override
	/**
	 * Fonction permettant de dessiner les ennemis tout au long du chemin.
	 * Cette fonction fait appel à la fonction orientationCaseSuivante afin de savoir dans quelle 
	 * direction vont les ennemis et donc sur quelle case les dessiner.
	 */
	public void dessiner(Graphics2D g) {
		if (this.caseCourante >= 0 && isBouge()) {
			CaseChemin caseChemin = Chemin.getPos(caseCourante);
			if (Chemin.orientationCaseSuivante(this.caseCourante) == Constantes.Orientation.Droite) {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX() + this.frame, caseChemin.getY(), null);
				this.realX = caseChemin.getX() + this.frame;
				this.realY = caseChemin.getY();
			} else if (Chemin.orientationCaseSuivante(this.caseCourante) == Constantes.Orientation.Haut) {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX(), caseChemin.getY() + this.frame, null);							
				this.realX = caseChemin.getX();
				this.realY = caseChemin.getY() + this.frame;
			} else if (Chemin.orientationCaseSuivante(this.caseCourante) == Constantes.Orientation.Gauche) {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX() - this.frame, caseChemin.getY(), null);							
				this.realX = caseChemin.getX() - this.frame;
				this.realY = caseChemin.getY();
			} else {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX(), caseChemin.getY() - this.frame, null);
				this.realX = caseChemin.getX();
				this.realY = caseChemin.getY() - this.frame;

			}
		}
	}

	public int getRealX() {
		return this.realX + 20;
	}

	public int getRealY() {
		return this.realY +20;
	}

	/**
	 * Fonction permettant de savoir si un ennemi est arrivé au château ou non.
	 * Cela est déterminé par une comparaison de la case courante de l'ennemi avec la 
	 * case du château.
	 * @return un booléen qui est à true si l'ennemi est arrivé
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

	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
		if(this.pointsDeVie <= 0) {
			bouge = false;
		}
	}

	public int getPointsDeVie() {
		return pointsDeVie;
	}
	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public Case getCaseCourante() {
		if (this.caseCourante >= 0)
			return Chemin.getPos(this.caseCourante);
		return null;
	}
	
	public int getMonnaiesGenere() {
		return monnaiesGenere;
	}

}
