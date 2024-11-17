package application;
import java.sql.SQLException;
import java.util.ArrayList;

public class TourismManagementSystem {
	
	private DatabaseHandler DB;
	
	TourismManagementSystem() throws SQLException {
		DB = new DatabaseHandler();
	}
	public ArrayList<ArrayList<Object>> getTop3Customers() {
		ArrayList<ArrayList<Object>> topCustomers = DB.getTop3Customers();
		return topCustomers;
	}

}
