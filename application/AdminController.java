package application;

import Models.Booking;
import Models.TopCustomers;
import Models.Tours;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	private TourismManagementSystem TMS;

	public AdminController() throws SQLException {
		TMS = new TourismManagementSystem();
	}

	@FXML
	public void initialize() throws SQLException {

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

	private void setBackGroundColor(TableColumn<Tours, String> test) {
		test.setCellFactory(column -> {
			return new TableCell<Tours, String>() {
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

	private void setBackGroundColorTopCustomers(TableColumn<TopCustomers, String> test) {
		test.setCellFactory(column -> {
			return new TableCell<TopCustomers, String>() {
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

	private void setBackGroundColorBooking(TableColumn<Booking, String> test) {
		test.setCellFactory(column -> {
			return new TableCell<Booking, String>() {
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

	private void setAllToursTable() throws SQLException {

		// ----------------------------------------

		ArrayList<Tours> allTours = TMS.getAllTours();
		ObservableList<Tours> data3 = FXCollections.observableArrayList(allTours);

		nameTourColumn.setCellValueFactory(new PropertyValueFactory<Tours, String>("TourName"));
		setBackGroundColor(nameTourColumn);
		durationTourColumn.setCellValueFactory(new PropertyValueFactory<Tours, String>("Duration"));
		setBackGroundColor(durationTourColumn);
		priceTourColumn.setCellValueFactory(new PropertyValueFactory<Tours, String>("Price"));
		setBackGroundColor(priceTourColumn);
		bookingsTourColumn.setCellValueFactory(new PropertyValueFactory<Tours, String>("Bookings"));
		setBackGroundColor(bookingsTourColumn);

		tourTable.setItems(data3);

		// Set a custom cellFactory for the action column
		actionColumn.setCellFactory(param -> new TableCell<>() {
			private final Button actionButton = new Button();
			{
				// Create an ImageView with the image you want to display on the button
				Image image = new Image(getClass().getResourceAsStream("..\\icons\\icons8-edit-64.png"));
				ImageView imageView = new ImageView(image);
				imageView.setFitWidth(20); // Adjust the size of the image
				imageView.setFitHeight(20); // Adjust the size of the image

				// Set the ImageView as the graphic of the button
				actionButton.setGraphic(imageView);

				// Set the style for the button (background, text color, and no borders)
				actionButton.getStyleClass().add("table-button");
				this.setStyle("-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");

				// Button action
				actionButton.setOnAction(event -> {
					Tours selectedTour = getTableView().getItems().get(getIndex());
					System.out.println("Button clicked for: " + selectedTour.getTourName());
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(actionButton);
				}
			}
		});
	}

	private void setAllBookingTable() throws SQLException {

		ArrayList<Booking> allBookings = TMS.getAllBookings();
		ObservableList<Booking> data = FXCollections.observableArrayList(allBookings);

		tourBookingColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Tour"));
		setBackGroundColorBooking(tourBookingColumn);
		customerBookingColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Customer"));
		setBackGroundColorBooking(customerBookingColumn);
		dateBookingColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Date"));
		setBackGroundColorBooking(dateBookingColumn);

		statusBookingColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Status"));
		statusBookingColumn.setCellFactory(column -> {
			return new TableCell<Booking, String>() {
				private final Button statusButton = new Button();
				{
					// Button styling
					statusButton.setStyle("-fx-background-color: #2563EB; " + "-fx-text-fill: white; "
							+ "-fx-border-color: transparent; " + "-fx-font-size: 12px; " + // Adjust font size
							"-fx-font-weight: bold; " + // Bold text
							"-fx-padding: 3 8; " + // Reduce padding for smaller button size
							"-fx-border-radius: 10; " + // Rounded edges
							"-fx-background-radius: 4;"); // Match border radius for button shape
					statusButton.setPrefHeight(20); // Adjust height

					statusButton.setOnMouseEntered(event -> statusButton.setStyle("-fx-background-color: #1E3A8A; "
							+ "-fx-text-fill: white; " + "-fx-border-color: transparent; " + "-fx-font-size: 12px; "
							+ "-fx-font-weight: bold; " + "-fx-padding: 3 8; " + "-fx-border-radius: 4; "
							+ "-fx-background-radius: 4;"));
					
					statusButton.setOnMouseExited(event -> statusButton.setStyle("-fx-background-color: #2563EB; "
							+ "-fx-text-fill: white; " + "-fx-border-color: transparent; " + "-fx-font-size: 12px; "
							+ "-fx-font-weight: bold; " + "-fx-padding: 3 8; " + "-fx-border-radius: 4; "
							+ "-fx-background-radius: 4;"));
					this.setStyle(
							"-fx-background-color: #1F2937; -fx-text-fill: #F3F4F6; -fx-border-color: transparent;");
				}

				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					if (empty || item == null) {
		                setGraphic(null);
		            } else {
		                // Set button text based on status
		                statusButton.setText(item);
		                
		               

		                setGraphic(statusButton);
		            }
				}
			};
		});
		bookingTable.setItems(data);
		
		actionBookingColumn.setCellFactory(column -> new TableCell<Booking, Void>() {
		    private final ComboBox<String> actionComboBox = new ComboBox<>();

		    {
		        actionComboBox.setStyle("-fx-background-color: #1F2937; " +
		                                "-fx-text-fill: white; " +
		                                "-fx-border-color: transparent; " +
		                                "-fx-font-size: 12px; " +
		                                "-fx-font-weight: bold;");
		        actionComboBox.setPrefWidth(150); // Set a preferred width
		        actionComboBox.setOnAction(event -> {
		            String selectedAction = actionComboBox.getValue();
		            Booking booking = getTableView().getItems().get(getIndex());
		            if ("Confirm Booking".equals(selectedAction)) {
//		                System.out.println("Confirming booking for: " + booking.getId());
		                // Add logic to confirm the booking
		            } else if ("Cancel Booking".equals(selectedAction)) {
//		                System.out.println("Cancelling booking for: " + booking.getId());
		                // Add logic to cancel the booking
		            }
		        });
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
		            actionComboBox.setValue("Select Action"); // Set it as the default value

		            // Add dynamic options based on status
		            if ("Pending".equalsIgnoreCase(booking.getStatus())) {
		                actionComboBox.getItems().addAll("Confirm Booking", "Cancel Booking");
		            } else if ("Completed".equalsIgnoreCase(booking.getStatus())) {
		                actionComboBox.getItems().add("Cancel Booking");
		            }

		            // Prevent "Select Action" from being reselected
		            actionComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		                if ("Select Action".equals(newVal)) {
		                    actionComboBox.getSelectionModel().clearSelection();
		                }
		            });

		            setGraphic(actionComboBox);
		        }
		    }
		});



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
		} else if (customersTab.getStyleClass().contains("tab-selected")) {
			customersTab.getStyleClass().remove("tab-selected");
			customersTab.getStyleClass().add("tab");
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
			this.setAllToursTable();
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
			setAllBookingTable();
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

	public void transportSelected() {
		if (transportTab.getStyleClass().contains("tab")) {
			removeAllButtonClasses();
			hideAllPane();
			transportTab.getStyleClass().remove("tab");
			transportTab.getStyleClass().add("tab-selected");
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
