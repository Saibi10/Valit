package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;  // Import the correct Button class

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
			
			passwordText.setText("Password");
			
			passwordRegisterLable.setVisible(false);
			passwordCRegisterLable.setVisible(false);
			
			passwordTextField.setVisible(false);
			passwordTextField2.setVisible(false);
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
			
			passwordText.setText("Full Name");
			
			passwordRegisterLable.setVisible(true);
			passwordCRegisterLable.setVisible(true);
			
			passwordTextField.setVisible(true);
			passwordTextField2.setVisible(true);
		}
	}
}
