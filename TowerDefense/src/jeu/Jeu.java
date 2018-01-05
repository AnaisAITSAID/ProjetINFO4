package jeu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JFrame;

import carte.Achats;
import carte.Carte;
import carte.Souris_position;

public class Jeu extends JFrame{
	/* cr�ation d'une fen�tre */
	
	public static int largeur;
	public static int hauteur;
	//point o� se trouve la souris 
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
	
	/* cr�ation de l'interface utilisateur */
	/* pour commencer on se contentera d'ajouter la carte */
	public void interfaceUtilisateur () {
		Carte carte = new Carte();
		Achats zone_achats=new Achats();
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc= new GridBagConstraints();
		gc.fill=GridBagConstraints.BOTH;
		gc.insets=new Insets(1,1,1,1);
		gc.ipady=gc.anchor=GridBagConstraints.WEST;
		gc.weightx=4;
		gc.weighty=4;
		gc.gridx=0;
		gc.gridy=0;
	
		this.add(carte,gc);
		gc.gridx=0;
		gc.gridy=1;
	
		this.add(zone_achats,gc);
		this.setVisible(true);
 
	}
	
	public static void main(String [] args){
		 Jeu jeu = new Jeu ();
		 jeu.interfaceUtilisateur();

	}
}
