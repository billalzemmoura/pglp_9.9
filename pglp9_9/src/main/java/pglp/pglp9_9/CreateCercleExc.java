package pglp.pglp9_9;

public class CreateCercleExc extends CreateForms implements CreateCercle {

	public Cercle excute(String nom, Point centre, int rayon) {

		return new Cercle(nom, centre, rayon);

	}
}
