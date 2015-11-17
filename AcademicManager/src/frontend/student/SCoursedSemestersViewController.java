package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Coursed;
import backend.courses.CoursedSemester;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.users.Student;
import frontend.main.MViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class SCoursedSemestersViewController extends MViewController {

	@FXML
	Label labelMain;
	@FXML
	Label labelCoursedSemesterCourses;
	@FXML
	ChoiceBox<String> chBxCoursedSemesters;
	
	Student user = (Student) Manager.INSTANCE.currentUser;
	static URL view = Object.class.getResource("/frontend/student/SCoursedSemestersView.fxml");
	
	public void setUp() {
		super.setUp();
		
//		TODO: Create UILabel
//		labelMain.setText(Messages.getUILabel(UILabel.STUDENT_COURSED_SEMESTERS_MAIN));
		
		chBxCoursedSemesters.setItems(FXCollections.observableArrayList(generateCoursedSemesters()));
		chBxCoursedSemesters.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						if (newValue != null) {
							showCoursedSemesterGrades(newValue);
						}
					}
				});
		chBxCoursedSemesters.getSelectionModel().selectLast();
	}
	
	ArrayList<String> generateCoursedSemesters() {
		ArrayList<String> coursedSemesters = new ArrayList<String>();
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			String coursedSemesterString = coursedSemester.getYear() + " - " + coursedSemester.getSemester().getSemesterNumber();
			if (!(coursedSemesters.contains(coursedSemesterString))) {
				coursedSemesters.add(coursedSemesterString);
			}
		}
		coursedSemesters.sort(null);
		for (Coursed coursed : user.getCurriculum().getCoursedCourses()) {
			System.out.println(coursed.getInitials() + "-" + coursed.getName());
		}
		System.out.println(coursedSemesters);
		return coursedSemesters;
	}
	
	void showCoursedSemesterGrades(String yearSemesterRawString) {
		String coursedCoursesString = "";
		int year = Integer.valueOf(yearSemesterRawString.split(" - ")[0]);
		AcademicSemester semester = AcademicSemester.createWithNumber(yearSemesterRawString.split(" - ")[1]);
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			if (coursedSemester.getYear() == year && coursedSemester.getSemester() == semester) {
				coursedCoursesString += year + "-" + semester.getSemesterNumber() + ": " + coursedSemester.getGrade() + "\n\t";
				for (Coursed coursed : coursedSemester.getCoursedCourses()) {
					coursedCoursesString += coursed.getInitials() + "-" + coursed.getSection() + " " + coursed.getName() + ": " + coursed.getGrade() + "\n\t";
				}
			}
		}
		labelCoursedSemesterCourses.setText(coursedCoursesString);
	}
}
