package frontend.main;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public final class MViewUtilities {
	
	/**
	 * Open a new window view
	 * @param location
	 */
	public static void openView(URL location, URL sender) {
		
		if (location == null) {
			System.out.println("ADJAWAWDWD");
		}
		
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null; 	
		try {
			root = (Parent) loader.load();
			((MViewController) loader.getController()).setUp();
			Stage stage = MCurrentViewHandler.INSTANCE.primaryStage;
			if (sender != null) {
				MCurrentViewHandler.INSTANCE.addNewParentView(sender);
			}
			stage.setScene(new Scene(root));
			stage.setTitle("RENNAB");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void openView(URL location) {
		openView(location, null);
	}
	
	/**
	 * Changes the view title
	 */
	public static void setTitle(MViewController view, String title) {
		
	}
	
	/***
	 * Change Object Visibility
	 * @param o
	 */
	public static void changeOV(Object o) {
		if(o instanceof Button)
			((Button)o).visibleProperty().set(!((Button)o).visibleProperty().get());
		else if(o instanceof Label)
			((Label)o).visibleProperty().set(!((Label)o).visibleProperty().get());
		else if(o instanceof TextField)
			((TextField)o).visibleProperty().set(!((TextField)o).visibleProperty().get());
		else if(o instanceof ChoiceBox)
			((ChoiceBox<?>)o).visibleProperty().set(!((ChoiceBox<?>)o).visibleProperty().get());
		else if(o instanceof TextArea)
			((TextArea)o).visibleProperty().set(!((TextArea)o).visibleProperty().get());
		else if(o instanceof ListView)
			((ListView<?>)o).visibleProperty().set(!((ListView<?>)o).visibleProperty().get());
	}
}
