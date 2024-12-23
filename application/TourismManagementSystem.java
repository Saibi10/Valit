package application;
import Models.Booking;
import Models.CustomerCareMessage;
import Models.MyBooking;
import Models.Request;
import Models.TopCustomers;
import Models.Tours;
import Models.TransportProvider;
import Models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.stage.Stage;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;  // Import the correct Button class

public class TourismManagementSystem {
	
	private DatabaseHandler DB;
	

	TourismManagementSystem(Stage primaryStage) throws SQLException, IOException  {
		DB = new DatabaseHandler();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Home.fxml"));
		loader.setControllerFactory(param -> {
			try {
				return new LoginController(this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return param;
		});
		Parent root = loader.load();
		Scene scene = new Scene(root,1200,800);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public ArrayList<TopCustomers> getTop3Customers() throws SQLException  {
		ArrayList<TopCustomers> topCustomers = DB.getTop3CustomersModel();
		return topCustomers;
	}
	
	public ArrayList<Tours> getTopTours() throws SQLException {
		ArrayList<Tours> topTours = DB.getTopTours();
		return topTours;
	}
	
	//--------------------------------Tours Actions--------------------------------\\
	
	public ArrayList<Tours> getAllTours() throws SQLException {
		ArrayList<Tours> allTours = DB.getAllTours();
		return allTours;
	}
	
	public ArrayList<Tours> getAllTours2() throws SQLException {
		ArrayList<Tours> allTours = DB.getAllTours2();
		return allTours;
	}
	
	public ArrayList<Tours> getAllToursSearch(String searchText) throws SQLException {
		ArrayList<Tours> allTours = DB.getAllTours();
		
		ArrayList<Tours> matchingTours = new ArrayList<>();

		// Filter tours based on the search text (best match based on startsWith)
		for (Tours tour : allTours) {
			if (tour.getTourName().toLowerCase().startsWith(searchText.toLowerCase())) {
				matchingTours.add(tour);
			}
		}
		return matchingTours;
	}
	
	public ArrayList<Tours> getAllCompleted() throws SQLException {
		ArrayList<Tours> allTours = DB.getAllTours();
		
		LocalDate today = LocalDate.now();
		ArrayList<Tours> completedTours = new ArrayList<>();

		for (Tours tour : allTours) {
			LocalDate startDate = LocalDate.parse(tour.getStartDate()); // Assuming startDate is in ISO format
																		// (yyyy-MM-dd)
			int durationDays = Integer.parseInt(tour.getDuration());
			LocalDate endDate = startDate.plusDays(durationDays);

			// Add to completed tours if the end date is before today
			if (endDate.isBefore(today)) {
				completedTours.add(tour);
			}
		}
		
		return completedTours;
	}
	
	public ArrayList<Tours> getAllToursActive() throws SQLException {
		ArrayList<Tours> allTours = DB.getAllTours();
		
		// Get the current date
		LocalDate today = LocalDate.now();

		// Filter tours with start dates in the future
		ArrayList<Tours> futureTours = new ArrayList<>();
		for (Tours tour : allTours) {
			LocalDate startDate = LocalDate.parse(tour.getStartDate()); // Assuming startDate is in ISO format
																		// (yyyy-MM-dd)
			if (startDate.isAfter(today)) { // Check if the start date is after today
				futureTours.add(tour);
			}
		}
		
		return futureTours;
	}
	
	//-----------------------------------------------------------------------------\\
	
	//--------------------------------Tours Bookings--------------------------------\\
	
	public ArrayList<Booking> getAllBookings() throws SQLException {
		ArrayList<Booking> allBookings = DB.getAllBookings();
		return allBookings;
	}
	
	public ArrayList<Booking> getAllBookingsSearch(String searchText) throws SQLException {
		ArrayList<Booking> allBookings = DB.getAllBookings();
		
		ArrayList<Booking> matchingTours = new ArrayList<>();

		// Filter tours based on the search text (best match based on startsWith)
		for (Booking tour : allBookings) {
			if (tour.getCustomer().toLowerCase().startsWith(searchText.toLowerCase())) {
				matchingTours.add(tour);
			}
		}

		
		return allBookings;
	}
	
	public ArrayList<Booking> getAllBookingsStatus(String status) throws SQLException {
		ArrayList<Booking> allBookings = DB.getAllBookings();
		
		ArrayList<Booking> filteredBookings = allBookings.stream()
				.filter(booking -> status.equalsIgnoreCase(booking.getStatus()))
				.collect(Collectors.toCollection(ArrayList::new));
		
		return filteredBookings;
	}
	
	//-----------------------------------------------------------------------------\\
	
	public ArrayList<TransportProvider> getAllTransportProviders() throws SQLException {
		ArrayList<TransportProvider> allTransportProvider = DB.getAllTransportProviders();
		return allTransportProvider;
	}
	
	public ArrayList<TransportProvider> getAllTransportProvidersSearch(String searchText) throws SQLException {
		ArrayList<TransportProvider> allTransportProvider = DB.getAllTransportProviders();
		ArrayList<TransportProvider> matchingTours = new ArrayList<>();

		// Filter tours based on the search text (best match based on startsWith)
		for (TransportProvider tour : allTransportProvider) {
			if (tour.getName().toLowerCase().startsWith(searchText.toLowerCase())) {
				matchingTours.add(tour);
			}
		}
		
		return matchingTours;
	}
	
	
	public Boolean updateTour(Tours tour) {
		Boolean check = DB.updateTour(tour);
		return check;
	}
	
	public Boolean addNewTour(Tours tour) {
		Boolean check = DB.addNewTour(tour);
		return check;
	}
	
	public Boolean deleteTour(String tour) {
		Boolean check = DB.deleteTourByID(tour);
		return check;
	}
	
	public Boolean confirmTourBooking(String booking) {
		Boolean check = DB.confirmBooking(booking);
		return check;
	}
	
	public Boolean cancelTourBooking(String booking) {
		Boolean check = DB.cancelBooking(booking);
		return check;
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
	
	public void addSupportMessage(int uId, String title, String detail)
	{
		DB.addSupportMessage(uId, title, detail);
	}
	
	public void joinTour(int uId, int tId)
	{
		DB.joinTour(uId, tId);
	}
	
	public boolean authenticateUser(String email, String pass, int[] userId , ActionEvent event)
	{
		int auth = DB.authenticateUser(email, pass, userId);
		
		if (auth == 1) { 
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Admin.fxml"));
                loader.setControllerFactory(param -> {
					try {
						return new AdminController(this);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return param;
				});
                
                Parent root = loader.load();

                AdminController adminController = loader.getController();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else if (auth == 2) { // Customer
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/User.fxml"));
                loader.setControllerFactory(param -> {
					try {
						return new UserController(this);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return param;
				});
                Parent root = loader.load();

                // Pass the UserID to the next controller if needed
                UserController userController = loader.getController();
                userController.setUserId(userId[0]);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
	
	}
	
	public void addNewUser(String email, String pass, String fullName)
	{
		DB.addNewUser(email, pass, fullName);
	}
	public void deleteTransportProvider(String ID) {
		DB.deleteTransportProvider(ID);
	}
	
	public ArrayList<Request> getAllRequests() {
		ArrayList<Request>  req = DB.getAllRequests();
		return req;
	}
	
	public ArrayList<Request> getAllRequestsStatus(String status) {
		ArrayList<Request>  req = DB.getAllRequests();
		ArrayList<Request> filteredRequests = req.stream()
				.filter(request -> status.equalsIgnoreCase(request.getStatus()))
				.collect(Collectors.toCollection(ArrayList::new));
		
		return filteredRequests;
	}
	
	public ArrayList<CustomerCareMessage> getAllCustomerCareMessage() {
		ArrayList<CustomerCareMessage>  req = DB.getAllCustomerCareMessages();
		
		
		return req;
	}
	
	public ArrayList<CustomerCareMessage> getAllCustomerCareMessageStatus(String status) {
		ArrayList<CustomerCareMessage>  req = DB.getAllCustomerCareMessages();
		ArrayList<CustomerCareMessage> filteredreq = req.stream()
				.filter(booking -> status.equalsIgnoreCase(booking.getStatus()))
				.collect(Collectors.toCollection(ArrayList::new));
		
		return filteredreq;
	}
	
	public void updateRequests(Request req) {
		DB.updateRequest(req);
	}
	
	public boolean updateCustomerCareMessage(CustomerCareMessage req) {
		Boolean check = DB.updateCustomerCareMessage(req);
		return check;
	}
	
	public boolean markCompletedBookings() {
		Boolean check = DB.markCompletedBookings();
		return check;
	}
	
	public String[] getStats() {
		String[] stats = DB.getDashboardStatistics();
		return stats;
	}
	
	public void deleteBooking(int id) {
		DB.deleteBooking(id);
	}
	
	 public void changeRating(int id, int rating) {
		 DB.changeRating(id, rating);
	 }
	 
	 public ArrayList<Request> getRequestsByUserId(int userId) throws SQLException {
		    ArrayList<Request> userRequests = DB.getMyRequestsByUserId(userId);
		    return userRequests;
	}
	 
	 public ArrayList<CustomerCareMessage> getCustomerCareMessagesByUserId(int userId) throws SQLException {
		    // Delegate the retrieval to the database handler method
		    ArrayList<CustomerCareMessage> userMessages = DB.getCustomerCareMessagesByUserId(userId);
		    return userMessages;
		}

	 public boolean insertRequest(int userId, String location, String description) throws SQLException {
		    boolean isInserted = DB.insertRequest(userId, location, description);
		    return isInserted;
		}

	 public boolean deleteRequest(String reqID) throws SQLException {
		    boolean isDeleted = DB.deleteRequest(reqID);
		    return isDeleted;
		}

	 public boolean deleteCustomerCareMessage(String messageId) throws SQLException {
		    // Delegate the deletion to the database handler method
		    boolean isDeleted = DB.deleteCustomerCareMessage(messageId);
		    return isDeleted;
		}
	
	 public User getUser(int userId) {
		    return DB.getUserById(userId);
		}
	 
	 public boolean updateUser(User user) {
		    return DB.updateUser(user);
		}
	 
	 public double getWalletBalance(int userID) {
		    return DB.getWalletBalance(userID);
		}
	 
	 public boolean setWalletBalance(int userID, double money) {
		    return DB.setWalletBalance(userID, money);
		}



}
