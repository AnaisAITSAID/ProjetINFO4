package carte;

import javax.swing.JPanel;

import utils.Constantes;

public class Carte extends JPanel{
	private int carte [][];
	
	public Carte () {
		this.chargeCarte();
	}
	
	private void chargeCarte() {
		CarteFichier singleton = CarteFichier.getInstance();
		for (int i = 0; i < Constantes.taille; ++i) {
			for (int j = 0; j < Constantes.taille; ++j) {
				this.carte[i][j] = singleton.getCarte()[i][j];
			}			
		}
	}
	
	public void dessiner () {
		/* C'est ici que l'on va ajouter les cases � la cartes */
		/* ici la meilleur solution c'est d'utiliser un grid layout, en gros
		 * changer le layout de ta carte en gridlayout
		 * parcourir le tableau carte, pour chaque element de ce tableau 
		 * tester la valeur pour savoir qu'elle type de cases instancier, si c'est 0 
		 * on instancie une CaseChemin, et si c'est 1, une CaseJouable
		 * puis tu ajoute cette case a ton JPanel 
		 */
	}
	
}
