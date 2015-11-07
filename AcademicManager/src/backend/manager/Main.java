package backend.manager;

import java.io.IOException;

import frontend.main.MLogInController;
import frontend.others.CurrentViewHandler;
import backend.others.Range;
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
	        @Override
			public void run() {
	        	Manager.INSTANCE.saveData();
	        }
	    }));
		// -----    Delete any test code from this line   -----
		
		System.out.println("Year: " + Manager.getYear());
		System.out.println("Month: " + Manager.getMonth());
		for (int i : new Range(9)) {
	        System.out.println(i);
	    }
		
		// ----- 			To this line				  -----
		
		Application.launch(Main.class, args);
		
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.showLogInView();		
	}
	
	private void showLogInView() {
		FXMLLoader fxmlLoader = new FXMLLoader(MLogInController.getView());
		
		try {
			root = (Pane)fxmlLoader.load();
			((MLogInController) fxmlLoader.getController()).setUp();
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
