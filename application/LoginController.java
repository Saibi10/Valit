package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;  // Import the correct Button class
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	@FXML
	private Pane loginMainDiv;
	@FXML
	private Label servicePolicyLabel;
	@FXML
	private Label passwordText;
	@FXML
	private Label emailText;
	@FXML
	private Button signinButton;
	@FXML
	private TextField passwordTextField;
	@FXML
	private TextField passwordTextField2;
	@FXML
	private Label passwordRegisterLable;
	@FXML
	private Label passwordCRegisterLable;
	@FXML
	private Label invalidLabel;
	@FXML
	private TextField emailS;
	@FXML
	private TextField passS;
	
	@FXML
	public int isSignIn = 1;
	
	
	@FXML
	private TourismManagementSystem TMS;
	
	public LoginController(TourismManagementSystem TMS) throws SQLException
	{
		this.TMS = TMS;
	}
	
	
	public void Login() {
		if(loginButton.getStyleClass().contains("button-nonselected"))
		{
			loginButton.getStyleClass().remove("button-nonselected");
			loginButton.getStyleClass().add("button-selected");
			registerButton.getStyleClass().remove("button-selected");
			registerButton.getStyleClass().add("button-nonselected");
			
			
			loginMainDiv.setPrefHeight(522);
			loginMainDiv.setLayoutY(144);
			servicePolicyLabel.setLayoutY(464);
			signinButton.setLayoutY(387);
			invalidLabel.setLayoutY(353);
			
			passwordText.setText("Password");
			
			passwordRegisterLable.setVisible(false);
			passwordCRegisterLable.setVisible(false);
			
			passwordTextField.setVisible(false);
			passwordTextField2.setVisible(false);
			
			isSignIn = 1;
			
		}
		
		
	}
	
	public void Register() {
		if(registerButton.getStyleClass().contains("button-nonselected"))
		{
			registerButton.getStyleClass().remove("button-nonselected");
			registerButton.getStyleClass().add("button-selected");
			loginButton.getStyleClass().remove("button-selected");
			loginButton.getStyleClass().add("button-nonselected");
			
			
			loginMainDiv.setPrefHeight(638);
			loginMainDiv.setLayoutY(41);
			servicePolicyLabel.setLayoutY(580);
			signinButton.setLayoutY(535);
			invalidLabel.setLayoutY(510);
			
			passwordText.setText("Full Name");
			
			passwordRegisterLable.setVisible(true);
			passwordCRegisterLable.setVisible(true);
			
			passwordTextField.setVisible(true);
			passwordTextField2.setVisible(true);
			
			isSignIn = 0;
			
		}
	}
	
	public void goToNextPage(ActionEvent event)
	{
		if (isSignIn == 1) {
	        if (emailS.getText() != null && !emailS.getText().trim().isEmpty() &&
	            passS.getText() != null && !passS.getText().trim().isEmpty()) {
	            
	            // Array to hold the UserID
	            int[] userId = new int[1];
	            boolean check = TMS.authenticateUser(emailS.getText(), passS.getText(), userId , event);

	            if(check) { 
	                invalidLabel.setText("Invalid Information");
	                invalidLabel.setVisible(true);
	            }
	        }
	    }
		else if(isSignIn == 0)
		{
			if((emailS.getText() != null && emailS.getText().trim().isEmpty() == false) && (passS.getText() != null && passS.getText().trim().isEmpty() == false) && (passwordTextField.getText() != null && passwordTextField.getText().trim().isEmpty() == false) && (passwordTextField2.getText() != null && passwordTextField2.getText().trim().isEmpty() == false))
			{
				if(passwordTextField.getText().equals(passwordTextField2.getText()))
				{
					TMS.addNewUser(emailS.getText(), passwordTextField2.getText(), passS.getText());
					invalidLabel.setVisible(true);
					invalidLabel.setText("Go To Sign In Page");
				}
				else
				{
					System.out.println("Not Same");
					invalidLabel.setText("Invalid Information");
					invalidLabel.setVisible(true);
				}
			}
			else
			{
				invalidLabel.setText("Invalid Information");
				invalidLabel.setVisible(true);
			}
		}
	}
}
