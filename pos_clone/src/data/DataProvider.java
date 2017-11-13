package data;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DataProvider {
	public ArrayList<?> getAllData() throws SQLException;
	public ArrayList<?> findRecordById (String id) throws SQLException;
	public boolean insert(Object o) throws SQLException;
	public boolean update(Object o) throws SQLException;
	public boolean delete(Object o) throws SQLException;
}


