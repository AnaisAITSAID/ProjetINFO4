package Element;

import java.awt.Graphics;

import utils.Constantes;
import utils.Constantes.Type_tour;

public abstract class Tour extends AffichageSprite implements Runnable{
		private Type_tour type_tour;
		private Case case_position;
		private int x;


		private int y;
		private int degats;
		private int portee;
		private int vitesse;
		private int niveau;
		
		public Tour(Case case_position, Type_tour type_tour, int portee, int vitesse, int niveau, int degats) {
			this.case_position=case_position;
			this.type_tour=type_tour;
			this.portee=portee;
			this.degats=degats;
			this.vitesse=vitesse;
			this.niveau=niveau;
		}
		
		public Tour(int x, int y, Type_tour type_tour) {
			this.x=x;
			this.y=y;
			this.type_tour=type_tour;

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
		
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		
		public void viser() {
			
		}
		
		public boolean ennemiAPortée(Ennemi e) {
			if (e != null && e.getCaseCourante() != null) {
				if (((Math.abs(case_position.getX() - e.getCaseCourante().getX())) <= portee) && ((Math.abs(case_position.getY() - e.getCaseCourante().getY()) <= portee)))  {
					System.out.println("ennemi " + Math.abs(case_position.getX()));

					return true;
				}	
			}
			return false;		
		}
		
		@Override
		public void run() {
			while (true) {
				//int i=0;
				//System.out.println("enneis visé " + Vague.nb_ennemis);
				try {
					for(int i = 0; i < Vague.nb_ennemis; ++i) {
						
						while(Vague.collec_ennemi[i] != null && Vague.collec_ennemi[i].isBouge() && ennemiAPortée(Vague.collec_ennemi[i])) {
							Vague.collec_ennemi[i].setPointsDeVie(Vague.collec_ennemi[i].getPointsDeVie()-degats);
							
							try {
								Thread.sleep(this.vitesse*10);
							}
							catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}				
						}
						//i++;
					} 					
				} catch (NullPointerException e) {
					System.err.println("ennemis deja tué");
				}
			}

		}
}
