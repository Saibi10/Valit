package Models;
import java.util.ArrayList;

public class Tours {
	String TourName;
	String Bookings;
	String Rating;
	String TourDescription;
	String Price;
	String TransportID;
	String TransportType;
	String StartDate;
	String Duration;
	String GoogleMapLink;
	ArrayList<String> TourImages;
	
	public Tours(String TourName, String Bookings, String Rating) {
		this.TourName  = TourName;
		this.Bookings = Bookings;
		this.Rating = Rating;
	}
	
	public Tours(String TourName, String Price, String Duration, String TransportType) {
		this.TourName  = TourName;
		this.Price = Price;
		this.Duration = Duration;
		this.TransportType = TransportType;
	}
	
	public Tours(String TourName, String Bookings, String Rating , String TourDescription , String TourPrice , String TransportID , String StartDate , String Duration , String GoogleMapLink , ArrayList<String> TourImages) {
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
