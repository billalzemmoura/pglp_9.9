package pglp.pglp9_9;

public class CreateDessinExc extends CreateForms implements CreateDessin {

	public Dessin excute(String nom) {
		return new Dessin(nom);
	}
}
