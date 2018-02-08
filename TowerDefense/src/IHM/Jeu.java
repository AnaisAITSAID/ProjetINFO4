package IHM;

import java.applet.AudioClip;
import java.awt.BorderLayout;
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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Element.CaseJouable;
import Element.Chateau;
import Exception.ExceptionFenetre;
import utils.Constantes;
import utils.Constantes.Type_tour;


public class Jeu extends JFrame{
	/* crï¿½ation d'une fenï¿½tre */
	private URL u1;
	private AudioClip s1;	
	private Clip clip;
	public static int largeur;
	public static int hauteur;
	private Achats zone_achats;
	private Carte carte;
	private Chateau joueur; 
	private InfosTour infoTour;
	private InfosJoueur infoJoueur;
	private Menu m;
	private Aide a;
	
	public class Jouer implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			clip.stop();
			remove(m);
			interfaceUtilisateur();
		}
		
	}
	
	public class Regles implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			remove(m);
			afficherAide();
		}
		
	}
	
	public class JouerAide implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			remove(a);
			interfaceUtilisateur();
		}
		
	}
	
	public class Retour implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			remove(a);
			retourMenu();
			setVisible(true);
		}
		
	}
	public class AmeliorationPossible implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int prix = infoTour.getTourInfo().getPrix();
			if(joueur.getArgent()>=prix && infoTour.getTourInfo() != null) {
				infoTour.getTourInfo().setPrix();
				
				joueur.setArgent(joueur.getArgent()-prix);	
				infoTour.getTourInfo().setDegats();	
				
				infoTour.getTourInfo().setNiveau();
				infoTour.repaint();
				infoJoueur.repaint();
			}else {
				try {
					throw new ExceptionFenetre("Vous n'avez pas assez d'argent pour améliorer la tour");
				} catch (ExceptionFenetre e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	public Jeu () {
		
		this.setTitle("Tower Defense");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();*/
		this.setSize(700,700);
		this.setResizable(false);
		m = new Menu(this);
		this.add(m);

		
		try {
			u1 = new URL("file:son/intro.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(u1);
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		this.setVisible(true);
		
	}
	
	public void afficherAide() {
		a = new Aide(this);
		this.add(a);
		
		this.setVisible(true);
	}
	
	public void retourMenu() {
		m = new Menu(this);
		this.add(m);
		this.setVisible(true);
	}
	
	/* crï¿½ation de l'interface utilisateur */
	/* pour commencer on se contentera d'ajouter la carte */
	public void interfaceUtilisateur () {
		
		//instances des panneaux
		carte = new Carte();
		
		zone_achats = new Achats();
		zone_achats.addMouseListener(new Souris_position());
		carte.addMouseListener(new SelectTour());
		
		joueur=new Chateau();
		
		JPanel jp  = new JPanel();
		JPanel jp2  = new JPanel();

		infoTour = new InfosTour(this);
		infoJoueur = new InfosJoueur(this.joueur);
		carte.setI_j(infoJoueur);
		this.add(carte);

		jp.setLayout(new FlowLayout());
		jp.add(carte, FlowLayout.LEFT);
		jp.add(infoTour);
		jp2.setLayout(new FlowLayout());
		jp2.add(zone_achats, FlowLayout.LEFT);
		jp2.add(infoJoueur);
		this.add(jp);

		this.add(jp2,BorderLayout.SOUTH);

		this.pack();
	
		this.setVisible(true);


		carte.setChateau(joueur);
	}
	
	
	//classe interne pour la position de la souris
	private class Souris_position extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent evenement) {
			
			if(zone_achats.getBoutons_produits()[0].contain(evenement.getX(), evenement.getY())) {
				carte.setTypeTourAjoutee(Type_tour.TourForte);
			}else if(zone_achats.getBoutons_produits()[1].contain(evenement.getX(), evenement.getY())){
				carte.setTypeTourAjoutee(Type_tour.TourRapide);
			}
		}
	}
	
	public class SelectTour extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent evenement) {
			for (int i = 0; i < Constantes.taille; ++i) {
				for (int j = 0; j < Constantes.taille; ++j) {
					if(carte.getCarte(j, i).contain(evenement.getX(), evenement.getY()) && carte.getCarte(j, i).getType() == Constantes.Type.CaseJouable && (((CaseJouable)(carte.getCarte(j, i))).getTour())!=null) {
						infoTour.setTourInfo(((CaseJouable)(carte.getCarte(j, i))).getTour());
					}
				}
			}
		
			infoTour.repaint();
		}
	}
	public static void main(String [] args){
		 Jeu jeu = new Jeu ();
		// jeu.interfaceUtilisateur();

	}
}