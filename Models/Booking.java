package Models;

public class Booking {
	String ID;
	
	String Tour;

	String Customer;

	String Date;

	String Status;

	public Booking(String ID,String Tour, String Customer, String Date, String Status) {
		this.ID = ID;
		this.Tour = Tour;
		this.Customer = Customer;
		this.Date = Date;
		this.Status = Status;
	}
	
	public String getID() {
		return ID;
	}
	
	public String getTour() {
		return Tour;
	}
	
	public String getCustomer() {
		return Customer;
	}
	
	public String getDate() {
		return Date;
	}
	
	public String getStatus() {
		return Status;
	}

}
