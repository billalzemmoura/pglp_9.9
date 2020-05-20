package pglp.pglp9_9;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.apache.derby.impl.sql.catalog.SYSPERMSRowFactory;
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
	@Before
	public void create() throws Exception {
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
    System.out.println(dessin.getForme().get(0).getNom());
    assertTrue(dessin.getForme().get(0).getNom().equals(cercle.getNom()));
}
    @Test
    public void  deleteFormeDessin() {
    	
    	dessin.removeForme(cercle);
	    assertTrue(dessin.getForme().size()==0);
}
    @Test
    public void  afficherFormes() {
    	
    	da.d.afficherForm(carre);
    	da.d.afficherForm(rectangle);
    	da.d.afficherForm(cercle);
    	da.d.afficherForm(triangle);
	    
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
    
    @After
    public void DeleteAll() {
    	
    	cdd.delete(dessin);
    	cdd.delete(petitDessin);
    	
    	
    }
}