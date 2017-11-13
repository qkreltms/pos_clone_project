package logic;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DatabaseAbstract {
	public abstract ArrayList<?> select(String query) throws SQLException;
	public abstract boolean insert(Object o) throws SQLException;
	public abstract boolean update(Object o) throws SQLException;
	public abstract boolean delete(Object o) throws SQLException;
}
