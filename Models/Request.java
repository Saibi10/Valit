package Models;

public class Request {
    private final String requestID;
    private final String location;
    private final String description;
    private String status;
    private final String createdAt; // Added 'createdAt' for the timestamp
    private String response; // Added 'response' field to store the response

    // Updated constructor to include 'response'
    public Request(String requestID, String location, String description, String status, String createdAt, String response) {
        this.requestID = requestID;
        this.location = location;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt; // Initialize createdAt
        this.response = response; // Initialize response
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
    
    public void setStatus(String status) {
        this.status = status;
    }
}
