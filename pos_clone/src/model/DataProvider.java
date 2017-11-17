package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DataProvider {
	public ArrayList<?> getAllData();
	public ArrayList<?> findRecordBy (String colmnName, int id); 
	public boolean insert(Object o);
	public boolean update(Object o);
	public boolean delete(Object o);
	public long getMaxId();
}


