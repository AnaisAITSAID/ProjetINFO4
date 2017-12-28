package jeu;

import javax.swing.JFrame;

import carte.Carte;

public class Jeu extends JFrame{
	/* cr�ation d'une fen�tre */
	public Jeu () {
		
		this.setTitle("Tower Defense");
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	/* cr�ation de l'interface utilisateur */
	/* pour commencer on se contentera d'ajouter la carte */
	public void interfaceUtilisateur () {
		Carte carte = new Carte();
		this.setContentPane(carte);
		this.setVisible(true);
		Thread thread = new Thread(carte);
		thread.start();
 
	}
	
	public static void main(String [] args){
		 Jeu jeu = new Jeu ();
		 jeu.interfaceUtilisateur();
	}
}
