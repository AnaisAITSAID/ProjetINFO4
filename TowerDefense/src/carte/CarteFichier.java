package carte;

import java.io.File;
import java.util.Scanner;

import utils.Constantes;

public class CarteFichier {
	private String nomFichier = "carte/carte.txt";
	private int    carte [][];
	private static CarteFichier carteFichier = null;
	
	private CarteFichier () {
		this.chargeFichier();
	}
	
	public static CarteFichier getInstance() {
		carteFichier = new CarteFichier(); 
		return carteFichier;
	}
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

	public int[][] getCarte() {
		return carte;
	}
}