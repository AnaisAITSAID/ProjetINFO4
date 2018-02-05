package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JPanel;

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
//import IHM.InfosTour.SelectTour;
import utils.Constantes;
import utils.Constantes.Type;
import utils.Constantes.Type_tour;

public class Carte extends JPanel implements Runnable{

	private Case carte [][];
	private Chemin chemin;
	private Vague la_vague;
	private Chateau chateau;
	private volatile ArrayList<Tour> tours_joueur;
	private Type_tour typeTourAjoutee;
	//private Tour tour_infos;
	public static int largeur;
	public static int hauteur;
	private static  Carte instanceCarte; 
	private InfosJoueur i_j;
	private Ellipse2D portee = null; 

	
	/**
	 * Constructeur de la classe carte
	 */
	public Carte () {
		this.chemin = new Chemin();
		this.la_vague = new Vague();
		//this.chateau = new Chateau();
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
		largeur=getWidth();
		hauteur=getHeight();
		
		//g2.setColor(new Color(100,100,100));
		//g2.fillRect(0, 0, largeur, hauteur);
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
				if (ennemi != null && ennemi.isBouge() && !ennemi.estArrive()) {
					ennemi.dessiner(g2);
				}

			}
			
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
							throw new ExceptionFenetre("Vous ne pouvez pas poser de tour sur le chemin²");
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
							System.out.println("Pos " + tour.getXcaseposition());
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
		while (!chateau.gameOver()) {
			if (!la_vague.ennemisMorts()) {
				
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
					for(int j = 0; j < 7 - la_vague.nb_ennemis; ++j) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();												
						}	
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
		System.out.println("GameOver");
	}
	
	
	private class TourHandler implements Runnable{

		@Override
		public void run() {
			Chrono tempsEcoule = new Chrono();
			int tempsTotal = 1;
			while (true){
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