package pglp.pglp9_9;

import java.util.ArrayList;

public class Cercle extends Forme {

	private Point centre;
	private int rayon;

	public Cercle(String nom, Point centre, int rayon) {
		super.nom = nom;
		this.centre = new Point(centre.getX(), centre.getY());
		this.rayon = rayon;
	}

	public ArrayList<Point> getCentre() {
		ArrayList<Point> coordone = new ArrayList<Point>();
		coordone.add(centre);
		return coordone;
	}

	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

	public void move(int X, int Y) {
		this.centre.move(X, Y);

	}

}
