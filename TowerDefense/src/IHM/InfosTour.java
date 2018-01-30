package IHM;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Element.CaseJouable;
import Element.Tour;
import utils.Constantes;
import utils.Constantes.Type;

public class InfosTour extends JPanel{
	
	private Tour tourInfo;
	
	
	public InfosTour() {
	
		this.tourInfo = null;
		
	}

	public void setTourInfo(Tour tour) {
		this.tourInfo = tour;
	}

	
	public void paintComponent(Graphics g) {
		if(tourInfo!=null) {
			g.drawString("Type:"+tourInfo.getType_tour(),50, 10);
			System.out.println(tourInfo.getType_tour());
			g.drawString("Niveau:"+tourInfo.getNiveau(),50, 20);
			System.out.println(tourInfo.getNiveau());
			g.drawString("Vitesse:"+tourInfo.getVitesse(),50, 30);
			System.out.println(tourInfo.getVitesse());
			g.drawString("Dégâts:"+tourInfo.getPortee(),50, 40);
			System.out.println(tourInfo.getPortee());
		}
	}
	

	
}
