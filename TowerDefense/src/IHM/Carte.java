package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

//import IHM.InfosTour.SelectTour;
import utils.Constantes;
import utils.Constantes.Type;
import utils.Constantes.Type_tour;
import Element.CarteFichier;
import Element.Case;
import Element.CaseChemin;
import Element.CaseJouable;
import Element.Chateau;
import Element.Chemin;
import Element.Chrono;
import Element.Ennemi;
import Element.Tour;
import Element.TourForte;
import Element.TourRapide;
import Element.Vague;
import Exception.ExceptionFenetre;

public class Carte extends JPanel implements Runnable{

	private Case carte [][];
	private Chemin chemin;
	private Vague la_vague;
	private Chateau chateau;
	private volatile ArrayList<Tour> tours_joueur;
	private Type_tour typeTourAjoutee;
	private static  Carte instanceCarte; 
	private InfosJoueur i_j;
	private Ellipse2D portee = null; 
    protected volatile boolean running = true;
    private Clip clip1;
    private Clip clip2;
	/**
	 * Constructeur de la classe carte
	 */
	public Carte () {
		this.chemin = new Chemin();
		this.la_vague = new Vague();
		this.chargeCarte();
		this.tours_joueur = new ArrayList<Tour>();
		
		this.addMouseListener(new SelectCase());
		this.addMouseMotionListener(new DessinerPortee());
		instanceCarte = this;
	}
	/* Cete fonction permet de charger dans le chemin les bonnes cases. Elle prend 
	 * en paramètre la case à tester ainsi que son orientation (pour ne pas 
	 * tester de nouveau ce coté). Nous regardons donc de tout les cotés 
	 * par rapport à la case passée en paramètre, puis si on trouve une case 
	 * on ajoute la case dans le chemin et on passe à la nouvelle 
	 */
	private void chargeChemin(Case caseDepart, Constantes.Orientation orientation) {
		CarteFichier singleton = CarteFichier.getInstance();
		int x = caseDepart.getX()/Constantes.tailleCase;
		int y = caseDepart.getY()/Constantes.tailleCase;
		
		if (x < Constantes.taille-1 && singleton.getCarte()[y][x+1] >= 1 && orientation != Constantes.Orientation.Droite) { // si la nouvelle case est à droite
			this.chemin.inserer( new CaseChemin(caseDepart.getX()+Constantes.tailleCase, caseDepart.getY()));
			chargeChemin(new CaseChemin(caseDepart.getX()+Constantes.tailleCase, caseDepart.getY()), Constantes.Orientation.Gauche);
			
		} else if (y < Constantes.taille-1 && singleton.getCarte()[y + 1][x] >= 1 && orientation != Constantes.Orientation.Bas) { // si la nouvelle case est en bas
			this.chemin.inserer( new CaseChemin(caseDepart.getX(), caseDepart.getY()+Constantes.tailleCase));
			chargeChemin(new CaseChemin(caseDepart.getX(), caseDepart.getY()+Constantes.tailleCase), Constantes.Orientation.Haut);
			
		} else if (x > 0 && singleton.getCarte()[y][x-1] >= 1 && orientation != Constantes.Orientation.Gauche) { // si la nouvelle case est à gauche
			this.chemin.inserer( new CaseChemin(caseDepart.getX()-Constantes.tailleCase, caseDepart.getY()));
			chargeChemin(new CaseChemin(caseDepart.getX()-Constantes.tailleCase, caseDepart.getY()), Constantes.Orientation.Droite);
		}
		else if(y > 0 && singleton.getCarte()[y-1][x] >= 1 && orientation != Constantes.Orientation.Haut) { // si la nouvelle case est en haut
			this.chemin.inserer( new CaseChemin(caseDepart.getX(), caseDepart.getY() - Constantes.tailleCase));
			chargeChemin(new CaseChemin(caseDepart.getX(), caseDepart.getY() - Constantes.tailleCase), Constantes.Orientation.Bas);
		}
		
		return;
	
	}

	/**
	 * Fonction permettant de charger la carte.
	 * En fonction de la valeur (1 ou 0) dans le champ carte de la classe CarteFichier
	 * on crée des cases chemin ou des cases jouables dans le champ carte de la classe Carte
	 */
	private void chargeCarte() {
		this.carte = new Case[Constantes.taille][Constantes.taille];
		CarteFichier singleton = CarteFichier.getInstance();
		for (int i = 0; i < Constantes.taille; ++i) {
			for (int j = 0; j < Constantes.taille; ++j) {
				if (singleton.getCarte()[j][i] > 0){
					this.carte[j][i] = new CaseChemin(i*Constantes.tailleCase, j*Constantes.tailleCase);
					if (singleton.getCarte()[j][i] == 2) {
						//this.chemin.inserer(i*Constantes.tailleCase, (Constantes.taille -1-j)*Constantes.tailleCase);		
						this.chemin.inserer((CaseChemin) this.carte[j][i]);
						this.chargeChemin(this.carte[j][i], Constantes.Orientation.Gauche);
					}
					//this.chemin.inserer((CaseChemin) this.carte[j][i]);
				}
				else if (singleton.getCarte()[j][i] == 0)
					this.carte[j][i] = new CaseJouable(i*Constantes.tailleCase, j*Constantes.tailleCase);
				
			//	System.out.print(singleton.getCarte()[j][i]);
				
			}	
			//System.out.println();
			
		}

	}
	
	/**
	 * Fonction permettant de dessiner tous les éléments de la carte
	 */
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0));
		try {
			//on dessine les cases une par une
			for (int i = 0; i < Constantes.taille; ++i) {
				for (int j = 0; j < Constantes.taille; ++j) {
					carte[j][i].dessiner(g2);
				}			
			}
			
			//on dessine chaque ennemi de la vague
			for(int i = 0; i < la_vague.getNb_ennemis(); ++i) {
				Ennemi ennemi = la_vague.getEnnemi(i);
				int widhtVie = 30;
				int heightVie = 4;
				if (ennemi != null && ennemi.isBouge() && !ennemi.estArrive()) {
					Rectangle2D vie = new Rectangle2D.Double
								(ennemi.getRealX() - 20, ennemi.getRealY() - 30,  widhtVie,  heightVie );
					g2.setColor(Color.GREEN);
					g2.fill(vie);
					float pourcentageVie = (float) ennemi.getPointsDeVie()/la_vague.getPointDeVie();
					vie = new Rectangle2D.Double
							(ennemi.getRealX() - 20 + widhtVie*pourcentageVie, ennemi.getRealY() - 30,  widhtVie*(1-pourcentageVie),  heightVie );
					g2.setColor(Color.RED);
					g2.fill(vie);
					ennemi.dessiner(g2);
				}

			}
			g2.setColor(Color.BLACK);
			
			for (Tour tour : this.tours_joueur) {
				tour.dessiner(g2);
				tour.dessinerLaser(g2);
			}


			//on dessine le château
			chateau.dessiner(g2);
			
			g2.setColor(Color.ORANGE);
			g2.draw(this.portee);
		} catch (Exception e) {
			repaint();
		}	
		
		
	}
	public void setTypeTourAjoutee(Type_tour typeTourAjoutee) {
		this.typeTourAjoutee = typeTourAjoutee;
	}
	
	//classe interne pour la position de la souris
	private class SelectCase extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent evenement) {
			for (int i = 0; i < Constantes.taille; ++i) {
				for (int j = 0; j < Constantes.taille; ++j) {
					if (carte[i][j].contain(evenement.getX(), evenement.getY()) && carte[i][j].getType() == Type.CaseJouable) {
						if(((CaseJouable)carte[i][j]).getTour()==null) {
							try {
								ajouterTour(typeTourAjoutee, carte[i][j]);
							} catch (ExceptionFenetre e) {
								e.printStackTrace();
							}
							typeTourAjoutee = null;
						}
						
					} else if (carte[i][j].contain(evenement.getX(), evenement.getY()) && carte[i][j].getType() == Type.CaseChemin){
						try {
							throw new ExceptionFenetre("Vous ne pouvez pas poser de tour sur le chemin");
						} catch (ExceptionFenetre e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			repaint();
		}
	}

	//classe interne pour la position de la souris
	private class DessinerPortee extends MouseMotionAdapter{

		@Override
		public void mouseMoved(MouseEvent evenement) {
			boolean trouver = false;
			for (int i = 0; i < Constantes.taille; ++i) {
				for (int j = 0; j < Constantes.taille; ++j) {
					if (carte[i][j].contain(evenement.getX(), evenement.getY()) && carte[i][j].getType() == Type.CaseJouable) {
						Tour tour = ((CaseJouable)carte[i][j]).getTour();
						if(tour!=null) {
							trouver = true;
							portee = new Ellipse2D.Double(tour.getXcaseposition() -tour.getPortee(), tour.getYcaseposition()-tour.getPortee(), 
																					 tour.getPortee()*2+(Constantes.tailleCase), tour.getPortee()*2+(Constantes.tailleCase));
						}
						
					}
				}
			}
			if (trouver)
				repaint();
			else 
				portee = null;
		}
	}
	/*public Tour getTour_infos() {
		return tour_infos;
	}*/
	public void ajouterTour (Type_tour type, Case case_position) throws ExceptionFenetre {
		Tour nouvelle_tour = null;
		if (type == null){
			throw new ExceptionFenetre("Vous n'avez pas selectionnez de tour");
		}
		if (type == Type_tour.TourForte ) {
			nouvelle_tour = new TourForte(case_position);

		} else if(type == Type_tour.TourRapide) {
			nouvelle_tour = new TourRapide(case_position);	
		}
		if(chateau.getArgent()>=nouvelle_tour.getPrix()) {
			this.tours_joueur.add(nouvelle_tour);
			((CaseJouable)case_position).setTour(nouvelle_tour);
			chateau.setArgent(chateau.getArgent()-nouvelle_tour.getPrix());
			nouvelle_tour.setPrix();

			i_j.repaint();
		}else {
			throw new ExceptionFenetre("vous ne possédez pas l'argent nécessaire pour acheter la tour");
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Constantes.taille*Constantes.tailleCase, Constantes.taille*Constantes.tailleCase);
	}

	
	
	@Override
	/**
	 * Fonction qui lance la vague puis fait avancer les ennemis.
	 * Si les ennemis arrivent au château, les points de vie du joueur sont décrémentés.
	 * Si tous les ennemis sont morts, une nouvelle vague est lancée.
	 * Si le joueur n'a plus de points de vie, la partie est terminée.
	 */
	public void run() {
		Thread tourThread = new Thread (new TourHandler());
		tourThread.start();
		la_vague.lancer_Vague();
		URL u1;

		try {
			u1 = new URL("file:son/battle.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(u1);
	        clip1 = AudioSystem.getClip();
	        clip1.open(audioInputStream);
			FloatControl gainControl2 = 
				    (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl2.setValue(-10.0f); 
	        clip1.loop(Clip.LOOP_CONTINUOUSLY);
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
		boolean lance = false;
		while (!chateau.gameOver() && running) {
			
			if (chateau.getVieChateau() == 2 && !lance) {
				lance = true;
				try {
					
					u1 = new URL("file:son/heartsound.wav");
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(u1);
			        clip2 = AudioSystem.getClip();
			        clip2.open(audioInputStream);
			        FloatControl gainControl = 
						    (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
						gainControl.setValue(6.0f); 

			        clip2.loop(Clip.LOOP_CONTINUOUSLY);
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
				
			}
			if (!la_vague.ennemisMorts()) {
				i_j.repaint();
				for (int i = 0; i < la_vague.getNb_ennemis(); ++i) {
					if (chateau.gameOver()) break;
					if(!la_vague.getEnnemi(i).isBouge()){
						chateau.setArgent(chateau.getArgent()+la_vague.getEnnemi(i).getMonnaiesGenere());
						i_j.repaint();
						la_vague.supprimerEnnemi(la_vague.getEnnemi(i));						
					}
					
					if (la_vague.getEnnemi(i).estArrive() && la_vague.getEnnemi(i).isBouge()) {
						this.chateau.setVieChateau(la_vague.getEnnemi(i).attaquer());
						i_j.repaint();
						//System.out.println("Points de vie : " + this.chateau.getVieChateau());
					} 

					try {
						Thread.sleep(600/(la_vague.nb_ennemis));
					} catch (InterruptedException e) {
						e.printStackTrace();												
					}	
					
					if (la_vague.getEnnemi(i).isBouge()) {
						la_vague.getEnnemi(i).deplacer();
					} 
					repaint();
				}
				
			} else {
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				la_vague.lancer_Vague();
			}
		}
		if (running) {
			System.out.println("GameOver");
			try {
				tourThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			running = false;
			
		}
	}
	
	public void stopGame () {
		clip1.stop();
		if (clip2 != null)
			clip2.stop();
		this.running = false;
	}
	private class TourHandler implements Runnable{

		@Override
		public void run() {
			Chrono tempsEcoule = new Chrono();
			int tempsTotal = 1;
			while (running){
				tempsEcoule.Go_Chrono();
				for (Tour tour : tours_joueur) {

					if (tour.peutTirer(tempsTotal)){
						tour.tirer(la_vague);
					}
				}
	
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				tempsTotal += (int)tempsEcoule.Stop_Chrono()/1000;
			}
			
		}
		
	}
	public void setChateau(Chateau chateau) {
		this.chateau = chateau;
		Thread thread = new Thread(this);
		thread.start();
	}
	public Case getCarte(int i, int j) {
		return carte[i][j];
	}
	
	public void setI_j(InfosJoueur i_j) {
		this.i_j = i_j;
	}	
	public static Carte getCarte() {
		return instanceCarte;
	}
}