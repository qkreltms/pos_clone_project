package data;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDataProvider {
	public ArrayList<Order> getAllData() throws SQLException;
}
