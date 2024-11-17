package application;

import java.sql.SQLException;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	
	private TourismManagementSystem TMS;
	
	public AdminController() throws SQLException {
		TMS = new TourismManagementSystem();
	}
	
 	private void removeAllButtonClasses() {
		if(dashboardTab.getStyleClass().contains("tab-selected"))
		{
			dashboardTab.getStyleClass().remove("tab-selected");
			dashboardTab.getStyleClass().add("tab");
		}
		else if(toursTab.getStyleClass().contains("tab-selected")) 
		{
			toursTab.getStyleClass().remove("tab-selected");
			toursTab.getStyleClass().add("tab");
		}
		else if(bookingTab.getStyleClass().contains("tab-selected")) 
		{
			bookingTab.getStyleClass().remove("tab-selected");
			bookingTab.getStyleClass().add("tab");
		}
		else if(customersTab.getStyleClass().contains("tab-selected")) 
		{
			customersTab.getStyleClass().remove("tab-selected");
			customersTab.getStyleClass().add("tab");
		}
		else if(transportTab.getStyleClass().contains("tab-selected")) 
		{
			transportTab.getStyleClass().remove("tab-selected");
			transportTab.getStyleClass().add("tab");
		}
		else if(settingsTab.getStyleClass().contains("tab-selected")) 
		{
			settingsTab.getStyleClass().remove("tab-selected");
			settingsTab.getStyleClass().add("tab");
		}
			
	}

	
	public void dashboardSelected() {
		if(dashboardTab.getStyleClass().contains("tab"))
		{
			removeAllButtonClasses();
			dashboardTab.getStyleClass().remove("tab");
			dashboardTab.getStyleClass().add("tab-selected");
		}
	}
	
	public void toursSelected() throws SQLException {
		if(toursTab.getStyleClass().contains("tab"))
		{
			removeAllButtonClasses();
			toursTab.getStyleClass().remove("tab");
			toursTab.getStyleClass().add("tab-selected");
		}
	}
	
	public void bookingsSelected() {
		if(bookingTab.getStyleClass().contains("tab"))
		{
			removeAllButtonClasses();
			bookingTab.getStyleClass().remove("tab");
			bookingTab.getStyleClass().add("tab-selected");
		}
	}
	
	public void customersSelected() {
		if(customersTab.getStyleClass().contains("tab"))
		{
			removeAllButtonClasses();
			customersTab.getStyleClass().remove("tab");
			customersTab.getStyleClass().add("tab-selected");
		}
	}
	
	public void transportSelected() {
		if(transportTab.getStyleClass().contains("tab"))
		{
			removeAllButtonClasses();
			transportTab.getStyleClass().remove("tab");
			transportTab.getStyleClass().add("tab-selected");
		}
	}
	
	public void settingSelected() {
		if(settingsTab.getStyleClass().contains("tab"))
		{
			removeAllButtonClasses();
			settingsTab.getStyleClass().remove("tab");
			settingsTab.getStyleClass().add("tab-selected");
		}
	}

}
