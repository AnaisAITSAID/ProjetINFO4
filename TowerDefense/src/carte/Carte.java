package carte;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import utils.Constantes;

public class Carte extends JPanel implements Runnable{
	private Case carte [][];
	private Chemin chemin;
	private Vague la_vague;

	//Ennemi ennemi = new Ennemi (10, 10); // test
	
	public Carte () {
		this.chemin = new Chemin();
		this.la_vague = new Vague();

		this.chargeCarte();
		
	}
	
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
				System.out.print(singleton.getCarte()[Constantes.taille -1 -j][i] );
			}			
			System.out.println();
		}
	}
	/* C'est ici que l'on va dessiner les cases une par une dans la carte*/		
	@Override 
	public void paintComponent(Graphics g) {
		Chateau chateau = new Chateau();
		
		for (int i = 0; i < Constantes.taille; ++i) {
			for (int j = 0; j < Constantes.taille; ++j) {
				carte[j][i].dessiner(g);
			}			
		}
		//ennemi.dessiner(g); // test
		//ennemi.deplacer(); // test 
		
		//tentative de déplacement des ennemis
		// pour chaque ennemis 
		
		for (Ennemi  ennemi : la_vague.getCollec_ennemi()) {
			if (ennemi.isBouge()) {
			//	System.out.println(++test);
				ennemi.dessiner(g);
			}

		}
		
		/*for(int i = 0; i < 4; i++) {
			Ennemi e;
			e = la_vague.getEnnemi(i);
			e.dessiner(g);

			if(i > 0) {
				for(int j = i-1; j >= 0; j--) {
					Ennemi e2;
					e2 = la_vague.getEnnemi(j);
					e2.deplacer();
				}
				
			}
		}*/

		chateau.dessiner(g);
		
	}

	@Override
	public void run() {
		la_vague.lancer_Vague();
		while (true) {
			
			if (!la_vague.ennemisMorts()) {
				for (Ennemi  ennemi : la_vague.getCollec_ennemi()) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
												
					}
					ennemi.deplacer();
					
					repaint();
				}
				
			} else {
				la_vague.lancer_Vague();
			}
		}
		
	}
}
