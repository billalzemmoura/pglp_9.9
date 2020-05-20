package pglp.pglp9_9;

import java.util.ArrayList;

public class Rectangle extends Forme{

	Point A=new Point();
	Point B=new Point();
	Point C=new Point();
	Point D=new Point();
	int largeur;
	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongeur() {
		return longeur;
	}

	public void setLongeur(int longeur) {
		this.longeur = longeur;
	}




	int longeur;
	
	
	public Rectangle(String nom,Point A,int longeur,int largeur) {
		 this.A.setXY(A.getX(),A.getY());
		 this.B.setXY(A.getX()+longeur,A.getY());
		 this.C.setXY(A.getX(),A.getY()+largeur);
		 this.D.setXY(A.getX()+longeur,A.getY()+largeur);
		 this.nom=nom;
	     this.largeur=largeur;
	     this.longeur=longeur;

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
