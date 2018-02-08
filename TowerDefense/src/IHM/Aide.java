package IHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import IHM.Jeu.JouerAide;
import IHM.Jeu.Retour;

public class Aide extends JPanel{
	
	private JButton retour;
	private JButton jouerAide;
	private Image fond;
	private Jeu j;
	
	public Aide(Jeu j) {
		this.j=j;
		try {
			fond = ImageIO.read(new File("image/fondaide.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		retour = new JButton("Retour");
		jouerAide = new JButton("Jouer");
		
		JouerAide ja;
		ja = j.new JouerAide();
		jouerAide.addActionListener(ja);
		
		Retour r;
		r = j.new Retour();
		retour.addActionListener(r);
		jouerAide.setBounds(j.getWidth()/2-170, j.getHeight()-90,150,50);
		retour.setBounds(j.getWidth()/2+40, j.getHeight()-90,150,50);
		this.setLayout(null);
		this.add(retour);
		this.add(jouerAide);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(fond, 0, 0, j.getWidth(), j.getHeight(),null);
		
		
		g2.setFont(new Font("default", Font.BOLD, 16));
		g2.drawString("Bienvenu dans Tower Defense, le jeu dont vous ne pourrez plus vous passer !!!", 10, 20);
		g2.setFont(new Font("default", Font.BOLD, 18));
		g2.drawString("Objectifs du jeu :", 10, 50);
		g2.setFont(new Font("default", Font.BOLD, 16));
		g2.drawString("L'objectif de ce jeu est de d�fendre votre ch�teau qui se situe au bout d'un chemin.", 10, 80);
		g2.drawString("Tout au long de ce chemin des ennemis se d�placent dans le but d'attaquer votre ch�teau.", 10, 100);
		
		g2.setFont(new Font("default", Font.BOLD, 18));
		g2.drawString("Indications:", 10, 130);
		g2.setFont(new Font("default", Font.BOLD, 16));
		g2.drawString("Pour d�fendre votre ch�teau, vous devez placer des tours sur les cases qui",10, 160);
		g2.drawString("ne font pas parties du chemin (les cases vertes).",10, 180);
		g2.drawString("Pour placer une tour, il faut acherter des tours.", 10, 200);
		g2.drawString("Pour acheter une tour il faut cliquer sur la tour que vous souhaitez acqu�rir",10,220);
		g2.drawString("dans la zone d'achats situ�e en bas � gauche de l'�cran.",10, 240);
		g2.drawString("Puis, cliquer sur la case o� vous souhaitez placer la tour.", 10, 260);
		g2.drawString("Pensez � vous assurer que vous disposez de l'argent n�cessaire pour l'achat de la tour.",10, 280);
		g2.drawString("Vous pouvez consulter les informations des tours pr�sentes sur la carte en cliquant", 10, 300);
		g2.drawString("sur la tour de votre choix.", 10, 320);
		g2.drawString("Les d�tails des informations s'affichent � droite de l'�cran ainsi que les am�liorations", 10, 340);
		g2.drawString("possibles pour cette tour.", 10, 360);
		g2.drawString("Pour am�liorer la tour, il vous suffit de cliquer sur le bouton Am�lioration.", 10, 380);
		g2.drawString("Les tours vont tirer sur les ennemis pour les �liminer", 10, 400);
		
		g2.setFont(new Font("default", Font.BOLD, 40));
		g2.setPaint(Color.RED);
		g2.drawString("Bonne chance � toutes et � tous!!!", 20, 500);
	}
}