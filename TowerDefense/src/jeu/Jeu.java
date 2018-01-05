package jeu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;

import carte.Achats;
import carte.Carte;
import carte.Souris_position;

public class Jeu extends JFrame{
	/* crï¿½ation d'une fenï¿½tre */
	
	public static int largeur;
	public static int hauteur;
	//point où se trouve la souris 
		public static Point pt_souris= new Point(0,0);
	public Jeu () {
		
		this.setTitle("Tower Defense");
		
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		largeur= (int)dimension.getHeight();
		hauteur= (int)dimension.getWidth();
		setSize(hauteur, largeur);
		this.setResizable(false);
		this.addMouseListener(new Souris_position());
		this.addMouseMotionListener(new Souris_position());
		
	}
	
	/* crï¿½ation de l'interface utilisateur */
	/* pour commencer on se contentera d'ajouter la carte */
	public void interfaceUtilisateur () {
		Carte carte = new Carte();
		Achats zone_achats=new Achats();
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc= new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1;
		gc.weighty = 0.70;

		
		this.add(carte, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.gridwidth = 1;
		gc.weightx = 1;
		gc.weighty = 0.30;
		this.add(zone_achats, gc);

		this.setVisible(true);
 
	}
	
	public static void main(String [] args){
		 Jeu jeu = new Jeu ();
		 jeu.interfaceUtilisateur();

	}
}
