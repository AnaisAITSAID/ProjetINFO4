package IHM;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Element.Chateau;
import Element.Vague;
import utils.Constantes;

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
			g2.setFont(new Font("default", Font.BOLD, 16));
			g2.drawString("Points de vie :" + this.joueur.getVieChateau(), 120, 20);
			g2.drawString("Argent du joueur :" + this.joueur.getArgent(), 120, 45);
			g2.drawString("Numéro vague :" + Vague.num_vague, 120, 70);
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 120);
		}
}
