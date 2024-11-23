package application;

import Models.Booking;
import Models.TopCustomers;
import Models.Tours;
import Models.TransportProvider;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class AdminController {
	@FXML
	private Button dashboardTab;
	@FXML
	private Button toursTab;
	@FXML
	private Button bookingTab;
	@FXML
	private Button customersTab;
	@FXML
	private Button transportTab;
	@FXML
	private Button settingsTab;
	@FXML
	private Pane dashboardDiv;
	@FXML
	private ScrollPane dashboardMainDiv;
	@FXML
	private Pane toursMainDiv;
	@FXML
	private Pane BookingMainDiv;
	@FXML
	private Pane transportMainDiv;
	@FXML
	private ScrollPane editTourMainDiv;

	@FXML
	private Label headerEditTour;
	@FXML
	private Label bookingEditTour;
	@FXML
	private TextField textImage1EditTour;
	@FXML
	private TextField textImage2EditTour;
	@FXML
	private TextField textImage3EditTour;
	@FXML
	private TextField tourNameEditTour;
	@FXML
	private TextArea descriptionEditTour;
	@FXML
	private TextField priceEditTour;
	@FXML
	private TextField duarationEditTour;
	@FXML
	private TextField googleMapEditTour;
	@FXML
	private ComboBox transportProviderEditTour;
	@FXML
	private ImageView image1;
	@FXML
	private ImageView image2;
	@FXML
	private ImageView image3;
	@FXML
	private DatePicker tourDateEditTour;
	@FXML
	private Button editTourButton;
	@FXML
	private TextField searchTourText;
	@FXML
	private Button showCompletedButton;
	@FXML
	private ComboBox bookingStatusDropDown;
	@FXML
	private TextField searchBooking;
	@FXML
	private ScrollPane editTransportProviderMainDiV;
	@FXML
	private TextField providerNameBooking;
	@FXML
	private TextField vehicleTypeBooking;
	@FXML
	private TextField contactInfoBooking;
	@FXML
	private TextField fleetSizeBooking;
	@FXML
	private Button transportProviderButton;
	@FXML
	private Button addTransportProviderButton;
	@FXML
	private Label addTransportProviderHeader;

	// ----------------------------TABLE
	@FXML
	private TableView<TopCustomers> recentBookingTable;
	@FXML
	private TableColumn<TopCustomers, String> CustomerColumn;
	@FXML
	private TableColumn<TopCustomers, String> nobColumn;
	@FXML
	private TableColumn<TopCustomers, String> tapColumn;

	// ----------------------------TABLE
	@FXML
	private TableView<Tours> popularToursTable;
	@FXML
	private TableColumn<Tours, String> tournameColumn;
	@FXML
	private TableColumn<Tours, String> bookingsColumn;
	@FXML
	private TableColumn<Tours, String> ratingColumn;

	// ----------------------------TABLE

	@FXML
	private TableView<Tours> tourTable;
	@FXML
	private TableColumn<Tours, String> nameTourColumn;
	@FXML
	private TableColumn<Tours, String> durationTourColumn;
	@FXML
	private TableColumn<Tours, String> priceTourColumn;
	@FXML
	private TableColumn<Tours, String> bookingsTourColumn;
	@FXML
	private TableColumn<Tours, Void> actionColumn;
	@FXML
	private TableColumn<Tours, String> StatusTourColumn;

	// ----------------------------TABLE

	@FXML
	private TableView<Booking> bookingTable;
	@FXML
	private TableColumn<Booking, String> tourBookingColumn;
	@FXML
	private TableColumn<Booking, String> customerBookingColumn;
	@FXML
	private TableColumn<Booking, String> dateBookingColumn;
	@FXML
	private TableColumn<Booking, String> statusBookingColumn;
	@FXML
	private TableColumn<Booking, Void> actionBookingColumn;

	// ----------------------------TABLE
	@FXML
	private TableView<TransportProvider> transportTable;
	@FXML
	private TableColumn<TransportProvider, String> nameTransportColumn;
	@FXML
	private TableColumn<TransportProvider, String> vehicleTypeTransportColumn;
	@FXML
	private TableColumn<TransportProvider, String> fleetSizeTransportColumn;
	@FXML
	private TableColumn<TransportProvider, String> contactTransportColumn;
	@FXML
	private TableColumn<TransportProvider, String> ratingTransportColumn;
	@FXML
	private TableColumn<TransportProvider, Void> actionTransportColumn;

	private TourismManagementSystem TMS;
	
	private Tours editTourCurrent;
	private TransportProvider editTourTransport;

	private boolean addTour;
	
	private boolean addTranportProvider;

	public AdminController() throws SQLException {
		TMS = new TourismManagementSystem();
	}

	@FXML
	public void initialize() throws SQLException {
		
		bookingStatusDropDown.getItems().addAll(
		        "All Bookings",
		        "Pending Bookings",
		        "Confirmed Bookings",
		        "Completed Bookings",
		        "Cancelled Bookings"
		    );

		    // Optionally, set a default value
		    bookingStatusDropDown.setValue("All Booking");

		    // Add style (if needed)
		    bookingStatusDropDown.setStyle("-fx-background-color: #374151; -fx-text-fill: #9CA3AF; " +
		                                   "-fx-border-color: transparent; -fx-font-size: 15px; -fx-font-weight: bold;");
		
		addTour = false;
		addTranportProvider = false;
		descriptionEditTour.setWrapText(true);
		ArrayList<TopCustomers> topCustomers = TMS.getTop3Customers();
		ObservableList<TopCustomers> data = FXCollections.observableArrayList(topCustomers);

		CustomerColumn.setCellValueFactory(new PropertyValueFactory<TopCustomers, String>("Customer"));
		setBackGroundColorTopCustomers(CustomerColumn);
		nobColumn.setCellValueFactory(new PropertyValueFactory<TopCustomers, String>("NumberofBookings"));
		setBackGroundColorTopCustomers(nobColumn);
		tapColumn.setCellValueFactory(new PropertyValueFactory<TopCustomers, String>("TotalAmountPaid"));
		setBackGroundColorTopCustomers(tapColumn);

		recentBookingTable.setItems(data);

		ArrayList<Tours> topTours = TMS.getTopTours();
		ObservableList<Tours> data2 = FXCollections.observableArrayList(topTours);

		tournameColumn.setCellValueFactory(new PropertyValueFactory<Tours, String>("TourName"));
		setBackGroundColor(tournameColumn);
		bookingsColumn.setCellValueFactory(new PropertyValueFactory<Tours, String>("Bookings"));
		setBackGroundColor(bookingsColumn);
		ratingColumn.setCellValueFactory(new PropertyValueFactory<Tours, String>("Rating"));
		setBackGroundColor(ratingColumn);

		popularToursTable.setItems(data2);

	}

	private void setBackGroundColor(TableColumn<Tours, String> column) {
		column.setCellFactory(col -> {
			return new TableCell<Tours, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
						setStyle("-fx-background-color: #1F2937; -fx-border-color: transparent;"); // Set consistent
																									// background color
																									// for empty rows
					} else {
						setText(item);
						setStyle(
								"-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
					}
				}
			};
		});
	}

	private void setBackGroundColorTopCustomers(TableColumn<TopCustomers, String> test) {
		test.setCellFactory(column -> {
			return new TableCell<TopCustomers, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
						setStyle(
								"-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
					} else {
						setText(item);
						setStyle(
								"-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
					}
				}
			};
		});
	}

	private void setBackGroundColorBooking(TableColumn<Booking, String> test) {
		test.setCellFactory(column -> {
			return new TableCell<Booking, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
						setStyle(
								"-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
					} else {
						setText(item);
						setStyle(
								"-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
					}
				}
			};
		});
	}

	private void setBackGroundColorTransportProvider(TableColumn<TransportProvider, String> test) {
		test.setCellFactory(column -> {
			return new TableCell<TransportProvider, String>() {
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

	private void setAllToursTable(ArrayList<Tours> allTours) throws SQLException {

		ObservableList<Tours> data3 = FXCollections.observableArrayList(allTours);

		// Set up other columns
		nameTourColumn.setCellValueFactory(new PropertyValueFactory<>("TourName"));
		setBackGroundColor(nameTourColumn);
		durationTourColumn.setCellValueFactory(new PropertyValueFactory<>("Duration"));
		setBackGroundColor(durationTourColumn);
		priceTourColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
		setBackGroundColor(priceTourColumn);
		bookingsTourColumn.setCellValueFactory(new PropertyValueFactory<>("Bookings"));
		setBackGroundColor(bookingsTourColumn);
		StatusTourColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		setBackGroundColor(bookingsTourColumn);

		// Set StatusTourColumn
		StatusTourColumn.setCellFactory(param -> new TableCell<>() {
			private final Button statusButton = new Button();

			{
				// Set base styling for the button
				statusButton.setStyle("-fx-font-size: 10px; " + // Smaller text
						"-fx-font-weight: normal; " + // Normal font weight
						"-fx-padding: 2px 5px; " + // Reduced padding
						"-fx-border-radius: 5px; " + // Slightly rounded corners
						"-fx-cursor: default;"); // No pointer cursor
				statusButton.setFocusTraversable(false);
				this.setStyle("-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
			}

			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);

				if (empty) {
					setGraphic(null);
				} else {
					// Get the tour associated with the current row
					Tours tour = getTableView().getItems().get(getIndex());

					try {
						// Parse start date and duration
						LocalDate startDate = LocalDate.parse(tour.getStartDate());
						int durationDays = Integer.parseInt(tour.getDuration());
						LocalDate endDate = startDate.plusDays(durationDays);
						LocalDate today = LocalDate.now();

						if (endDate.isAfter(today)) {
							// Future or in-progress tour
							statusButton.setText("Active");
							statusButton.setStyle("-fx-background-color: #2563EB; " + // Blue background
									"-fx-text-fill: white; " + // White text
									"-fx-font-size: 14px; " + // Smaller text
									"-fx-font-weight: bold; " + // Normal font weight
									"-fx-padding: 2px 5px; " + // Reduced padding
									"-fx-border-radius: 10px;");
						} else {
							// Completed tour
							statusButton.setText("Completed");
							statusButton.setStyle("-fx-background-color: #10B981; " + // Green background
									"-fx-text-fill: white; " + // White text
									"-fx-font-size: 14px; " + // Smaller text
									"-fx-font-weight: bold; " + // Normal font weight
									"-fx-padding: 2px 5px; " + // Reduced padding
									"-fx-border-radius: 5px;");
						}
					} catch (Exception e) {
						// Handle any parsing or logic errors
						e.printStackTrace();
					}

					// Set the button as the graphic
					setGraphic(statusButton);
				}
			}
		});

		// Set data to the table
		tourTable.setItems(data3);

		// Set action column (Edit/Delete buttons)
		actionColumn.setCellFactory(param -> new TableCell<>() {
			private final Button editButton = new Button();
			private final Button deleteButton = new Button();
			private final HBox buttonContainer = new HBox(10);

			{
				// Setup edit button
				Image editImage = new Image(getClass().getResourceAsStream("..\\icons\\icons8-edit-64.png"));
				ImageView editImageView = new ImageView(editImage);
				editImageView.setFitWidth(20);
				editImageView.setFitHeight(20);
				editButton.setGraphic(editImageView);
				editButton.getStyleClass().add("table-button");

				// Setup delete button
				Image deleteImage = new Image(getClass().getResourceAsStream("..\\icons\\icons8-delete-48.png"));
				ImageView deleteImageView = new ImageView(deleteImage);
				deleteImageView.setFitWidth(20);
				deleteImageView.setFitHeight(20);
				deleteButton.setGraphic(deleteImageView);
				deleteButton.getStyleClass().add("table-button");

				this.setStyle("-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");

				// Add actions for buttons
				editButton.setOnAction(event -> {
					try {
						Tours selectedTour = getTableView().getItems().get(getIndex());
						editTourCurrent = selectedTour;
						System.out.println("Edit button clicked for: " + selectedTour.getTourImages());
						setEditTourTabEmpty();
						hideAllPane();
						editTourButton.setText("Save Changes");
						addTour = false;
						editTourMainDiv.setVisible(true);
						headerEditTour.setText(selectedTour.getTourName());
						bookingEditTour.setText(selectedTour.getBookings());
						tourNameEditTour.setText(selectedTour.getTourName());
						descriptionEditTour.setText(selectedTour.getTourDescription());
						priceEditTour.setText(selectedTour.getPrice());
						duarationEditTour.setText(selectedTour.getDuration());
						googleMapEditTour.setText(selectedTour.getGoogleMapLink());
						ArrayList<String> img = selectedTour.getTourImages();
						if (img.size() > 0) {
							textImage1EditTour.setText(img.get(0));
							image1.setImage(new Image(getClass().getResourceAsStream(img.get(0))));
						}
						if (img.size() > 1) {
							textImage2EditTour.setText(img.get(1));
							image2.setImage(new Image(getClass().getResourceAsStream(img.get(1))));
						}
						if (img.size() > 2) {
							textImage3EditTour.setText(img.get(2));
							image3.setImage(new Image(getClass().getResourceAsStream(img.get(2))));
						}
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						LocalDate localDate = LocalDate.parse(selectedTour.getStartDate());
						tourDateEditTour.setValue(localDate);

						ArrayList<TransportProvider> allTransportProviders = TMS.getAllTransportProviders();

						transportProviderEditTour.getItems().clear();

						for (TransportProvider provider : allTransportProviders) {
							String formattedProvider = provider.getName() + " | " + provider.getVehicleTypes() + " | "
									+ provider.getContact() + " | " + provider.getRating();
							transportProviderEditTour.getItems().add(formattedProvider);

							// Set selected item based on TransportID
							if (provider.getID().equals(selectedTour.getTransportID())) {
								transportProviderEditTour.setValue(formattedProvider);
							}
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				});

				deleteButton.setOnAction(event -> {
					Tours selectedTour = getTableView().getItems().get(getIndex());
					System.out.println("Delete button clicked for: " + selectedTour.getTourName());
					TMS.deleteTour(selectedTour.getTourID());
					hideAllPane();
					removeAllButtonClasses();
					toursTab.getStyleClass().remove("tab-selected");
					toursTab.getStyleClass().add("tab");
					try {
						toursSelected();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				});

				buttonContainer.getChildren().addAll(editButton, deleteButton);
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(buttonContainer);
				}
			}
		});
	}

	private void setAllBookingTable(ArrayList<Booking> allBookings ) throws SQLException {

		ObservableList<Booking> data = FXCollections.observableArrayList(allBookings);

		tourBookingColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Tour"));
		setBackGroundColorBooking(tourBookingColumn);
		customerBookingColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Customer"));
		setBackGroundColorBooking(customerBookingColumn);
		dateBookingColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Date"));
		setBackGroundColorBooking(dateBookingColumn);

		statusBookingColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Status"));

		statusBookingColumn.setCellFactory(column -> new TableCell<Booking, String>() {
		    private final Button statusButton = new Button();

		    {
		        // Base styling for the button
		        statusButton.setStyle("-fx-text-fill: white; " + 
		                              "-fx-border-color: transparent; " + 
		                              "-fx-font-size: 12px; " + 
		                              "-fx-font-weight: bold; " + 
		                              "-fx-padding: 3 8; " + 
		                              "-fx-border-radius: 10; " + 
		                              "-fx-background-radius: 4;");
		        statusButton.setPrefHeight(20); // Adjust height
		        this.setStyle("-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
		    }

		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);

		        if (empty || item == null) {
		            setGraphic(null);
		        } else {
		            // Set button text based on status
		            statusButton.setText(item);

		            // Change button color based on booking status
		            switch (item.toLowerCase()) {
		                case "pending":
		                    statusButton.setStyle("-fx-background-color: #F59E0B; -fx-text-fill: white; " +
		                                          "-fx-border-color: transparent; -fx-font-size: 12px; " +
		                                          "-fx-font-weight: bold; -fx-padding: 3 8; -fx-border-radius: 10; " +
		                                          "-fx-background-radius: 4;");
		                    break;
		                case "confirmed":
		                    statusButton.setStyle("-fx-background-color: #16A34A; -fx-text-fill: white; " +
		                                          "-fx-border-color: transparent; -fx-font-size: 12px; " +
		                                          "-fx-font-weight: bold; -fx-padding: 3 8; -fx-border-radius: 10; " +
		                                          "-fx-background-radius: 4;");
		                    break;
		                case "completed":
		                    statusButton.setStyle("-fx-background-color: #9333EA; -fx-text-fill: white; " +
		                                          "-fx-border-color: transparent; -fx-font-size: 12px; " +
		                                          "-fx-font-weight: bold; -fx-padding: 3 8; -fx-border-radius: 10; " +
		                                          "-fx-background-radius: 4;");
		                    break;
		                case "cancelled":
		                    statusButton.setStyle("-fx-background-color: #EF4444; -fx-text-fill: white; " +
		                                          "-fx-border-color: transparent; -fx-font-size: 12px; " +
		                                          "-fx-font-weight: bold; -fx-padding: 3 8; -fx-border-radius: 10; " +
		                                          "-fx-background-radius: 4;");
		                    break;
		                default:
		                    // Default styling for unknown status
		                    statusButton.setStyle("-fx-background-color: #6B7280; -fx-text-fill: white; " +
		                                          "-fx-border-color: transparent; -fx-font-size: 12px; " +
		                                          "-fx-font-weight: bold; -fx-padding: 3 8; -fx-border-radius: 10; " +
		                                          "-fx-background-radius: 4;");
		                    break;
		            }

		            // Set the button as the cell's graphic
		            setGraphic(statusButton);
		        }
		    }
		});

		bookingTable.setItems(data);

		actionBookingColumn.setCellFactory(column -> new TableCell<Booking, Void>() {
			private final ComboBox<String> actionComboBox = new ComboBox<>();

			{
				// ComboBox base style
				actionComboBox.setStyle("-fx-background-color: #374151; -fx-text-fill: #9CA3AF; "
						+ "-fx-border-color: transparent; -fx-font-size: 12px; -fx-font-weight: bold; -fx-cursor: hand;");
				this.setStyle("-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");

				// Style the displayed value (button cell)
				actionComboBox.setButtonCell(new ListCell<>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setText("Select Action"); // Default text
							setStyle("-fx-background-color: #374151; -fx-text-fill: #9CA3AF; "
									+ "-fx-font-size: 12px; -fx-font-weight: bold;");
						} else {
							setText(item);
							setStyle("-fx-background-color: #374151; -fx-text-fill: #9CA3AF; "
									+ "-fx-font-size: 12px; -fx-font-weight: bold;");
						}
					}
				});

				// Style dropdown items
				actionComboBox.setCellFactory(listView -> new ListCell<>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setText(null);
							setStyle(null);
						} else {
							setText(item);
							setStyle("-fx-background-color: #374151; -fx-text-fill: #9CA3AF; "
									+ "-fx-font-size: 12px; -fx-font-weight: bold;");
							// Add hover effect
							setOnMouseEntered(
									event -> setStyle("-fx-background-color: #1F2937; -fx-text-fill: #9CA3AF; "
											+ "-fx-font-size: 12px; -fx-font-weight: bold; -fx-cursor: hand;"));

							setOnMouseExited(event -> setStyle("-fx-background-color: #374151; -fx-text-fill: #9CA3AF; "
									+ "-fx-font-size: 12px; -fx-font-weight: bold;"));
						}
					}
				});

				actionComboBox.setPrefWidth(150); // Fixed width
				actionComboBox.setPrefHeight(30); // Fixed height
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					Booking booking = getTableView().getItems().get(getIndex());
					actionComboBox.getItems().clear(); // Clear previous options

					// Add default option
					actionComboBox.getItems().add("Select Action");

					// Add dynamic options based on status
					if ("Pending".equalsIgnoreCase(booking.getStatus())) {
						actionComboBox.getItems().addAll("Confirm Booking", "Cancel Booking");
					} else if ("Confirmed".equalsIgnoreCase(booking.getStatus())) {
						actionComboBox.getItems().add("Cancel Booking");
					}

					// Restore the previously selected value
					String selectedAction = booking.getSelectedAction();
					if (selectedAction != null && actionComboBox.getItems().contains(selectedAction)) {
						actionComboBox.setValue(selectedAction); // Set to previously selected value
					} else {
						actionComboBox.setValue("Select Action"); // Set default value
					}

					// Handle ComboBox actions and save the selected value
					actionComboBox.setOnAction(event -> {
						String selectedActionValue = actionComboBox.getValue();
						if (!"Select Action".equals(selectedActionValue)) {
							booking.setSelectedAction(selectedActionValue); // Save selected action to Booking
							if ("Confirm Booking".equals(selectedActionValue)) {
								TMS.confirmTourBooking(booking.getID());
								hideAllPane();
								removeAllButtonClasses();
								bookingTab.getStyleClass().remove("tab-selected");
								bookingTab.getStyleClass().add("tab");
								try {
									bookingsSelected();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if ("Cancel Booking".equals(selectedActionValue)) {
								// Add logic to cancel the booking
							}
						}
					});

					setGraphic(actionComboBox); // Set the ComboBox as the cell's graphic
				}
			}
		});

	}

	private void setAllTransportProvider(ArrayList<TransportProvider> allTransportProvider ) throws SQLException {
		
		ObservableList<TransportProvider> data3 = FXCollections.observableArrayList(allTransportProvider);

		nameTransportColumn.setCellValueFactory(new PropertyValueFactory<TransportProvider, String>("Name"));
		setBackGroundColorTransportProvider(nameTransportColumn);
		vehicleTypeTransportColumn
				.setCellValueFactory(new PropertyValueFactory<TransportProvider, String>("VehicleTypes"));
		setBackGroundColorTransportProvider(vehicleTypeTransportColumn);
		fleetSizeTransportColumn.setCellValueFactory(new PropertyValueFactory<TransportProvider, String>("FleetSize"));
		setBackGroundColorTransportProvider(fleetSizeTransportColumn);
		contactTransportColumn.setCellValueFactory(new PropertyValueFactory<TransportProvider, String>("Contact"));
		setBackGroundColorTransportProvider(contactTransportColumn);
		ratingTransportColumn.setCellValueFactory(new PropertyValueFactory<TransportProvider, String>("Rating"));
		setBackGroundColorTransportProvider(ratingTransportColumn);

		transportTable.setItems(data3);

		actionTransportColumn.setCellFactory(param -> new TableCell<>() {
			private final Button editButton = new Button();
			private final Button deleteButton = new Button();
			private final HBox buttonContainer = new HBox(10); // HBox to hold the buttons, with spacing

			{
				this.setStyle("-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
				// Edit Button
				Image editImage = new Image(getClass().getResourceAsStream("..\\icons\\icons8-edit-64.png"));
				ImageView editImageView = new ImageView(editImage);
				editImageView.setFitWidth(20); // Set image width
				editImageView.setFitHeight(20); // Set image height
				editButton.setGraphic(editImageView);
				editButton.getStyleClass().add("table-button"); // Add style class for the button

				// Delete Button
				Image deleteImage = new Image(getClass().getResourceAsStream("..\\icons\\icons8-delete-48.png"));
				ImageView deleteImageView = new ImageView(deleteImage);
				deleteImageView.setFitWidth(20); // Set image width
				deleteImageView.setFitHeight(20); // Set image height
				deleteButton.setGraphic(deleteImageView);
				deleteButton.getStyleClass().add("table-button"); // Add style class for the button

				// Set up actions for buttons
				editButton.setOnAction(event -> {
					TransportProvider selectedProvider = getTableView().getItems().get(getIndex());
					System.out.println("Edit button clicked for: " + selectedProvider.getName());
					addTranportProvider = false;
					hideAllPane();
					setTransportTabEmpty();
					editTourTransport = selectedProvider;
					addTransportProviderHeader.setText("Edit Transport Provider");
					transportProviderButton.setText("Save Changes");
					editTransportProviderMainDiV.setVisible(true);
					providerNameBooking.setText(selectedProvider.getName());
					fleetSizeBooking.setText(selectedProvider.getFleetSize());
					contactInfoBooking.setText(selectedProvider.getContact());
					vehicleTypeBooking.setText(selectedProvider.getVehicleTypes());

				});

				deleteButton.setOnAction(event -> {
					TransportProvider selectedProvider = getTableView().getItems().get(getIndex());
					System.out.println("Delete button clicked for: " + selectedProvider.getName());
					
				});

				// Add buttons to the HBox
				buttonContainer.getChildren().addAll(editButton, deleteButton);
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(buttonContainer);
				}
			}
		});
	}
	
	private void setTransportTabEmpty() {
		providerNameBooking.setText("");
		fleetSizeBooking.setText("");
		contactInfoBooking.setText("");
		vehicleTypeBooking.setText("");
	}
	
	private void setEditTourTabEmpty() {
		headerEditTour.setText("");
		bookingEditTour.setText("");
		tourNameEditTour.setText("");
		descriptionEditTour.setText("");
		priceEditTour.setText("");
		duarationEditTour.setText("");
		googleMapEditTour.setText("");
		
		textImage1EditTour.setText("");
		image1.setImage(new Image(getClass().getResourceAsStream("")));
		textImage2EditTour.setText("");
		image2.setImage(new Image(getClass().getResourceAsStream("")));
		textImage3EditTour.setText("");
		image3.setImage(new Image(getClass().getResourceAsStream("")));

		tourDateEditTour.setValue(null);


		transportProviderEditTour.setValue("");

	}

	public void editTour() {
		try {
			// Fetch data from fields and labels
			String tourName = tourNameEditTour.getText();
			String bookings = bookingEditTour.getText();
			String description = descriptionEditTour.getText();
			String price = priceEditTour.getText();
			String duration = duarationEditTour.getText();
			String googleMapLink = googleMapEditTour.getText();
			LocalDate startDate1 = tourDateEditTour.getValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String startDate = startDate1.format(formatter);

			// Fetch images
			ArrayList<String> tourImages = new ArrayList<>();
			if (!textImage1EditTour.getText().isEmpty()) {
				tourImages.add(textImage1EditTour.getText());
			}
			if (!textImage2EditTour.getText().isEmpty()) {
				tourImages.add(textImage2EditTour.getText());
			}
			if (!textImage3EditTour.getText().isEmpty()) {
				tourImages.add(textImage3EditTour.getText());
			}

			// Fetch the selected transport provider
			String selectedTransportProvider = (String) transportProviderEditTour.getValue();
			String transportProviderID = null;

			if (selectedTransportProvider != null) {
				// Split the ComboBox value to match Name, VehicleTypes, Contact, and Rating
				String[] parts = selectedTransportProvider.split(" \\| ");
				String name = parts[0].trim();
				String vehicleTypes = parts[1].trim();

				// Get all transport providers and find the matching ID
				ArrayList<TransportProvider> allTransportProviders = TMS.getAllTransportProviders();
				for (TransportProvider provider : allTransportProviders) {
					if (provider.getName().equals(name) && provider.getVehicleTypes().equals(vehicleTypes)) {
						transportProviderID = provider.getID();
						break;
					}
				}
			}

			if (transportProviderID == null) {
				throw new Exception("TransportProvider ID not found for the selected transport provider.");
			}
			Tours updatedTour = null;

			updatedTour = new Tours(editTourCurrent.getTourID(), tourName, bookings, description, price, duration, googleMapLink,
					startDate.toString(), transportProviderID, tourImages);

			// Print the updated tour details for debugging
			System.out.println("Updated Tour: " + updatedTour);
			if (addTour == false)
				TMS.updateTour(updatedTour);
			else
				TMS.addNewTour(updatedTour);
			hideAllPane();
			removeAllButtonClasses();
			toursTab.getStyleClass().remove("tab-selected");
			toursTab.getStyleClass().add("tab");
			toursSelected();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error editing tour: " + e.getMessage());
		}
	}

	public void addNewTour() throws SQLException {
		hideAllPane();
		editTourMainDiv.setVisible(true);
		addTour = true;
		editTourButton.setText("Add Tour");
		bookingEditTour.setText("0");
		editTourMainDiv.setVisible(true);
		ArrayList<TransportProvider> allTransportProviders = TMS.getAllTransportProviders();

		transportProviderEditTour.getItems().clear(); // Clear existing items

		for (TransportProvider provider : allTransportProviders) {
			String formattedProvider = provider.getName() + " | " + provider.getVehicleTypes() + " | "
					+ provider.getContact() + " | " + provider.getRating();
			transportProviderEditTour.getItems().add(formattedProvider);
		}

	}

	public void searchTour(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) { // Check if the Enter key is pressed
			String searchText = searchTourText.getText().trim(); // Get and trim the search text

			try {
				// Get all tours from TMS
				ArrayList<Tours> allTours = TMS.getAllTours();

				if (searchText.isEmpty()) {
					// If the search text is empty, restore all tours
					setAllToursTable(allTours);
					System.out.println("Search text is empty. Restoring all tours.");
					return;
				}

				// Create a new list to store matching tours
				ArrayList<Tours> matchingTours = new ArrayList<>();

				// Filter tours based on the search text (best match based on startsWith)
				for (Tours tour : allTours) {
					if (tour.getTourName().toLowerCase().startsWith(searchText.toLowerCase())) {
						matchingTours.add(tour);
					}
				}

				// Pass the filtered tours to the setAllToursTable function
				setAllToursTable(matchingTours);

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Error fetching tours: " + e.getMessage());
			}
		}
	}

	public void searchBooking(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) { // Check if the Enter key is pressed
			String searchText = searchBooking.getText().trim(); // Get and trim the search text
			System.out.print("hello");
			try {
				// Get all tours from TMS
				ArrayList<Booking> allTours = TMS.getAllBookings();

				if (searchText.isEmpty()) {
					// If the search text is empty, restore all tours
					setAllBookingTable(allTours);
					System.out.println("Search text is empty. Restoring all tours.");
					return;
				}

				// Create a new list to store matching tours
				ArrayList<Booking> matchingTours = new ArrayList<>();

				// Filter tours based on the search text (best match based on startsWith)
				for (Booking tour : allTours) {
					if (tour.getCustomer().toLowerCase().startsWith(searchText.toLowerCase())) {
						matchingTours.add(tour);
					}
				}

				// Pass the filtered tours to the setAllToursTable function
				setAllBookingTable(matchingTours);

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Error fetching tours: " + e.getMessage());
			}
		}
	}
	
	public void showCompetedTours() throws SQLException {
		if (showCompletedButton.getText().equals("Show Completed")) {
			showCompletedButton.setText("Showing Completed");

			// Get all tours
			ArrayList<Tours> allTours = TMS.getAllTours();

			// Filter completed tours
			LocalDate today = LocalDate.now();
			ArrayList<Tours> completedTours = new ArrayList<>();

			for (Tours tour : allTours) {
				LocalDate startDate = LocalDate.parse(tour.getStartDate()); // Assuming startDate is in ISO format
																			// (yyyy-MM-dd)
				int durationDays = Integer.parseInt(tour.getDuration());
				LocalDate endDate = startDate.plusDays(durationDays);

				// Add to completed tours if the end date is before today
				if (endDate.isBefore(today)) {
					completedTours.add(tour);
				}
			}

			// Pass the completed tours to the table
			setAllToursTable(completedTours);

		} else {
			showCompletedButton.setText("Show Completed");
			hideAllPane();
			removeAllButtonClasses();
			toursTab.getStyleClass().remove("tab-selected");
			toursTab.getStyleClass().add("tab");
			toursSelected();
		}

	}

	public void bookingDropDown()  throws SQLException {
		ArrayList<Booking> allBookings = TMS.getAllBookings();
		if(bookingStatusDropDown.getValue().equals("All Bookings")) {
			setAllBookingTable(allBookings);
		} else if (bookingStatusDropDown.getValue().equals("Pending Bookings")) {

			ArrayList<Booking> filteredBookings = allBookings.stream()
			    .filter(booking -> "Pending".equalsIgnoreCase(booking.getStatus()))
			    .collect(Collectors.toCollection(ArrayList::new));

			setAllBookingTable(filteredBookings);
		} else if (bookingStatusDropDown.getValue().equals("Confirmed Bookings")) {

			ArrayList<Booking> filteredBookings = allBookings.stream()
			    .filter(booking -> "Confirmed".equalsIgnoreCase(booking.getStatus()))
			    .collect(Collectors.toCollection(ArrayList::new));

			setAllBookingTable(filteredBookings);
		} else if (bookingStatusDropDown.getValue().equals("Completed Bookings")) {

			ArrayList<Booking> filteredBookings = allBookings.stream()
			    .filter(booking -> "Completed".equalsIgnoreCase(booking.getStatus()))
			    .collect(Collectors.toCollection(ArrayList::new));

			setAllBookingTable(filteredBookings);
		} else if (bookingStatusDropDown.getValue().equals("Cancelled Bookings")) {

			ArrayList<Booking> filteredBookings = allBookings.stream()
			    .filter(booking -> "Cancelled".equalsIgnoreCase(booking.getStatus()))
			    .collect(Collectors.toCollection(ArrayList::new));

			setAllBookingTable(filteredBookings);
		}
		
	}
	
	public void AddTransportProvider() throws SQLException {
		if(addTranportProvider == true) {
			TransportProvider tranportProvider = new TransportProvider("",providerNameBooking.getText(),"0", fleetSizeBooking.getText() , contactInfoBooking.getText() , vehicleTypeBooking.getText()  );
			TMS.addNewTransportProvider(tranportProvider);
		} else {
			TransportProvider tranportProvider = new TransportProvider(editTourTransport.getID(),providerNameBooking.getText(),"0", fleetSizeBooking.getText() , contactInfoBooking.getText() , vehicleTypeBooking.getText()  );
			TMS.updateTransportProvider(tranportProvider);
		}
		hideAllPane();
		removeAllButtonClasses();
		transportTab.getStyleClass().remove("tab-selected");
		transportTab.getStyleClass().add("tab");
		transportSelected();
	}
	public void addNewTransport() {
		addTranportProvider = true;
		addTransportProviderHeader.setText("Add New Transport Provider");
		transportProviderButton.setText("Add Transport Provider");
		hideAllPane();
		setTransportTabEmpty();
		editTransportProviderMainDiV.setVisible(true);
	}
	
	private void removeAllButtonClasses() {
		if (dashboardTab.getStyleClass().contains("tab-selected")) {
			dashboardTab.getStyleClass().remove("tab-selected");
			dashboardTab.getStyleClass().add("tab");
		} else if (toursTab.getStyleClass().contains("tab-selected")) {
			toursTab.getStyleClass().remove("tab-selected");
			toursTab.getStyleClass().add("tab");
		} else if (bookingTab.getStyleClass().contains("tab-selected")) {
			bookingTab.getStyleClass().remove("tab-selected");
			bookingTab.getStyleClass().add("tab");
		} else if (transportTab.getStyleClass().contains("tab-selected")) {
			transportTab.getStyleClass().remove("tab-selected");
			transportTab.getStyleClass().add("tab");
		} else if (settingsTab.getStyleClass().contains("tab-selected")) {
			settingsTab.getStyleClass().remove("tab-selected");
			settingsTab.getStyleClass().add("tab");
		} 
	}

	
	private void hideAllPane() {
		dashboardMainDiv.setVisible(false);
		toursMainDiv.setVisible(false);
		BookingMainDiv.setVisible(false);
		transportMainDiv.setVisible(false);
		editTourMainDiv.setVisible(false);
		editTransportProviderMainDiV.setVisible(false);
	}

	public void dashboardSelected() throws SQLException {
		if (dashboardTab.getStyleClass().contains("tab")) {
			removeAllButtonClasses();
			hideAllPane();
			dashboardTab.getStyleClass().remove("tab");
			dashboardTab.getStyleClass().add("tab-selected");
			dashboardMainDiv.setVisible(true);

		}
	}

	public void toursSelected() throws SQLException {
		if (toursTab.getStyleClass().contains("tab")) {
			removeAllButtonClasses();
			hideAllPane();
			toursTab.getStyleClass().remove("tab");
			toursTab.getStyleClass().add("tab-selected");

			try {
				ArrayList<Tours> allTours = TMS.getAllTours();

				// Get the current date
				LocalDate today = LocalDate.now();

				// Filter tours with start dates in the future
				ArrayList<Tours> futureTours = new ArrayList<>();
				for (Tours tour : allTours) {
					LocalDate startDate = LocalDate.parse(tour.getStartDate()); // Assuming startDate is in ISO format
																				// (yyyy-MM-dd)
					if (startDate.isAfter(today)) { // Check if the start date is after today
						futureTours.add(tour);
					}
				}

				// Send future tours to the table
				this.setAllToursTable(futureTours);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Error while fetching or filtering tours: " + e.getMessage());
			}

			toursMainDiv.setVisible(true);
		}
	}

	public void bookingsSelected() throws SQLException {
		if (bookingTab.getStyleClass().contains("tab")) {
			removeAllButtonClasses();
			hideAllPane();
			bookingTab.getStyleClass().remove("tab");
			bookingTab.getStyleClass().add("tab-selected");
			BookingMainDiv.setVisible(true);
			ArrayList<Booking> allBookings = TMS.getAllBookings();
			setAllBookingTable(allBookings);
		}
	}

	public void customersSelected() {
		if (customersTab.getStyleClass().contains("tab")) {
			removeAllButtonClasses();
			hideAllPane();
			customersTab.getStyleClass().remove("tab");
			customersTab.getStyleClass().add("tab-selected");
		}
	}

	public void transportSelected() throws SQLException {
		if (transportTab.getStyleClass().contains("tab")) {
			removeAllButtonClasses();
			hideAllPane();
			transportTab.getStyleClass().remove("tab");
			transportTab.getStyleClass().add("tab-selected");
			transportMainDiv.setVisible(true);
			ArrayList<TransportProvider> allTransportProvider = TMS.getAllTransportProviders();
			setAllTransportProvider(allTransportProvider);
		}
	}

	public void settingSelected() {
		if (settingsTab.getStyleClass().contains("tab")) {
			removeAllButtonClasses();
			hideAllPane();
			settingsTab.getStyleClass().remove("tab");
			settingsTab.getStyleClass().add("tab-selected");
		}
	}

}
