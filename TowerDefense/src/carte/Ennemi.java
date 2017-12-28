package carte;

import java.awt.Graphics;

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
		this.vitesse = 4;
		this.frame = 0;
	}


	public void attaquer () {
		this.bouge = false;
		System.out.println("L'ennemi Attaque ! ");
	}
	

	public boolean isBouge() {
		return this.bouge;
	}


	public void deplacer () {
		if (this.caseCourante >= 0) {
			CaseChemin caseChemin = Chemin.getPos(caseCourante);
			if (caseChemin == Chemin.getArrive())
				attaquer();
			else {
				this.frame += vitesse;
				if (this.frame == 40) {
					++this.caseCourante;
					this.frame = 0;
				}
			}
		}else {
			this.frame += vitesse;
			if (this.frame == 40) {
				++this.caseCourante;
				this.frame = 0;
			}
		}
	
		
	}
	
	@Override
	public void dessiner(Graphics g) {
		System.out.println(caseCourante);
		if (this.caseCourante >= 0) {
			
			System.out.println(this.caseCourante);
			CaseChemin caseChemin = Chemin.getPos(caseCourante);
			if (Chemin.orientationCaseSuivante(this.caseCourante) == 1) {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX() + this.frame, caseChemin.getY(), null);							
			} else if (Chemin.orientationCaseSuivante(this.caseCourante) == 2) {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX(), caseChemin.getY() + this.frame, null);							
			} else if (Chemin.orientationCaseSuivante(this.caseCourante) == 3) {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX() - this.frame, caseChemin.getY(), null);							
			} else {
				g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX(), caseChemin.getY() - this.frame, null);											
			}
		}
	}

	
}
