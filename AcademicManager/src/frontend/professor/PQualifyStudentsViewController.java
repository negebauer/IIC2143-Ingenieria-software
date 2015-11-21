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
import frontend.others.ViewUtilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * When creating a new ViewController this file allows to reduce the amount of
 * writing.
 */
public class PQualifyStudentsViewController extends MViewController {

	@FXML
	ComboBox<String> cmBxSelectedStudent;
	@FXML
	ComboBox<String> cmBxSelectedCourse;
	@FXML
	TextField txBxGrade;
	@FXML
	Button btnSetGrade;
	
	Boolean firstLoad = true;
	
	Professor user = (Professor) Manager.INSTANCE.currentUser;
	public static URL view = Object.class.getResource("/frontend/professor/PQualifyStudentsView.fxml");
	ArrayList<Student> studentsToShow = generateStudentsToShowArray();
	ArrayList<Course> coursesToShow = generateCoursesToShowArray();
	Student currentStudent;
	Course currentCourse;
	
	@Override
	public void setUp() {
		super.setUp();
		
		cmBxSelectedCourse.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					currentCourse = getCourseForParsed(newValue);
				} else {
					currentCourse = null;
				}
				refreshStudentsToShowArray();
				ArrayList<String> parsedStudents = new ArrayList<String>();
				for (Student student : studentsToShow) {
					parsedStudents.add(getParsedStudent(student));
				}
				cmBxSelectedStudent.setItems(FXCollections.observableArrayList(parsedStudents));
			}
		});
		cmBxSelectedStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					currentStudent = getStudentForParsed(newValue);
				} else {
					currentStudent = null;
				}
			}
		});
		
		if (firstLoad) {
			ArrayList<String> parsedCourses = new ArrayList<String>();
			for (Course course : coursesToShow) {
				parsedCourses.add(getParsedCourse(course));
			}
			cmBxSelectedCourse.setItems(FXCollections.observableArrayList(parsedCourses));
			firstLoad = false;
		}
	}
	
	public void showGradeStudent(Student student) {
		if (student.getCurriculum().getCurrentCoursedSemester().getCoursedCourses().size() > 0) {
			for (Coursed coursed : student.getCurriculum().getCurrentCoursedSemester().getCoursedCourses()) {
				if (coursed.getInitials().equals(currentCourse.getInitials()) && coursed.getSection() == currentCourse.getSection()) {
					txBxGrade.setText(String.valueOf(coursed.getGrade()));
					return;
				}
			}
		}
		txBxGrade.setText("0.0");
	}
	
	public Student getStudentForParsed(String parsed) {
		String rut = parsed.split(" - ")[0];
		for (Student student : studentsToShow) {
			if (student.getRut().equals(rut)) {
				return student;
			}
		}
		return null;
	}
	
	public String getParsedStudent(Student student) {
		return student.getRut() + " - "
				+ student.getName() + " "
				+ student.getLastnameFather() + " "
				+ student.getLastnameMother() + " ";
	}
	
	public Course getCourseForParsed(String parsed) {
		String initials = parsed.split("-")[0];
		int section = Integer.valueOf(parsed.split("-")[1].split(":")[0]);
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section) {
				return course;
			}
		}
		return null;
	}
	
	public String getParsedCourse(Course course) {
		return course.getInitials() + "-"
				+ course.getSection() + ": "
				+ course.getName();
	}
	
	public void updateCoursesShow(AcademicSemester semester) {
		ArrayList<String> studentsStrings = new ArrayList<String>();
		for (Student student : studentsToShow) {
			studentsStrings.add(getParsedStudent(student));
		}
		cmBxSelectedStudent.setItems(FXCollections.observableArrayList(studentsStrings));
		ViewUtilities.autoComplete(cmBxSelectedStudent);
	}
	
	public ArrayList<Student> generateStudentsToShowArray() {
		if (currentCourse == null) {
			return new ArrayList<Student>();
		}
		ArrayList<Student> studentsToShow = new ArrayList<Student>();
		for (Student student : Manager.INSTANCE.students) {
			System.out.println("Checking student: " + student.getName());
			if (student.getCurriculum() != null &&
					student.getCurriculum().getCurrentSemester() != null &&
					student.getCurriculum().getCurrentSemester().getCourses().contains(currentCourse)) {
				System.out.println("Student ok: " + student.getName());
				studentsToShow.add(student);
			}
		}
		return studentsToShow;
	}
	
	public ArrayList<Course> generateCoursesToShowArray() {
		ArrayList<Course> coursesToShow = new ArrayList<Course>();
		for (Course course : Manager.INSTANCE.courses) {
			if (course.getLecture() != null && course.getLecture().getProfessors().contains(user)) {
				coursesToShow.add(course);
			} else if (course.getLaboratory() != null && course.getLaboratory().getProfessors().contains(user)) {
				coursesToShow.add(course);
			}
		}
		return coursesToShow;
	}
	
	public void refreshStudentsToShowArray() {
		studentsToShow = generateStudentsToShowArray();
	}
	
	public void btnSetGrade_Pressed() {
		if (currentStudent != null) {
			Double grade = Double.valueOf(txBxGrade.getText());
			Boolean approved = grade >= 4.0;
			currentStudent.getCurriculum().addCoursedCourse(currentCourse, approved, grade, Manager.getYear());
		}
	}
}
