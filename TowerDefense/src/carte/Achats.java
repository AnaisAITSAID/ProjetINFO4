package carte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import jeu.Jeu;
import utils.Constantes;


public class Achats extends AffichageSprite{

	private static int nb_produits =2;
	private static int taille_bouton=60;
	private static int espace=2;
	private Rectangle[] boutons_produits=new Rectangle[nb_produits];
	
	public Achats() {
		charger_achats();
	}
	
	public void charger_achats() {
		System.out.println("largeur du jeu\n " + Jeu.largeur);
		System.out.println("hauteur du jeu\n " + Jeu.hauteur);
		for(int i=0; i<nb_produits;i++) {
			boutons_produits[i]=new Rectangle(((Constantes.taille*Constantes.tailleCase)/2) - ((nb_produits*(taille_bouton+espace))/2) + ((taille_bouton+espace)*i),(Constantes.taille*Constantes.tailleCase)+(taille_bouton/2),taille_bouton,taille_bouton);
		}
			 
		
	}
	
	public void dessiner(Graphics g) {
		for(int i=0;i<nb_produits;i++) {
			g.drawImage(sprite.getSpriteTour1(),boutons_produits[i].x, boutons_produits[i].y, taille_bouton,taille_bouton,null);
			if(boutons_produits[i].contains(Carte.pt_souris)) {
				g.setColor(new Color(255, 255, 245, 90));
				g.fillRect(boutons_produits[i].x, boutons_produits[i].y, taille_bouton,taille_bouton);
			}
		}
	}

	public static int getTaille_bouton() {
		return taille_bouton;
	}

	public static void setTaille_bouton(int taille_bouton) {
		Achats.taille_bouton = taille_bouton;
	}
}
