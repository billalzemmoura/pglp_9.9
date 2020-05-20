package pglp.pglp9_9;

import java.util.Scanner;



public class DrawingApp {

DrawingTUI d=new DrawingTUI();
String commande;
ExpReg expreg=new ExpReg();
private AddForms addFormes=new AddForms();
private CreateRectangleExc createRectangleExc =new CreateRectangleExc();
private CreateCarreExc createCarreExc=new CreateCarreExc();
private CreateCercleExc createCercleExc=new CreateCercleExc();
private CreateTriangleExc createTriangleExc=new CreateTriangleExc();
private CreateDessinExc createDessinExc=new CreateDessinExc();
private Moveformes moveFormes=new Moveformes();
CreateCercle createCercle = createCercleExc::excute;
CreateCaree createCaree=createCarreExc::excute;
CreateRectangle createRectangle = createRectangleExc::excute;
CreateTriangle createTriangle = createTriangleExc::excute;
CreateDessin createDessin = createDessinExc::excute;
CommandeMoveForms<Forme> moveForme=moveFormes::excute;
AddForme add=addFormes::excute;

public DrawingApp() {
	d.Addcommande("createCercle", createCercle);
	d.Addcommande("createCaree",  createCaree);
	d.Addcommande("createTriangle", createTriangle);
	d.Addcommande("createRectangle",createRectangle );
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
