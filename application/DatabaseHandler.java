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
		 String url = "jdbc:sqlserver://127.0.0.1;instanceName=HUSSNAINMUGHAL;databaseName=TMS;encrpt=true;trustServerCertificate=true";
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
    
    public boolean cancelBooking(String bookingID) {
        String updateQuery = "UPDATE Booking SET Status = 'Cancelled' WHERE ID = ?";

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
                       "t.TourID, " +
                       "t.TourName, " +
                       "t.TourPrice, " +
                       "t.Duration, " +
                       "t.TourDescription, " +
                       "t.StartDate, " +
                       "COUNT(b.ID) AS Bookings, " +
                       "AVG(b.Rating) AS AverageRating " +
                       "FROM Booking b " +
                       "JOIN Tour t ON b.TourID = t.TourID " +
                       "GROUP BY t.TourID, t.TourName, t.TourPrice, t.Duration, t.TourDescription, t.StartDate " +
                       "ORDER BY Bookings DESC, AverageRating DESC";
        
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
        	// Assuming you fetch data from ResultSet
        	String tourID = Integer.toString(rs.getInt("TourID"));
        	String tourPrice = Double.toString(rs.getDouble("TourPrice"));
        	String duration = Integer.toString(rs.getInt("Duration"));
        	String startDate = rs.getDate("StartDate").toString(); // Converts Date to String
        	String tourName = rs.getString("TourName");
        	String tourDescription = rs.getString("TourDescription");

        	Tours tour = new Tours(tourID, tourName, tourPrice, duration, tourDescription, startDate);

            topToursList.add(tour);
        }
        
        return topToursList;
    }
    
    public ArrayList<Tours> getAllTours2() throws SQLException {
        ArrayList<Tours> toursList = new ArrayList<>();
        
        String query = "SELECT " +
                       "TourID, " +
                       "TourName, " +
                       "TourPrice, " +
                       "Duration, " +
                       "TourDescription, " +
                       "StartDate " +
                       "FROM Tour " +
                       "ORDER BY TourID"; // Optional ordering by TourID

        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            // Convert fetched data to Strings
            String tourID = Integer.toString(rs.getInt("TourID"));
            String tourPrice = Double.toString(rs.getDouble("TourPrice"));
            String duration = Integer.toString(rs.getInt("Duration"));
            String startDate = rs.getDate("StartDate").toString(); // Converts Date to String
            String tourName = rs.getString("TourName");
            String tourDescription = rs.getString("TourDescription");

            // Create a Tours object
            Tours tour = new Tours(tourID, tourName, tourPrice, duration, tourDescription, startDate);

            // Add it to the list
            toursList.add(tour);
        }
        
        return toursList;
    }

    public boolean addNewTransportProvider(TransportProvider provider) {
        String insertQuery = "INSERT INTO TransportProvider (Name, Rating, FleetSize, Contact, VehicleTypes) " +
                             "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            // Set the parameters using the TransportProvider object
            insertStmt.setString(1, provider.getName());
            insertStmt.setString(2, provider.getRating());
            insertStmt.setString(3, provider.getFleetSize());
            insertStmt.setString(4, provider.getContact());
            insertStmt.setString(5, provider.getVehicleTypes());

            // Execute the insert query
            int rowsAffected = insertStmt.executeUpdate();

            // Check if the insert was successful
            if (rowsAffected > 0) {
                System.out.println("Transport provider added successfully.");
                return true;
            }
            System.out.println("Failed to add transport provider.");
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateTransportProvider(TransportProvider provider) {
        String updateQuery = "UPDATE TransportProvider SET " +
                             "Name = ?, " +
                             "Rating = ?, " +
                             "FleetSize = ?, " +
                             "Contact = ?, " +
                             "VehicleTypes = ? " +
                             "WHERE ID = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            // Set the parameters using the TransportProvider object
            updateStmt.setString(1, provider.getName());
            updateStmt.setString(2, provider.getRating());
            updateStmt.setString(3, provider.getFleetSize());
            updateStmt.setString(4, provider.getContact());
            updateStmt.setString(5, provider.getVehicleTypes());
            updateStmt.setString(6, provider.getID()); // Use ID to locate the record

            // Execute the update query
            int rowsAffected = updateStmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Transport provider updated successfully.");
                return true;
            } else {
                System.out.println("No transport provider found with ID: " + provider.getID());
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<MyBooking> getMyBookingsByUserId(int userId) {
        ArrayList<MyBooking> myBookingsList = new ArrayList<>();

        // Query to join Booking and Tour tables to get tour information based on UserID
        String query = "SELECT " +
                "t.TourName, " +
                "b.BookingDate, " +
                "t.TourPrice, " +
                "t.StartDate, " +
                "t.TourDescription " +
                "FROM Booking b " +
                "JOIN Tour t ON b.TourID = t.TourID " +
                "WHERE b.UserID = ? AND b.status != 'Completed'"; // Add the status condition


        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId); // Set the UserID parameter in the query

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String tourName = rs.getString("TourName");
                    String bookingDate = rs.getString("BookingDate");
                    double tourPrice = rs.getDouble("TourPrice");
                    String startDate = rs.getString("StartDate");
                    String tourDescription = rs.getString("TourDescription");

                    // Create a MyBooking object and add it to the list
                    MyBooking myBooking = new MyBooking(tourName, bookingDate, tourPrice, startDate, tourDescription);
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
                "t.TourName, " +
                "b.BookingDate, " +
                "t.TourPrice, " +
                "t.StartDate, " +
                "t.TourDescription " +
                "FROM Booking b " +
                "JOIN Tour t ON b.TourID = t.TourID " +
                "WHERE b.UserID = ? AND b.status = 'Completed'"; // Add the status condition


        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId); // Set the UserID parameter in the query

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String tourName = rs.getString("TourName");
                    String bookingDate = rs.getString("BookingDate");
                    double tourPrice = rs.getDouble("TourPrice");
                    String startDate = rs.getString("StartDate");
                    String tourDescription = rs.getString("TourDescription");

                    // Create a MyBooking object and add it to the list
                    MyBooking myBooking = new MyBooking(tourName, bookingDate, tourPrice, startDate, tourDescription);
                    myBookingsList.add(myBooking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myBookingsList;
    }

    public boolean deleteTransportProvider(String id) {
        String deleteQuery = "DELETE FROM TransportProvider WHERE ID = ?";

        try (PreparedStatement deleteStmt = con.prepareStatement(deleteQuery)) {
            // Set the ID parameter
            deleteStmt.setString(1, id);

            // Execute the delete query
            int rowsAffected = deleteStmt.executeUpdate();

            // Check if the deletion was successful
            if (rowsAffected > 0) {
                System.out.println("Transport provider deleted successfully for ID: " + id);
                return true;
            } else {
                System.out.println("No transport provider found with ID: " + id);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Request> getAllRequests() {
        ArrayList<Request> requestsList = new ArrayList<>();
        String query = "SELECT r.RequestID, u.FullName AS CustomerName, r.Location, r.Description, " +
                       "r.Status, r.CreatedAt, r.Response " +
                       "FROM Request r " +
                       "JOIN Users u ON r.UserID = u.UserID";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Extract data from the result set
                String requestID = rs.getString("RequestID");
                String customerName = rs.getString("CustomerName");
                String location = rs.getString("Location");
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                String createdAt = rs.getString("CreatedAt");
                String response = rs.getString("Response");

                // Create a Request object
                Request request = new Request(customerName,requestID, location, description, status, createdAt, response);

                // Add it to the list
                requestsList.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requestsList;
    }

    public boolean updateRequest(Request request) {
        String updateQuery = "UPDATE Request SET " +
                             "Location = ?, " +
                             "Description = ?, " +
                             "Status = ?, " +
                             "Response = ? " +
                             "WHERE RequestID = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            // Set the parameters using the Request object
            updateStmt.setString(1, request.getLocation());
            updateStmt.setString(2, request.getDescription());
            updateStmt.setString(3, request.getStatus());
            updateStmt.setString(4, request.getResponse());
            updateStmt.setString(5, request.getRequestID());

            // Execute the update query
            int rowsAffected = updateStmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Request updated successfully for ID: " + request.getRequestID());
                return true;
            } else {
                System.out.println("No request found with ID: " + request.getRequestID());
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 	
    public ArrayList<CustomerCareMessage> getAllCustomerCareMessages() {
        ArrayList<CustomerCareMessage> messagesList = new ArrayList<>();
        String query = "SELECT c.ID, u.FullName AS CustomerName, c.Title, c.Message, " +
                       "c.Response, c.Status, c.DateTime " +
                       "FROM CustomerCareMessage c " +
                       "JOIN Users u ON c.UserID = u.UserID";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Extract data from the result set
                String id = rs.getString("ID"); // Retrieve ID as a String
                String customerName = rs.getString("CustomerName");
                String title = rs.getString("Title");
                String message = rs.getString("Message");
                String response = rs.getString("Response");
                String status = rs.getString("Status");
                String dateTime = rs.getString("DateTime");

                // Create a CustomerCareMessage object
                CustomerCareMessage customerCareMessage = new CustomerCareMessage(id, customerName, status, title, message,  response);

                // Add it to the list
                messagesList.add(customerCareMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messagesList;
    }
    
    public boolean updateCustomerCareMessage(CustomerCareMessage message) {
        String updateQuery = "UPDATE CustomerCareMessage SET " +
                             "Title = ?, " +
                             "Message = ?, " +
                             "Response = ?, " +
                             "Status = ? " +
                             "WHERE ID = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            // Set the parameters using the CustomerCareMessage object
            updateStmt.setString(1, message.getTitle());
            updateStmt.setString(2, message.getMessage());
            updateStmt.setString(3, message.getResponse());
            updateStmt.setString(4, message.getStatus());
            updateStmt.setString(5, message.getID()); // Use the ID from the message object

            // Execute the update query
            int rowsAffected = updateStmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Customer care message updated successfully for ID: " + message.getID());
                return true;
            } else {
                System.out.println("No customer care message found with ID: " + message.getID());
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean markCompletedBookings() {
        String updateQuery = "UPDATE Booking " +
                             "SET Status = 'Completed' " +
                             "FROM Booking b " +
                             "JOIN Tour t ON b.TourID = t.TourID " +
                             "WHERE DATEADD(DAY, t.Duration, t.StartDate) < GETDATE() " +
                             "AND b.Status != 'Completed'";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            // Execute the update query
            int rowsAffected = updateStmt.executeUpdate();

            // Check if any rows were updated
            if (rowsAffected > 0) {
                System.out.println("Bookings marked as completed successfully. Rows updated: " + rowsAffected);
                return true;
            } else {
                System.out.println("No bookings found that need to be marked as completed.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String[] getDashboardStatistics() {
        String[] stats = new String[4]; // Array to hold the results
        String query = "SELECT " +
                       "(SELECT COUNT(*) FROM Users) AS TotalUsers, " +
                       "(SELECT COUNT(*) FROM Booking) AS TotalBookings, " +
                       "(SELECT SUM(t.TourPrice) FROM Booking b " +
                       " JOIN Tour t ON b.TourID = t.TourID) AS TotalAmount, " +
                       "(SELECT COUNT(*) FROM Tour WHERE StartDate > GETDATE()) AS ActiveTours";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                // Fetch the results and populate the array
                stats[0] = rs.getString("TotalUsers");    // Total number of users
                stats[1] = rs.getString("TotalBookings"); // Total number of bookings
                stats[2] = rs.getString("TotalAmount");   // Total amount from bookings
                stats[3] = rs.getString("ActiveTours");   // Total number of active tours
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stats;
    }

    public void joinTour(int userId, int tourId) {
        String query = "INSERT INTO Booking (UserID, TourID, TransportProviderID, BookingDate, Rating, Status) VALUES (?, ?, 1, GETDATE(), NULL, 'Pending')";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Set the values for the prepared statement
            ps.setInt(1, userId);    // UserID
            ps.setInt(2, tourId);    // TourID
            
            // Execute the update
            int rowsAffected = ps.executeUpdate();
            
            // Confirm success
            if (rowsAffected > 0) {
                System.out.println("Booking added successfully.");
            } else {
                System.out.println("Failed to add the booking.");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }
    
    public int authenticateUser(String email, String pass, int[] userId) {
        String query = "SELECT UserID, UserType FROM Users WHERE Email = ? AND Password = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, pass);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Extract UserID and set it in the userId array
                    userId[0] = rs.getInt("UserID");
                    String userType = rs.getString("UserType");

                    if ("Admin".equalsIgnoreCase(userType)) {
                        return 1; // Admin
                    } else if ("Customer".equalsIgnoreCase(userType)) {
                        return 2; // Customer
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // User does not exist
    }


    public boolean addNewUser(String email, String pass, String fullName) {
        String insertQuery = "INSERT INTO Users (Email, Password, FullName, UserType) VALUES (?, ?, ?, 'Customer')";

        try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {
            // Set the parameters for the query
            stmt.setString(1, email);
            stmt.setString(2, pass);
            stmt.setString(3, fullName);

            // Execute the insert query
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("New user added successfully.");
                return true;
            } else {
                System.out.println("Failed to add new user.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public void addSupportMessage(int uId, String title, String detail) {
        String query = "INSERT INTO CustomerCareMessage (UserID, Title, Message, DateTime) VALUES (?, ?, ?, GETDATE())";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Set the values for the prepared statement
            ps.setInt(1, uId);         // UserID
            ps.setString(2, title);    // Title
            ps.setString(3, detail);   // Message content
            
            // Execute the update
            int rowsAffected = ps.executeUpdate();
            
            // Confirm success
            if (rowsAffected > 0) {
                System.out.println("Support message added successfully.");
            } else {
                System.out.println("Failed to add the support message.");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }
}

