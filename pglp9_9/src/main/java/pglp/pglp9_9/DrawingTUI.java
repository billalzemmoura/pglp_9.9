package pglp.pglp9_9;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;





public class DrawingTUI {
	final HashMap<String, Commande> map= new HashMap<String, Commande>();
	
	
	
	 public void Addcommande(String nomCom, Commande com) {
			map.put(nomCom, com);
     }
	 public void afficherForm(Forme f) {
		 if (f instanceof Cercle) {
			
			 System.out.print("Cercle(");
			 System.out.print("centre=("+((Cercle)f).getCentre().get(0).getX()+","+((Cercle)f).getCentre().get(0).getY()+"),");		
			 System.out.println("rayon="+((Cercle)f).getRayon()+")");	
          }
		 
		 if (f instanceof Dessin) {
			
			 System.out.print("Dessin(");
			for (int i = 0; i <(( Dessin)f).getForme().size(); i++) {
				afficherForm((( Dessin)f).getForme().get(i));
			if (i!=(( Dessin)f).getForme().size()-1) {
				System.out.println(",");
			}
				
			}
			 System.out.println(")");	
}
		 if (f instanceof Carre) {
				
			 System.out.print("Carre(");
			 for (int i = 0; i < 4; i++) {
				
			
			 System.out.print("Point"+"=("+((Carre)f).getcoordonnes().get(i).getX()+","+((Carre)f).getcoordonnes().get(i).getY()+"),");		
			 }
			 System.out.println("cote="+((Carre)f).getCote()+")");	
}
		 
		 if (f instanceof Rectangle) {
				
			 System.out.print("Rectangle(");
			 for (int i = 0; i < 4; i++) {
				
			
			 System.out.print("Point"+(i+1)+"=("+((Rectangle)f).getcoordonnes().get(i).getX()+","+((Rectangle)f).getcoordonnes().get(i).getY()+"),");		
			 }
			 System.out.print("longeur="+((Rectangle)f).getLongeur()+',');
			 System.out.println("largeur="+((Rectangle)f).getLargeur()+")");	
}
		 if (f instanceof Triangle) {
				
			 System.out.print("Triangle(");
			 for (int i = 0; i < 3; i++) {
				
			
			 System.out.print("Point"+(i+1)+"=("+((Triangle)f).getOrigine().get(i).getX()+","+((Triangle)f).getOrigine().get(i).getY()+")");		
			 if (i!=2) {
				 System.out.print(",");	
			}
			 
			 }
			System.out.println(")");
}
	 }
	 public Forme  nextCommande(String commande) throws Exception {
		 if (ExpReg.isMatchesCreateCercle(commande)) { 
		 return ExcuteCommandeCercle("createCercle", commande);
		 }
		 if (ExpReg.isMatchesAddforme(commande)) { 
			
			 return ExcuteCommandeAdd("add", commande);
			 }
		 if (ExpReg.isMatchesQuit(commande)) { 
				System.out.println("arrêt du programme. ");
			 return ExcuteCommandeQuit("quit", commande);
			 }
		 if (ExpReg.isMatchesDelete(commande)) { 
			 
			 return ExcuteCommandeDelete(commande);
			 }
		 if (ExpReg.isMatchesAfficher(commande)) { 
			 return ExcuteCommandeAfficher(commande);
			 }
		 if (ExpReg.isMatchesCreateDessin(commande)) { 
			
			 return ExcuteCommandeDessin("createDessin", commande);
			 }
		 if(ExpReg.isMatchesCreateCaree(commande)) { 
	    	 return ExcuteCommandeCaree("createCaree", commande);	 
			 }
	 
	     if(ExpReg.isMatchesCreateRectangle(commande)) { 
	    	 return excuteCommandeRectangle("createRectangle", commande);
		 }
	     
	     if(ExpReg.isMatchesCreateTriangle(commande)) { 
	    	
	    	 return excuteCommandeTriangle("createTriangle", commande);
		 }
	     if(ExpReg.isMatchesMove(commande)) { 
		    	
		    	 return excuteCommandeMove("move", commande);
			 }
	    	System.out.println("commande incorecte !");
		return null;
	 }
	 
	 private Forme ExcuteCommandeQuit(String string, String commande) {
		System.out.println("tout les formes et les dessins que vous avez créer sont sauvgarder");
		System.exit(0);
		return null;
	}
	private Forme ExcuteCommandeAfficher(String commande) throws Exception {
		 CompositeDaoDerby c=DAOFactory.getCompositeDaoDerby();
			DaoDerbyForme d= (DaoDerbyForme) DAOFactory.getFormDaoDerby();
			ArrayList<String> valeur=decortiquerCommande(commande);
			if(c.find(valeur.get(1))!=null) {
				 return c.find(valeur.get(1));
			}
			if(d.find(valeur.get(1))!=null) {
				 return d.find(valeur.get(1));
			}
			System.out.println("la forme ou le dessin n'existe pas");
			return null;
	}
	private Forme ExcuteCommandeDelete(String commande) throws Exception {
		 CompositeDaoDerby c=DAOFactory.getCompositeDaoDerby();
			DaoDerbyForme d= (DaoDerbyForme) DAOFactory.getFormDaoDerby();
			ArrayList<String> valeur=decortiquerCommande(commande);
		if(c.find(valeur.get(1))!=null) {
			 c.delete(c.find(valeur.get(1)));
			 System.out.println("le dessin est supprimé");
			 return c.find(valeur.get(1));
		}
		
		
		if(d.find(valeur.get(1))!=null) {
			 d.delete(d.find(valeur.get(1)));
			 System.out.println("la forme est supprimé");
			 return d.find(valeur.get(1));
		}
			System.out.println("la forme ou le dessin n'existe pas ");
		
		return null;
			
			
	}
	private Forme excuteCommandeMove(String NomCommande, String commande) throws Exception {
		 CommandeMoveForms<Forme> commandMove=(CommandeMoveForms<Forme>) map.get(NomCommande);
		if (commande == null) {
			throw new IllegalStateException("la commande n'existe pas !" + NomCommande);
		}else {
			ArrayList<String> valeur=decortiquerCommande(commande);
			CompositeDaoDerby c=DAOFactory.getCompositeDaoDerby();
			DaoDerbyForme d= (DaoDerbyForme) DAOFactory.getFormDaoDerby();
			d.close();
			if (d.find(valeur.get(1))!=null) {
				return d.update( commandMove.excute(d.find(valeur.get(1)), Integer.parseInt(valeur.get(2)),Integer.parseInt(valeur.get(3))));	
			}else {
				if (c.find(valeur.get(1))!=null) {
					return c.update( (Dessin) commandMove.excute(c.find(valeur.get(1)), Integer.parseInt(valeur.get(2)),Integer.parseInt(valeur.get(3))));
				}else {
				
				System.out.println("la forme que vous voulez déplacer n'existe pas ");
				
				return null;
			}}
			
			
		}
	}
  private Dessin ExcuteCommandeAdd(String NomCommande,String commandeBrute) {
	  
	  AddForme commande = (AddForme) map.get(NomCommande);
	  if (commande == null) {
			throw new IllegalStateException("la commande n'existe pas !" + NomCommande);
		}else {
		ArrayList<String> valeurs=decortiquerCommande(commandeBrute);
		try {
			 DaoDerbyForme ddf=(DaoDerbyForme) DAOFactory.getFormDaoDerby(); 
			 CompositeDaoDerby cdd=(CompositeDaoDerby) DAOFactory.getCompositeDaoDerby();
			 if (cdd.find(valeurs.get(0))!=null) {
				if ((ddf.find(valeurs.get(2))!=null)) {
					if (cdd.find(valeurs.get(0)).nom!=ddf.find(valeurs.get(2)).nomGRP) {
				
						return (Dessin) cdd.update(commande.excute( cdd.find(valeurs.get(0)),ddf.find(valeurs.get(2))));
	                   }else {
	                	   System.out.println("le dessin contient deja la forme que vous voullez inserer");
	                   }
									}else {
					if(cdd.find(valeurs.get(2))!=null){
						System.out.println(cdd.find(valeurs.get(0)).nom);
						System.out.println(cdd.find(valeurs.get(2)).nomGRP);
						if ((cdd.find(valeurs.get(0)).nom!=cdd.find(valeurs.get(2)).nomGRP)) {
							return (Dessin) cdd.update(commande.excute( cdd.find(valeurs.get(0)),cdd.find(valeurs.get(2))));
						}else {
							System.out.println("le dessin contient deja la forme que vous voullez inserer");

						}
						
					}else
						System.out.println("la forme:"+valeurs.get(2)+"n'existe pas" );
				}	
				}else {
					System.out.println("la forme:"+valeurs.get(0)+"n'existe pas" );
				}
		   
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return null;
      
	
	 
	 
  }
		public  Dessin ExcuteCommandeDessin(String NomCommande,String commandeBrute) {
			 
			 
			 CreateDessin commande = (CreateDessin) map.get(NomCommande);
				if (commande == null) {
					throw new IllegalStateException("la commande n'existe pas !" + NomCommande);
				}else {
				ArrayList<String> valeurs=decortiquerCommande(commandeBrute);
				try {
					
					CompositeDaoDerby ddf=(CompositeDaoDerby) DAOFactory.getCompositeDaoDerby();
					if (ddf.find(valeurs.get(0))==null) {
						
					
					return (Dessin) ddf.create(commande.excute( valeurs.get(0)));
					}else {
						System.out.println("le nom de la forme :"+" "+valeurs.get(0)+" existe deja");
						return null;
					}
					
					} catch (Exception e) {
					
				}
				}
				return null;
	            
			}
	 
	 
	 
	 public  Cercle ExcuteCommandeCercle(String NomCommande,String commandeBrute) {
		 int origineX;
		 int origineY;
		 int rayon;
		 
		 CreateCercle commande = (CreateCercle) map.get(NomCommande);
			if (commande == null) {
				throw new IllegalStateException("la commande n'existe pas !" + NomCommande);
			}else {
			ArrayList<String> valeurs=decortiquerCommande(commandeBrute);
			try {
				DaoDerbyForme ddf =(DaoDerbyForme) DAOFactory.getFormDaoDerby();
				if (ddf.find(valeurs.get(0))==null) {
					
				
				origineX=Integer.parseInt(valeurs.get(2));		
			    origineY=Integer.parseInt(valeurs.get(3));
			    rayon=Integer.parseInt(valeurs.get(4));
			    return ddf.createCercle(commande.excute( valeurs.get(0),Point.PointFactory(origineX, origineY),rayon));
			}else {
				System.out.println("le nom :"+" "+valeurs.get(0)+" existe deja");
				return null;
			}
				} catch (Exception e) {
				
			}
			}
			return null;
            
		}
	 public  Carre ExcuteCommandeCaree(String NomCommande,String commandeBrute) throws Exception {
		 
		 int cotee;
		 DaoDerbyForme ddf =(DaoDerbyForme) DAOFactory.getFormDaoDerby();
		 CreateCaree commande =  (CreateCaree) map.get(NomCommande);
			if (commande == null) {
				throw new IllegalStateException("la commande n'existe pas !" + NomCommande);
			}
			
			ArrayList<String> valeurs=decortiquerCommande(commandeBrute);
			try {
				
				ArrayList<Point> points= recupererCoordone(valeurs,1);
				if (ddf.find(valeurs.get(0))==null) {
					cotee=Integer.parseInt(valeurs.get(4));
			    return (Carre) ddf.create(commande.excute( valeurs.get(0),points.get(0),cotee));
			}else {
				System.out.println("le nom :"+" "+valeurs.get(0)+" existe deja");
			return null;
			} }catch (Exception e) {
				
			}
		
			return null;
            
		}
	 public ArrayList<Point >recupererCoordone(ArrayList<String> valeurs,int nombrePoint){
		 ArrayList<Point> coordonee=new ArrayList<Point>();
		int i=2;
		 while ( i <= nombrePoint*2) {
			coordonee.add(Point.PointFactory(Integer.parseInt(valeurs.get(i)), Integer.parseInt(valeurs.get(i+1))));	
		    i=2+i;
		 }
		return coordonee;
		 
	 }
	 public  Triangle excuteCommandeTriangle(String NomCommande,String commandeBrute) {

		 
		 
		 CreateTriangle commande = (CreateTriangle) map.get(NomCommande);
			if (commande == null) {
				throw new IllegalStateException("la commande n'existe pas !" + NomCommande);
			}
			ArrayList<String> valeurs=decortiquerCommande(commandeBrute);
			try {
				DaoDerbyForme ddf=(DaoDerbyForme) DAOFactory.getFormDaoDerby();
				if (ddf.find(valeurs.get(0))==null) {
					
				
				ArrayList<Point> points= recupererCoordone(valeurs, 3);
			   
			    
				return (Triangle) ddf.create(commande.excute( valeurs.get(0),points.get(0),points.get(1),points.get(2)));
			}else {
				System.out.println("le nom :"+" "+valeurs.get(0)+" existe deja");
				return null;
				
			}
				} catch (Exception e) {
				
			}
		
			return null;
            
		}
	 public  Rectangle excuteCommandeRectangle(String NomCommande,String commandeBrute) {
		 int longeur;
		 int largeur;
		 
		 
		 CreateRectangle commande = (CreateRectangle) map.get(NomCommande);
			if (commande == null) {
				throw new IllegalStateException("la commande n'existe pas !" + NomCommande);
			}
			ArrayList<String> valeurs=decortiquerCommande(commandeBrute);
			try {
				DaoDerbyForme ddf=(DaoDerbyForme) DAOFactory.getFormDaoDerby();
				if (ddf.find(valeurs.get(0))==null) {
					
				
				ArrayList<Point >points=recupererCoordone(valeurs, 1);
			    longeur=Integer.parseInt(valeurs.get(4));
			    largeur=Integer.parseInt(valeurs.get(5));
			    if (longeur>largeur) {
			    	return (Rectangle) ddf.create(commande.excute(  valeurs.get(0),points.get(0),longeur,largeur));	
				}else {
					System.out.println("il faut que la longeur(2eme parametre)>largeur(3eme parametre)");
				
				}
				
				}else {
					System.out.println("le nom :"+" "+valeurs.get(0)+" existe deja");
					return null;
					
				}
				} catch (Exception e) {
				
			}
		
			return null;
            
		}
	 public ArrayList<String> decortiquerCommande(String NomCommande) {
         ArrayList<String>valleurs=new ArrayList<String>();
		 StringTokenizer s = new StringTokenizer(NomCommande);
		 while (s.hasMoreElements()) {
			valleurs.add(s.nextToken("=,)(."));
			
		}
			return valleurs;
		}
	
	
}
