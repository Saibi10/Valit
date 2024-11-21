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
import javafx.scene.layout.Pane;

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

	
	private TextField locationBox;
	@FXML
	private TextField descriptionBox;

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
    
    private void setBackGroundColorString(TableColumn<MyBooking, String> test) {
		test.setCellFactory(column -> {
			return new TableCell<MyBooking, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
						setStyle(""); // Reset the style when the cell is empty
					} else {
						setText(item);
						setStyle(
								"-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
					}
				}
			};
		});
	}
    
    private void setBackGroundColorDouble(TableColumn<MyBooking, Double> test) {
        test.setCellFactory(column -> {
            return new TableCell<MyBooking, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setStyle(""); // Reset the style when the cell is empty
                    } else {
                        setText(String.format("%.2f", item)); // Format the Double value as a string (e.g., 12.34)
                        setStyle(
                                "-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
                    }
                }
            };
        });
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
        setBackGroundColorString(TourName);
        BookingDate.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getBookingDate())
        );
        setBackGroundColorString(BookingDate);
        Price.setCellValueFactory(cellData -> 
            new SimpleDoubleProperty(cellData.getValue().getTourPrice()).asObject()
        );
        setBackGroundColorDouble(Price);
        StartDate.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getStartDate())
        );
        setBackGroundColorString(StartDate);
        TourDesc.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTourDescription())
        );
        setBackGroundColorString(TourDesc);

        // Apply the data to the table
        myBookingsTable.setItems(data);

        // Add alternating row colors
        myBookingsTable.setRowFactory(tv -> {
            TableRow<MyBooking> row = new TableRow<>();
            row.setStyle("-fx-background-color: #f9f9f9;"); // Default background color for rows
            row.setOnMouseEntered(event -> row.setStyle("-fx-background-color: #e2e2e2;")); // Hover effect
            row.setOnMouseExited(event -> row.setStyle("-fx-background-color: #f9f9f9;")); // Reset on exit
            return row;
        });

        // Style table headers
        myBookingsTable.getColumns().forEach(column -> {
            column.setStyle("-fx-font-weight: bold; -fx-background-color: #1F2937; -fx-text-fill: #F3F4F6;");
        });

        // Optional: Set padding and borders for cells
        myBookingsTable.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1px;");

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
