package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.CoursedSemester;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.users.Student;
import frontend.main.MViewController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SCoursedSemestersViewController extends MViewController {

	@FXML
	Label labelMain;
	@FXML
	Label labelCoursedSemesterCourses;
	@FXML
	ComboBox<String> chBxCoursedSemesters;
	@FXML
	ComboBox<String> chBxCarreer;

	Student user = (Student) Manager.INSTANCE.currentUser;
	static URL view = Object.class.getResource("/frontend/student/SCoursedSemestersView.fxml");
	String carreer = "";

	@Override
	public void setUp() {
		super.setUp();

		// TODO: [STUDENT] Create UILabel
		// labelMain.setText(Messages.getUILabel(UILabel.STUDENT_COURSED_SEMESTERS_MAIN));

		ArrayList<String> sp = new ArrayList<String>();
		for (StudyProgram p : user.getCurriculum().getStudyPrograms()) {
			sp.add(p.getName());
		}
		chBxCarreer.setItems(FXCollections.observableArrayList(sp));
		chBxCoursedSemesters.setItems(FXCollections.observableArrayList(generateCoursedSemesters()));
		chBxCoursedSemesters.setOnAction((event) -> {
			showCoursedSemesterGrades(chBxCoursedSemesters.getSelectionModel().getSelectedItem());
		});	
		chBxCarreer.setOnAction((event) -> {
			carreer = chBxCarreer.getSelectionModel().getSelectedItem().trim();
			chBxCoursedSemesters.getSelectionModel().selectLast();
		});
		chBxCarreer.getSelectionModel().selectFirst();
		chBxCoursedSemesters.getSelectionModel().selectLast();
	}

	ArrayList<String> generateCoursedSemesters() {
		ArrayList<String> coursedSemesters = new ArrayList<String>();
		
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			String coursedSemesterString = coursedSemester.getYear() + " - "
					+ coursedSemester.getSemester().getSemesterNumber();
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
				int count = 0;
				double sum = 0;
				String aux = "";				
				for (Coursed coursed : coursedSemester.getCoursedCourses()) {
					for (StudyProgram sp : Manager.INSTANCE.studyPrograms) {
						if (sp.getName().equals(carreer)) {
							for (Semester s : sp.getSemesters()) {
								for (Course c : s.getCourses()) {
									if (c.getInitials().equals(coursed.getInitials())) {
										aux += coursed.getInitials() + "-" + coursed.getSection() + " " + coursed.getName()
										+ ": " + coursed.getGrade() + "\n\t";	
										sum+= coursed.getGrade();
										count++;
									}
								}
							}
						}	
					}
				}
				double prom = 0;
				if(count != 0) {
					prom = sum/count;
				}				
				coursedCoursesString += year + "-" + semester.getSemesterNumber() + ": " + prom
				+ "\n\t" + aux;
			}
		}
		labelCoursedSemesterCourses.setText(coursedCoursesString);
	}
}
