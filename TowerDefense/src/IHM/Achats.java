package IHM;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Element.BoutonsProduits;
import utils.Constantes;
import utils.Constantes.Type_tour;


public class Achats extends JPanel{

	private static int nb_produits =2;
	private static int taille_bouton=60;
	private static int espace=2;
	private BoutonsProduits[] boutons_produits=new BoutonsProduits[nb_produits];
	private Type_tour tour_achetee;
	private Color couleur=Color.BLACK;
	
	
	public Achats() {
		charger_achats();		
	}
	
	public void setTour_achetee(Type_tour tour_achetee) {
		this.tour_achetee = tour_achetee;
	}
	
	public Type_tour getTour_achetee() {
		return tour_achetee;
	}
	public BoutonsProduits[] getBoutons_produits() {
		return boutons_produits;
	}

	public void charger_achats() {
		for(int i=0; i<nb_produits;i++) {
			boutons_produits[i] = new BoutonsProduits(((Constantes.tailleCase *5)) - ((nb_produits*(taille_bouton+espace))/2) + ((taille_bouton+espace)*i), 10, Type_tour.values()[i]);
		}
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//setBackground(Color.ORANGE); 
		for(BoutonsProduits bouton : this.boutons_produits) {
			bouton.dessiner(g2);
		}
	}
	

	
}