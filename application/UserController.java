package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.Image;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Booking;
import Models.TopCustomers;
import Models.Tours;
import Models.TransportProvider;
import Models.MyBooking;

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
	@FXML
	private Button customTourSubmit;
	
	
	@FXML
	private Pane customTourPane;
	@FXML
	private Pane tourTablePane;
	@FXML
	private Pane myBookingsPane;
	
	
	@FXML
	private TableView<MyBooking> myBookingsTable;
	@FXML
	private TableColumn<MyBooking, String> TourName;
	@FXML
	private TableColumn<MyBooking, String> BookingDate;
	@FXML
	private TableColumn<MyBooking, Double> Price;
	@FXML
	private TableColumn<MyBooking, String> StartDate;
	@FXML
	private TableColumn<MyBooking, String> TourDesc;
	@FXML
	private TableColumn<MyBooking, Void> deleteAction;

	@FXML
	private TextField locationBox;
	@FXML
	private TextField descriptionBox;
	@FXML
	private TourismManagementSystem TMS;
	
	private int UserId = 1; // Assuming Userid = 1 is logged in right now
	
	public UserController() throws SQLException {
		TMS = new TourismManagementSystem();
	}
	
	public void SetVisibilityFalse()
    {
    	customTourPane.setVisible(false);
    	tourTablePane.setVisible(false);
    	myBookingsPane.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	SetVisibilityFalse();
    	customTourSubmit.setOnAction(event -> onCustomTourSubmit(event));
    	feature1Btn.setText("New Tours");
		feature2Btn.setText("Popular Tours");
		feature3Btn.setText("Tour Reviews");
		feature4Btn.setText("My Tours");
    }

    
    public void TourButtonClicked(ActionEvent actionEvent)
	{
    	SetVisibilityFalse();
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
    	SetVisibilityFalse();
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
    	SetVisibilityFalse();
    	feature1Btn.setText("Create Request");
		feature2Btn.setText("Pending Requests");
		
		feature3Btn.setDisable(true);
		feature3Btn.setOpacity(0);
		feature4Btn.setDisable(true);
		feature4Btn.setOpacity(0);
	}
    
    public void SupportButtonClicked(ActionEvent actionEvent)
	{
    	SetVisibilityFalse();
    	feature1Btn.setText("Contact Admin");
		feature2Btn.setText("Transport Issue");
		feature3Btn.setText("Menu Issue");
		
		feature3Btn.setDisable(false);
		feature3Btn.setOpacity(1);
		
		feature4Btn.setDisable(true);
		feature4Btn.setOpacity(0);
	}
    
    
    
    public void feature1Click(ActionEvent actionEvent) throws SQLException
    {
    		SetVisibilityFalse();
    		if(feature1Btn.getText() == "New Tours")
    		{
    			tourTablePane.setVisible(true);
    		}
    		else if(feature1Btn.getText() == "My Bookings")
    		{
    			myBookingsPane.setVisible(true);
    			setMyBookingsTable();
    		}
    		else if(feature1Btn.getText() == "Create Request")
    		{
    			customTourPane.setVisible(true);
    		}
    		else if(feature1Btn.getText() == "Contact Admin")
    		{
    			System.out.println("Contact Admin");
    		}
    }
    
    
    private void setMyBookingsTable() throws SQLException {
        // Retrieve the list of bookings for the current user
        ArrayList<MyBooking> allMyBookings = TMS.getBookingsByUserId(UserId);

        // Convert the list of bookings to an ObservableList
        ObservableList<MyBooking> data = FXCollections.observableArrayList(allMyBookings);

        // Set the value for each column to match the corresponding method in the MyBooking object
        TourName.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTourName())
        );
     
        BookingDate.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getBookingDate())
        );
     
        Price.setCellValueFactory(cellData -> 
            new SimpleDoubleProperty(cellData.getValue().getTourPrice()).asObject()
        );

        StartDate.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getStartDate())
        );
       
        TourDesc.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTourDescription())
        );
        
        
        deleteAction.setCellFactory(column -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #FF5C5C; -fx-text-fill: white; -fx-font-weight: bold;");

                // Handle button click
                deleteButton.setOnAction(event -> {
                    MyBooking booking = getTableView().getItems().get(getIndex());
                    if (booking != null) {
                        // Print the tour name and user ID
                        System.out.println("Tour Name: " + booking.getTourName());
                        System.out.println("User ID: " + UserId);

                        // Optional: Remove the booking from the table
                        getTableView().getItems().remove(booking);

                        // Optional: Implement actual delete logic (e.g., delete from database)
//                        try {
//                            //TMS.deleteBooking(booking.getBookingId());
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        

        // Apply the data to the table
        myBookingsTable.setItems(data);


        // Set font styling for the whole table
        myBookingsTable.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
        
       
        
        
        
    }

   

    
    @FXML
    private void onCustomTourSubmit(ActionEvent actionEvent) {
        String location = locationBox.getText();
        String description = descriptionBox.getText();
        
        System.out.println("Location: " + location);
        System.out.println("Description: " + description);

        //SavetoDatabase()
    }
    
    public void feature2Click(ActionEvent actionEvent)
    {
    	SetVisibilityFalse();
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
    	SetVisibilityFalse();
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
    	SetVisibilityFalse();
    	if(feature4Btn.getText() == "My Tours")
		{
			System.out.println("New Tours");
		}
    }
}
