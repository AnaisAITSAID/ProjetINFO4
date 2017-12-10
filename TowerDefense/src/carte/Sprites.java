package carte;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {
	private Image spriteCaseJouable;
	private Image spriteCaseChemin;

	private static Sprites sprite = null;
	
	private Sprites () {
		this.chargeSpriteCaseJouable();
		this.chargeSpriteCaseChemin();
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
			this.spriteCaseJouable = ImageIO.read(new File("image/sol.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Image getSpriteCaseJouable() {
		return spriteCaseJouable;
	}

	public Image getSpriteCaseChemin() {
		return spriteCaseChemin;
	}

}
