package jeu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		largeur= (int)dimension.getWidth();
		hauteur= (int)dimension.getHeight();
		setSize(largeur, hauteur);
		this.setResizable(false);
		this.addMouseListener(new Souris_position());
		this.addMouseMotionListener(new Souris_position());
		
	}
	
	/* crï¿½ation de l'interface utilisateur */
	/* pour commencer on se contentera d'ajouter la carte */
	public void interfaceUtilisateur () {
		Carte carte = new Carte();
		Achats zone_achats=new Achats();
		JPanel jp  = new JPanel();
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
		 
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 2;
		jp.add( zone_achats, gbc);
		this.add(jp);
		this.setVisible(true);
		
	}
	
	public static void main(String [] args){
		 Jeu jeu = new Jeu ();
		 jeu.interfaceUtilisateur();

	}
}