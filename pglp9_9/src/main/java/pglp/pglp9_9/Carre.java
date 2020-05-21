package pglp.pglp9_9;

import java.util.ArrayList;

public class Carre extends Forme {

	private String nom;
	private Point A = new Point();
	private Point B = new Point();
	private Point C = new Point();
	private Point D = new Point();
	private int cote;

	public int getCote() {
		return cote;
	}

	public void setCote(int cote) {
		this.cote = cote;
	}

	public Carre(String nom, Point A, int cote) {
		this.A.setXY(A.getX(), A.getY());
		this.B.setXY(A.getX() + cote, A.getY());
		this.C.setXY(A.getX(), A.getY() + cote);
		this.D.setXY(A.getX() + cote, A.getY() + cote);
		this.nom = nom;
		this.cote = cote;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Point> getcoordonnes() {
		ArrayList<Point> coordone = new ArrayList<Point>();
		coordone.add(A);
		coordone.add(B);
		coordone.add(C);
		coordone.add(D);
		return coordone;
	}

	public void move(int X, int Y) {
		this.A.move(X, Y);
		this.B.move(X, Y);
		this.C.move(X, Y);
		this.D.move(X, Y);
	}

}
