package Element;

import java.awt.Graphics;

import utils.Constantes;
import utils.Constantes.Type_tour;

public abstract class Tour extends AffichageSprite{
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
		
		public void viser(Ennemi[] ennemis) {
			int i=0;
			for(Ennemi e:ennemis) {
				while(ennemiAPortée(e) && e.isBouge()) {
					e.setPointsDeVie(e.getPointsDeVie()-degats);
					System.out.println("points de vie de l'ennemi " + i + "sont" + e.getPointsDeVie());
				}
				i++;
			}
		}
		
		public boolean ennemiAPortée(Ennemi e) {
			if (e.getCaseCourante() != null) {
				if (((Math.abs(case_position.getX() - e.getCaseCourante().getX())) <= portee) && ((Math.abs(case_position.getY() - e.getCaseCourante().getY()) <= portee)))  {
					return true;
				}	
			}
			return false;		
		}
}
