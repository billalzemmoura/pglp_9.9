package pglp.pglp9_9;

public class CreateCarreExc extends CreateForms implements CreateCaree {

	@Override
	public Carre excute(String nom, Point A, int cote) {
		return new Carre(nom, A, cote);
	}

}
