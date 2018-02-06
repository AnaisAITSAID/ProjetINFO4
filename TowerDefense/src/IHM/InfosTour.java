package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Element.Tour;
import IHM.Jeu.AmeliorationPossible;
import utils.Constantes;
import utils.Constantes.Type_tour;

public class InfosTour extends JPanel{
	
	private Tour tourInfo;
	private JButton amelioration; 
	private Jeu j;
	public InfosTour(Jeu j) {
		this.j = j;
		this.tourInfo = null;
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();

		Border border=  BorderFactory.createTitledBorder(
                loweredbevel, "Information tour");
		this.setBorder(border);
		amelioration = new JButton("Amélioration");
		AmeliorationPossible am;
		am = j.new AmeliorationPossible();
		amelioration.addActionListener(am);

	}

	
	public void setTourInfo(Tour tour) {
		this.tourInfo = tour;
		
	}

	public Tour getTourInfo() {
		return tourInfo;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, Constantes.taille*Constantes.tailleCase);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(tourInfo!=null) {
			g2.setFont(new Font("default", Font.BOLD, 16));
			g2.drawString("Type:"+tourInfo.getType_tour(),50, 30);
			g2.drawString("Niveau:"+tourInfo.getNiveau(),50, 60);
			g2.drawString("Vitesse:"+tourInfo.getVitesse(),50, 90);
			g2.drawString("Dégâts:"+tourInfo.getDegats(),50, 120);

			FontMetrics fm=g2.getFontMetrics();
			String s="Dégâts:"+tourInfo.getDegats();
			int width=fm.stringWidth(s);
			g2.setPaint(new Color (125, 183, 79));

			if(tourInfo.getType_tour()==Type_tour.TourForte) {
				g2.drawString("(+"+tourInfo.getNiveau()*10+")", 50+width+10, 120);

			} else if(tourInfo.getType_tour()==Type_tour.TourRapide) {
				g2.drawString("(+"+tourInfo.getNiveau()*6+")", 50+width+10, 120);				
			}
			
			System.out.println("test lvl " + tourInfo.getNiveau());
			if (tourInfo.getNiveau() < 3) {
				this.setLayout(null);
				amelioration.setBounds(75, 200, 150, 50);
				amelioration.setBackground(new Color (125, 183, 79));
				this.add(amelioration);			
			} 
			if(tourInfo.getNiveau() == 3){
				this.remove(amelioration);
			}
		}
	}
}
