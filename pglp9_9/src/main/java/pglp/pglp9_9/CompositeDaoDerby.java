package pglp.pglp9_9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompositeDaoDerby implements DAO<Dessin> {

	private static final String CREATE_TABLE_groupe = "CREATE  TABLE DATABASEDAOO.GROUPE("
			+ "GRP_ID VARCHAR(45) NOT NULL," + "GRP_ID_PERE VARCHAR(45) NOT NULL ,"
			+ "FOREIGN KEY (GRP_ID_PERE) REFERENCES DATABASEDAOO.dessin(GRP_ID),"
			+ "FOREIGN KEY (GRP_ID) REFERENCES DATABASEDAOO.dessin(GRP_ID)," + "PRIMARY KEY (GRP_ID ,GRP_ID_PERE))";

	private static final String CREATE_TABLE_dessin = "CREATE  TABLE DATABASEDAOO.dessin("
			+ "GRP_ID VARCHAR(45) NOT NULL," + "PRIMARY KEY (GRP_ID ))";

	private static final String CREATE_TABLE_SQL = "CREATE TABLE DATABASEDAOO.FORME(" + "NAME VARCHAR(45) NOT NULL,"
			+ "A_X INT NOT NULL," + "A_Y INT ," + "B_X INT ," + "B_Y INT ," + "C_X INT ," + "C_Y INT ," + "D_X INT ,"
			+ "D_Y INT ," + "rayon INT ," + "cotee INT ," + "longeur INT ," + "largeur INT ," + "type VARCHAR(45) , "
			+ "GRP VARCHAR(45) ," + "FOREIGN KEY (GRP) REFERENCES DATABASEDAOO.dessin(GRP_ID),"
			+ "PRIMARY KEY (NAME ))";

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public CompositeDaoDerby() {
		try {

			connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
			statement = connect.createStatement();
			statement.executeUpdate(CREATE_TABLE_dessin);
			statement.executeUpdate(CREATE_TABLE_groupe);
			statement.executeUpdate(CREATE_TABLE_SQL);

			/*
			 * resultSet = statement.executeQuery();
			 */
			close();
		} catch (SQLException e) {
			if (DerbyUtils.tableAlreadyExists(e)) { // check if the exception is because of pre-existing table.

				close();

			}

		} finally {
			close();
		}

	}

	public void close() {
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
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	public Dessin find(String nomdessin) {
		close();
		Dessin p = null;
		try {
			DAO<Forme> pDaoDerby = DAOFactory.getFormDaoDerby();
			connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
			PreparedStatement preparedStatement = connect
					.prepareStatement("SELECT * FROM DATABASEDAOO.dessin WHERE GRP_ID=?");
			preparedStatement.setString(1, nomdessin);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				p = new Dessin(nomdessin);
				PreparedStatement prepared = connect.prepareStatement("SELECT * FROM DATABASEDAOO.FORME WHERE GRP=?");
				prepared.setString(1, nomdessin);
				ResultSet resultPERSO = prepared.executeQuery();
				while (resultPERSO.next()) {
					(pDaoDerby.find(resultPERSO.getString("NAME"))).setNomGRP(nomdessin);
					p.addForme(pDaoDerby.find(resultPERSO.getString("NAME")));

				}

				prepared = connect.prepareStatement("SELECT * FROM DATABASEDAOO.GROUPE WHERE GRP_ID_PERE=?  ");
				prepared.setString(1, nomdessin);

				resultPERSO = prepared.executeQuery();

				while (resultPERSO.next()) {
					p.addForme(find(resultPERSO.getString("GRP_ID")));

				}
			} else {
				return null;
			}

		} catch (SQLException e) {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}

	public Dessin create(Dessin obj) {
		Dessin p = null;
		try {

			p = find(obj.getNom());

			if (p != null) {
				System.out.println(p.getNom() + " ce groupe existe dejas sur la base de données !");
				return p;
			} else {
				DAO<Forme> pDaoDerby = DAOFactory.getFormDaoDerby();
				connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
				PreparedStatement prepare = connect
						.prepareStatement("INSERT INTO DATABASEDAOO.dessin ( GRP_ID) VALUES (?)");
				prepare.setString(1, obj.getNom());
				prepare.executeUpdate();
				for (int i = 0; i < obj.getForme().size(); i++) {
					if (obj.getForme().get(i) instanceof Cercle)
						pDaoDerby.create((Cercle) obj.getForme().get(i));

					if (obj.getForme().get(i) instanceof Rectangle)
						pDaoDerby.create((Rectangle) obj.getForme().get(i));

					if (obj.getForme().get(i) instanceof Triangle)
						pDaoDerby.create((Triangle) obj.getForme().get(i));

					if (obj.getForme().get(i) instanceof Carre)
						pDaoDerby.create((Carre) obj.getForme().get(i));

					if (obj.getForme().get(i) instanceof Dessin) {

						create((Dessin) obj.getForme().get(i));
						PreparedStatement prepared = connect.prepareStatement(
								"SELECT * FROM DATABASEDAOO.GROUPE WHERE GRP_ID_PERE=? and GRP_ID=?  ");
						prepared.setString(1, obj.getNom());
						prepared.setString(2, ((Dessin) obj.getForme().get(i)).getNom());
						ResultSet resultPERSO = prepared.executeQuery();
						if (resultPERSO.next()) {

						} else {

							PreparedStatement prepare2 = connect.prepareStatement(
									"INSERT INTO DATABASEDAOO.GROUPE ( GRP_ID_PERE,GRP_ID) VALUES (?,?)");
							prepare2.setString(1, obj.getNom());
							prepare2.setString(2, ((Dessin) obj.getForme().get(i)).getNom());
							prepare2.executeUpdate();
							close();
						}

					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return find(obj.getNom());

	}

	@Override
	public Dessin update(Dessin obj) {
		delete(obj);
		return create(obj);
	}

	@Override
	public Dessin delete(Dessin obj) {
		
		if (obj!=null) {
			
		
		
		Dessin p = null;
		try {
            
			p = find(obj.getNom());
			if (p == null) {
				return null;
			} else {
				DAO<Forme> pDaoDerby = DAOFactory.getFormDaoDerby();
				connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");
				PreparedStatement prepare1 = connect
						.prepareStatement("DELETE FROM  DATABASEDAOO.GROUPE WHERE GRP_ID_PERE=?");
				prepare1.setString(1, p.getNom());
				prepare1.executeUpdate();

				prepare1 = connect.prepareStatement("DELETE FROM  DATABASEDAOO.GROUPE WHERE GRP_ID=?");
				prepare1.setString(1, p.getNom());
				prepare1.executeUpdate();
				close();

				for (int i = 0; i < obj.getForme().size(); i++) {

					if ((obj.getForme().get(i) instanceof Triangle) || (obj.getForme().get(i) instanceof Rectangle)
							|| (obj.getForme().get(i) instanceof Carre) || (obj.getForme().get(i) instanceof Cercle)) {

						pDaoDerby.delete(obj.getForme().get(i));

					}

					if (obj.getForme().get(i) instanceof Dessin) {
						delete((Dessin) obj.getForme().get(i));
					}

				}

				connect = DriverManager.getConnection("jdbc:derby:DATABASEDAOO;create=true");

				prepare1 = connect.prepareStatement("DELETE FROM  DATABASEDAOO.dessin WHERE GRP_ID=?");
				prepare1.setString(1, obj.getNom());
				prepare1.executeUpdate();
				close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return p;
		}
		return null;
		}
}
