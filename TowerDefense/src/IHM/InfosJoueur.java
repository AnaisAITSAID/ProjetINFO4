package IHM;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Element.Chateau;
import Element.Vague;

public class InfosJoueur extends JPanel{
		private Chateau joueur;
	/**
	 * Affiche numéro de vague, points de vie du joueur, argent du joueur
	 */
		public InfosJoueur(Chateau le_joueur) {
			this.joueur = le_joueur;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g.drawString("Points de vie :" + this.joueur.getVieChateau(), 120, 20);
			g.drawString("Argent du joueur :" + this.joueur.getArgent(), 120, 40);
			g.drawString("Numéro vague :" + Vague.num_vague, 120, 60);
		}

}
