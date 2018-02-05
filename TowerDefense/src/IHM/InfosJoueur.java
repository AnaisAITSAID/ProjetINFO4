package IHM;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Element.Chateau;
import Element.Vague;

public class InfosJoueur extends JPanel{
		private Chateau joueur;
	/**
	 * Affiche numéro de vague, points de vie du joueur, argent du joueur
	 */
		public InfosJoueur(Chateau le_joueur) {
			this.joueur = le_joueur;
			Border loweredbevel = BorderFactory.createLoweredBevelBorder();

			Border border=  BorderFactory.createTitledBorder(
                    loweredbevel, "Informations joueurs");
			this.setBorder(border);
			//this.setBackground(new Color(223, 204, 200));
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setFont(new Font("default", Font.BOLD, 16));
			g2.drawString("Points de vie :" + this.joueur.getVieChateau(), 10, 35);
			g2.drawString("Argent du joueur :" + this.joueur.getArgent(), 10, 60);
			g2.drawString("Numéro vague :" + Vague.num_vague, 10, 85);
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 110);
		}
}
