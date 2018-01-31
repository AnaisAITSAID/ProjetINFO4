package Element;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import IHM.Carte;
import utils.Constantes;
import utils.Constantes.Type_tour;

public abstract class Tour extends AffichageSprite {
		private Type_tour type_tour;
		private Case case_position;

		private int degats;
		private int portee;
		private int vitesse;
		private int niveau;
		private boolean displayLaser = false;
		private Laser laser;
		
		public Tour(Case case_position, Type_tour type_tour, int portee, int vitesse, int niveau, int degats) {
			this.case_position=case_position;
			this.type_tour=type_tour;
			this.portee=portee;
			this.degats=degats;
			this.vitesse=vitesse;
			this.niveau=niveau;
		}
		
		public boolean peutTirer(int tempsEcoule){
			if (tempsEcoule % this.vitesse == 0) return true;
			else return false;
		}

		public int getXcaseposition() {
			return case_position.getX();
		}
		
		public int getYcaseposition() {
			return case_position.getY();
		}
		
		public Type_tour getType_tour() {
			return type_tour;
		}

		public void setType_tour(Type_tour type_tour) {
			this.type_tour = type_tour;
		}

		public Case getCase_position() {
			return case_position;
		}

		public void setCase_position(Case case_position) {
			this.case_position = case_position;
		}

		public int getDegats() {
			return degats;
		}

		public void setDegats(int degats) {
			this.degats = degats;
		}

		public int getPortee() {
			return portee;
		}

		public void setPortee(int portee) {
			this.portee = portee;
		}

		public int getVitesse() {
			return vitesse;
		}

		public void setVitesse(int vitesse) {
			this.vitesse = vitesse;
		}

		public int getNiveau() {
			return niveau;
		}

		public void setNiveau(int niveau) {
			this.niveau = niveau;
		}

		
		public boolean ennemiAPortée(Ennemi e) {
			if (e != null && e.getCaseCourante() != null) {
				if (((Math.abs(case_position.getX() - e.getCaseCourante().getX())) <= portee) && ((Math.abs(case_position.getY() - e.getCaseCourante().getY()) <= portee)))  {
					//System.out.println("ennemi " + Math.abs(case_position.getX()));

					return true;
				}	
			}
			return false;		
		}
		
		public void tirer() {
			while (true) {
				//int i=0;
				//System.out.println("enneis visé " + Vague.nb_ennemis);
				try {
					for(int i = 0; i < Vague.nb_ennemis; ++i) {
						
						while(Vague.collec_ennemi[i] != null && Vague.collec_ennemi[i].isBouge() && ennemiAPortée(Vague.collec_ennemi[i])) {

							//dessinerProjectile(Vague.collec_ennemi[i].getRealX(), Vague.collec_ennemi[i].getRealY());			
							int x = Vague.collec_ennemi[i].getRealX();
							int y = Vague.collec_ennemi[i].getRealY();
							Thread tire = new Thread(new Runnable() {
								
								@Override
								public void run() {
									displayLaser = true;
									laser = new Laser(case_position.getX()+Constantes.tailleCase/2, case_position.getY() +Constantes.tailleCase/2, 
														   x, y);
									Carte.getCarte().repaint();
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									displayLaser = false;
									Carte.getCarte().repaint();									
								}
							});
							tire.start();
							Vague.collec_ennemi[i].setPointsDeVie(Vague.collec_ennemi[i].getPointsDeVie()-degats);

						}

					} 					
				} catch (NullPointerException e) {
					System.err.println("ennemis deja tué");
				}
			}

		}

		public void dessinerLaser(Graphics2D g2) {
			if (this.displayLaser) {
				g2.draw(this.laser.getLine());							
			}
		}
}
