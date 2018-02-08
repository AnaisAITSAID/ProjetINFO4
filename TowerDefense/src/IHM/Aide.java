package IHM;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import IHM.Jeu.JouerAide;
import IHM.Jeu.Retour;

public class Aide extends JPanel{
	
	private JButton retour;
	private JButton jouerAide;
	
	public Aide(Jeu j) {
		retour = new JButton("Retour");
		jouerAide = new JButton("Jouer");
		
		JouerAide ja;
		ja = j.new JouerAide();
		jouerAide.addActionListener(ja);
		
		Retour r;
		r = j.new Retour();
		retour.addActionListener(r);
		
		this.add(retour);
		this.add(jouerAide);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawString("Bienvenu dans Tower Defense, le jeu dont vous ne pourrez plus vous passer !!!", 10, 10);
		g2.drawString("Objectifs du jeu :", 10, 20);
		g2.drawString("L'objectif de ce jeu est de défendre votre château qui se situe au bout d'un chemin. Tout au long de ce chemin des ennemis se déplacent dans le but d'attaquer votre château.", 10, 30);
		
		g2.drawString("Indications:", 10, 40);
	
	}
}