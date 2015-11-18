package backend.manager;

import java.io.IOException;
import java.util.ArrayList;

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
		FolderFileManager.checkFolders();
		Manager.INSTANCE.loadData();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				Manager.INSTANCE.saveData();
			}
		}));
		// ----- Delete any test code from this line -----

		ArrayList<String> newarray = new ArrayList<String>();
		newarray.add("A");
		newarray.add("C");
		newarray.add("B");
		newarray.sort(null);
		for (String s : newarray) {
			System.out.println(s);
		}

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
