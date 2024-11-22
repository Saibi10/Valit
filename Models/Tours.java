package Models;
import java.util.ArrayList;

public class Tours {
	String TourID;
	String TourName;
	String Bookings;
	String Rating;
	String TourDescription;
	String Price;
	String TransportID;
	String StartDate;
	String Duration;
	String GoogleMapLink;
	ArrayList<String> TourImages;
	
	public Tours(String TourName, String Bookings, String Rating) {
		this.TourName  = TourName;
		this.Bookings = Bookings;
		this.Rating = Rating;
	}
	
	public Tours(String TourID,String TourName, String Bookings, String Rating , String TourDescription , String TourPrice , String TransportID , String StartDate , String Duration , String GoogleMapLink , ArrayList<String> TourImages) {
		this.TourID = TourID;
		this.TourName  = TourName;
		this.Bookings = Bookings;
		this.Rating = Rating;
		this.TourDescription = TourDescription;
		this.Price = TourPrice;
		this.TransportID = TransportID;
		this.StartDate = StartDate;
		this.Duration = Duration;
		this.GoogleMapLink = GoogleMapLink;
		this.TourImages = TourImages;
	}
	public Tours(String TourID, String tourName, String bookings, String description, String price, String duration, String googleMapLink, String startDate, String transportProviderID, ArrayList<String>tourImages) {
		this.TourID = TourID;
		this.TourName  = tourName;
		this.Bookings = bookings;
		this.TourDescription = description;
		this.Price = price;
		this.TransportID = transportProviderID;
		this.StartDate = startDate;
		this.Duration = duration;
		this.GoogleMapLink = googleMapLink;
		this.TourImages = tourImages;
	}
	
	
	public String getTourID() {
		return TourID;
	}
	
	public String getTourName() 
	{
		return TourName;
	}
	
	public String getBookings() 
	{
		return Bookings;
	}
	
	public String getRating() {
		return Rating;
	}
	
	public String getTourDescription() {
		return TourDescription;
	}
	
	public String getPrice() {
		return Price;
	}
	
	public String getTransportID() {
		return TransportID;
	}
	
	public String getStartDate() {
		return StartDate;
	}
	
	public String getDuration() {
		return Duration;
	}
	
	public String getGoogleMapLink() {
		return GoogleMapLink;
	}
	
	public ArrayList<String> getTourImages() {
		return TourImages;
	}
	
}
