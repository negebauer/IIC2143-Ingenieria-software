package backend.manager;

import java.io.IOException;
import java.net.URL;

import frontend.view.main.CurrentViewHandler;
import frontend.view.main.LogInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public final static Main INSTANCE = new Main();
	
	private Stage primaryStage;
	private Pane root;
	
	public static void main(String [ ] args) {
		FolderFileManager.checkFolders();
		Manager.INSTANCE.loadData();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	Manager.INSTANCE.saveData();
	        }
	    }));
		// ----- TODO Delete any test code from this line -----
		
		
		
		// ----- 			To this line				  -----
		
		Application.launch(Main.class, args);
		
	}

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.showLogInView();		
	}
	
	private void showLogInView() {
		
		URL location = getClass().getResource("/frontend/view/main/LogIn.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		
		try {
			root = (Pane)fxmlLoader.load();
			((LogInController) fxmlLoader.getController()).setUp();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root,600,400);
		primaryStage.setScene(scene);
        primaryStage.setTitle("Academic Manager");
		primaryStage.show();
		CurrentViewHandler.INSTANCE.primaryStage = primaryStage;
	}
}
