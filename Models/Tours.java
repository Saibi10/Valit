package Models;

public class Tours {
	String TourName;
	String Bookings;
	String Rating;
	String TourDescription;
	String Price;
	String TransportID;
	String StartDate;
	String Duration;
	String GoogleMapLink;
	
	public Tours(String TourName, String Bookings, String Rating) {
		this.TourName  = TourName;
		this.Bookings = Bookings;
		this.Rating = Rating;
	}
	
	public Tours(String TourName, String Bookings, String Rating , String TourDescription , String TourPrice , String TransportID , String StartDate , String Duration , String GoogleMapLink) {
		this.TourName  = TourName;
		this.Bookings = Bookings;
		this.Rating = Rating;
		this.TourDescription = TourDescription;
		this.Price = TourPrice;
		this.TransportID = TransportID;
		this.StartDate = StartDate;
		this.Duration = Duration;
		this.GoogleMapLink = GoogleMapLink;
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
}
