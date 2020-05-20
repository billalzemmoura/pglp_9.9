package pglp.pglp9_9;



	public class Moveformes implements CommandeMoveForms<Forme>{

 public Forme excute(Forme forme,int X,int Y) {
	forme.move(X,Y);
	
	return forme;
	}


}
