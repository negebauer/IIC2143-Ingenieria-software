package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.users.Student;
import frontend.main.MViewController;
import frontend.others.Parser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 * When creating a new ViewController this file allows to reduce the amount of
 * writing.
 */
public class SStudyProgramViewController extends MViewController {

	@FXML
	ComboBox<String> cmBxStudyProgram;
	@FXML
	TextArea txASemestersAndCourses;

	Student user = (Student) Manager.INSTANCE.currentUser;
	public static URL view = Object.class.getResource("/frontend/student/SStudyProgramView.fxml");
	ArrayList<StudyProgram> studyProgramsToShow = user.getCurriculum().getStudyPrograms();
	StudyProgram currentStudyProgram;
	
	@Override
	public void setUp() {
		super.setUp();
		
		cmBxStudyProgram.setItems(Parser.generateParsedStudyPrograms(studyProgramsToShow));
		cmBxStudyProgram.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					currentStudyProgram = Parser.getStudyProgramForParsed(newValue, studyProgramsToShow);
					showStudyProgram(currentStudyProgram);
				}
			}
		});
	}
	
	public void showStudyProgram(StudyProgram studyProgram) {
		String completeStudyProgram = "";
		int semesterIndex = 1;
		for (Semester semester : studyProgram.getSemesters()) {
			completeStudyProgram += "Semester " + semesterIndex + ":\n";
			for (Course course : semester.getCourses()) {
				completeStudyProgram += "\t" + course.getInitials() + " " + course.getName() + "\n";
			}
			semesterIndex++;
			completeStudyProgram += "\n";
		}
		txASemestersAndCourses.setText(completeStudyProgram);
	}
}
