package Models;

public class CustomerCareMessage {
	String ID;
	int userId;
	String customerName;
	String status;
	String title;
	String message;
	String response;
	String dateTime;
	
	public CustomerCareMessage(String ID,String customerName,String status,String title ,String message,String response) {
		this.ID = ID;
		this.customerName = customerName;
		this.status = status;
		this.title = title;
		this.message = message;
		this.response = response;
	}
	
	 public CustomerCareMessage(String ID, int userId, String title, String message, String response, String status, String dateTime) {
	        this.ID = ID;
	        this.userId = userId;
	        this.title = title;
	        this.message = message;
	        this.response = response;
	        this.status = status;
	        this.dateTime = dateTime;
	 }
	
	public String getID() {
		return ID;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public String getStatus() {
		return status;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getResponse() {
		return response;
	}
	

	
	    public String getDateTime() {
	        return dateTime;
	    }
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setresponse(String response) {
		this.response = response;
	}
}
