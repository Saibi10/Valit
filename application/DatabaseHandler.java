package application;
import Models.TopCustomers;

import Models.Tours;
import Models.TransportProvider;
import Models.Booking;
import Models.MyBooking;

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
                       "WHERE b.Status = 'Completed' " +
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
        String query = "SELECT " +
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
                       "GROUP BY " +
                       "t.TourName, " +
                       "t.TourPrice, " +
                       "t.TransportID, " +
                       "t.StartDate, " +
                       "t.Duration, " +
                       "CAST(t.TourDescription AS NVARCHAR(MAX)), " +
                       "CAST(t.GoogleMapLink AS NVARCHAR(MAX));";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
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
            Tours tour = new Tours(tourName, bookings, rating, tourDescription, tourPrice, transportID, startDate, duration, googleMapLink , imagesList);
            toursList.add(tour);
        }

        return toursList;
    }


    public ArrayList<Booking> getAllBookings() {
        ArrayList<Booking> bookingsList = new ArrayList<>();
        String query = "SELECT " +
                       "t.TourName, " +
                       "u.FullName AS Customer, " +
                       "b.BookingDate AS Date, " +
                       "b.Status " +
                       "FROM Booking b " +
                       "JOIN Tour t ON b.TourID = t.TourID " +
                       "JOIN Users u ON b.UserID = u.UserID";

        try (ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String tourName = rs.getString("TourName");
                String customerName = rs.getString("Customer");
                String date = rs.getString("Date");
                String status = rs.getString("Status");

                // Add the booking to the list
                bookingsList.add(new Booking(tourName, customerName, date, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingsList;
    }

    public ArrayList<TransportProvider> getAllTransportProviders() {
        ArrayList<TransportProvider> transportProvidersList = new ArrayList<>();

        String query = "SELECT Name, Rating, FleetSize, Contact, VehicleTypes FROM TransportProvider";

        try (ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String name = rs.getString("Name");
                String rating = rs.getString("Rating");
                String fleetSize = rs.getString("FleetSize");
                String contact = rs.getString("Contact");
                String vehicleTypes = rs.getString("VehicleTypes");

                // Create a TransportProvider object and add it to the list
                TransportProvider transportProvider = new TransportProvider(name, rating, fleetSize, contact, vehicleTypes);
                transportProvidersList.add(transportProvider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transportProvidersList;
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
                       "WHERE b.UserID = ?"; // Use the UserID to filter the bookings

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

    
   


}
