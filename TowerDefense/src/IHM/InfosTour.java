package IHM;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Element.Tour;

public class InfosTour extends JPanel implements Observer{
	
	private Tour tour;
	
	public InfosTour() {
		this.tour = null;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	
	
	
}
