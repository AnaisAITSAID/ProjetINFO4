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

	private static Sprites sprite = null;
	
	private Sprites () {
		this.chargeSpriteCaseJouable();
		this.chargeSpriteCaseChemin();
		this.chargeSpriteChateau();
		this.chargeSpriteEnnemi();

	}
	



	public static Sprites getInstance() {
		sprite = new Sprites(); 
		return sprite;
	}
	public void chargeSpriteCaseJouable(){
		try {
			this.spriteCaseJouable = ImageIO.read(new File("image/herbe.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void chargeSpriteCaseChemin(){
		try {
			this.spriteCaseChemin = ImageIO.read(new File("image/sol.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void chargeSpriteChateau(){
		try {
			this.spriteChateau = ImageIO.read(new File("image/chateau.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void chargeSpriteEnnemi(){
		try {
			this.spriteEnnemi = ImageIO.read(new File("image/ennemi.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Image getSpriteCaseJouable() {
		return this.spriteCaseJouable;
	}

	public Image getSpriteCaseChemin() {
		return this.spriteCaseChemin;
	}
	
	public Image getSpriteChateau() {
		return this.spriteChateau;
	}
	public Image getSpriteEnnemi() {
		return this.spriteEnnemi;
	}
}
