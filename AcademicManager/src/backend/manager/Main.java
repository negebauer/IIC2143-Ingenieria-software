package backend.manager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import backend.courses.StudyProgram;
import frontend.view.main.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String [ ] args) {
		FolderFileManager.checkFolders();
		
		// ----- TODO Delete any test code from this line -----
		String str = "asd1;;asd2;asd3;asd4;;asd5";
		String[] split1 = str.split(";;");
		String[] split2 = split1[1].split(";");
		System.out.println("str:");
		System.out.println(str);
		System.out.println("split1:");
		for (String str1 : split1) {
			System.out.println(str1);
		}
		System.out.println("split2:");
		for (String str2 : split2) {
			System.out.println(str2);
		}
		
		ArrayList<StudyProgram> studyPrograms = new ArrayList<StudyProgram>();
		StudyProgram studyProgram = new StudyProgram("Engineering Major Computers", 2013, null, null, 50, 100);
		studyPrograms.add(studyProgram);
//		ArrayList<Student> students = StudentsReaderWriter.readStudents(studyPrograms);
//		for (Student student : students) {
//			System.out.println("getId: " + student.getId());
//			System.out.println("getYearEntrance: " + student.getYearEntrance());
//			System.out.println("getYearGraduation: " + student.getYearGraduation());
//			System.out.println("isRegularStudent: " + student.isRegularStudent());
//			System.out.println("id: " + student.getId());
//			System.out.println("id: " + student.getId());
//			System.out.println("id: " + student.getId());
//			System.out.println("id: " + student.getId());
//		}
		// ----- 			To this line				  -----
		
		Application.launch(Main.class, args);
		
	}

	@Override
	public void start(Stage primaryStage){
		URL location = getClass().getResource("/frontend/view/main/MainView.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		Pane root = null;
		try {
			root = (Pane)fxmlLoader.load();
			((MainViewController) fxmlLoader.getController()).setUp();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root,450,300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
