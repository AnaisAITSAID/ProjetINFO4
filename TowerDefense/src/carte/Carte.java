package carte;

import java.awt.Graphics;

import javax.swing.JPanel;

import utils.Constantes;
import utils.Constantes.Type;

public class Carte extends JPanel{
	private Case carte [][];
	private Chemin chemin;
	private Vague la_vague= new Vague();
	//Ennemi ennemi = new Ennemi (10, 10); // test
	
	public Carte () {
		this.chemin = new Chemin();
		this.chargeCarte();
	}
	
	private void chargeCarte() {
		this.carte = new Case[Constantes.taille][Constantes.taille];
		CarteFichier singleton = CarteFichier.getInstance();
		for (int i = 0; i < Constantes.taille; ++i) {
			for (int j = 0; j < Constantes.taille; ++j) {
				if (singleton.getCarte()[j][i] == 1){
					this.chemin.inserer(i*Constantes.tailleCase, j*Constantes.tailleCase);
					this.carte[j][i] = new CaseChemin(i*Constantes.tailleCase, j*Constantes.tailleCase);
				}
				else if (singleton.getCarte()[j][i] == 0)
					this.carte[j][i] = new CaseJouable(i*Constantes.tailleCase, j*Constantes.tailleCase);
			}			
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
		System.out.println("cammanver");
		//ennemi.dessiner(g); // test
		//ennemi.deplacer(); // test 
		
		//tentative de déplacement des ennemis
		for(int i=0;i<4;i++) {
			Ennemi e;
			e=la_vague.getEnnemi(i);
			e.dessiner(g);
			//e.deplacer();
			if(i>0) {
				for(int j=i-1;j>=0;j--) {
					Ennemi e2;
					e2=la_vague.getEnnemi(j);
					e2.deplacer();
				}
				
			}
		}

		chateau.dessiner(g);
		
	}
}
