package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;


public class UserController {
    
    @FXML
    private void initialize() {
        // This method is automatically called after the FXML file is loaded
        setupImages();
    }
    
    private void setupImages() {
        try {
            // You can add image loading logic here when you have the images
            // For example:
            // toursButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/tours.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleAvailableTours() {
        // Handle available tours button click
        System.out.println("Available Tours clicked");
    }
    
    @FXML
    private void handleCustomTour() {
        // Handle custom tour button click
        System.out.println("Custom Tour clicked");
    }
    
    @FXML
    private void handleBookingData() {
        // Handle booking data button click
        System.out.println("Booking Data clicked");
    }
    
    @FXML
    private void handleCustomerSupport() {
        // Handle customer support button click
        System.out.println("Customer Support clicked");
    }
    
    @FXML
    private void handleProfile() {
        // Handle profile button click
        System.out.println("Profile clicked");
    }
}