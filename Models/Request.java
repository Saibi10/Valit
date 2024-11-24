package Models;

public class Request {
	private String customerName;
    private String requestID;
    private String location;
    private String description;
    private String status;
    private String createdAt; 
    private String response; 

    // Updated constructor to include 'response'
    public Request(String requestID, String location, String description, String status, String createdAt, String response) {
        this.requestID = requestID;
        this.location = location;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt; // Initialize createdAt
        this.response = response; // Initialize response
    }
    
    public Request(String customerName,String requestID, String location, String description, String status, String createdAt, String response) {
        this.customerName = customerName;
    	this.requestID = requestID;
        this.location = location;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt; // Initialize createdAt
        this.response = response; // Initialize response
    }
    
    public String getcustomerName() {
    	return customerName;
    }

    public String getRequestID() {
        return requestID;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response; // Set the response
    }
}
