package data;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShopOwnerDataProvider {
	public ArrayList<ShopOwner> getAllData() throws SQLException;
}
