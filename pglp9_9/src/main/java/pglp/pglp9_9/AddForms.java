package pglp.pglp9_9;

public class AddForms implements  AddForme{
	@Override
	public Dessin excute(Forme forme, Forme formeAjouter) {
	((Dessin) forme).addForme(formeAjouter);
	return  (Dessin) forme;
		
	}

	
}
