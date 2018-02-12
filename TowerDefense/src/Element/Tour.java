package Element;

import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.net.URL;

import IHM.Jeu;
import utils.Constantes;
import utils.Constantes.Type_tour;

public abstract class Tour extends AffichageSprite {
		private Type_tour type_tour;
		private Case case_position;

		protected int degats;
		private int portee;
		private int vitesse;
		private int niveau;
		private boolean displayLaser = false;
		private Laser laser;
		private float attente; 
		protected int prix;

		
		public Tour(Case case_position, Type_tour type_tour, int portee, int vitesse, int niveau, int degats, int prix) {
			this.case_position=case_position;
			this.type_tour=type_tour;
			this.portee=portee;
			this.degats=degats;
			this.vitesse=vitesse;
			this.niveau=niveau;
			this.attente = 3/this.vitesse;
			this.prix = prix; 
		}
		
		public int getPrix() {
			return prix;
		}

		public abstract void setPrix();

		public boolean peutTirer(int tempsEcoule){
			//System.out.println("temps ecoule : " + tempsEcoule + " attente : " + this.attente + " Resultat : " +  tempsEcoule % this.attente);
			if (tempsEcoule % this.attente == 0) return true;
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

		public abstract void setDegats();
		
		public abstract int calculDegat();

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
			this.attente = 3/this.vitesse;
		}

		public int getNiveau() {
			return niveau;
		}

		public void setNiveau() {
			++this.niveau;
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
		
		public void tirer(Vague vague) {
			//int i=0;
			//System.out.println("enneis visé " + Vague.nb_ennemis);
			boolean aTire = false;
			try {
				for(int i = 0; i < vague.getNb_ennemis() && !aTire ; ++i) {
					
					if(vague.getEnnemi(i) != null && vague.getEnnemi(i).isBouge() && ennemiAPortée(vague.getEnnemi(i))) {

						int x = vague.getEnnemi(i).getRealX();
						int y = vague.getEnnemi(i).getRealY();
						
						Thread tire = new Thread(new Runnable() {
							
							@Override
							public void run() {
								displayLaser = true;
								laser = new Laser(case_position.getX()+Constantes.tailleCase/2, case_position.getY() +Constantes.tailleCase/2, 
													   x, y);
								Jeu.getInstance().getCarte().repaint();
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								displayLaser = false;
								Jeu.getInstance().getCarte().repaint();									
							}
						});
						tire.start();
						vague.getEnnemi(i).setPointsDeVie(vague.getEnnemi(i).getPointsDeVie()-degats);
						
						aTire = true;
					}

				} 					
			} catch (NullPointerException e) {
				System.err.println("ennemis deja tué");
			}
		

		}

		public void dessinerLaser(Graphics2D g2) {
			if (this.displayLaser) {
				g2.draw(this.laser.getLine());							
			}
		}
		
		
}
