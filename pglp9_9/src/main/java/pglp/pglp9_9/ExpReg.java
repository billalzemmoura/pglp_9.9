package pglp.pglp9_9;

public class ExpReg {
	static String expregCreateCercle="^([a-zA-Z]+)(\\d*)(\\s)*=(\\s)*(cercle|CERCLE)(\\s)*(\\(){2}(\\d+),(\\d+)(\\)),(\\d+)(\\))$";   
	static String expregCreateCaree="^([a-zA-Z]+)(\\d*)(\\s)*=(\\s)*(caree|CAREE)(\\s)*(\\()(\\([0-9]+,[0-9]+(\\)),){4}(\\d+)(\\))$";   
	static String expregCreateRectangle="^([a-zA-Z]+)(\\d*)(\\s)*=(\\s)*(rectangle|RECTANGLE)(\\s)*(\\()(\\([0-9]+,[0-9]+(\\)),){4}([0-9]+,[0-9]+)(\\))$";
	static String expregCreateTriangle="^([a-zA-Z]+)(\\d*)(\\s)*=(\\s)*(triangle|TRIANGLE)(\\s)*(\\()((\\()[0-9]+,[0-9]+(\\)),){2}(\\()[0-9]+,[0-9]+(\\))(\\))$";
	static String expregMove="^(\\s)*(move|MOVE)(\\s)*(\\()([a-zA-Z]+)(\\d*),(\\()[0-9]+,[0-9]+(\\)){2}$";
	static String commande ="u2dd=Cercle((0,00),7)";
	static String expregCreateDessin="^([a-zA-Z]+)(\\d*)(\\s)*=(\\s)*(d|D)(essin)(\\s)*(\\()(\\))$";  
	static String expregAddForm="^(\\s)*([a-zA-Z]+)(\\d*).((ADD)|(add))(\\s)*(\\()(\\s)*([a-zA-Z]+)(\\d*)(\\))$" ; 
	static String expregDelete="^(\\s)*(DELETE|delete)(\\s)*(\\()([a-zA-Z]+)(\\))(\\d*)$";
	static String expregAfficher="^(\\s)*(AFFICHER|afficher)(\\s)*(\\()([a-zA-Z]+)(\\))(\\d*)$";
	static String expregQuit="^(\\s)*(QUIT|quit)(\\s)*$";
	static boolean isMatchesAfficher(String commande) {
	 	   
	 	   
	 	   return commande.matches(expregAfficher);
	 	    
	    }
	static boolean isMatchesAddforme(String commande) {
 	   
 	   
 	   return commande.matches(expregAddForm);
 	    
    }   
	static boolean isMatchesQuit(String commande) {
	 	   
	 	   
	 	   return commande.matches(expregQuit);
	 	    
	    }   
	static boolean isMatchesDelete(String commande) {
	 	   
	 	   
	 	   return commande.matches(expregDelete);
	 	    
	    }   
	static boolean isMatchesCreateCercle(String commande) {
    	   
    	   
    	   return commande.matches(expregCreateCercle);
    	    
       }
       static boolean isMatchesCreateTriangle(String commande) {
    	   
    	   boolean t=commande.matches(expregCreateTriangle);
    	   if (t)
    		   System.out.println("je suis la ");
    	   return t;
    	   
       
       }
       static boolean isMatchesCreateRectangle(String commande) {
    	   
    	   
    	   return commande.matches(expregCreateRectangle);
    	    
       
       }
       static boolean isMatchesCreateCaree(String commande) {
    	   
    	   
    	   return commande.matches(expregCreateCaree);
    	    
       }
       static boolean isMatchesMove(String commande) {
    	   
    	   return commande.matches(expregMove);
    	    
       }
	public static boolean isMatchesCreateDessin(String commande) {
		
		return commande.matches(expregCreateDessin);
	    
	}
}
