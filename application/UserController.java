package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
	
	
	@FXML
    private Button feature1Btn;
	@FXML
    private Button feature2Btn;
	@FXML
    private Button feature3Btn;
	@FXML
    private Button feature4Btn;
	
	@FXML
    private Button tourTabBtn;
	@FXML
    private Button customTabBtn;
	@FXML
    private Button bookingTabBtn;
	@FXML
    private Button supportTabBtn;
	

    @Override
    public void initialize(URL location, ResourceBundle resources) {}


    public void TourButtonClicked(ActionEvent actionEvent)
	{
		feature1Btn.setText("New Tours");
		feature2Btn.setText("Popular Tours");
		feature3Btn.setText("Tour Reviews");
		feature4Btn.setText("My Tours");
		
		feature3Btn.setDisable(false);
		feature3Btn.setOpacity(1);
		feature4Btn.setDisable(false);
		feature4Btn.setOpacity(1);
	}

    public void BookingButtonClicked(ActionEvent actionEvent)
	{
    		feature1Btn.setText("My Bookings");
		feature2Btn.setText("Booking History");
		feature3Btn.setText("Booking Reports");
		
		feature3Btn.setDisable(false);
		feature3Btn.setOpacity(1);
		feature4Btn.setDisable(true);
		feature4Btn.setOpacity(0);
	}
    
    public void CustomButtonClicked(ActionEvent actionEvent)
	{
    		feature1Btn.setText("Create Request");
		feature2Btn.setText("Pending Requests");
		
		feature3Btn.setDisable(true);
		feature3Btn.setOpacity(0);
		feature4Btn.setDisable(true);
		feature4Btn.setOpacity(0);
	}
    
    public void SupportButtonClicked(ActionEvent actionEvent)
	{
    		feature1Btn.setText("Contact Admin");
		feature2Btn.setText("Transport Issue");
		feature3Btn.setText("Menu Issue");
		
		feature3Btn.setDisable(false);
		feature3Btn.setOpacity(1);
		
		feature4Btn.setDisable(true);
		feature4Btn.setOpacity(0);
	}
    
    public void feature1Click(ActionEvent actionEvent)
    {
    		if(feature1Btn.getText() == "New Tours")
    		{
    			System.out.println("New Tours");
    		}
    		else if(feature1Btn.getText() == "My Bookings")
    		{
    			System.out.println("My Bookings");
    		}
    		else if(feature1Btn.getText() == "Create Request")
    		{
    			System.out.println("Create Request");
    		}
    		else if(feature1Btn.getText() == "Contact Admin")
    		{
    			System.out.println("Contact Admin");
    		}
    }
    
    public void feature2Click(ActionEvent actionEvent)
    {
    		if(feature2Btn.getText() == "Popular Tours")
		{
			System.out.println("New Tours");
		}
		else if(feature2Btn.getText() == "Booking History")
		{
			System.out.println("My Bookings");
		}
		else if(feature2Btn.getText() == "Pending Requests")
		{
			System.out.println("Create Request");
		}
		else if(feature2Btn.getText() == "Transport Issue")
		{
			System.out.println("Contact Admin");
		}
    }
    
    public void feature3Click(ActionEvent actionEvent)
    {
    		if(feature3Btn.getText() == "Tour Reviews")
		{
			System.out.println("New Tours");
		}
		else if(feature3Btn.getText() == "Booking Reports")
		{
			System.out.println("My Bookings");
		}
		else if(feature3Btn.getText() == "Menu Issue")
		{
			System.out.println("Create Request");
		}
    }
    
    public void feature4Click(ActionEvent actionEvent)
    {
    		if(feature4Btn.getText() == "My Tours")
		{
			System.out.println("New Tours");
		}
    }
}
