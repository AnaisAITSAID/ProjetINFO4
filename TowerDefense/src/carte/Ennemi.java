package carte;

import java.awt.Graphics;

import utils.Constantes;

public class Ennemi extends AffichageSprite {

	private int pointsDeVie;
	private int monnaiesGenere;
	private int caseCourante;	

	private boolean bouge; // un ennemi ne bouge plus si il est mort ou si il est arrivé 
	private int vitesse;
	private int frame;
	public Ennemi(int pointsDeVie, int monnaiesGenere, int caseCourante) {
		super();
		this.pointsDeVie = pointsDeVie;
		this.monnaiesGenere = monnaiesGenere;
		this.caseCourante = caseCourante;
		this.bouge = true;
		this.vitesse = 20;
		this.frame = 0;
	}


	public int attaquer () {
		this.bouge = false;
		int degat = 1;
		return degat;
	}
	

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
