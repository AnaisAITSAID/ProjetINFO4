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
import utils.Constantes;
import utils.Sprites;

public class InfosJoueur extends JPanel{
		private Chateau joueur;
		Sprites sprite = Sprites.getInstance();
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
//			g2.drawString("Points de vie :" + this.joueur.getVieChateau(), 10, 35);
			g2.drawString("Points de vie : ", 10, 35);
			int cpt = 0;
			for (int i = 0; i < this.joueur.getVieChateau(); ++i) {
				g.drawImage(sprite.getSpriteLife(), 130+i*16, 22,null);
				cpt = i;
			}
			if (cpt > 0) ++cpt;
			while (cpt <= 9) {
				g.drawImage(sprite.getSpriteNoLife(), 130+cpt*16, 22,null);
				++cpt;
			}
			g2.drawString("Argent du joueur :" + this.joueur.getArgent(), 10, 60);
			g2.drawString("Numéro vague :" + Vague.num_vague, 10, 85);
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(450, 110);
		}
}
