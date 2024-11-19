package Models;

public class Booking {
	String Tour;

	String Customer;

	String Date;

	String Status;

	public Booking(String Tour, String Customer, String Date, String Status) {
		this.Tour = Tour;
		this.Customer = Customer;
		this.Date = Date;
		this.Status = Status;
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
