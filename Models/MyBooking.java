package Models;

public class MyBooking {
    private String tourName;
    private String bookingDate;
    private double tourPrice;
    private String startDate;
    private String tourDescription;

    public MyBooking(String tourName, String bookingDate, double tourPrice, String startDate, String tourDescription) {
        this.tourName = tourName;
        this.bookingDate = bookingDate;
        this.tourPrice = tourPrice;
        this.startDate = startDate;
        this.tourDescription = tourDescription;
    }

    public String getTourName() {
        return tourName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTourDescription() {
        return tourDescription;
    }
}
