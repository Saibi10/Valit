package application;
import Models.Booking;
import Models.TopCustomers;
import Models.Tours;

import java.sql.SQLException;
import java.util.ArrayList;

public class TourismManagementSystem {
	
	private DatabaseHandler DB;
	

	TourismManagementSystem() throws SQLException  {
		DB = new DatabaseHandler();
	}
	
	public ArrayList<TopCustomers> getTop3Customers() throws SQLException  {
		ArrayList<TopCustomers> topCustomers = DB.getTop3CustomersModel();
		return topCustomers;
	}
	
	public ArrayList<Tours> getTopTours() throws SQLException {
		ArrayList<Tours> topTours = DB.getTopTours();
		return topTours;
	}
	
	public ArrayList<Tours> getAllTours() throws SQLException {
		ArrayList<Tours> allTours = DB.getAllTours();
		return allTours;
	}
	
	public ArrayList<Booking> getAllBookings() throws SQLException {
		ArrayList<Booking> allBookings = DB.getAllBookings();
		return allBookings;
	}
	
}
