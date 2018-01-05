package carte;

import utils.Constantes.Type_tour;

public abstract class Tour extends AffichageSprite{
		private Type_tour type_tour;
		private Case case_position;
		private int degats;
		private int portee;
		private int vitesse;
		private int niveau;
		
		public Tour(Case case_position, Type_tour type_tour) {
			this.case_position=case_position;
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
}
