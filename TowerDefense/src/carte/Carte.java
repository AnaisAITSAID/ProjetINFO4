package carte;

import java.awt.Graphics;

import javax.swing.JPanel;

import utils.Constantes;
import utils.Constantes.Type;

public class Carte extends JPanel{
	private Case carte [][];
	
	public Carte () {
		this.chargeCarte();
	}
	
	private void chargeCarte() {
		this.carte = new Case[Constantes.taille][Constantes.taille];
		CarteFichier singleton = CarteFichier.getInstance();
		for (int i = 0; i < Constantes.taille; ++i) {
			for (int j = 0; j < Constantes.taille; ++j) {
				if (singleton.getCarte()[j][i] == 1)
					this.carte[j][i] = new CaseChemin(i*Constantes.tailleCase, j*Constantes.tailleCase);
				else if (singleton.getCarte()[j][i] == 0)
					this.carte[j][i] = new CaseJouable(i*Constantes.tailleCase, j*Constantes.tailleCase);
			}			
		}
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		/* C'est ici que l'on va dessiner les cases une par une dans la carte*/		 	
	}

}
