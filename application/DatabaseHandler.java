package application;
import Models.TopCustomers;
import Models.Tours;
import Models.TransportProvider;
import Models.Booking;
import Models.CustomerCareMessage;
import Models.MyBooking;
import Models.Request;

import java.lang.invoke.MethodHandle;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.util.ArrayList;

public class DatabaseHandler {
	
	Connection con;
    Statement st;
    
    
    public DatabaseHandler() throws SQLException {
		 DriverManager.registerDriver(new SQLServerDriver()); 
		 String url = "jdbc:sqlserver://127.0.0.1;instanceName=SQLEXPRESS;databaseName=TMS;encrpt=true;trustServerCertificate=true";
		 con = DriverManager.getConnection(url, "sa", "123"); 
		 st = con.createStatement();
		 System.out.println("Connected");

    }
    
    public ArrayList<TopCustomers> getTop3CustomersModel() {
        ArrayList<TopCustomers> topCustomers = new ArrayList<>();
        String query = 
            "SELECT TOP 5 u.FullName, COUNT(b.ID) AS NumberOfBookings, SUM(t.TourPrice) AS TotalAmountPaid " +
            "FROM Booking b " +
            "JOIN Users u ON b.UserID = u.UserID " +
            "JOIN Tour t ON b.TourID = t.TourID " +
            "GROUP BY u.FullName " +
            "ORDER BY NumberOfBookings DESC, TotalAmountPaid DESC";

        try (ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                TopCustomers customer = new TopCustomers(rs.getString("FullName") ,rs.getString("NumberOfBookings") ,rs.getString("TotalAmountPaid" ));
                topCustomers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topCustomers;
    }
    
 // Method to fetch top tours based on bookings and average rating
    public ArrayList<Tours> getTopTours() throws SQLException {
        ArrayList<Tours> topToursList = new ArrayList<>();
        
        String query = "SELECT TOP 5" +
                       "t.TourName, " +
                       "COUNT(b.ID) AS Bookings, " +
                       "AVG(b.Rating) AS AverageRating " +
                       "FROM Booking b " +
                       "JOIN Tour t ON b.TourID = t.TourID " +
                       "GROUP BY t.TourName " +
                       "ORDER BY Bookings DESC, AverageRating DESC ";
        
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            String tourName = rs.getString("TourName");
            String bookings = rs.getString("Bookings");
            String rating = rs.getString("AverageRating");
            
            topToursList.add(new Tours(tourName, bookings, rating));
        }
        
        return topToursList;
    }
    
    public ArrayList<Tours> getAllTours() throws SQLException {
        ArrayList<Tours> toursList = new ArrayList<>();

        // Updated query to join with TourImages and aggregate images
        String query = "SELECT t.TourID, " +
                       "t.TourName, " +
                       "t.TourPrice, " +
                       "t.TransportID, " +
                       "t.StartDate, " +
                       "CAST(t.TourDescription AS NVARCHAR(MAX)) AS TourDescription, " +
                       "t.Duration, " +
                       "CAST(t.GoogleMapLink AS NVARCHAR(MAX)) AS GoogleMapLink, " +
                       "COUNT(b.ID) AS Bookings, " +
                       "AVG(CAST(b.Rating AS FLOAT)) AS AverageRating, " +
                       "STRING_AGG(CAST(ti.ImagePath AS NVARCHAR(MAX)), ',') AS TourImages " + // Aggregate image paths
                       "FROM Tour t " +
                       "LEFT JOIN Booking b ON t.TourID = b.TourID " +
                       "LEFT JOIN TourImages ti ON t.TourID = ti.TourID " + // Join with TourImages
                       "GROUP BY t.TourID, " +
                       "t.TourName, " +
                       "t.TourPrice, " +
                       "t.TransportID, " +
                       "t.StartDate, " +
                       "t.Duration, " +
                       "CAST(t.TourDescription AS NVARCHAR(MAX)), " +
                       "CAST(t.GoogleMapLink AS NVARCHAR(MAX));";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
        	String TourID = rs.getString("TourID");
            String tourName = rs.getString("TourName");
            String bookings = rs.getString("Bookings");
            String rating = rs.getString("AverageRating");
            String tourDescription = rs.getString("TourDescription");
            String tourPrice = rs.getString("TourPrice");
            String transportID = rs.getString("TransportID");
            String startDate = rs.getString("StartDate");
            String duration = rs.getString("Duration");
            String googleMapLink = rs.getString("GoogleMapLink");
            String tourImages = rs.getString("TourImages");

            // Split the aggregated image paths into a list
            ArrayList<String> imagesList = new ArrayList<>();
            if (tourImages != null) {
                String[] imagesArray = tourImages.split(",");
                for (String image : imagesArray) {
                    imagesList.add(image.trim());
                }
            }

            // Create a Tours object and add it to the list
            Tours tour = new Tours(TourID,tourName, bookings, rating, tourDescription, tourPrice, transportID, startDate, duration, googleMapLink , imagesList);
            toursList.add(tour);
        }

        return toursList;
    }

    public ArrayList<Booking> getAllBookings() {
        ArrayList<Booking> bookingsList = new ArrayList<>();
        String query = "SELECT b.ID, " +
                       "t.TourName, " +
                       "u.FullName AS Customer, " +
                       "b.BookingDate AS Date, " +
                       "b.Status " +
                       "FROM Booking b " +
                       "JOIN Tour t ON b.TourID = t.TourID " +
                       "JOIN Users u ON b.UserID = u.UserID";

        try (ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
            	String ID = rs.getString("ID");
                String tourName = rs.getString("TourName");
                String customerName = rs.getString("Customer");
                String date = rs.getString("Date");
                String status = rs.getString("Status");

                // Add the booking to the list
                bookingsList.add(new Booking(ID,tourName, customerName, date, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingsList;
    }

    public ArrayList<TransportProvider> getAllTransportProviders() {
        ArrayList<TransportProvider> transportProvidersList = new ArrayList<>();

        String query = "SELECT ID ,Name, Rating, FleetSize, Contact, VehicleTypes FROM TransportProvider";

        try (ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
            	String ID = rs.getString("ID");
                String name = rs.getString("Name");
                String rating = rs.getString("Rating");
                String fleetSize = rs.getString("FleetSize");
                String contact = rs.getString("Contact");
                String vehicleTypes = rs.getString("VehicleTypes");

                // Create a TransportProvider object and add it to the list
                TransportProvider transportProvider = new TransportProvider(ID,name, rating, fleetSize, contact, vehicleTypes);
                transportProvidersList.add(transportProvider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transportProvidersList;
    }
    
    public boolean updateTour(Tours tour) {
        String updateQuery = "UPDATE Tour SET " +
                             "TourName = ?, " +
                             "TourPrice = ?, " +
                             "TransportID = ?, " +
                             "StartDate = ?, " +
                             "TourDescription = ?, " +
                             "Duration = ?, " +
                             "GoogleMapLink = ? " +
                             "WHERE TourID = ?";

        String deleteImagesQuery = "DELETE FROM TourImages WHERE TourID = ?";
        String insertImagesQuery = "INSERT INTO TourImages (TourID, ImagePath) VALUES (?, ?)";
        
        System.out.println("Tour GoogleMapLink: " + tour.getGoogleMapLink());
        
        try {
            // Start a transaction
            con.setAutoCommit(false);

            // Update the Tour table
            try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                updateStmt.setString(1, tour.getTourName());
                updateStmt.setString(2, tour.getPrice());
                updateStmt.setString(3, tour.getTransportID());
                updateStmt.setString(4, tour.getStartDate());
                updateStmt.setString(5, tour.getTourDescription());
                updateStmt.setString(6, tour.getDuration());
                updateStmt.setString(7, tour.getGoogleMapLink());
                updateStmt.setString(8, tour.getTourID());

                updateStmt.executeUpdate();
            }
            
        
            // Delete existing images for the tour
            try (PreparedStatement deleteStmt = con.prepareStatement(deleteImagesQuery)) {
                deleteStmt.setString(1, tour.getTourID());
                deleteStmt.executeUpdate();
            }

            // Insert new images for the tour
            try (PreparedStatement insertStmt = con.prepareStatement(insertImagesQuery)) {
                for (String imagePath : tour.getTourImages()) {
                    insertStmt.setString(1, tour.getTourID());
                    insertStmt.setString(2, imagePath);
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch();
            }

            // Commit the transaction
            con.commit();
            return true;
        } catch (SQLException e) {
            try {
                con.rollback(); // Roll back the transaction in case of an error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                con.setAutoCommit(true); // Restore auto-commit mode
            } catch (SQLException autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }
    }
    
    public boolean addNewTour(Tours tour) {
        String insertTourQuery = "INSERT INTO Tour (TourName, TourPrice, TransportID, StartDate, TourDescription, Duration, GoogleMapLink) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertImagesQuery = "INSERT INTO TourImages (TourID, ImagePath) VALUES (?, ?)";

        try {
            con.setAutoCommit(false);

            // Step 1: Insert into Tour table
            int generatedTourID;
            try (PreparedStatement insertTourStmt = con.prepareStatement(insertTourQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertTourStmt.setString(1, tour.getTourName());
                insertTourStmt.setString(2, tour.getPrice());
                insertTourStmt.setString(3, tour.getTransportID());
                insertTourStmt.setString(4, tour.getStartDate());
                insertTourStmt.setString(5, tour.getTourDescription());
                insertTourStmt.setString(6, tour.getDuration());
                insertTourStmt.setString(7, tour.getGoogleMapLink());

                int affectedRows = insertTourStmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating tour failed, no rows affected.");
                }

                // Get the generated TourID
                try (ResultSet generatedKeys = insertTourStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedTourID = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating tour failed, no ID obtained.");
                    }
                }
            }

            // Step 2: Insert into TourImages table
            try (PreparedStatement insertImagesStmt = con.prepareStatement(insertImagesQuery)) {
                for (String imagePath : tour.getTourImages()) {
                    insertImagesStmt.setInt(1, generatedTourID);
                    insertImagesStmt.setString(2, imagePath);
                    insertImagesStmt.addBatch();
                }
                insertImagesStmt.executeBatch();
            }

            // Commit the transaction
            con.commit();
            return true;
        } catch (SQLException e) {
            try {
                con.rollback(); // Roll back in case of error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                con.setAutoCommit(true); // Restore auto-commit mode
            } catch (SQLException autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }
    }
    
    public boolean deleteTourByID(String tourID) {
        String deleteImagesQuery = "DELETE FROM TourImages WHERE TourID = ?";
        String deleteTourQuery = "DELETE FROM Tour WHERE TourID = ?";

        try {
            con.setAutoCommit(false);

            // Step 1: Delete associated images from TourImages
            try (PreparedStatement deleteImagesStmt = con.prepareStatement(deleteImagesQuery)) {
                deleteImagesStmt.setString(1, tourID);
                deleteImagesStmt.executeUpdate();
            }

            // Step 2: Delete the tour from the Tour table
            try (PreparedStatement deleteTourStmt = con.prepareStatement(deleteTourQuery)) {
                deleteTourStmt.setString(1, tourID);
                int rowsAffected = deleteTourStmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("No tour found with ID: " + tourID);
                }
            }

            // Commit the transaction
            con.commit();
            return true;
        } catch (SQLException e) {
            try {
                con.rollback(); // Roll back in case of an error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                con.setAutoCommit(true); // Restore auto-commit mode
            } catch (SQLException autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }
    }

    public boolean confirmBooking(String bookingID) {
        String updateQuery = "UPDATE Booking SET Status = 'Confirmed' WHERE ID = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            // Set the booking ID parameter
            updateStmt.setString(1, bookingID);

            // Execute the update query
            int rowsAffected = updateStmt.executeUpdate();

            // Check if any rows were updated
            if (rowsAffected > 0) {
                System.out.println("Booking confirmed successfully for ID: " + bookingID);
                return true;
            } else {
                System.out.println("No booking found with ID: " + bookingID);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Tours> getTopTours2() throws SQLException {
        ArrayList<Tours> topToursList = new ArrayList<>();

        String query = "SELECT TOP 5 " +
                       "t.TourName, " +
                       "t.TourPrice, " +
                       "t.Duration, " +
                       "tp.TransportType, " +
                       "COUNT(b.ID) AS Bookings, " +
                       "AVG(b.Rating) AS AverageRating " +
                       "FROM Booking b " +
                       "JOIN Tour t ON b.TourID = t.TourID " +
                       "JOIN TransportProvider tp ON t.TransportID = tp.ID " +
                       "WHERE b.Status = 'Completed' " +
                       "GROUP BY t.TourName, t.TourPrice, t.Duration, tp.TransportType " +
                       "ORDER BY Bookings DESC, AverageRating DESC";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String tourName = rs.getString("TourName");
            String price = rs.getString("TourPrice");
            String duration = rs.getString("Duration");
            String transportType = rs.getString("TransportType");

            topToursList.add(new Tours(tourName, price, duration, transportType));
        }

        return topToursList;
    }
    
    public ArrayList<MyBooking> getMyBookingsByUserId(int userId) {
        ArrayList<MyBooking> myBookingsList = new ArrayList<>();

        // Query to join Booking and Tour tables to get tour information based on UserID
        String query = "SELECT " +
                "b.ID, " +
                "t.TourName, " +
                "b.BookingDate, " +
                "t.TourPrice, " +
                "t.StartDate, " +
                "t.TourDescription, " +
                "b.Rating " + // Include Rating in the query
                "FROM Booking b " +
                "JOIN Tour t ON b.TourID = t.TourID " +
                "WHERE b.UserID = ? AND b.status != 'Completed'";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId); // Set the UserID parameter in the query

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int bookingId = rs.getInt("ID");
                    String tourName = rs.getString("TourName");
                    String bookingDate = rs.getString("BookingDate");
                    double tourPrice = rs.getDouble("TourPrice");
                    String startDate = rs.getString("StartDate");
                    String tourDescription = rs.getString("TourDescription");
                    int rating = rs.getInt("Rating"); // Get the Rating

                    // Create a MyBooking object and add it to the list
                    MyBooking myBooking = new MyBooking(bookingId, tourName, bookingDate, tourPrice, startDate, tourDescription, rating);
                    myBookingsList.add(myBooking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myBookingsList;
    }


    public ArrayList<MyBooking> getBookingsHistoryByUserId(int userId) {
        ArrayList<MyBooking> myBookingsList = new ArrayList<>();

        // Query to join Booking and Tour tables to get tour information based on UserID
        String query = "SELECT " +
                "b.ID, " +
                "t.TourName, " +
                "b.BookingDate, " +
                "t.TourPrice, " +
                "t.StartDate, " +
                "t.TourDescription, " +
                "b.Rating " + // Include Rating in the query
                "FROM Booking b " +
                "JOIN Tour t ON b.TourID = t.TourID " +
                "WHERE b.UserID = ? AND b.status = 'Completed'";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId); // Set the UserID parameter in the query

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int bookingId = rs.getInt("ID");
                    String tourName = rs.getString("TourName");
                    String bookingDate = rs.getString("BookingDate");
                    double tourPrice = rs.getDouble("TourPrice");
                    String startDate = rs.getString("StartDate");
                    String tourDescription = rs.getString("TourDescription");
                    int rating = rs.getInt("Rating"); // Get the Rating

                    // Create a MyBooking object and add it to the list
                    MyBooking myBooking = new MyBooking(bookingId, tourName, bookingDate, tourPrice, startDate, tourDescription, rating);
                    myBookingsList.add(myBooking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myBookingsList;
    }

    
    public void deleteBooking(int id) {
        String query = "DELETE FROM Booking WHERE ID = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Set the BookingId parameter in the query
            ps.setInt(1, id);

            // Execute the delete operation
            int rowsAffected = ps.executeUpdate();

            // Optionally, confirm deletion
            if (rowsAffected > 0) {
                System.out.println("Booking with ID " + id + " was successfully deleted.");
            } else {
                System.out.println("No booking found with ID " + id + ".");
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            System.out.println("Error deleting booking with ID " + id + ": " + e.getMessage());
        }
    }
    
    public void changeRating(int id, int rating) {
        String query = "UPDATE Booking SET Rating = ? WHERE ID = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Validate the rating input
            if (rating < 1 || rating > 5) {
                System.out.println("Invalid rating. Please provide a value between 1 and 5.");
                return;
            }

            // Set parameters in the query
            ps.setInt(1, rating); // Set the new rating
            ps.setInt(2, id);     // Set the BookingId

            // Execute the update
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Rating " + rating + "updated successfully for Booking ID " + id + ".");
            } else {
                System.out.println("No booking found with Booking ID " + id + ".");
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            System.out.println("Error updating rating for Booking ID " + id + ": " + e.getMessage());
        }
    }
    
    public ArrayList<Request> getMyRequestsByUserId(int userId) {
        ArrayList<Request> myRequestsList = new ArrayList<>();

        // Query to get request information based on UserID and include Response
        String query = "SELECT " +
                "r.RequestID, " +
                "r.Location, " +
                "r.Description, " +
                "r.Status, " +
                "r.CreatedAt, " +
                "r.Response " + // Include Response in the query
                "FROM Request r " +
                "WHERE r.UserID = ? AND r.Status != 'Completed'"; // Filter out completed requests

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId); // Set the UserID parameter in the query

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String requestID = rs.getString("RequestID");
                    String location = rs.getString("Location");
                    String description = rs.getString("Description");
                    String status = rs.getString("Status");
                    String createdAt = rs.getString("CreatedAt");
                    String response = rs.getString("Response"); // Fetch the Response from the ResultSet

                    // Create a Request object with the response and add it to the list
                    Request request = new Request(requestID, location, description, status, createdAt, response);
                    myRequestsList.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myRequestsList;
    }


    
    public boolean insertRequest(int userId, String location, String description) {
        // SQL query to insert a new request into the Request table
        String query = "INSERT INTO Request (UserID, Location, Description, Status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Set the parameters for the query
            ps.setInt(1, userId);       // Set the UserID
            ps.setString(2, location);  // Set the Location
            ps.setString(3, description); // Set the Description
            ps.setString(4, "Pending");    // Set the Status to 'Pending'

            // Execute the insert query
            int rowsAffected = ps.executeUpdate();
            
            // Return true if the insert was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    
    public boolean deleteRequest(String requestId) {
        // SQL query to delete a request from the Request table where RequestID matches
        String query = "DELETE FROM Request WHERE RequestID = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Set the parameter for the query as a string
            ps.setString(1, requestId);  // Set the RequestID

            // Execute the delete query
            int rowsAffected = ps.executeUpdate();

            // Return true if the delete was successful (at least one row affected)
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }

        return false; // Return false if the operation failed
    }

    
    public boolean deleteCustomerCareMessage(int messageId) {
        // SQL query to delete a message from the CustomerCareMessage table where ID matches
        String query = "DELETE FROM CustomerCareMessage WHERE ID = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Set the parameter for the query as an integer
            ps.setInt(1, messageId);  // Set the ID

            // Execute the delete query
            int rowsAffected = ps.executeUpdate();

            // Return true if the delete was successful (at least one row affected)
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }

        return false; // Return false if the operation failed
    }

    
    public ArrayList<CustomerCareMessage> getCustomerCareMessagesByUserId(int userId) {
        ArrayList<CustomerCareMessage> messagesList = new ArrayList<>();

        // Query to fetch customer care messages based on UserID and exclude completed messages
        String query = "SELECT " +
                "ID, " +
                "UserID, " +
                "Title, " +
                "Message, " +
                "Response, " +
                "Status, " +
                "DateTime " + // Include DateTime in the query
                "FROM CustomerCareMessage " +
                "WHERE UserID = ? AND Status != 'Completed'"; // Filter out completed messages

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId); // Set the UserID parameter in the query

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int retrievedUserId = rs.getInt("UserID");
                    String title = rs.getString("Title");
                    String message = rs.getString("Message");
                    String response = rs.getString("Response");
                    String status = rs.getString("Status");
                    String dateTime = rs.getString("DateTime");

                    // Create a CustomerCareMessage object and add it to the list
                    CustomerCareMessage customerCareMessage = new CustomerCareMessage(
                            id, retrievedUserId, title, message, response, status, dateTime
                    );
                    messagesList.add(customerCareMessage);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messagesList;
    }




}
