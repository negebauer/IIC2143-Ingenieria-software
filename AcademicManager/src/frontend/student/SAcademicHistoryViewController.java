package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.CoursedSemester;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.users.Student;
import frontend.main.MViewController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SAcademicHistoryViewController extends MViewController {

	@FXML
	TextArea txAAcademicHistory;
	@FXML
	TextArea txAAcademic;
	@FXML
	ComboBox<String> chBxCarreer;
	Student user = (Student) Manager.INSTANCE.currentUser;
	static URL view = Object.class.getResource("/frontend/student/SAcademicHistoryView.fxml");
	String carreer = "";
	
	@Override
	public void setUp() {
		super.setUp();

		ArrayList<String> sp = new ArrayList<String>();
		for (StudyProgram p : user.getCurriculum().getStudyPrograms()) {
			sp.add(p.getName());
			System.out.println(p.getName());
		}
		chBxCarreer.setItems(FXCollections.observableArrayList(sp));
		chBxCarreer.setOnAction((event) -> {
			carreer = chBxCarreer.getSelectionModel().getSelectedItem().trim();
			refresh();
		});
		chBxCarreer.getSelectionModel().selectFirst();	
	}
	
	public void refresh() {
		String fullText = "";
		String academic = "";
		double totalGrade = 0;
		double totalCarreerGrade = 0;
		int totalCourses = 0;
		for (Coursed coursed : user.getCurriculum().getCoursedCourses()) {
			totalGrade += coursed.getGrade();		
			if (checkCourse(coursed.getInitials())) {
				totalCarreerGrade += coursed.getGrade();
				totalCourses++;
			}
		}
		totalGrade = totalGrade / user.getCurriculum().getCoursedCourses().size();
		if (totalCourses > 0) {
			totalCarreerGrade = totalCarreerGrade / totalCourses;
		}
		fullText += totalGrade + "\n\n";
		academic += totalCarreerGrade + "\n\n";
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			fullText += coursedSemester.getYear() + "-" + coursedSemester.getSemester().getSemesterNumber() + ": "
					+ coursedSemester.getGrade() + "\n\t";
			academic += coursedSemester.getYear() + "-" + coursedSemester.getSemester().getSemesterNumber() + ": "
					+ coursedSemester.getGrade() + "\n\t";
			for (Coursed coursed : coursedSemester.getCoursedCourses()) {
				fullText += coursed.getInitials() + "-" + coursed.getSection() + " " + coursed.getName() + ": "
						+ coursed.getGrade() + "\n\t";
				if (checkCourse(coursed.getInitials())) {
					academic += coursed.getInitials() + "-" + coursed.getSection() + " " + coursed.getName() + ": "
							+ coursed.getGrade() + "\n\t";
				}
			}
			fullText += "\n";
			academic +="\n"; 
		}
		txAAcademicHistory.setText(fullText);
		txAAcademic.setText(academic);
	}
	
	private boolean checkCourse(String initials) {
		for (StudyProgram sp : Manager.INSTANCE.studyPrograms) {
			if (sp.getName().equals(carreer)) {
				for (Semester s : sp.getSemesters()) {
					for (Course c : s.getCourses()) {
						if (c.getInitials().equals(initials)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
