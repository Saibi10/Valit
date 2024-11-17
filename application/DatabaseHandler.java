package application;

import java.lang.invoke.MethodHandle;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.util.ArrayList;

public class DatabaseHandler {
	
	Connection con;
    Statement st;
    
    
    public DatabaseHandler() throws SQLException {
		 DriverManager.registerDriver(new SQLServerDriver()); 
		 String url = "jdbc:sqlserver://127.0.0.1;instanceName=SQLEXPRESS;databaseName=TourismManagementSystem;encrpt=true;trustServerCertificate=true";
		 con = DriverManager.getConnection(url, "sa", "123"); 
		 st = con.createStatement();
		 System.out.println("Connected");

    }
    
    public ArrayList<ArrayList<Object>> getTop3Customers() {
        ArrayList<ArrayList<Object>> topCustomers = new ArrayList<>();
        String query = 
            "SELECT TOP 3 u.FullName, COUNT(b.ID) AS NumberOfBookings, SUM(t.TourPrice) AS TotalAmountPaid " +
            "FROM Booking b " +
            "JOIN Users u ON b.UserID = u.UserID " +
            "JOIN Tour t ON b.TourID = t.TourID " +
            "GROUP BY u.FullName " +
            "ORDER BY NumberOfBookings DESC, TotalAmountPaid DESC "; // Adjust for your SQL Server (LIMIT is not supported, use TOP for SQL Server)

        try (ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                ArrayList<Object> customerData = new ArrayList<>();
                customerData.add(rs.getString("FullName"));           // Customer's Full Name
                customerData.add(rs.getInt("NumberOfBookings"));     // Number of bookings
                customerData.add(rs.getDouble("TotalAmountPaid"));   // Total amount paid
                topCustomers.add(customerData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topCustomers;
    }

}
