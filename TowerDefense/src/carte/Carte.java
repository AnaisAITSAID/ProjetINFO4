package carte;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import javax.swing.JPanel;

import utils.Constantes;

public class Carte extends JPanel implements Runnable{
	private Case carte [][];
	private Chemin chemin;
	private Vague la_vague;
	private Chateau chateau;
	public static int largeur;
	public static int hauteur; 
	
	//point où se trouve la souris 
	public static Point pt_souris= new Point(0,0);
	
	//partie réservée aux achats dans l'IHM
	public static Achats zone_achats;
	//Ennemi ennemi = new Ennemi (10, 10); // test
	
	/**
	 * Constructeur de la classe carte
	 */
	public Carte (Frame fenetre) {
		this.chemin = new Chemin();
		this.la_vague = new Vague();
		this.chateau = new Chateau();
		this.chargeCarte();
		fenetre.addMouseListener(new Souris_position());
		fenetre.addMouseMotionListener(new Souris_position());
		Thread thread = new Thread(this);
		thread.start();

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
				if (singleton.getCarte()[j][i] == 1){
					this.carte[j][i] = new CaseChemin(i*Constantes.tailleCase, j*Constantes.tailleCase);
				}
				else if (singleton.getCarte()[j][i] == 0)
					this.carte[j][i] = new CaseJouable(i*Constantes.tailleCase, j*Constantes.tailleCase);
				
				
				if (singleton.getCarte()[Constantes.taille -1 -j][i] == 1)
					this.chemin.inserer(i*Constantes.tailleCase, (Constantes.taille -1-j)*Constantes.tailleCase);
			}	
			
		}
		//zone des achats
		zone_achats=new Achats();
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
		
		//on dessine la zone d'achats
		zone_achats.dessiner(g);
		
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
