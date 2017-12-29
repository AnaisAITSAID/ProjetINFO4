package carte;

import java.io.File;
import java.util.Scanner;

import utils.Constantes;

public class CarteFichier {
	private String nomFichier = "carte/carte.txt";
	private int    carte [][];
	private static CarteFichier carteFichier = null;
	
	/**
	 * Constructeur de la classe CarteFichier
	 */
	private CarteFichier () {
		this.chargeFichier();
	}
	
	public static CarteFichier getInstance() {
		carteFichier = new CarteFichier(); 
		return carteFichier;
	}
	
	/**
	 * Le fichier carte.txt contient une matrice de 1 et 0.
	 * Les 1 correspondent aux cases du chemin et les 0 correspondent aux cases jouables.
	 * Cette fonction permet de charger cette matrice dans le champs carte[][].
	 */
	public void chargeFichier(){
		File fichierCarte = new File (this.nomFichier);
		this.carte = new int [Constantes.taille][Constantes.taille];
		try{
			String valeur;
			Scanner scanner = new Scanner (fichierCarte);
			while (scanner.hasNext()) {
				for (int i = 0; i < Constantes.taille ; ++i) {
					for (int j = 0; j < Constantes.taille; ++j) {
						this.carte[i][j] = scanner.nextInt();
					}					
				}				
			}
		}		
		catch (Exception e){
			System.err.println(e.toString());
		}
	}

	/**
	 * Fonction permettant de récupérer la carte.
	 * @return le tableau d'entiers correspondant à la matrice de la carte.
	 */
	public int[][] getCarte() {
		return carte;
	}
}
