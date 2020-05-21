package pglp.pglp9_9;
import static org.junit.Assert.assertTrue;

import java.lang.module.FindException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




/**
 * Unit test for simple App.
 */
public class AppTest {
	Cercle cercle;
	Dessin dessin;
	Dessin petitDessin;
	Rectangle rectangle;
	Triangle triangle;
	Carre carre;
	DaoDerbyForme ddf;
	CompositeDaoDerby cdd;
	DrawingApp da;
	CreateForms	creatorForm;
	@SuppressWarnings("unused")
	@Before
	public void create() throws Exception {
		//commande a taper a la main pour déplacer les formes 
    	
		String commandeCreateDessin="des1=dessin()"; 
    	String commandeCreateCercle="cer1=cercle( (4, 8 ) , 49 )";
    	String commandeCreateTriangle="tri2=triangle( (56, 65 ) ,(01,78 ),(16, 89 ))";
    	String commandeCreateRectangle="rec1=RECTANGLE( (4, 8 ) , 50,30 )";
    	String commandeCreateCarre="car1=carre( (4, 8 ) , 49 )";
    //creation des differents objets  
    	
    
    creatorForm=new CreateCercleExc();
	cercle=((CreateCercleExc) creatorForm).excute("cercle", Point.PointFactory(3, 7),67);
	creatorForm=new CreateDessinExc();
	dessin=((CreateDessin) creatorForm).excute("dessin");
	petitDessin=((CreateDessin) creatorForm).excute("petitdessin");
	creatorForm=new CreateRectangleExc();
	rectangle=((CreateRectangleExc) creatorForm).excute("rectangle", Point.PointFactory(3, 7),7,3);
	creatorForm=new CreateCarreExc();
	carre=((CreateCarreExc) creatorForm).excute("carre", Point.PointFactory(3, 7),60);
	creatorForm=new CreateTriangleExc();
	triangle=((CreateTriangleExc) creatorForm).excute("triangle", Point.PointFactory(3, 32),Point.PointFactory(6, 37),Point.PointFactory(89,53) );
    ddf=(DaoDerbyForme) DAOFactory.getFormDaoDerby();
    cdd=DAOFactory.getCompositeDaoDerby();
    da=new DrawingApp();
	
	
	}
	
	@Test
    public void  AddFormeDessin() {
    AddForme add=new AddForms();
    add.excute(dessin, cercle);
  
    assertTrue(dessin.getForme().get(0).getNom().equals(cercle.getNom()));
}
    @Test
    public void  deleteFormeDessin() {
    	
    	dessin.removeForme(cercle);
	    assertTrue(dessin.getForme().size()==0);
}
    @Test
    public void  afficherFormes() {
    	System.out.println("######### TEST:afficherFormes #########");
    	da.d.afficherForm(carre);
    	da.d.afficherForm(rectangle);
    	da.d.afficherForm(cercle);
    	da.d.afficherForm(triangle);
    	da.d.afficherForm(dessin);
    	System.out.println("######### FIN TEST:afficherFormes #########");
}
    @Test
    public void  CreateFormesDERBY() throws Exception {
	
    ddf.create(carre);
    ddf.create(rectangle);
    ddf.create(cercle);
    ddf.create(triangle);
    assertTrue(ddf.find(carre.getNom())!=null);
    assertTrue(ddf.find(cercle.getNom())!=null);
    assertTrue(ddf.find(rectangle.getNom())!=null);
    assertTrue(ddf.find(triangle.getNom())!=null);
    	
}
    @Test
    public void  DeleteFormsDERBY() {
	    
    	ddf.delete(carre);
    	ddf.delete(rectangle);
    	ddf.delete(cercle);
    	ddf.delete(triangle);
     assertTrue(ddf.find(carre.getNom())==null);
     assertTrue(ddf.find(rectangle.getNom())==null);
     assertTrue(ddf.find(cercle.getNom())==null);
     assertTrue(ddf.find(triangle.getNom())==null);
}
 
    @Test
    public void  UpdateFormsDERBY() {
    	ddf.create(carre);
        ddf.create(rectangle);
        ddf.create(cercle);
        ddf.create(triangle);
    	carre.move(10, 10);
    	rectangle.move(10, 10);
    	cercle.move(10, 10);
    	triangle.move(10, 10);
       	ddf.update(carre);
    	ddf.update(rectangle);
    	ddf.update(cercle);
    	ddf.update(triangle);
     assertTrue(((Carre)ddf.find(carre.getNom())).getcoordonnes().get(0).getX()==13);
     assertTrue(((Rectangle)ddf.find(rectangle.getNom())).getcoordonnes().get(1).getY()==17);
     assertTrue(((Cercle)ddf.find(cercle.getNom())).getCentre().get(0).getY()==17);
     assertTrue(((Triangle)ddf.find(triangle.getNom())).getOrigine().get(2).getX()==99);
}
    
    
    @Test
    public void CreateDessin() {
    	
  cdd.create(dessin);
  assertTrue(cdd.find(dessin.getNom())!=null);
    	
    }

    
    @Test
    public void DeleteDessin() {
    	
    cdd.delete(petitDessin);
    	
   assertTrue(cdd.find(dessin.nom)==null);
    	
    }

    
    @Test
    public void UpdateDessin() {
    	cdd.create(petitDessin);
    	cdd.create(dessin);
    	dessin.addForme(carre);
    	dessin.addForme(rectangle);
    	petitDessin.addForme(cercle);
    	petitDessin.addForme(triangle);
    	dessin.addForme(petitDessin);
    	
    	cdd.update(dessin);
    	dessin=cdd.find(dessin.getNom());
    	petitDessin=cdd.find(petitDessin.getNom());
    	assertTrue(dessin.getForme().size()==3);
    	assertTrue(petitDessin.getForme().size()==2);
    	
    	
    }
  
    @Test
    public void  CommandeCreate() throws Exception {
    	
    	cdd.delete(cdd.find("des1"));
        ddf.delete(ddf.find("car1"));
    	ddf.delete(ddf.find("cer1"));
    	ddf.delete(ddf.find("tri2"));
    	ddf.delete(ddf.find("rec1"));
    	System.out.println("#########TEST:CommandeCreate########");
    	
    	//commande a taper a la main pour créer les formes 
    	String commandeCreateDessin="des1=dessin()"; 
    	String commandeCreateCercle="cer1=cercle( (4, 8 ) , 49 )";
    	String commandeCreateTriangle="tri2=triangle( (56, 65 ) ,(01,78 ),(16, 89 ))";
    	String commandeCreateRectangle="rec1=RECTANGLE( (4, 8 ) , 50,30 )";
    	String commandeCreateCarre="car1=carre( (4, 8 ) , 49 )";
    	
    	
    	Dessin des1=(Dessin) da.d.nextCommande(commandeCreateDessin);
    	Cercle cer1=(Cercle) da.d.nextCommande(commandeCreateCercle);
    	Carre car1=(Carre) da.d.nextCommande(commandeCreateCarre);
    	Rectangle rec1=(Rectangle) da.d.nextCommande(commandeCreateRectangle );
    	Triangle tri1=(Triangle) da.d.nextCommande(commandeCreateTriangle);
    	des1.addForme(cercle);
    	des1.addForme(carre);
    	cdd.update(des1);
    	da.d.afficherForm(des1);
    	da.d.afficherForm(cer1);
    	da.d.afficherForm(car1);
    	da.d.afficherForm(rec1);
    	da.d.afficherForm(tri1);
    	
    	System.out.println("##########FinTEST :CommandeCreate##########");
    	assertTrue(ddf.find(car1.getNom())!=null);
    	assertTrue(cdd.find(des1.getNom())!=null);
        assertTrue(ddf.find(rec1.getNom())!=null);
        assertTrue(ddf.find(cer1.getNom())!=null);
        assertTrue(ddf.find(tri1.getNom())!=null);
        
    }
    @Test
 public void  CommandeMove() throws Exception {
    	
    	
    	System.out.println("################TEST:CommandeMove##############");
    	
    	System.out.println("#######coordonnées avant le déplacement######");
    	da.d.afficherForm(cdd.find("des1"));
    	da.d.afficherForm(ddf.find("cer1"));
    	da.d.afficherForm(ddf.find("car1"));
    	da.d.afficherForm(ddf.find("rec1"));
    	da.d.afficherForm(ddf.find("tri2"));
    	
    	
    	
    	String commandeMoveDessin="move(des1,(10,10))";
       	Dessin des1=(Dessin) da.d.nextCommande(commandeMoveDessin);
    	
    	String commandeMoveCercle=("move(cer1,(10,10))");
		Cercle cer1=(Cercle) da.d.nextCommande(commandeMoveCercle);
    	String commandeMoveCarre="move(car1,(10,10))";
		Carre car1=(Carre) da.d.nextCommande(commandeMoveCarre);
    	String commandeMoveRectangle="move(rec1,(10,10))";
		Rectangle rec1=(Rectangle) da.d.nextCommande(commandeMoveRectangle );
    	String commandeMoveTriangle="move(tri2,(10,10))";
		Triangle tri1=(Triangle) da.d.nextCommande(commandeMoveTriangle);
		System.out.println("##########coordonnées apres le déplacement########");
    	da.d.afficherForm(des1);
    	da.d.afficherForm(cer1);
    	da.d.afficherForm(car1);
    	da.d.afficherForm(rec1);
    	da.d.afficherForm(tri1);
    	
    	System.out.println("###############FinTEST :CommandeCreate###############");
    	assertTrue(ddf.find(car1.getNom())!=null);
    	assertTrue(cdd.find(des1.getNom())!=null);
        assertTrue(ddf.find(rec1.getNom())!=null);
        assertTrue(ddf.find(cer1.getNom())!=null);
        assertTrue(ddf.find(tri1.getNom())!=null);
        
    }
    @After
    public void DeleteAll() {
    	
    	
    	cdd.delete(dessin);
    	cdd.delete(petitDessin);
    	
    	
    }
}