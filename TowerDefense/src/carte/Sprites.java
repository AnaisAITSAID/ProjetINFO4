package carte;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {
	private Image spriteCaseJouable;
	private Image spriteCaseChemin;
	private Image spriteChateau;
	private Image spriteEnnemi;
	private Image spriteTour1;

	private static Sprites sprite = null;
	
	private Sprites () {
		this.chargeSpriteCaseJouable();
		this.chargeSpriteCaseChemin();
		this.chargeSpriteChateau();
		this.chargeSpriteEnnemi();
		this.chargeSpriteTour1();

	}

	public static Sprites getInstance() {
		sprite = new Sprites(); 
		return sprite;
	}
	/**
	 * Fonction permettant de charger les sprites des cases jouables
	 */
	public void chargeSpriteCaseJouable(){
		try {
			this.spriteCaseJouable = ImageIO.read(new File("image/herbe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fonction permettant de charger les sprites des cases du chemin
	 */
	public void chargeSpriteCaseChemin(){
		try {
			this.spriteCaseChemin = ImageIO.read(new File("image/sol.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fonction permettant de charger le sprite du château
	 */
	public void chargeSpriteChateau(){
		try {
			this.spriteChateau = ImageIO.read(new File("image/chateau.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fonction permettant de charger les sprites des ennemis
	 */
	public void chargeSpriteEnnemi(){
		try {
			this.spriteEnnemi = ImageIO.read(new File("image/ennemi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fonction permettant de charger les sprites des ennemis
	 */
	public void chargeSpriteTour1(){
		try {
			this.spriteTour1 = ImageIO.read(new File("image/tour1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return le sprite des cases jouables
	 */
	public Image getSpriteCaseJouable() {
		return this.spriteCaseJouable;
	}

	/**
	 * @return le sprite des cases du chemin
	 */
	public Image getSpriteCaseChemin() {
		return this.spriteCaseChemin;
	}
	
	/**
	 * @return le sprite du château
	 */
	public Image getSpriteChateau() {
		return this.spriteChateau;
	}
	
	/**
	 * @return le sprite de l'ennemi
	 */
	public Image getSpriteEnnemi() {
		return this.spriteEnnemi;
	}
	
	/**
	 * @return le sprite de la tour1
	 */
	public Image getSpriteTour1() {
		return this.spriteTour1;
	}
}
