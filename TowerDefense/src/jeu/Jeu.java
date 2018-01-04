package jeu;

import java.awt.Frame;

import javax.swing.JFrame;

import carte.Carte;
import carte.Souris_position;

public class Jeu extends JFrame{
	/* cr�ation d'une fen�tre */
	
	public static int largeur;
	public static int hauteur;
	public Jeu () {
		
		this.setTitle("Tower Defense");
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		largeur=this.getWidth();
		hauteur=this.getHeight();
		//this.setResizable(false);
		
	}
	
	/* cr�ation de l'interface utilisateur */
	/* pour commencer on se contentera d'ajouter la carte */
	public void interfaceUtilisateur (Frame fenetre) {
		Carte carte = new Carte(fenetre);
		this.setContentPane(carte);
		
		this.setVisible(true);
 
	}
	
	public static void main(String [] args){
		 Jeu jeu = new Jeu ();
		 jeu.interfaceUtilisateur(jeu);

	}
}
