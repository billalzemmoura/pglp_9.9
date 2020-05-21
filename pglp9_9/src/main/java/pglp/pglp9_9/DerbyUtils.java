package pglp.pglp9_9;

import java.sql.SQLException;

public class DerbyUtils {

	public DerbyUtils() {
		// empty constructor -- helper class
	}

	public static boolean tableAlreadyExists(SQLException e) {
		boolean exists;
		if (e.getSQLState().equals("X0Y32")) {
			exists = true;
		} else {
			exists = false;
		}
		return exists;
	}

}
