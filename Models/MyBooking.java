package Models;

public class MyBooking {
    private int bookingId;
    private String tourName;
    private String bookingDate;
    private double tourPrice;
    private String startDate;
    private String tourDescription;
    private int rating; // New field for rating

    // Updated constructor to include Rating
    public MyBooking(int bookingId, String tourName, String bookingDate, double tourPrice, String startDate, String tourDescription, int rating) {
        this.bookingId = bookingId;
        this.tourName = tourName;
        this.bookingDate = bookingDate;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.tourDescription = tourDescription;
        this.rating = rating;
    }

    // Getter for bookingId
    public int getID() {
        return bookingId;
    }

    // Getter for TourName
    public String getTourName() {
        return tourName;
    }

    // Getter for BookingDate
    public String getBookingDate() {
        return bookingDate;
    }

    // Getter for TourPrice
    public double getTourPrice() {
        return tourPrice;
    }

    // Getter for StartDate
    public String getStartDate() {
        return startDate;
    }

    // Getter for TourDescription
    public String getTourDescription() {
        return tourDescription;
    }

    // Getter for Rating
    public int getRating() {
        return rating;
    }

    // Setter for Rating
    public void setRating(int rating) {
        this.rating = rating;
    }
}
