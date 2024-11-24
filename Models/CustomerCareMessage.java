package Models;

public class CustomerCareMessage {
	String ID;
	String customerName;
	String status;
	String title;
	String message;
	String response;
	
	public CustomerCareMessage(String ID,String customerName,String status,String title ,String message,String response) {
		this.ID = ID;
		this.customerName = customerName;
		this.status = status;
		this.title = title;
		this.message = message;
		this.response = response;
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
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setresponse(String response) {
		this.response = response;
	}
}
