package pglp.pglp9_9;

public abstract class Forme {
	String nomGRP = "";

	public String getNomGRP() {
		return nomGRP;
	}

	public void setNomGRP(String nomGRP) {
		this.nomGRP = nomGRP;
	}

	String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	void move(int X, int Y) {
	}
}
