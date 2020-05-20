package pglp.pglp9_9;

import java.awt.print.Printable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;







public class DaoDerbyForme implements DAO<Forme> {

	private static final String CREATE_TABLE_groupe="CREATE  TABLE DATABASEDAOO.GROUPE("
            + "GRP_ID VARCHAR(45) NOT NULL,"
            + "GRP_ID_PERE VARCHAR(45) NOT NULL ,"
            + "FOREIGN KEY (GRP_ID_PERE) REFERENCES DATABASEDAOO.dessin(GRP_ID),"
            + "FOREIGN KEY (GRP_ID) REFERENCES DATABASEDAOO.dessin(GRP_ID),"
            + "PRIMARY KEY (GRP_ID ,GRP_ID_PERE))";	
	
	private static final String CREATE_TABLE_dessin="CREATE  TABLE DATABASEDAOO.dessin("
            + "GRP_ID VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY (GRP_ID ))";	
	
	private static final String CREATE_TABLE_SQL="CREATE TABLE DATABASEDAOO.FORME("
            + "NAME VARCHAR(45) NOT NULL,"
            + "A_X INT NOT NULL,"
            + "A_Y INT ,"
            + "B_X INT ,"
            + "B_Y INT ,"
            + "C_X INT ,"
            + "C_Y INT ,"
            + "D_X INT ,"
            + "D_Y INT ,"
            + "rayon INT ,"
            + "cotee INT ,"
            + "longeur INT ,"
            + "largeur INT ,"
            + "type VARCHAR(45) , "
            + "GRP VARCHAR(45) ,"
            + "FOREIGN KEY (GRP) REFERENCES DATABASEDAOO.dessin(GRP_ID),"
            + "PRIMARY KEY (NAME ))";


    

	    private Connection connect = null;
	    private Statement statement = null;
	    private ResultSet resultSet = null;
	    
	    public DaoDerbyForme() {
        try {

        	
            connect =DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
            statement=connect.createStatement();
            statement.executeUpdate( CREATE_TABLE_dessin);
            statement.executeUpdate(CREATE_TABLE_groupe);
            statement.executeUpdate(CREATE_TABLE_SQL);
           
          
            close();  
        } catch (SQLException e) {
        	  if(DerbyUtils.tableAlreadyExists(e)) { //check if the exception is because of pre-existing table.
                 
                  close();
        	  
        	  }

        } finally {
        	close();
        }

    }
	
	
	
	
	
		
		 
		public Cercle createCercle(Cercle obj) {
			try {
				
				connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
				PreparedStatement prepare =connect.prepareStatement("INSERT INTO DATABASEDAOO.FORME"
						+ "( NAME ,A_X, A_Y, rayon ,GRP,type" + 
						") VALUES (?,?,?,?,?,?)");
				prepare.setString(1, obj.getNom());
				prepare.setInt(2, obj.getCentre().get(0).getX());
				prepare.setInt(3, obj.getCentre().get(0).getY());
				prepare.setInt(4, obj.getRayon());
				if (obj.getNomGRP()=="") {
					prepare.setString(5, null);
				}else {
					prepare.setString(5, obj.getNomGRP());
				}
				
				prepare.setString(6, "CERCLE");
				
				int result=prepare.executeUpdate();
				
				assert result==1;
				
				
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
			
			close();
			// TODO Auto-generated method stub
			return ((Cercle)find(obj.getNom()));
		}
			
			
			
			
		
		
			
	
		public Forme create(Forme obj) {
	
		try {
			
			
			Forme p=find(obj.getNom());
			if (p!=null) {
				
				return p;
			}else {
			if (obj instanceof Cercle) {
			return createCercle((Cercle)obj);
				
			}
			if (obj  instanceof Carre) {
				return CreateCaree((Carre)obj);
				
			}
			if (obj instanceof Triangle) {
			  
				Triangle t= CreateTriangle((Triangle)obj);
				
				return t;
			}
			if (obj  instanceof Rectangle) {
				
				return CreateRectangle((Rectangle)obj);
				
			}
		
          

			
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub
		return obj;
	}

	private Rectangle CreateRectangle(Rectangle obj) {
		
		try {
		connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
		PreparedStatement prepare =connect.prepareStatement("INSERT INTO DATABASEDAOO.FORME"
				+ "( NAME ,A_X, A_Y, B_X, B_Y,C_X, C_Y,D_X, D_Y, largeur,longeur,GRP,type" + 
				") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ArrayList< Point> variables =obj.getcoordonnes();
		prepare.setString(1, obj.getNom());
    	prepare.setInt(2, variables.get(0).getX());
		prepare.setInt(3, variables.get(0).getY());
		prepare.setInt(4, variables.get(1).getX());
		prepare.setInt(5, variables.get(1).getY());
		prepare.setInt(6, variables.get(2).getX());
		prepare.setInt(7, variables.get(2).getY());
		prepare.setInt(8, variables.get(3).getX());
		prepare.setInt(9, variables.get(3).getY());
		prepare.setInt(10, obj.getLargeur());
		prepare.setInt(11, obj.getLongeur());
		if (obj.getNomGRP().equals("")) {
			prepare.setString(12, null);
		}else {
     		prepare.setString(12, obj.getNomGRP());
		}
	    	prepare.setString(13, "RECTANGLE");
		int result=prepare.executeUpdate();
		assert result==1;
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		close();
		// TODO Auto-generated method stub
		return ((Rectangle)find(obj.getNom()));
	}
		
		
		
		
			
			
	








	private Triangle CreateTriangle(Triangle obj) {
		
			
			try {

			 
				// TODO Auto-generated catch block
				
			
			connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
			
			PreparedStatement prepare =connect.prepareStatement("INSERT INTO DATABASEDAOO.FORME"
					+ "( NAME ,A_X, A_Y, B_X, B_Y,C_X, C_Y,GRP,type" + 
					") VALUES (?,?,?,?,?,?,?,?,?)");
			
			ArrayList< Point> variables =obj.getOrigine();
			prepare.setString(1, obj.getNom());
			
				prepare.setInt(2, variables.get(0).getX());
				prepare.setInt(3, variables.get(0).getY());
				prepare.setInt(4, variables.get(1).getX());
				prepare.setInt(5, variables.get(1).getY());
				prepare.setInt(6, variables.get(2).getX());
				prepare.setInt(7, variables.get(2).getY());
			if (obj.getNomGRP().equals("")) {
				prepare.setString(8, null);	
			}else {
			prepare.setString(8, obj.getNomGRP());
			}
			prepare.setString(9, "Triangle");
			
			int result=prepare.executeUpdate();
			
			assert result==1;
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			close();
			// TODO Auto-generated method stub
			return obj;
		}








	private Carre CreateCaree(Carre obj) {
		
			
			try {
				
					
				
				connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
				
					           
				
				
				
				PreparedStatement prepare =connect.prepareStatement("INSERT INTO DATABASEDAOO.FORME"
						+ "( NAME ,A_X, A_Y, B_X, B_Y,C_X, C_Y,D_X,D_Y,cotee,GRP,type" + 
						") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
				
				ArrayList< Point> variables =obj.getcoordonnes();
				prepare.setString(1, obj.getNom());

				prepare.setInt(2, variables.get(0).getX());
				prepare.setInt(3, variables.get(0).getY());
				prepare.setInt(4, variables.get(1).getX());
				prepare.setInt(5, variables.get(1).getY());
				prepare.setInt(6, variables.get(2).getX());
				prepare.setInt(7, variables.get(2).getY());
				prepare.setInt(8, variables.get(3).getX());
				prepare.setInt(9, variables.get(3).getY());
			
				
				prepare.setInt(10, obj.getCote());
				if (obj.getNomGRP().equals("")) {
					prepare.setString(11, null);	
				}else {
				prepare.setString(11, obj.getNomGRP());
				}
				prepare.setString(12, "CAREE");

				int result=prepare.executeUpdate();
				assert result==1;
				
				
		
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			close();
			// TODO Auto-generated method stub
			return ((Carre)find(obj.getNom()));
		}
		
		
		
		
		
		







	public Forme find(String nomForme) {
       Forme p =null;
		try {
			connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");			
			PreparedStatement preparedStatement=connect.prepareStatement("SELECT * FROM DATABASEDAOO.FORME WHERE NAME=?");
			preparedStatement.setString(1, nomForme);
			resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) {
			
			if (resultSet.getString("TYPE").equals("Triangle")) {
				 p=new Triangle(resultSet.getString("NAME"), Point.PointFactory(resultSet.getInt("A_X"), resultSet.getInt("A_Y")), Point.PointFactory(resultSet.getInt("B_X"), resultSet.getInt("B_Y")), Point.PointFactory(resultSet.getInt("C_X"), resultSet.getInt("C_Y")));	
					
				
				}
				
				if (resultSet.getString("TYPE").equals("RECTANGLE")) {
					
					p=new Rectangle(resultSet.getString("NAME"), Point.PointFactory(resultSet.getInt("A_X"), resultSet.getInt("A_Y")), Point.PointFactory(resultSet.getInt("B_X"), resultSet.getInt("B_Y")), Point.PointFactory(resultSet.getInt("C_X"), resultSet.getInt("C_Y")),Point.PointFactory(resultSet.getInt("D_X"), resultSet.getInt("D_Y")),resultSet.getInt("largeur"),resultSet.getInt("longeur"));	
					
				}				
				if (resultSet.getString("type").equals("CERCLE")) {
							
                     p=new Cercle(resultSet.getString("NAME"), Point.PointFactory(resultSet.getInt("A_X"), resultSet.getInt("A_Y")),resultSet.getInt("rayon"));	
                    
				}
				if (resultSet.getString("TYPE").equals("CAREE")) {
					
					p=new Carre(resultSet.getString("NAME"), Point.PointFactory(resultSet.getInt("A_X"), resultSet.getInt("A_Y")), Point.PointFactory(resultSet.getInt("B_X"), resultSet.getInt("B_Y")), Point.PointFactory(resultSet.getInt("C_X"), resultSet.getInt("C_Y")),Point.PointFactory(resultSet.getInt("D_X"), resultSet.getInt("D_Y")),resultSet.getInt("cotee"));
					
				}	
			
			
			}	
			
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		close();
		return p;
	}

	public Forme update(Forme obj) {
		delete(obj);
		return create(obj);

	}

	public Forme delete(Forme obj) {
		
			try {
				
				
				Forme p=find(obj.getNom());
				
				if (p==null) {
					System.out.println("la fome n'existe pas" );
					//return null;
				}else {
					
					connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
					PreparedStatement prepare =connect.prepareStatement("DELETE FROM  DATABASEDAOO.FORME WHERE NAME=?");
				  prepare.setString(1,  obj.getNom());
                  prepare.executeUpdate();
                 
			}
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
			
			// TODO Auto-generated method stub
		
		
		return null;
	}
	public  void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
	
	
}
