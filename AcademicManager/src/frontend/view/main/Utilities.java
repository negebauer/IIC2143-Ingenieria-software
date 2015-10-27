package frontend.view.main;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Utilities {

	public static void openView(URL location, String title) {

		FXMLLoader loader = new FXMLLoader(location);
		Parent root; 
		
		try 
		{
			root = (Parent) loader.load();
			Stage stage = new Stage();
			// TODO Bring the primaryStage from the Main here.
			// Stage stage = Main.primaryStage
			
			stage.setScene(new Scene(root));
			stage.setTitle(title);  
			stage.show();	
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
