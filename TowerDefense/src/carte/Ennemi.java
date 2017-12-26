package carte;

import java.awt.Graphics;

public class Ennemi extends AffichageSprite {

	private int pointsDeVie;
	private int monnaiesGenere;
	private int caseCourante;	

	
	public Ennemi(int pointsDeVie, int monnaiesGenere) {
		super();
		this.pointsDeVie = pointsDeVie;
		this.monnaiesGenere = monnaiesGenere;
		this.caseCourante = 0;
	}


	public void attaquer () {
		
	}
	

	public void deplacer () {
		++this.caseCourante;
		
	}
	
	@Override
	public void dessiner(Graphics g) {
		
		CaseChemin caseChemin = Chemin.getPos(caseCourante);
		g.drawImage(sprite.getSpriteEnnemi(), caseChemin.getX(), caseChemin.getY(), null);
	}

}
