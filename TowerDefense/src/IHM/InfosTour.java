package IHM;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Element.Tour;

public class InfosTour extends JPanel{
	
	private Tour tourInfo;
	
	
	public InfosTour() {
	
		this.tourInfo = null;
		
	}

	public void setTourInfo(Tour tour) {
		this.tourInfo = tour;
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(tourInfo!=null) {
			g2.drawString("Type:"+tourInfo.getType_tour(),50, 10);
			
			g2.drawString("Niveau:"+tourInfo.getNiveau(),50, 20);
			
			g2.drawString("Vitesse:"+tourInfo.getVitesse(),50, 30);
		
			g2.drawString("Dégâts:"+tourInfo.getDegats(),50, 40);
		
		}
	}
	

	
}
