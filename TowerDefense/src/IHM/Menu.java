package IHM;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import IHM.Jeu.Jouer;
import IHM.Jeu.Regles;

public class Menu extends JPanel{

	private JButton jouer;
	private JButton regles;
	private Jeu j;
	private Image fond;
	private Image titre;
	
	public Menu(Jeu j) {
		this.j = j;
		
		try {
			fond = ImageIO.read(new File("image/fond.png"));
			titre = ImageIO.read(new File("image/TOWERDEFENSE.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jouer = new JButton("Jouer");
		regles = new JButton("Règles");
		Jouer jo;
		jo = j.new Jouer();
		jouer.addActionListener(jo);
		Regles ad;
		ad = j.new Regles();
		regles.addActionListener(ad);
		this.setLayout(null);
		jouer.setBounds(j.getWidth()/2-170, j.getHeight()-90,150,50);
		regles.setBounds(j.getWidth()/2+40, j.getHeight()-90,150,50);
		this.add(jouer);
		this.add(regles);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(fond, 0, 0, j.getWidth(), j.getHeight(),null);
        g2.drawImage(titre,	j.getWidth()-560, j.getHeight()-650, 500, 500, null);
	}
}
