package application;
import Models.Booking;
import Models.CustomerCareMessage;
import Models.MyBooking;
import Models.Request;
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
	
	public void addNewTransportProvider(TransportProvider tp) {
		DB.addNewTransportProvider(tp);
	}
	
	public void updateTransportProvider(TransportProvider tp) {
		DB.updateTransportProvider(tp);
	}
	
	public ArrayList<MyBooking> getBookingsByUserId(int userId) throws SQLException {
	    ArrayList<MyBooking> userBookings = DB.getMyBookingsByUserId(userId);
	    return userBookings;
	}
	
	public ArrayList<MyBooking> getBookingsHistoryByUserId(int userId) throws SQLException {
	    ArrayList<MyBooking> userBookings = DB.getBookingsHistoryByUserId(userId);
	    return userBookings;
	}
	
	public void deleteTransportProvider(String ID) {
		DB.deleteTransportProvider(ID);
	}
	
	public ArrayList<Request> getAllRequests() {
		ArrayList<Request>  req = DB.getAllRequests();
		return req;
	}
	
	public ArrayList<CustomerCareMessage> getAllCustomerCareMessage() {
		ArrayList<CustomerCareMessage>  req = DB.getAllCustomerCareMessages();
		return req;
	}
	
	public void updateRequests(Request req) {
		DB.updateRequest(req);
	}
	
	public void updateCustomerCareMessage(CustomerCareMessage req) {
		DB.updateCustomerCareMessage(req);
	}
	
}
