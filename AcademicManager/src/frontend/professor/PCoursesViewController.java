package frontend.professor;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Coursed;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.users.Professor;
import backend.users.Student;
import frontend.main.MViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class PCoursesViewController extends MViewController {

	@FXML
	Label labelMain;
	@FXML
	Label labelMyCourses;
	@FXML
	ChoiceBox<String> chBxSemesters;

	Boolean firstLoad = true;

	Professor user = (Professor) Manager.INSTANCE.currentUser;
	ArrayList<Coursed> myCoursedCourses = new ArrayList<Coursed>();
	ArrayList<Course> myCurrentCourses = new ArrayList<Course>();
	ArrayList<String> semestersOfCourses = new ArrayList<String>();
	public static URL view = Object.class.getResource("/frontend/professor/PCoursesView.fxml");

	@Override
	public void setUp() {
		super.setUp();
		// TODO: [PROFESSOR] Create UILabel
		// labelMain.setText(Messages.getUILabel(UILabel.PROFESSOR_SEE_MY_COURSES_MAIN));

		chBxSemesters.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					showCourses(newValue);
				}
			}
		});
		if (firstLoad) {
			getMySemesters();
			chBxSemesters.getSelectionModel().selectLast();
			firstLoad = false;
		}
	}

	String parseYearSemesterToString(int year, AcademicSemester semester) {
		return year + " - " + semester.getSemesterNumber();
	}

	void getMySemesters() {
		myCoursedCourses = new ArrayList<Coursed>();
		myCurrentCourses = new ArrayList<Course>();
		semestersOfCourses = new ArrayList<String>();

		Boolean addCourse = false;

		for (Student student : Manager.INSTANCE.students) {
			for (Coursed coursed : student.getCurriculum().getCoursedCourses()) {
				if (coursed.getLecture() != null && coursed.getLecture().getProfessors().contains(user)) {
					addCourse = true;
				} else if (coursed.getLaboratory() != null && coursed.getLaboratory().getProfessors().contains(user)) {
					addCourse = true;
				} else {
					addCourse = false;
				}
				if (addCourse) {
					myCoursedCourses.add(coursed);
					if (coursed.getSemester() == AcademicSemester.BOTH) {
						if (!(semestersOfCourses
								.contains(parseYearSemesterToString(coursed.getYear(), AcademicSemester.FIRST)))
								|| !(semestersOfCourses.contains(
										parseYearSemesterToString(coursed.getYear(), AcademicSemester.SECOND)))) {
							semestersOfCourses
									.add(parseYearSemesterToString(coursed.getYear(), AcademicSemester.FIRST));
							semestersOfCourses
									.add(parseYearSemesterToString(coursed.getYear(), AcademicSemester.SECOND));
						}
					} else {
						if (!(semestersOfCourses
								.contains(parseYearSemesterToString(coursed.getYear(), coursed.getSemester())))) {
							semestersOfCourses.add(parseYearSemesterToString(coursed.getYear(), coursed.getSemester()));
						}
					}
				}
			}
		}

		addCourse = false;
		for (Course course : Manager.INSTANCE.courses) {
			if (course.getLecture() != null && course.getLecture().getProfessors().contains(user)) {
				addCourse = true;
			} else if (course.getLaboratory() != null && course.getLaboratory().getProfessors().contains(user)) {
				addCourse = true;
			} else {
				addCourse = false;
			}
			if (addCourse) {
				myCurrentCourses.add(course);
				if (course.getSemester() == AcademicSemester.BOTH) {
					if (!(semestersOfCourses
							.contains(parseYearSemesterToString(Manager.getYear(), AcademicSemester.FIRST)))
							|| !(semestersOfCourses
									.contains(parseYearSemesterToString(Manager.getYear(), AcademicSemester.SECOND)))) {
						semestersOfCourses.add(parseYearSemesterToString(Manager.getYear(), AcademicSemester.FIRST));
						semestersOfCourses.add(parseYearSemesterToString(Manager.getYear(), AcademicSemester.SECOND));
					}
				} else {
					if (!(semestersOfCourses
							.contains(parseYearSemesterToString(Manager.getYear(), course.getSemester())))) {
						semestersOfCourses.add(parseYearSemesterToString(Manager.getYear(), course.getSemester()));
					}
				}
			}
		}
		chBxSemesters.setItems(FXCollections.observableArrayList(semestersOfCourses));
	}

	void showCourses(String newValue) {
		String newLabelText = "";
		int year = Integer.valueOf(newValue.split(" - ")[0]);
		AcademicSemester semester = AcademicSemester.createWithNumber(newValue.split(" - ")[1]);
		if (year == Manager.getYear() && semester == Manager.INSTANCE.currentSemester.getSemester()) {
			for (Course course : myCurrentCourses) {
				newLabelText += course.getInitials() + "-" + course.getSection() + " " + course.getName() + "\n";
			}
		} else {
			for (Coursed coursed : myCoursedCourses) {
				if (coursed.getYear() == year
						&& (coursed.getSemester() == semester || coursed.getSemester() == AcademicSemester.BOTH)) {
					newLabelText += coursed.getInitials() + "-" + coursed.getSection() + " " + coursed.getName() + "\n";
				}
			}
		}
		labelMyCourses.setText(newLabelText);
	}

	ArrayList<Course> myCourses() {
		ArrayList<Course> myCourses = new ArrayList<Course>();
		for (Course course : Manager.INSTANCE.courses) {
			if (course.getLecture() != null && course.getLecture().getProfessors().contains(user)) {
				myCourses.add(course);
			} else if (course.getLaboratory() != null && course.getLaboratory().getProfessors().contains(user)) {
				myCourses.add(course);
			}
		}
		return myCourses;
	}
}
