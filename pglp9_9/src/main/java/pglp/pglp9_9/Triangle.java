package pglp.pglp9_9;

import java.util.ArrayList;

public class Triangle extends Forme {

	private Point A;
	private Point B;
	private Point C;

	public Triangle(String nom, Point A, Point B, Point C) {
		this.A = new Point(A.getX(), A.getY());
		this.B = new Point(B.getX(), B.getY());
		this.C = new Point(C.getX(), C.getY());
		super.nom = nom;
	}

	public ArrayList<Point> getOrigine() {
		ArrayList<Point> coordone = new ArrayList<Point>();
		coordone.add(A);
		coordone.add(B);
		coordone.add(C);

		return coordone;
	}

	public void move(int X, int Y) {
		this.A.move(X, Y);
		this.B.move(X, Y);
		this.C.move(X, Y);

	}

}
