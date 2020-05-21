package pglp.pglp9_9;

import java.util.ArrayList;

public class Dessin extends Forme {
	ArrayList<Forme> formes = new ArrayList<Forme>();

	public Dessin(String nom) {

		super.nom = nom;
	}

	public void removeForme(Forme forme) {
		formes.remove(forme);
	}

	public void addForme(Forme forme) {

		forme.setNomGRP(nom);
		formes.add(forme);

	}

	public ArrayList<Forme> getForme() {
		return formes;
	}

	public void setForme(ArrayList<Forme> forme) {
		this.formes = forme;
	}

	public void move(int x, int Y) {
		for (int i = 0; i < formes.size(); i++) {
			formes.get(i).move(x, Y);

		}

	}
}
