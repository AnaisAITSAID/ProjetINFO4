package Exception;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ExceptionFenetre extends Exception{
	public ExceptionFenetre (String message) {
		super ();
		JFrame fenetre = new JFrame("Oups");
		JLabel label = new JLabel(message);

		fenetre.add(label);
		fenetre.setSize(500, 150);
		fenetre.setVisible(true);
	}
}
