package application;


import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage)  {
		try {
			TourismManagementSystem TMS = new TourismManagementSystem();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Home.fxml"));
			loader.setControllerFactory(param -> {
				try {
					return new LoginController(TMS);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return param;
			});
			Parent root = loader.load();
			Scene scene = new Scene(root,1200,800);
			primaryStage.setScene(scene);
			primaryStage.show();
			
 			
 			
		} catch(Exception e) {
			System.out.print("Error");
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws SQLException 
	{ 
		launch(args); 
	}
	
}