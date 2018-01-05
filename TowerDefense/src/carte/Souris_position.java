package carte;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import jeu.Jeu;

public class Souris_position implements MouseMotionListener, MouseListener{

	@Override
	public void mouseClicked(MouseEvent evenement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent evenement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent evenement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent evenement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent evenement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent evenement) {
		// TODO Auto-generated method stub
		//Achats.pt_souris=new Point(evenement.getX(),evenement.getY()-Jeu.zone_achats.getTaille_bouton()/2);
	}

	@Override
	public void mouseMoved(MouseEvent evenement ) {
		//Achats.pt_souris=new Point(evenement.getX(),evenement.getY()-Carte.zone_achats.getTaille_bouton()/2);
	}

}
