package pglp.pglp9_9;

public class DAOFactory {

	public static DAO<Forme> getFormDaoDerby() throws Exception {

		return new DaoDerbyForme();
	}

	public static CompositeDaoDerby getCompositeDaoDerby() throws Exception {

		return new CompositeDaoDerby();
	}

}
