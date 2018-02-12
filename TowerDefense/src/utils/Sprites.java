package utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {
	private Image spriteCaseJouable;
	private Image spriteCaseChemin;
	private Image spriteChateau;
	private Image spriteTourForte;
	private Image spriteTourForteAchat;
	private Image spriteTourRapide;
	private Image spriteTourRapideAchat;
	private Image spriteLife;
	private Image spriteNoLife;
	
	private Image spriteSlime01;
	private Image spriteSlime02;
	private Image spriteSlime03;
	
	private static Sprites sprite = null;
	
	private Sprites () {
		this.chargeSpriteCaseJouable();
		this.chargeSpriteCaseChemin();
		this.chargeSpriteChateau();
		this.chargespriteTourForte();
		this.chargespriteTourForteAchat();
		this.chargespriteTourRapide();
		this.chargespriteTourRapideAchat();
		this.chargespriteLife();
		this.chargespriteNoLife();
		
		this.chargespriteSlime01();
		this.chargespriteSlime02();
		this.chargespriteSlime03();
	}

	private void chargespriteSlime01() {
		try {
			this.spriteSlime01 = ImageIO.read(new File("image/slime01.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void chargespriteSlime02() {
		try {
			this.spriteSlime02 = ImageIO.read(new File("image/slime02.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void chargespriteSlime03() {
		try {
			this.spriteSlime03 = ImageIO.read(new File("image/slime03.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image getspriteSlime01() {
		return this.spriteSlime01;
	}
	public Image getspriteSlime02() {
		return this.spriteSlime02;
	}
	public Image getspriteSlime03() {
		return this.spriteSlime03;
	}
	 
	private void chargespriteTourForteAchat() {
		try {
			this.spriteTourForteAchat = ImageIO.read(new File("image/tourAchatForte.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void chargespriteLife() {
		try {
			this.spriteLife = ImageIO.read(new File("image/life.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void chargespriteNoLife() {
		try {
			this.spriteNoLife = ImageIO.read(new File("image/nolife.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void chargespriteTourRapideAchat() {
		try {
			this.spriteTourRapideAchat = ImageIO.read(new File("image/tourAchatRapide.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}private void chargespriteTourRapide() {
		try {
			this.spriteTourRapide = ImageIO.read(new File("image/tour2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Sprites getInstance() {
		if (sprite == null) sprite = new Sprites(); 
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
	

	public void chargespriteTourForte(){
		try {
			this.spriteTourForte = ImageIO.read(new File("image/tour1.png"));
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
	 * @return le sprite de la tour1
	 */
	public Image getspriteTourForte() {
		return this.spriteTourForte;
	}
	
	public Image getspriteTourForteAchat() {
		return this.spriteTourForteAchat;
	}
	
	public Image getspriteTourRapideAchat() {
		return this.spriteTourRapideAchat;
	}
	
	public Image getspriteTourRapide() {
		return this.spriteTourRapide;
	}
	
	public Image getSpriteLife() {
		return this.spriteLife;
	}
	public Image getSpriteNoLife() {
		return this.spriteNoLife;
	}
	
}
