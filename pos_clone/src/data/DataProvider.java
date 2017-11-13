package data;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DataProvider {
	public ArrayList<?> getAllData() throws SQLException;
}


