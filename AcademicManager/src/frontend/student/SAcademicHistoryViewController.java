package frontend.student;

import java.net.URL;

import backend.courses.Coursed;
import backend.courses.CoursedSemester;
import backend.manager.Manager;
import backend.users.Student;
import frontend.main.MViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SAcademicHistoryViewController extends MViewController {

	@FXML
	TextArea txAAcademicHistory;
	
	Student user = (Student) Manager.INSTANCE.currentUser;
	static URL view = Object.class.getResource("/frontend/student/SAcademicHistoryView.fxml");
	
	public void setUp() {
		super.setUp();
		
		String fullText = "";
		double totalGrade = 0;
		for (Coursed coursed : user.getCurriculum().getCoursedCourses()) {
			totalGrade += coursed.getGrade();
		}
		totalGrade = totalGrade / user.getCurriculum().getCoursedCourses().size();
		fullText += totalGrade + "\n\n";
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			fullText += coursedSemester.getYear() + "-" + coursedSemester.getSemester().getSemesterNumber() + ": " + coursedSemester.getGrade() + "\n\t";
			for (Coursed coursed : coursedSemester.getCoursedCourses()) {
				fullText += coursed.getInitials() + "-" + coursed.getSection() + " " + coursed.getName() + ": " + coursed.getGrade() + "\n\t";
			}
			fullText += "\n";
		}
		txAAcademicHistory.setText(fullText);
	}
}
