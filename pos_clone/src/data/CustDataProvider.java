package data;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustDataProvider {
	public ArrayList<Customer> getAllData() throws SQLException;
}


