package pglp.pglp9_9;

import java.util.ArrayList;

public class Carre extends Forme {

	String nom;
	Point A =new Point();
	Point B =new Point();
	Point C =new Point();
	Point D =new Point();
	int cote;
	
	public Carre(String nom,Point A,Point B,Point C,Point D,int cote) {
		 this.A.setXY(A.getX(),A.getY());
		 this.B.setXY(B.getX(),B.getY());
		 this.C.setXY(C.getX(),C.getY());
		 this.D.setXY(D.getX(),D.getY()); 
		 this.nom=nom;
		 this.cote=cote;
		}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCote() {
		return cote;
	}

	public void setCote(int cote) {
		this.cote = cote;
	}

	public ArrayList<Point> getcoordonnes() {
		 ArrayList<Point> coordone=new ArrayList<Point>();
		 coordone.add(A);
		 coordone.add(B);
		 coordone.add(C);
		 coordone.add(D);
		return coordone;
	}




public void move(int X, int Y) {
	this.A.move( X,Y);
	this.B.move( X,Y);
	this.C.move( X,Y);
	this.D.move( X,Y);
}


}
