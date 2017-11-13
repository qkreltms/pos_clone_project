package logic;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DatabaseBuilder {
	public abstract ArrayList<?> select(String query) throws SQLException;
	public abstract boolean update(String query) throws SQLException;
	public abstract ArrayList<?> delete(String query) throws SQLException;
	public abstract boolean insert(Object o) throws SQLException;
}
