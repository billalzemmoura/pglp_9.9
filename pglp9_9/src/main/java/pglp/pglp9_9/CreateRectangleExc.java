package pglp.pglp9_9;

public class CreateRectangleExc extends CreateForms implements CreateRectangle {
	public Rectangle excute(String nom, Point A, int longeur, int largeur) {

		return new Rectangle(nom, A, longeur, largeur);

	}

}
