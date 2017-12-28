package carte;

import java.awt.Graphics;

public class Ennemi extends AffichageSprite {

	private int pointsDeVie;
	private int monnaiesGenere;
	private int caseCourante;	
	private boolean bouge; // un ennemi ne bouge plus si il est mort ou si il est arrivé 
	
	public Ennemi(int pointsDeVie, int monnaiesGenere) {
		super();
		this.pointsDeVie = pointsDeVie;
		this.monnaiesGenere = monnaiesGenere;
		this.caseCourante = 0;
		this.bouge = true;
	}


	public void attaquer () {
		this.bouge = false;
		System.out.println("L'ennemis Attaque ! ");
	}
	

	public boolean isBouge() {
		return this.bouge;
	}


	public void deplacer () {
		System.out.println(this.caseCourante);
		CaseChemin caseChemin = Chemin.getPos(caseCourante);
		
		++this.caseCourante;
		if (caseChemin == Chemin.getArrive())
			attaquer();
	}
	
	@Override
	public void dessiner(Graphics g) {
		
		CaseChemin caseChemin = Chemin.getPos(caseCourante);
		g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX(), caseChemin.getY(), null);
	}

	
}
