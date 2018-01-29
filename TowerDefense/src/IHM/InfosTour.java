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
	
	private Tour tour;
	private Carte carte;
	
	public InfosTour(Carte carte) {
		this.carte = carte;
		this.tour = null;
		
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	
	public void paintComponent(Graphics g) {
		if(tour!=null) {
			g.drawString("Type:"+tour.getType_tour(),50, 10);
			System.out.println(tour.getType_tour());
			g.drawString("Niveau:"+tour.getNiveau(),50, 20);
			System.out.println(tour.getNiveau());
			g.drawString("Vitesse:"+tour.getVitesse(),50, 30);
			System.out.println(tour.getVitesse());
			g.drawString("Dégâts:"+tour.getPortee(),50, 40);
			System.out.println(tour.getPortee());
		}
	}
	
	public class SelectTour extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent evenement) {
		/*	System.out.println("lapin11");
			for (int i = 0; i < Constantes.taille; ++i) {
				for (int j = 0; j < Constantes.taille; ++j) {
					System.out.println("lapin1");
					if(carte.getCarte(j, i).contain(evenement.getX(), evenement.getY()) && carte.getCarte(j, i).getType() == Type.CaseJouable && ((CaseJouable)carte.getCarte(j, i)).getTour()!=null) {
						setTour(((CaseJouable)carte.getCarte(j, i)).getTour());
						System.out.println("lapin2");
					}
				}
			}
			repaint();
		*/}
	}
	
}
