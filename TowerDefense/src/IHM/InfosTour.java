package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Element.Tour;
import IHM.Jeu.AmeliorationPossible;
import utils.Constantes.Type;
import utils.Constantes.Type_tour;

public class InfosTour extends JPanel{
	
	private Tour tourInfo;
	private JButton amelioration; 
	
	public InfosTour(Jeu j) {
	
		this.tourInfo = null;
		//this.setLayout(new BorderLayout());
		amelioration = new JButton("Amélioration");
		
		AmeliorationPossible am;
		am = j.new AmeliorationPossible();
		amelioration.addActionListener(am);
		this.add(amelioration);
	}

	
	public void setTourInfo(Tour tour) {
		this.tourInfo = tour;
		
	}

	
	public Tour getTourInfo() {
		return tourInfo;
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(tourInfo!=null) {
			g2.drawString("Type:"+tourInfo.getType_tour(),50, 10);
			
			g2.drawString("Niveau:"+tourInfo.getNiveau(),50, 20);
			
			
			g2.drawString("Vitesse:"+tourInfo.getVitesse(),50, 30);
			
			g2.drawString("Dégâts:"+tourInfo.getDegats(),50, 40);
			if(tourInfo.getType_tour()==Type_tour.TourForte) {
				FontMetrics fm=g2.getFontMetrics();
				String s="Dégâts:"+tourInfo.getDegats();
				int width=fm.stringWidth(s);
				g2.setPaint(Color.RED);
				g2.drawString("(+"+tourInfo.getNiveau()*10+")", 50+width+10, 30);
			}
			if(tourInfo.getType_tour()==Type_tour.TourRapide) {
				FontMetrics fm=g2.getFontMetrics();
				String s="Vitesse:"+tourInfo.getVitesse();
				int width=fm.stringWidth(s);
				g2.setPaint(Color.RED);
				g2.drawString("(+"+tourInfo.getNiveau()*10+")", 50+width+10, 30);
			}
			
			
		}
	}
	

	
}
