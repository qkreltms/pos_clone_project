package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AccountDB {
	private Connection con;
	public final static String dbName = "Account";

	public void select(String query) throws SQLException {
			PreparedStatement preparedStatement = con.prepareStatement(query);
	}
}
