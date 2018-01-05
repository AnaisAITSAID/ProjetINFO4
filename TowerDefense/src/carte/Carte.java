package carte;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import javax.swing.JPanel;

import utils.Constantes;
import utils.Constantes.Orientation;

public class Carte extends JPanel implements Runnable{
	private Case carte [][];
	private Chemin chemin;
	private Vague la_vague;
	private Chateau chateau;
	public static int largeur;
	public static int hauteur; 
	

	//Ennemi ennemi = new Ennemi (10, 10); // test
	
	/**
	 * Constructeur de la classe carte
	 */
	public Carte () {
		this.chemin = new Chemin();
		this.la_vague = new Vague();
		this.chateau = new Chateau();
		this.chargeCarte();
		
		Thread thread = new Thread(this);
		thread.start();

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
		
		largeur=getWidth();
		hauteur=getHeight();
		
		g.setColor(new Color(100,100,100));
		g.fillRect(0, 0, largeur, hauteur);
		g.setColor(new Color(0,0,0));
		
		//on dessine les cases une par une
		for (int i = 0; i < Constantes.taille; ++i) {
			for (int j = 0; j < Constantes.taille; ++j) {
				carte[j][i].dessiner(g);
			}			
		}
		
		//on dessine chaque ennemi de la vague
		for (Ennemi  ennemi : la_vague.getCollec_ennemi()) {
			if (ennemi != null && ennemi.isBouge() && !ennemi.estArrive()) {
				ennemi.dessiner(g);
			}

		}
		
		//on dessine le château
		chateau.dessiner(g);

		
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
		la_vague.lancer_Vague();
		while (!chateau.gameOver()) {
			if (!la_vague.ennemisMorts()) {
				for (Ennemi  ennemi : la_vague.getCollec_ennemi()) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();												
					}
					if (chateau.gameOver()) break;
					if (ennemi.estArrive() && ennemi.isBouge()) {
						this.chateau.setVieChateau(ennemi.attaquer());
						System.out.println("Points de vie : " + this.chateau.getVieChateau());
					} else if (ennemi.isBouge()) {
						ennemi.deplacer();	
					}
					repaint();
				}
				
			} else {
				la_vague.lancer_Vague();
			}
		}
		System.out.println("GameOver");
	}
}