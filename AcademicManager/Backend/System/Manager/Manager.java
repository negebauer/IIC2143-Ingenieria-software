package System.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import System.Users.User;
import View.Main.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * [Singleton] Main class that does what the application user requires. 
 */
public class Manager extends Application {

	private ArrayList<User> users = new ArrayList<User>();
	
	private final static Manager INSTANCE = new Manager();
	
	/**
	 * @return The instance of Manager.
	 */
	public static Manager getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates the instance of manager.
	 */
	private Manager() {
		//TODO Load files and stuff.
	}
	
	public static void main(String [ ] args) {
		FolderFileManager.checkFolders();
		Manager manager = Manager.INSTANCE;
		manager.startScene(args);
		
	}
	
	public void startScene(String [ ] args) {
		Application.launch(Manager.class, args);
	}

	@Override
	public void start(Stage primaryStage){
		URL location = getClass().getResource("Home.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		MainViewController mainViewController = new MainViewController();
		fxmlLoader.setController(mainViewController);
		Pane root = null;
		try {
			root = (Pane)fxmlLoader.load();
			mainViewController.setUp();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root,800,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Adds a user to the application users.
	 * @param user The new user of the application.
	 */
	public void addUser(User user) {
		this.users.add(user);
	}
	
	/**
	 * Removes a user to the application users.
	 * @param user The user of the application to be removed.
	 */
	public void removeUser(User user) {
		this.users.remove(user);
	}
	
	/**
	 * Gets a user given a user index.
	 * @param index The user index.
	 * @return The user that matches the given index.
	 */
	public User getUser(int index) {
		return this.users.get(index);
	}
	
	/**
	 * @return The list of all the users.
	 */
	public ArrayList<User> getList() {
		return this.users;
	}
	
	/**
	 * @return The amount of users of the application.
	 */
	public int getSize() {
		return this.users.size();
	}
}
