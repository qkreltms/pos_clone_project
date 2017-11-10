package data;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShopDataProvider {
	public ArrayList<Shop> getAllData() throws SQLException;
}
