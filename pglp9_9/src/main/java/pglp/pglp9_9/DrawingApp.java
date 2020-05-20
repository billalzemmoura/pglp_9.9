package pglp.pglp9_9;

import java.util.Scanner;



public class DrawingApp {
DrawingTUI d=new DrawingTUI();
String commande;
ExpReg expreg=new ExpReg();
private CreateForms createForme=new CreateForms();
private Moveformes moveFormes=new Moveformes();
CreateCercle createCercle = createForme::excute;
CreateCaree createCaree= createForme::excute;
CreateRectangle createRectangle = createForme::excute;
CreateTriangle createTriangle = createForme::excute;
CreateDessin createDessin = createForme::excute;
CommandeMoveForms<Forme> moveForme=moveFormes::excute;
AddForme add=createForme::excute;

public DrawingApp() {
	d.Addcommande("createCercle", createCercle);
	d.Addcommande("createCaree",  createCaree);
	d.Addcommande("createTriangle", createTriangle);
	d.Addcommande("createRectangle", createRectangle);
	d.Addcommande("createDessin", createDessin);
	d.Addcommande("move", moveForme);
	d.Addcommande("add", add);
}


public final String saisie() {
	@SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
	System.out.println(
			"Veuillez saisir une commande a excuter ou quit  :");
	String str = sc.nextLine();
	return str;

}

private void run() throws Exception {
  commande =saisie();	
  commande =commande.replaceAll("\\s", "");
  Forme f=d.nextCommande(commande);
  if (f==null) {
	  System.out.println("aucun résultat");
}
  
  d.afficherForm(f);
	
	

}

public static  void main( String[] args ) throws Exception
{
	DrawingApp main=new DrawingApp();
	while (true) {
		main.run();	
		
	}
	
 
}







}
