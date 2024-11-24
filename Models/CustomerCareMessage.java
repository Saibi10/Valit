package Models;

public class CustomerCareMessage {
    private final int id;           // Auto-incrementing primary key
    private final int userId;       // Foreign key for the user sending the message
    private final String title;     // Title of the message
    private final String message;   // Content of the message
    private String response;        // Response to the message
    private String status;          // Status of the message (default 'Pending')
    private final String dateTime;  // Date and time when the message was created

    // Constructor
    public CustomerCareMessage(int id, int userId, String title, String message, String response, String status, String dateTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.response = response;
        this.status = status;
        this.dateTime = dateTime;
    }

    // Getters
    public int getID() {
        return id;
    }

    public int getUserId() {
        return userId;
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

    public String getStatus() {
        return status;
    }

    public String getDateTime() {
        return dateTime;
    }

    // Setters
    public void setResponse(String response) {
        this.response = response;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
