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

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Admin.fxml"));
      
			Parent root = loader.load();
			Scene scene = new Scene(root,1200,800);
			primaryStage.setScene(scene);
			primaryStage.show();
			
 			TourismManagementSystem TMS = new TourismManagementSystem();
 			
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