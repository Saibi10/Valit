package application;
import Models.Booking;
import Models.TopCustomers;
import Models.Tours;
import Models.TransportProvider;

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
	
	public ArrayList<TransportProvider> getAllTransportProviders() throws SQLException {
		ArrayList<TransportProvider> allTransportProvider = DB.getAllTransportProviders();
		return allTransportProvider;
	}
	
	public void updateTour(Tours tour) {
		DB.updateTour(tour);
	}
	
	public void addNewTour(Tours tour) {
		DB.addNewTour(tour);
	}
	
	public void deleteTour(String tour) {
		DB.deleteTourByID(tour);
	}
	
	public void confirmTourBooking(String booking) {
		DB.confirmBooking(booking);
	}
	
	public ArrayList<Tours> getTopTours2() throws SQLException {
		ArrayList<Tours> topTours = DB.getTopTours2();
		return topTours;
	}
	
//	public ArrayList<MyBooking> getBookingsByUserId(int userId) throws SQLException {
//	    ArrayList<MyBooking> userBookings = DB.getMyBookingsByUserId(userId);
//	    return userBookings;
//	}
	
}
