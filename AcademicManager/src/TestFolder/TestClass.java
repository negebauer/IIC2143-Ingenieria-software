package TestFolder;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import System.Courses.Course;
import System.Manager.CoursesReaderWriter;
import System.Manager.FolderFileManager;
import System.Manager.ProfessorsReaderWriter;
import System.Users.Professor;
import Tools.Others.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class TestClass extends Application{
	
	public static void main(String [ ] args) {
		FolderFileManager.checkFolders();
		
		ArrayList<Professor> professors = ProfessorsReaderWriter.readProfessors();
		for (Professor professor : professors) {
			System.out.println(professor.getRut());
			System.out.println(professor.getName());
			System.out.println(professor.getLastnameFather());
			System.out.println(professor.getLastnameMother());
			System.out.println(professor.getAddress());
			System.out.println(professor.getGender());
			System.out.println(professor.getPhone());
			System.out.println(Utilities.getStringFromDate(professor.getBirthday()));
		}
		
		ArrayList<Course> courses = CoursesReaderWriter.readCourses();
		for (Course course : courses) {
			System.out.println(course.getName());
			System.out.println(course.getInitials());
			System.out.println(course.getSection());
			System.out.println(course.getCredits());
			System.out.println(course.getDetails());
			System.out.println(course.getSchool());
			System.out.println(course.getSemester());
		}
		
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

		Scene scene = new Scene(root,800,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
} 
