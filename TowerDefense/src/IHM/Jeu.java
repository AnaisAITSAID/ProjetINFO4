package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Element.Chateau;
import utils.Constantes;
import utils.Constantes.Type_tour;


public class Jeu extends JFrame{
	/* cr�ation d'une fen�tre */
	
	public static int largeur;
	public static int hauteur;
	private Achats zone_achats;
	private Carte carte;
	private Chateau joueur; 
	private InfosTour infoTour;
	private InfosJoueur infoJoueur;
	
	public Jeu () {
		
		this.setTitle("Tower Defense");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		largeur= (int)dimension.getWidth();
		hauteur= (int)dimension.getHeight();
		setSize(largeur, hauteur);
		
		this.setResizable(false);
		
		carte = new Carte();
		
		zone_achats = new Achats();
		zone_achats.addMouseListener(new Souris_position());
		
		joueur=new Chateau();
	}
	
	/* cr�ation de l'interface utilisateur */
	/* pour commencer on se contentera d'ajouter la carte */
	public void interfaceUtilisateur () {
		
		JPanel jp  = new JPanel();
		infoTour = new InfosTour(this.carte);
		carte.addMouseListener(new SelectTour());
		infoJoueur = new InfosJoueur(this.joueur);
		
		//carte.setI_t(infoTour);

		jp.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		 
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		jp.add(carte, gbc);
		
		gbc.gridx = 2;
		jp.add(infoTour, gbc);
		
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 2;
		jp.add( zone_achats, gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 2;
		jp.add(infoJoueur, gbc);
		
		this.add(jp);
		this.setVisible(true);
		infoTour.setBackground(Color.BLUE);
		infoJoueur.setBackground(Color.green);
	}
	
	
	//classe interne pour la position de la souris
	private class Souris_position extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent evenement) {
			
			if(zone_achats.getBoutons_produits()[0].contain(evenement.getX(), evenement.getY())) {
				carte.setTypeTourAjoutee(Type_tour.TourForte);
			}else if(zone_achats.getBoutons_produits()[1].contain(evenement.getX(), evenement.getY())){
				carte.setTypeTourAjoutee(Type_tour.TourRapide);
			}
		}
	}
	
	public class SelectTour extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent evenement) {
			System.out.println("lapin11");
			for (int i = 0; i < Constantes.taille; ++i) {
				for (int j = 0; j < Constantes.taille; ++j) {
					System.out.println("lapin1");
					if(carte.getCarte(j, i).contain(evenement.getX(), evenement.getY()) && carte.getCarte(j, i).getType() == Constantes.Type.CaseJouable && (((Constantes.Type.CaseJouable)(carte.getCarte(j, i))).getTour())!=null) {
						infoTour.setTourInfo();
						System.out.println("lapin2");
					}
				}
			}
			repaint();
		}
	}
	public static void main(String [] args){
		 Jeu jeu = new Jeu ();
		 jeu.interfaceUtilisateur();

	}
}