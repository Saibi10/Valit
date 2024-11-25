package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.Image;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Booking;
import Models.CustomerCareMessage;
import Models.TopCustomers;
import Models.Tours;
import Models.TransportProvider;
import Models.User;
import Models.MyBooking;
import Models.Request;

public class UserController implements Initializable {
	
	
	@FXML
    private Button feature1Btn;
	@FXML
    private Button feature2Btn;
	@FXML
    private Button feature3Btn;
	@FXML
    private Button btnSignout;
	
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
	private Button closeRate;
	@FXML
	private Button viewTourBackBtn;
	@FXML
	private Button RateButton;
	@FXML
	private Button closeResponse;
	@FXML
	private Button deleteRequest;
	@FXML
	private Button submitSupportBtn;
	@FXML
	private Button ProfileBtn;
	@FXML
	private Button SaveProfile;
	
	
	@FXML
	private Pane customTourPane;
	@FXML
	private Pane tourTablePane;
	@FXML
	private Pane myBookingsPane;
	@FXML
	private Pane ratePane;
	@FXML
	private Pane tourViewPane;
	@FXML
	private Pane paymentPane;
	@FXML
	private Pane SupportPane;
	@FXML
	private Pane myRequestPane;
	@FXML
	private Pane myRequestPane1;
	@FXML
	private Pane responsePane;
	@FXML
	private Pane ProfilePane;
	
	@FXML
	private Label ratelabel1;
	
	
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
	private TableView<Tours> tourTable;
	@FXML
	private TableColumn<Tours, String> tourNameCol;
	@FXML
	private TableColumn<Tours, String> tourPriceCol;
	@FXML
	private TableColumn<Tours, String> tourDurationCol;
	@FXML
	private TableColumn<Tours, String> tourTransportCol;
	@FXML
	private TableColumn<Tours, Void> tourActionCol;
	
	@FXML
	private TableView<Request> myRequestTable;
	@FXML
	private TableColumn<Request, String> LocationRequst;
	@FXML
	private TableColumn<Request, String> DescriptionRequest;
	@FXML
	private TableColumn<Request, String> DateRequest;
	@FXML
	private TableColumn<Request, String> RequestStatus;
	
	@FXML
	private TableView<CustomerCareMessage> myRequestTable1;
	@FXML
	private TableColumn<CustomerCareMessage, String> LocationRequst1;
	@FXML
	private TableColumn<CustomerCareMessage, String> DescriptionRequest1;
	@FXML
	private TableColumn<CustomerCareMessage, String> DateRequest1;
	@FXML
	private TableColumn<CustomerCareMessage, String> RequestStatus1;
	
	
	@FXML
	private Label viewTourDesc;
	@FXML
	private Label viewTourDate;
	@FXML
	private Label viewTourId;
	@FXML
	private Label viewTourName;
	
	@FXML
	private TextField supportTitle;
	@FXML
	private TextField supportDetail;
	
	@FXML
	private TextField namePayment;
	@FXML
	private TextField cardPayment;
	@FXML
	private TextField expPayment;
	@FXML
	private TextField cvvPayment;
	@FXML
	private TextField NameP;
	@FXML
	private TextField EmailP;
	@FXML
	private TextField PassP;

	@FXML
	private TextField locationBox;
	@FXML
	private TextField descriptionBox;
	@FXML
	private TextField rateTextField;
	@FXML
	private TextField responseTextField;
	@FXML
	private TourismManagementSystem TMS;
	
	private int UserId = 0; // Assuming Userid = 1 is logged in right now
	
	public UserController(TourismManagementSystem TMS) throws SQLException {
		this.TMS = TMS;
	}
	
	public void setUserId(int id)
	{
		UserId = id;
	}
	
	public void SetVisibilityFalse()
    {
    	customTourPane.setVisible(false);
    	tourTablePane.setVisible(false);
    	myBookingsPane.setVisible(false);
    	ratePane.setVisible(false);
    	myRequestPane.setVisible(false);
    	responsePane.setVisible(false);
    	myRequestPane1.setVisible(false);
    	tourViewPane.setVisible(false);
    	paymentPane.setVisible(false);
    	SupportPane.setVisible(false);
    	ProfilePane.setVisible(false);	

    	
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	SetVisibilityFalse();
    	customTourSubmit.setOnAction(event -> onCustomTourSubmit(event));
    	ProfileBtn.setOnAction(event -> handleProfileButtonAction());
    	SaveProfile.setOnAction(e -> handleSaveProfileButtonAction());

    	
    	feature1Btn.setText("Popular Tours");
		feature2Btn.setText("All Tours");
		feature3Btn.setDisable(true);
		feature3Btn.setOpacity(0);
    }

    
    public void TourButtonClicked(ActionEvent actionEvent)
	{
    	SetVisibilityFalse();
		feature1Btn.setText("Popular Tours");
		feature2Btn.setText("All Tours");
		
		feature3Btn.setDisable(true);
		feature3Btn.setOpacity(0);
	}

    public void BookingButtonClicked(ActionEvent actionEvent)
	{
    	SetVisibilityFalse();
    	feature1Btn.setText("My Bookings");
		feature2Btn.setText("Booking History");
		//feature3Btn.setText("Booking Reports");
		
		feature3Btn.setDisable(true);
		feature3Btn.setOpacity(0);

	}
    
    public void CustomButtonClicked(ActionEvent actionEvent)
	{
    	SetVisibilityFalse();
    	feature1Btn.setText("Create Request");
		feature2Btn.setText("Pending Requests");
		
		feature3Btn.setDisable(true);
		feature3Btn.setOpacity(0);
	}
    
    public void SupportButtonClicked(ActionEvent actionEvent)
	{
    	SetVisibilityFalse();
    	feature1Btn.setText("Report Issue");
		feature2Btn.setText("Response");
		
		feature3Btn.setDisable(true);
		feature3Btn.setOpacity(0);
		
	}
    
    public void feature1Click(ActionEvent actionEvent) throws SQLException
    {
    		SetVisibilityFalse();
    		if(feature1Btn.getText() == "Popular Tours")
    		{
    			tourTablePane.setVisible(true);
    			setPopularToursTable();
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
    		else if(feature1Btn.getText() == "Report Issue")
    		{
    			SupportPane.setVisible(true);
    			
    		}
    }

    public void feature2Click(ActionEvent actionEvent) throws SQLException
    {
    	SetVisibilityFalse();
    	if(feature2Btn.getText() == "All Tours")
		{
    		tourTablePane.setVisible(true);
			setAllToursTable();
		}
		else if(feature2Btn.getText() == "Booking History")
		{
			myBookingsPane.setVisible(true);
			setMyBookingsHistoryTable();
		}
		else if(feature2Btn.getText() == "Pending Requests")
		{
			myRequestPane.setVisible(true);
			setMyRequestTable();	
		}
		else if(feature2Btn.getText() == "Response")
		{
			myRequestPane1.setVisible(true);
			setMyCustomerCareTable();
		}
    }
    
    public void feature3Click(ActionEvent actionEvent) throws SQLException
    {
    	SetVisibilityFalse();
    	if(feature3Btn.getText() == "My Tours")
		{
			System.out.println("New Tours");
		}
		else if(feature3Btn.getText() == "Booking Reports")
		{
			System.out.println("Booking Reports");
		}
		else if(feature3Btn.getText() == "Menu Issue")
		{
			System.out.println("Create Request");
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
            private final Button deleteButton = new Button("Cancel");

            {
            	// Initial button style
            	deleteButton.setStyle(
            	    "-fx-background-color: #FF5C5C; " +
            	    "-fx-text-fill: white; " +
            	    "-fx-font-weight: bold; " +
            	    "-fx-border-radius: 5px; " +
            	    "-fx-background-radius: 5px; " +
            	    "-fx-padding: 10px; " +
            	    "-fx-cursor: hand;");

            	// Hover effect: changes the background color when the mouse enters
            	deleteButton.setOnMouseEntered(event -> {
            	    deleteButton.setStyle(
            	        "-fx-background-color: #FF2D2D; " +  // A darker red for hover effect
            	        "-fx-text-fill: white; " +
            	        "-fx-font-weight: bold; " +
            	        "-fx-border-radius: 5px; " +
            	        "-fx-background-radius: 5px; " +
            	        "-fx-padding: 10px; " +
            	        "-fx-cursor: hand;");
            	});

            	// Revert to the original style when the mouse exits
            	deleteButton.setOnMouseExited(event -> {
            	    deleteButton.setStyle(
            	        "-fx-background-color: #FF5C5C; " +  // Original background color
            	        "-fx-text-fill: white; " +
            	        "-fx-font-weight: bold; " +
            	        "-fx-border-radius: 5px; " +
            	        "-fx-background-radius: 5px; " +
            	        "-fx-padding: 10px; " +
            	        "-fx-cursor: hand;");
            	});


                // Handle button click
                deleteButton.setOnAction(event -> {
                    MyBooking booking = getTableView().getItems().get(getIndex());
                    if (booking != null) {
                        // Print the tour name and user ID
                        System.out.println("Tour Name: " + booking.getTourName());
                        System.out.println("User ID: " + UserId);
                        System.out.println("Booking Id: " + booking.getID());

                       
                        getTableView().getItems().remove(booking);

                        
                        TMS.cancelTourBooking(Integer.toString(booking.getID()));
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
        
        TourDesc.setCellFactory(tc -> new TableCell<MyBooking, String>() {
            private final Tooltip tooltip = new Tooltip();

            {
                // Set the delay to 0 milliseconds (immediate display)
                tooltip.setShowDelay(javafx.util.Duration.millis(0));
                tooltip.setHideDelay(javafx.util.Duration.millis(0));
                tooltip.getStyleClass().add("tooltip");  // Apply the custom tooltip style
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);  // Clear tooltip if the cell is empty
                } else {
                    setText(item);  // Set the text for the table cell
                    tooltip.setText(item);  // Set the full description as the tooltip text
                    setTooltip(tooltip);  // Assign the tooltip to the table cell
                }
            }
        });

        // Apply the data to the table
        myBookingsTable.setItems(data);
        // Set font styling for the whole table
        myBookingsTable.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");   
    }
    
    private void setMyRequestTable() throws SQLException {
        // Retrieve the list of requests for the current user
        ArrayList<Request> allMyRequests = TMS.getRequestsByUserId(UserId);

 
        // Convert the list of requests to an ObservableList
        ObservableList<Request> data = FXCollections.observableArrayList(allMyRequests);
        
         LocationRequst.setCellValueFactory(cell -> 
            new SimpleStringProperty(cell.getValue().getLocation()) 
        );

         DescriptionRequest.setCellValueFactory(cell -> 
         new SimpleStringProperty(cell.getValue().getDescription()) 
        );

        DateRequest.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getCreatedAt()) 
        );
        
        RequestStatus.setCellValueFactory(cell -> 
        new SimpleStringProperty(cell.getValue().getStatus())  // Make sure this line is present
    );

        
        RequestStatus.setCellFactory(column -> new TableCell<Request, String>() {
            private final Button statusButton = new Button();

            {
                // Initial button style
                statusButton.setStyle(
                    "-fx-background-color: #FF5C5C; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-border-radius: 5px; " +
                    "-fx-background-radius: 5px; " +
                    "-fx-padding: 10px; " +
                    "-fx-cursor: hand;");
                
                // Hover effect
                statusButton.setOnMouseEntered(event -> {
                    statusButton.setStyle(
                        "-fx-background-color: #FF2D2D; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-padding: 10px; " +
                        "-fx-cursor: hand;");
                });

                statusButton.setOnMouseExited(event -> {
                    statusButton.setStyle(
                        "-fx-background-color: #FF5C5C; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-padding: 10px; " +
                        "-fx-cursor: hand;");
                });

                // Action event when button is clicked
                statusButton.setOnAction(event -> {
                    Request req = getTableView().getItems().get(getIndex());
                    if (req != null) {
                        System.out.println("User ID: " + UserId);
                        System.out.println("Booking Id: " + req.getStatus());
                        
                        if ("Responded".equals(req.getStatus())) {
                            responsePane.setVisible(true);
                            responseTextField.setText(req.getResponse());
                            
                            deleteRequest.setOnAction(e -> {
                            	responseTextField.clear();
                    		    responsePane.setVisible(false);
                    		    try {
									TMS.deleteRequest(req.getRequestID());
									setMyRequestTable();
										
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
                    		});
                        }

                    }
                });
                
                
                
                closeResponse.setOnAction(event -> {
                	responseTextField.clear();
        		    responsePane.setVisible(false);  // Hide the ratePane
        		});
            }

            @Override
            protected void updateItem(String Status, boolean empty) {
                super.updateItem(Status, empty);

             

                if (empty || Status == null || Status.isEmpty()) {
                    setGraphic(null);  // Clear graphic if the status is empty or null
                } else {
                    statusButton.setText(Status);  // Set the button text to the status
                    setGraphic(statusButton);  // Add the button to the table cell
                }

                // Ensure the cell is properly redrawn
                requestLayout();
            }
        });



        
        DescriptionRequest.setCellFactory(tc -> new TableCell<Request, String>() {
            private final Tooltip tooltip = new Tooltip();

            {
                // Set the delay to 0 milliseconds (immediate display)
                tooltip.setShowDelay(javafx.util.Duration.millis(0));
                tooltip.setHideDelay(javafx.util.Duration.millis(0));
                tooltip.getStyleClass().add("tooltip");  // Apply the custom tooltip style
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);  // Clear tooltip if the cell is empty
                } else {
                    setText(item);  // Set the text for the table cell
                    tooltip.setText(item);  // Set the full description as the tooltip text
                    setTooltip(tooltip);  // Assign the tooltip to the table cell
                }
            }
        });
        
        
        myRequestTable.setItems(data);
        
        myRequestTable.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
    }

    
    private void setMyCustomerCareTable() throws SQLException {
        // Retrieve the list of requests for the current user
        ArrayList<CustomerCareMessage> allMyRequests = TMS.getCustomerCareMessagesByUserId(UserId);

 
        // Convert the list of requests to an ObservableList
        ObservableList<CustomerCareMessage> data = FXCollections.observableArrayList(allMyRequests);
        
         LocationRequst1.setCellValueFactory(cell -> 
            new SimpleStringProperty(cell.getValue().getTitle()) 
        );

         DescriptionRequest1.setCellValueFactory(cell -> 
         new SimpleStringProperty(cell.getValue().getMessage()) 
        );

        DateRequest1.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getDateTime()) 
        );
        
        RequestStatus1.setCellValueFactory(cell -> 
        new SimpleStringProperty(cell.getValue().getStatus())  // Make sure this line is present
    );

        
        RequestStatus1.setCellFactory(column -> new TableCell<CustomerCareMessage, String>() {
            private final Button statusButton = new Button();

            {
                // Initial button style
                statusButton.setStyle(
                    "-fx-background-color: #FF5C5C; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-border-radius: 5px; " +
                    "-fx-background-radius: 5px; " +
                    "-fx-padding: 10px; " +
                    "-fx-cursor: hand;");
                
                // Hover effect
                statusButton.setOnMouseEntered(event -> {
                    statusButton.setStyle(
                        "-fx-background-color: #FF2D2D; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-padding: 10px; " +
                        "-fx-cursor: hand;");
                });

                statusButton.setOnMouseExited(event -> {
                    statusButton.setStyle(
                        "-fx-background-color: #FF5C5C; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-padding: 10px; " +
                        "-fx-cursor: hand;");
                });

                // Action event when button is clicked
                statusButton.setOnAction(event -> {
                    CustomerCareMessage req = getTableView().getItems().get(getIndex());
                    if (req != null) {
                        System.out.println("User ID: " + UserId);
                        System.out.println("Booking Id: " + req.getStatus());
                        
                        if ("Responded".equals(req.getStatus())) {
                            responsePane.setVisible(true);
                            responseTextField.setText(req.getResponse());
                            
                            deleteRequest.setOnAction(e -> {
                            	responseTextField.clear();
                    		    responsePane.setVisible(false);
                    		    try {
									TMS.deleteCustomerCareMessage(req.getID());
									setMyCustomerCareTable();
										
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
                    		});
                        }

                    }
                });
                
                
                
                closeResponse.setOnAction(event -> {
                	responseTextField.clear();
        		    responsePane.setVisible(false);  // Hide the ratePane
        		});
            }

            @Override
            protected void updateItem(String Status, boolean empty) {
                super.updateItem(Status, empty);

             

                if (empty || Status == null || Status.isEmpty()) {
                    setGraphic(null);  // Clear graphic if the status is empty or null
                } else {
                    statusButton.setText(Status);  // Set the button text to the status
                    setGraphic(statusButton);  // Add the button to the table cell
                }

                // Ensure the cell is properly redrawn
                requestLayout();
            }
        });



        
        DescriptionRequest1.setCellFactory(tc -> new TableCell<CustomerCareMessage, String>() {
            private final Tooltip tooltip = new Tooltip();

            {
                // Set the delay to 0 milliseconds (immediate display)
                tooltip.setShowDelay(javafx.util.Duration.millis(0));
                tooltip.setHideDelay(javafx.util.Duration.millis(0));
                tooltip.getStyleClass().add("tooltip");  // Apply the custom tooltip style
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);  // Clear tooltip if the cell is empty
                } else {
                    setText(item);  // Set the text for the table cell
                    tooltip.setText(item);  // Set the full description as the tooltip text
                    setTooltip(tooltip);  // Assign the tooltip to the table cell
                }
            }
        });
        
        
        myRequestTable1.setItems(data);
        
        myRequestTable1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
    }
    
    private void setMyBookingsHistoryTable() throws SQLException {
        // Retrieve the list of bookings for the current user

        ArrayList<MyBooking> allMyBookings = TMS.getBookingsHistoryByUserId(UserId);

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
            private final Button rateButton = new Button("rate");

            {
            	rateButton.setStyle(
            		    "-fx-background-color: #2563EB; " +
            		    "-fx-text-fill: #FFFFFF; " +
            		    "-fx-border-radius: 5px; " +
            		    "-fx-background-radius: 5px; " +
            		    "-fx-padding: 10px; " +
            		    "-fx-font-size: 14px; " +
            		    "-fx-font-weight: bold; " +
            		    "-fx-cursor: hand;");

            		rateButton.setOnMouseEntered(event -> {
            		    // Hover effect for the button
            		    rateButton.setStyle(
            		        "-fx-background-color: #1D4ED8; " +
            		        "-fx-text-fill: #FFFFFF; " +
            		        "-fx-border-radius: 5px; " +
            		        "-fx-background-radius: 5px; " +
            		        "-fx-padding: 10px; " +
            		        "-fx-font-size: 14px; " +
            		        "-fx-font-weight: bold; " +
            		        "-fx-cursor: hand;");
            		});

            		rateButton.setOnMouseExited(event -> {
            		    // Revert back to normal styling after hover
            		    rateButton.setStyle(
            		        "-fx-background-color: #2563EB; " +
            		        "-fx-text-fill: #FFFFFF; " +
            		        "-fx-border-radius: 5px; " +
            		        "-fx-background-radius: 5px; " +
            		        "-fx-padding: 10px; " +
            		        "-fx-font-size: 14px; " +
            		        "-fx-font-weight: bold; " +
            		        "-fx-cursor: hand;");
            		});
            		
            		
            		
            		rateButton.setOnAction(event -> {
            		    MyBooking booking = getTableView().getItems().get(getIndex());
            		    if (booking != null) {
            		        // Print the tour name and user ID
            		        System.out.println("Tour Name: " + booking.getTourName());
            		        System.out.println("User ID: " + UserId);
            		        System.out.println("Booking ID: " + booking.getID());
            		        
            		        ratelabel1.setText(booking.getTourName());
            		        ratelabel1.setStyle("-fx-text-fill: #83a4e8; -fx-font-size: 25;");

            		        ratePane.setVisible(true);
            		        rateTextField.setText(Integer.toString(booking.getRating()));

            		        RateButton.setOnAction(e -> {
            		            int newRating = Integer.parseInt(rateTextField.getText());
            		            TMS.changeRating(booking.getID(), newRating); // Update rating in the database
            		            
            		            booking.setRating(newRating); // Synchronize in-memory object
            		            System.out.println("Updated Rating: " + booking.getRating());
            		        });
            		    }
            		});

            		
            			
            		closeRate.setOnAction(event -> {
            		    ratePane.setVisible(false);  // Hide the ratePane
            		});

            		
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(rateButton);
                }
            }
        });
        
        TourDesc.setCellFactory(tc -> new TableCell<MyBooking, String>() {
            private final Tooltip tooltip = new Tooltip();

            {
                // Set the delay to 0 milliseconds (immediate display)
                tooltip.setShowDelay(javafx.util.Duration.millis(0));
                tooltip.setHideDelay(javafx.util.Duration.millis(0));
                tooltip.getStyleClass().add("tooltip");  // Apply the custom tooltip style
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);  // Clear tooltip if the cell is empty
                } else {
                    setText(item);  // Set the text for the table cell
                    tooltip.setText(item);  // Set the full description as the tooltip text
                    setTooltip(tooltip);  // Assign the tooltip to the table cell
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

        // Call insertRequest to insert the request into the database
        try {
            boolean isInserted = TMS.insertRequest(UserId, location, description);
            if (isInserted) {
                System.out.println("Request inserted successfully.");
                
                // Clear the text fields after successful insertion
                locationBox.clear();
                descriptionBox.clear();
            } else {
                System.out.println("Failed to insert the request.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while inserting the request.");
        }
    }
    
    private void setPopularToursTable() throws SQLException {
        // Retrieve the list of bookings for the current user
        ArrayList<Tours> tours = TMS.getTopTours2();

        // Convert the list of bookings to an ObservableList
        ObservableList<Tours> data = FXCollections.observableArrayList(tours);

        // Set the value for each column to match the corresponding method in the MyBooking object
        tourNameCol.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTourName())
        );
     
        tourPriceCol.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getPrice())
        );
     
        tourDurationCol.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getDuration())
        );

        tourTransportCol.setCellValueFactory(cellData -> 
        	new SimpleStringProperty(cellData.getValue().getStartDate())
        );
        
        tourActionCol.setCellFactory(column -> new TableCell<>() {
            private final Button tourActionButton = new Button("Join");

            {
            	tourActionButton.setStyle(
            		    "-fx-background-color: linear-gradient(to bottom, #FF7F50, #FF4500);" +
            		    "-fx-text-fill: white;" +
            		    "-fx-font-weight: bold;" +
            		    "-fx-font-size: 14px;" +
            		    "-fx-padding: 5px 20px;" +  // Reduced vertical padding
            		    "-fx-background-radius: 15px;" +
            		    "-fx-border-radius: 15px;" +
            		    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0.3, 0, 2);"
            		);
            	
                // Handle button click
                tourActionButton.setOnAction(event -> {
                    Tours tour = getTableView().getItems().get(getIndex());
                    if (tour != null) {
                        // Print the tour name and user ID
                        System.out.println("Tour Name: " + tour.getTourName());
                        System.out.println("User ID: " + UserId);

                        // Optional: Remove the booking from the table
                        tourTablePane.setVisible(false);
                        tourViewPane.setVisible(true);
                        viewTourDesc.setText(tour.getTourDescription());
                        viewTourDate.setText(tour.getStartDate());
                        viewTourId.setText(tour.getTourID());
                        viewTourName.setText(tour.getTourName());
                        
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(tourActionButton);
                }
            }
        });
        

        // Apply the data to the table
        tourTable.setItems(data);
        // Set font styling for the whole table
        tourTable.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
        
    }
    
    private void setAllToursTable() throws SQLException {
        // Retrieve the list of bookings for the current user
        ArrayList<Tours> tours = TMS.getAllTours2();

        // Convert the list of bookings to an ObservableList
        ObservableList<Tours> data = FXCollections.observableArrayList(tours);

        // Set the value for each column to match the corresponding method in the MyBooking object
        tourNameCol.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTourName())
        );
     
        tourPriceCol.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getPrice())
        );
     
        tourDurationCol.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getDuration())
        );

        tourTransportCol.setCellValueFactory(cellData -> 
        	new SimpleStringProperty(cellData.getValue().getStartDate())
        );
        
        tourActionCol.setCellFactory(column -> new TableCell<>() {
            private final Button tourActionButton = new Button("Join");

            {
            	tourActionButton.setStyle(
            		    "-fx-background-color: linear-gradient(to bottom, #FF7F50, #FF4500);" +
            		    "-fx-text-fill: white;" +
            		    "-fx-font-weight: bold;" +
            		    "-fx-font-size: 14px;" +
            		    "-fx-padding: 5px 20px;" +  // Reduced vertical padding
            		    "-fx-background-radius: 15px;" +
            		    "-fx-border-radius: 15px;" +
            		    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0.3, 0, 2);"
            		);
            	
                // Handle button click
                tourActionButton.setOnAction(event -> {
                    Tours tour = getTableView().getItems().get(getIndex());
                    if (tour != null) {
                        // Print the tour name and user ID
                        System.out.println("Tour Name: " + tour.getTourName());
                        System.out.println("User ID: " + UserId);

                        // Optional: Remove the booking from the table
                        tourTablePane.setVisible(false);
                        tourViewPane.setVisible(true);
                        viewTourDesc.setText(tour.getTourDescription());
                        viewTourDate.setText(tour.getStartDate());
                        viewTourId.setText(tour.getTourID());
                        viewTourName.setText(tour.getTourName());
                        
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(tourActionButton);
                }
            }
        });
        

        // Apply the data to the table
        tourTable.setItems(data);
        // Set font styling for the whole table
        tourTable.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
        
    }
    
    
    public void backToTourTable()
    {
    	tourViewPane.setVisible(false);
    	paymentPane.setVisible(false);
    	tourTablePane.setVisible(true);
    }
    
    public void joinTour()
    {
    	if((namePayment.getText() != null && namePayment.getText().trim().isEmpty() == false) && (cardPayment.getText() != null && cardPayment.getText().trim().isEmpty() == false) && (expPayment.getText() != null && expPayment.getText().trim().isEmpty() == false) && (cvvPayment.getText() != null && cvvPayment.getText().trim().isEmpty() == false))
    	{
    		TMS.joinTour(UserId, Integer.parseInt(viewTourId.getText()));
    	}
    	namePayment.clear();
    	cardPayment.clear();
    	expPayment.clear();
    	cvvPayment.clear();
    	
    	tourViewPane.setVisible(false);
    	paymentPane.setVisible(false);
    	tourTablePane.setVisible(true);
    }
    
    public void goToPaymentPage()
    {
    	tourViewPane.setVisible(false);
    	paymentPane.setVisible(true);
    	tourTablePane.setVisible(false);
    }
    
    public void reportIssue()
    {
    	String title = supportTitle.getText();
    	String detail = supportDetail.getText();
    	
    	if((supportTitle.getText() != null && supportTitle.getText().trim().isEmpty() == false) && (supportDetail.getText() != null && supportDetail.getText().trim().isEmpty() == false))
    	{
    		TMS.addSupportMessage(UserId, title, detail);
    		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    		
    	}
    	
    	supportTitle.clear();
    	supportDetail.clear();
    }
    
    private void handleProfileButtonAction() {
    	SetVisibilityFalse();
        ProfilePane.setVisible(true);
        User user = TMS.getUser(UserId);
        NameP.setText(user.getFullName());
        EmailP.setText(user.getEmail());
        PassP.setText(user.getPassword());
        
        
        
    }
    
    private void handleSaveProfileButtonAction() {
        // Get updated values from the text fields
        String updatedName = NameP.getText();
        String updatedEmail = EmailP.getText();
        String updatedPassword = PassP.getText();

        // Perform input validation (optional)
        if (updatedName.isEmpty() || updatedEmail.isEmpty() || updatedPassword.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        // Create a User object with updated values
        User updatedUser = new User(UserId, updatedEmail, updatedName, updatedPassword);

        // Update the user in the database
        boolean isUpdated = TMS.updateUser(updatedUser);

        if (isUpdated) {
            System.out.println("Profile updated successfully!");
        } else {
            System.out.println("Failed to update profile.");
        }
        
        SetVisibilityFalse();
    }
    
}