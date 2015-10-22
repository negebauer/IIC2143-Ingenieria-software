package TestFolder;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import System.Courses.Schedule;
import Tools.Others.Messages;
import Tools.Others.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import Tools.Others.Messages.Message;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class TestClass extends Application{

	public static void main(String [ ] args) {
			Application.launch(TestClass.class, args);
	}

	@Override
	public void start(Stage primaryStage){
		  URL location = getClass().getResource("Home.fxml");
		  FXMLLoader fxmlLoader = new FXMLLoader(location);
		  fxmlLoader.setController(new HomeController());
		  Pane root = null;
		  try {
		    root = (Pane)fxmlLoader.load();
		  } catch (IOException e) {
		    e.printStackTrace();
		  }
		  
		  Scene scene = new Scene(root,450,300);
		  primaryStage.setScene(scene);
		  primaryStage.show();
		}
} 
