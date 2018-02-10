package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameOver extends JPanel{
	private JButton menu;
	private JButton revanche;
	private Image fond;
	private Image gameover;
	
	public GameOver() {
		
		try {
			fond = ImageIO.read(new File("image/chateau3.jpg"));
			gameover = ImageIO.read(new File("image/GAMEOVER.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.menu = new JButton("Menu");
		this.revanche = new JButton("Revanche");
		
		this.revanche.addActionListener(new Revanche());
		this.menu.setBackground(new Color(119, 181, 254));
		this.revanche.setBackground(new Color(119, 181, 254));
		Retour r;
		r = new Retour();
		this.menu.addActionListener(r);
		this.revanche.setBounds(Jeu.getInstance().getWidth()/2-170, Jeu.getInstance().getHeight()-90,150,50);
		this.menu.setBounds(Jeu.getInstance().getWidth()/2+40, Jeu.getInstance().getHeight()-90,150,50);
		this.setLayout(null);
		this.add(this.menu);
		this.add(this.revanche);
	}
	
	
	private class Retour implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Jeu.getInstance().remove(Jeu.getInstance().getGameOver());
			Jeu.getInstance().lanceMenu();
		}
		
	}
	
	private class Revanche implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Jeu.getInstance().remove(Jeu.getInstance().getGameOver());
			Jeu.getInstance().interfaceUtilisateur();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(fond, 0, 0, Jeu.getInstance().getWidth(), Jeu.getInstance().getHeight(),null);
		g2.drawImage(gameover, Jeu.getInstance().getWidth()-750, Jeu.getInstance().getHeight()-800, 600,600, null);
	}
}
