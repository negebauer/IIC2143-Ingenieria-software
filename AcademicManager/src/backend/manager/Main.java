package backend.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;

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
		// Get your app key and secret from the Dropbox developers website.
        final String APP_KEY = "hvbbay0m9n2krxo";
        final String APP_SECRET = "vay4fyg4k3tb9gb";

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig(
            "JavaTutorial/1.0", Locale.getDefault().toString());
        DbxClient client = new DbxClient(config, "dVvPUaRLEWAAAAAAAAAABv0uS4g09s18mmjwGQSfF7D_xtqbqKbvwz6A_iGU2Anq");
        try {
			System.out.println("Linked account: " + client.getAccountInfo().displayName);
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
		// Trying an app for my iPad
      	// Another try for an app from my iPad∫

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
