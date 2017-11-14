package logic;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DatabaseAbstract {
	public abstract ArrayList<?> select(String query);
	public abstract boolean insert(Object o);
	public abstract boolean update(Object o);
	public abstract boolean delete(Object o);
}
