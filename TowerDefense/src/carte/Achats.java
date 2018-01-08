package carte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import jeu.Jeu;
import utils.Constantes;
import utils.Constantes.Type_tour;


public class Achats extends JPanel{

	private static int nb_produits =2;
	private static int taille_bouton=60;
	private static int espace=2;
	private Rectangle[] boutons_produits=new Rectangle[nb_produits];
	private Type_tour tour_achetee;
	private Color couleur=Color.BLACK;

	//classe interne pour la position de la souris
	private class Souris_position extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent evenement) {
			if(boutons_produits[0].contains(evenement.getPoint())) {
				tour_achetee=Type_tour.TourForte;
			}else if(boutons_produits[1].contains(evenement.getPoint())){
				tour_achetee=Type_tour.TourRapide;
			}
		
		}



	}
	
	public Achats() {
		
		charger_achats();
		
		this.addMouseMotionListener(new Souris_position());
	}
	
	public void charger_achats() {
		System.out.println("largeur du jeu\n " + Jeu.largeur);
		System.out.println("hauteur du jeu\n " + Jeu.hauteur);
		for(int i=0; i<nb_produits;i++) {
			boutons_produits[i]=new Rectangle(((Constantes.tailleCase *5)) - ((nb_produits*(taille_bouton+espace))/2) + ((taille_bouton+espace)*i),10,taille_bouton,taille_bouton);
		}
			 
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.ORANGE); 
		Tour tour_forte_dessin=new TourForte(boutons_produits[0].x,boutons_produits[0].y);
		System.out.println("x et y"+ boutons_produits[0].x + boutons_produits[0].y);
		tour_forte_dessin.dessiner(g);
		Tour tour_rapide_dessin=new TourRapide(boutons_produits[1].x,boutons_produits[1].y);
		tour_rapide_dessin.dessiner(g);
	}

	/*@Override
	public Dimension getPreferredSize() {
		return new Dimension(Jeu.largeur, Jeu.hauteur - Constantes.taille*Constantes.tailleCase);
	}*/
	
	/*@Override
	public Dimension getPreferredSize() {
		return new Dimension(1,1);
	}*/
	
	public static int getTaille_bouton() {
		return taille_bouton;
	}

	public static void setTaille_bouton(int taille_bouton) {
		Achats.taille_bouton = taille_bouton;
	}
}