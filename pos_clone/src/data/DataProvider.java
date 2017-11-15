package data;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DataProvider {
	public ArrayList<?> getAllData();
	public ArrayList<?> findRecordById (String colmnName, int id); //TODO: findRecordBy로 수정할까?
	public boolean insert(Object o);
	public boolean update(Object o);
	public boolean delete(Object o);
}


