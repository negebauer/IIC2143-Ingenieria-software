package backend.manager;

import java.io.IOException;
import java.net.URL;

import backend.courses.Classroom;
import backend.users.Admin;
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
		
		// ----- TODO Delete any test code from this line -----
		//for (Admin admin : Manager.INSTANCE.admins) {
		//	System.out.print(admin.getRut());
		//	System.out.print(" ");
		//	System.out.print(admin.getName());
		//	System.out.print(" ");
		//	System.out.print(admin.getLastnameFather());
		//	System.out.println("");
		//}
			// WORKS
//		Admin admin = new Admin("111", "Nombre falso2", "Apellido falso", "str", "str", Gender.MALE, 0, "10.09.1993");
//		Admin admin2 = new Admin("222", "Nombre falso2", "Apellido falso", "str", "str", Gender.MALE, 0, "10.09.1993");
//		Manager.INSTANCE.admins.add(admin);
//		Manager.INSTANCE.admins.add(admin2);
//		System.out.print(Utilities.getStringFromDate(admin.getBirthday()));
		
//		for (Course course : Manager.INSTANCE.courses) {
//			
//		}
		
		//for (Classroom classroom : Manager.INSTANCE.classrooms) {
		//	System.out.print(classroom.getInitials());
		//	System.out.print(" ");
		//	System.out.print(classroom.getSchool());
		//	System.out.print(" ");
		//	System.out.print(classroom.getCampus());
		//	System.out.print(" ");
		//	System.out.print(classroom.getSize());
		//	System.out.println("");
		//}
			// WORKS
//		Classroom classroom = new Classroom("test", School.ENGINEERING, Classroom.Campus.SAN_JOAQUIN, 100);
//		Manager.INSTANCE.classrooms.add(classroom);
		
		Manager.INSTANCE.saveData();
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
	}
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}
}
