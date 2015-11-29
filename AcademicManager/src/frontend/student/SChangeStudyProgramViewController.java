package frontend.student;

import java.net.URL;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 * When creating a new ViewController this file allows to reduce the amount of
 * writing.
 */
public class SChangeStudyProgramViewController extends MViewController {

	@FXML
	ListView<String> listStudyPrograms;
	@FXML
	ComboBox<String> cmBxStudyProgram;
	@FXML
	Button btnAddStudyProgram;
	@FXML
	Button btnRemoveStudyProgram;
	@FXML
	TextArea txASemestersAndCourses;
	
	public final static URL VIEW = Object.class.getResource("/frontend/student/SChangeStudyProgramView.fxml");
	Student user = (Student) Manager.INSTANCE.currentUser;
	StudyProgram currentStudyProgram;
	
	@Override
	public void setUp() {
		super.setUp();
		
		updateList();
		cmBxStudyProgram.setItems(Parser.generateParsedStudyPrograms(Manager.INSTANCE.studyPrograms));
		cmBxStudyProgram.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					currentStudyProgram = Parser.getStudyProgramForParsed(newValue, Manager.INSTANCE.studyPrograms);
					showStudyProgram(currentStudyProgram);
				}
			}
		});
		cmBxStudyProgram.getSelectionModel().selectLast();
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
	
	public void btnAddStudyProgram_Pressed() {
		if (cmBxStudyProgram.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		user.getCurriculum().addStudyProgram(Parser.getStudyProgramForParsed(cmBxStudyProgram.getSelectionModel().getSelectedItem(), Manager.INSTANCE.studyPrograms));
		updateList();
	}
	
	public void btnRemoveStudyProgram_Pressed() {
		if (cmBxStudyProgram.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		user.getCurriculum().removeStudyProgram(Parser.getStudyProgramForParsed(listStudyPrograms.getSelectionModel().getSelectedItem(), Manager.INSTANCE.studyPrograms));
		updateList();
	}
	
	public void updateList() {
		listStudyPrograms.setItems(Parser.generateParsedStudyPrograms(user.getCurriculum().getStudyPrograms()));
	}
}
