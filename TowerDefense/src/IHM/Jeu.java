package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Element.CaseJouable;
import Element.Chateau;
import utils.Constantes;
import utils.Constantes.Type_tour;


public class Jeu extends JFrame{
	/* crï¿½ation d'une fenï¿½tre */
	
	public static int largeur;
	public static int hauteur;
	private Achats zone_achats;
	private Carte carte;
	private Chateau joueur; 
	private InfosTour infoTour;
	private InfosJoueur infoJoueur;
	
	public class AmeliorationPossible implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int prix = infoTour.getTourInfo().getNiveau();
			if(joueur.getArgent()>=prix) {
				joueur.setArgent(joueur.getArgent()-prix);
				if(infoTour.getTourInfo().getType_tour()==Type_tour.TourForte) {
					infoTour.getTourInfo().setDegats(infoTour.getTourInfo().getDegats()+10*infoTour.getTourInfo().getNiveau());
					infoTour.getTourInfo().setNiveau(infoTour.getTourInfo().getNiveau()+1);
				}else {
					infoTour.getTourInfo().setVitesse(infoTour.getTourInfo().getVitesse()+infoTour.getTourInfo().getNiveau());
					infoTour.getTourInfo().setNiveau(infoTour.getTourInfo().getNiveau()+1);
				}
				infoTour.repaint();
				infoJoueur.repaint();
			}else {
				System.out.println("vous n'avez pas assez d'argent pour l'amélioration");
			}
			
		}
		
	}
	public Jeu () {
		
		this.setTitle("Tower Defense");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	/*	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		largeur= (int)dimension.getWidth();
		hauteur= (int)dimension.getHeight();
		setSize(largeur, hauteur);
*/		
		this.setResizable(false);
		
		carte = new Carte();
		
		zone_achats = new Achats();
		zone_achats.addMouseListener(new Souris_position());
		carte.addMouseListener(new SelectTour());
		
		joueur=new Chateau();
	}
	
	
	/* crï¿½ation de l'interface utilisateur */
	/* pour commencer on se contentera d'ajouter la carte */
	public void interfaceUtilisateur () {
		
		JPanel jp  = new JPanel();
		JPanel jp2  = new JPanel();

		infoTour = new InfosTour(this);
		infoJoueur = new InfosJoueur(this.joueur);
		carte.setI_j(infoJoueur);
		this.add(carte);
//		this.setLayout();
		jp.setLayout(new FlowLayout());
		jp.add(carte, FlowLayout.LEFT);
		jp.add(infoTour);

		jp2.setLayout(new FlowLayout());
		jp2.add(zone_achats, FlowLayout.LEFT);
		jp2.add(infoJoueur);
		this.add(jp);

		this.add(jp2,BorderLayout.SOUTH);
		/*GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		 
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		
		
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
		
		this.add(jp);*/
		this.pack();
	
		this.setVisible(true);
//		infoTour.setBackground(Color.BLUE);
//		infoJoueur.setBackground(Color.green);

		carte.setChateau(joueur);
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
			//System.out.println("lapin11");
			for (int i = 0; i < Constantes.taille; ++i) {
				for (int j = 0; j < Constantes.taille; ++j) {
				//	System.out.println("lapin1");
					if(carte.getCarte(j, i).contain(evenement.getX(), evenement.getY()) && carte.getCarte(j, i).getType() == Constantes.Type.CaseJouable && (((CaseJouable)(carte.getCarte(j, i))).getTour())!=null) {
						infoTour.setTourInfo(((CaseJouable)(carte.getCarte(j, i))).getTour());
						System.out.println("lapin2");
					}
				}
			}
			infoTour.repaint();
		}
	}
	public static void main(String [] args){
		 Jeu jeu = new Jeu ();
		 jeu.interfaceUtilisateur();

	}
}