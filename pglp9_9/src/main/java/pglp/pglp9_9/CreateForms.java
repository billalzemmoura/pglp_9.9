package pglp.pglp9_9;

public class CreateForms implements AddForme,CreateDessin,CreateCercle ,CreateCaree,CreateRectangle,CreateTriangle{

	
	 
			public Cercle excute(String nom,Point centre, int rayon) {
		
		return new Cercle(nom, centre, rayon);
		
	
	}
	

	public Rectangle  excute(String nom,Point A,Point B,Point C,Point D,int largeur,int longeur) {
		
			return new Rectangle(nom,A,B,C,D,largeur,longeur) ;

	}
	public Triangle excute(String nom,Point A,Point B,Point C) {
	
			return new Triangle( nom,A,B,C) ;

}
	public Carre excute (String nom,Point A,Point B,Point C,Point D,int cote) {

			return new Carre(nom,A,B,C,D,cote) ;

	}



	public Dessin excute(String nom) {
		return new Dessin(nom);
	}


	@Override
	public Dessin excute(Forme forme,Forme DessinAjouter) {
		((Dessin)forme).addForme(DessinAjouter);
		return (Dessin) forme;
	}


	
	

	
}
