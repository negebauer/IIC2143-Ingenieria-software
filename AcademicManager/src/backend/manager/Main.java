package backend.manager;

import java.io.IOException;

import frontend.main.MLogInController;
import frontend.others.CurrentViewHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public final static Main INSTANCE = new Main();

	private Stage primaryStage;
	private Pane root;

	public static void main(String[] args) {
		// Check folder consistency
		FolderFileManager.checkFolders();
		
		// Download latest info from Dropbox and load the data to RAM
		Manager.INSTANCE.downloadData();
		// IMPORTANT: loadData is called after downloading the data and extracting it
		// If the data fetch fails, loadData is called (in the catch of the try catch part of downloadData)
		// Manager.INSTANCE.loadData();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				// Save all data from RAM and upload it to Dropbox
				Manager.INSTANCE.saveData();
				Manager.INSTANCE.uploadData();
			}
		}));
		
		// ----- Delete any test code from this line -----

		
		// ----- To this line -----

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
			root = (Pane) fxmlLoader.load();
			((MLogInController) fxmlLoader.getController()).setUp();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Academic Manager");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("file:documents/images/icon_colors.png"));
		primaryStage.show();
		CurrentViewHandler.INSTANCE.primaryStage = primaryStage;
	}
}
