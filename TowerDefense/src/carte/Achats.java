package carte;

import java.awt.Graphics;
import java.awt.Rectangle;

import jeu.Jeu;


public class Achats {

	private static int nb_produits =2;
	private static int taille_bouton=60;
	private static int espace=2;
	private Rectangle[] boutons_produits=new Rectangle[nb_produits];
	
	public Achats() {
		charger_achats();
	}
	
	public void charger_achats() {
		for(int i=0; i<nb_produits;i++) {
			boutons_produits[i]=new Rectangle((Jeu.largeur/2) - ((nb_produits*(taille_bouton+espace))/2) + ((taille_bouton+espace)*i),Jeu.hauteur-(taille_bouton*2),taille_bouton,taille_bouton);
		}
			 
		
	}
	
	public void dessiner(Graphics g) {
		for(int i=0;i<nb_produits;i++) {
			g.fillRect(boutons_produits[i].x, boutons_produits[i].y, taille_bouton,taille_bouton);
		}
	}
}
