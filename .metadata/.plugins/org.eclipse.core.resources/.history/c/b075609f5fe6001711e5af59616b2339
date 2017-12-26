package ihmtp1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Dessin extends JPanel {
	public Dessin (){
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    Graphics2D g2 = ((Graphics2D)g);
	    Rectangle2D rec = new Rectangle2D.Double(100, 100, 400, 200);
	    ((Graphics2D)g).draw(rec); 


	   
	    
	    Arc2D arc = new Arc2D.Double(rec, 100, 400, 1);
	    g2.setPaint(new Color (255, 0, 0)); // setPaint car plus riche 	  
	    g2.fill(arc);
	    g2.draw(arc); 

	    g2.setPaint(new Color (0,0,0)); // setPaint car plus riche 	  
	    g2.drawString("Hello",  (int)rec.getCenterX(), (int)rec.getCenterY()); // reste 
	    
	    g2.setPaint(new Color (0,0,255)); 
	    g2.draw(new Line2D.Double(rec.getX(), rec.getY(), rec.getX()+rec.getWidth(), rec.getY() + rec.getHeight()));
	    
	    g2.setPaint(new Color (0,255,0));
	    
	    Ellipse2D ell = new Ellipse2D.Double(rec.getCenterX()-50, rec.getY()+50, 100, 100);
	    g2.draw(ell);
	    
	    AffineTransform af = new AffineTransform();
	    af.translate(100, 0);
	    g2.draw(af.createTransformedShape(ell));
	    
	    g2.setPaint(new Color (0,0,0));
  
	    af.rotate(Math.cos(1));
	    g2.draw(af.createTransformedShape(rec));
	    
	    
	}
}
