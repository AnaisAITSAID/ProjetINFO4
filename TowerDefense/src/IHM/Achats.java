package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Element.BoutonsProduits;
import utils.Constantes;
import utils.Constantes.Type_tour;


public class Achats extends JPanel{

	private static int nb_produits =2;
	private static int taille_bouton=60;
	private static int espace=15;
	private BoutonsProduits[] boutons_produits=new BoutonsProduits[nb_produits];
	private Type_tour tour_achetee;
	
	
	public Achats() {
		charger_achats();		
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		
		Border border=  BorderFactory.createTitledBorder(
                loweredbevel, "Achat");
		this.setBorder(border);
		this.addMouseListener(new Souris_position());
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
			boutons_produits[i] = new BoutonsProduits(taille_bouton*i+espace+75*i, 25, Type_tour.values()[i]);
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
	
	//classe interne pour la position de la souris
	private class Souris_position extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent evenement) {
			
			if(getBoutons_produits()[0].contain(evenement.getX(), evenement.getY())) {
				Jeu.getInstance().getCarte().setTypeTourAjoutee(Type_tour.TourForte);
			}else if(getBoutons_produits()[1].contain(evenement.getX(), evenement.getY())){
				Jeu.getInstance().getCarte().setTypeTourAjoutee(Type_tour.TourRapide);
			}
		}
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Constantes.tailleCase*nb_produits+espace+200, 110);
	}
	
}