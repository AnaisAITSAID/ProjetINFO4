package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Element.CaseJouable;
import Element.Chateau;
import Exception.ExceptionFenetre;
import utils.Constantes;
import utils.Constantes.Type_tour;


public class Jeu extends JFrame{
	private Achats zone_achats;
	private Carte carte;
	private Chateau joueur; 
	private InfosTour infoTour;
	private InfosJoueur infoJoueur;
	private Menu m;
	private Aide a;
	private static Jeu jeu; 
	private GameOver gameOver;
	JPanel jp;
	JPanel jp2;
    private Clip clip;
	public static Jeu getInstance() {
		if (jeu == null) {
			jeu  = new Jeu(); 			
		}
		return jeu;
	}
	private Jeu () {
		
		this.setTitle("Tower Defense");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setResizable(false);
	}

	
	public void lanceMenu() {
		m = new Menu();
		this.add(m);
		URL u1;

		try {
			u1 = new URL("file:son/menu.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(u1);
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pack();
		this.setVisible(true);
	}
	public void stopSonMenu() {
		clip.stop();
	}

	
	public Achats getZone_achats() {
		return zone_achats;
	}
	public Carte getCarte() {
		return carte;
	}
	public Chateau getJoueur() {
		return joueur;
	}
	public InfosTour getInfoTour() {
		return infoTour;
	}
	public InfosJoueur getInfoJoueur() {
		return infoJoueur;
	}
	public Menu getM() {
		return m;
	}
	public Aide getA() {
		return a;
	}
	public static Jeu getJeu() {
		return jeu;
	}
	
	public void afficherAide() {
		a = new Aide();
		this.add(a);
		
		this.setVisible(true);
	}
	
	
	/* cr�ation de l'interface utilisateur */
	/* pour commencer on se contentera d'ajouter la carte */
	public void interfaceUtilisateur () {
		
		//instances des panneaux
		carte = new Carte();
		
		zone_achats = new Achats();
		
		
		joueur=new Chateau();
		
		JButton menuButton = new JButton("Menu");
		menuButton.setPreferredSize(new Dimension(130, 100));
		jp = new JPanel();
		jp2  = new JPanel();
		infoTour = new InfosTour();
		infoJoueur = new InfosJoueur(this.joueur);
		
		
		carte.setI_j(infoJoueur);
		this.add(carte);

		jp.setLayout(new FlowLayout());
		jp.add(carte, FlowLayout.LEFT);
		jp.add(infoTour);
		jp2.setLayout(new FlowLayout());
		jp2.add(zone_achats, FlowLayout.LEFT);
		jp2.add(infoJoueur);
		jp2.add(menuButton);

		this.add(jp);

		this.add(jp2,BorderLayout.SOUTH);
		
		this.pack();
	
		this.setVisible(true);

		menuButton.setBackground(new Color(119, 181, 254));
		carte.setChateau(joueur);
		menuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				carte.stopGame();
				remove(jp);
				remove (jp2);
				lanceMenu();
			}
		});
	}
	
	public void lanceGameOver() {
		carte.stopGame();
		remove(jp);
		remove (jp2);
		this.gameOver = new GameOver();
		this.add(this.gameOver);
		
		this.setVisible(true);
	}
	public GameOver getGameOver() {
		return this.gameOver;
	}

	public static void main(String [] args){
		 Jeu jeu = Jeu.getInstance();
		 jeu.lanceMenu();
	}
}