package frontend.view.main;

import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.CoursedSemester;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SemesterEditorCreatorController implements IController {
	
	@FXML
	Label labelSemesterEditorWelcomeMessage;
	@FXML
	Button btnCreateSemester;
	@FXML
	Button btnEditSemester;
	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnSaveSemester;
	@FXML
	ChoiceBox<String> chBxSemesters;
	@FXML
	ChoiceBox<String> chBxCourses;
	@FXML
	ListView<String> listCoursesInSemester;
	@FXML
	ChoiceBox<String> chBxSemesterType;
	@FXML
	TextField txBxYear;
	@FXML
	TextField txBxMaxCredits;
	@FXML
	Label labelSelectSemester;
	@FXML
	Label labelYear;
	@FXML
	Label labelMaxCredits;
	@FXML
	Label labelSelectCourse;
	
	
	private Semester actualSemester;
	private CoursedSemester actualCoursedSemester;
	private ArrayList<String> courses = new ArrayList<String>();
	
	public void btnCreateSemester_Pressed(){
		changeToEditionMode();
	}

	public void btnEditSemester_Pressed(){
		changeToEditionMode();
		chBxCourses.setItems(FXCollections.observableArrayList(courses));
		
		ArrayList<String> coursesString = new ArrayList<String>();
		if (actualSemester != null) {
			for (Course course : actualSemester.getCourses()) {
				coursesString.add(course.getInitials());
			}
			chBxSemesterType.setValue(actualSemester.getSemester().toString());
			txBxYear.setText(Integer.toString(actualSemester.getYear()));
			txBxMaxCredits.setText(Integer.toString(actualSemester.getMaxCredits()));
		} else if (actualCoursedSemester != null) {
			for (Coursed coursed : actualCoursedSemester.getCoursedCourses()) {
				coursesString.add(coursed.getInitials());
			}
			chBxSemesterType.setValue(actualCoursedSemester.getSemester().toString());
			txBxYear.setText(Integer.toString(actualCoursedSemester.getYear()));
			txBxMaxCredits.setText(Integer.toString(actualCoursedSemester.getMaxCredits()));
		}
		
		listCoursesInSemester.setItems(FXCollections.observableArrayList(coursesString));
		
		
	}

	public void btnAddCourse_Pressed(){

		boolean exist = false;
		String course = chBxCourses.getSelectionModel().getSelectedItem().toString();
		
		for(String str : courses)
			if(str == course)
				exist = true;
		
		if(exist)
			courses.add(course);	

		this.listCoursesInSemester.setItems(FXCollections.observableArrayList(courses));	
	}

	public void btnRemoveCourse_Pressed(){

		String course = this.listCoursesInSemester.getSelectionModel().getSelectedItem();
		
		for(String str : courses)
			if(str == course)
				courses.remove(str);
				
		this.listCoursesInSemester.setItems(FXCollections.observableArrayList(courses));
	}

	public void btnSaveSemester_Pressed(){
		//Save semester
		//actual = new Semester(AcademicSemester.BOTH, 2015, 55, null, null);
	}
	
	public SemesterEditorCreatorController(){
		ArrayList<String> stringSemesters = new ArrayList<String>();
		ArrayList<Semester> semesters = new ArrayList<Semester>();
		ArrayList<CoursedSemester> coursedSemesters = new ArrayList<CoursedSemester>();
		boolean isStudent = Manager.INSTANCE.currentUser instanceof Student;
		boolean isAdmin = Manager.INSTANCE.currentUser instanceof Admin;
		
		if (isStudent) {
			for (CoursedSemester coursedSemester : ((Student)Manager.INSTANCE.currentUser).getCurriculum().getCoursedSemesters()) {
				stringSemesters.add(coursedSemester.getYear() + "-" + coursedSemester.getSemester());
				coursedSemesters.add(coursedSemester);
			}
			Semester semester = ((Student)Manager.INSTANCE.currentUser).getCurriculum().getCurrentSemester();
			stringSemesters.add(semester.getYear() + "-" + semester.getSemester());
			semesters.add(semester);
			
		} else if (isAdmin) {
			for (StudyProgram studyProgram : Manager.INSTANCE.studyPrograms) {
				for (Semester semester : studyProgram.getSemesters()) {
					stringSemesters.add(studyProgram.getName() + "-" + semester.getYear() + "-" + semester.getSemester());
					semesters.add(semester);
				}
			}
		}
		
		chBxSemesters.setItems(FXCollections.observableArrayList(stringSemesters));
		
		chBxSemesters.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
					public void changed (ObservableValue<? extends Number> observableValue, Number value, Number newValue) {
						if (stringSemesters.get(newValue.intValue()) != null){
							btnEditSemester.setVisible(true);
							if (isAdmin) {
								actualSemester = semesters.get(newValue.intValue());
								actualCoursedSemester = null;
							} else if (isStudent) {
								if (newValue.intValue() == coursedSemesters.size()){
									actualSemester = semesters.get(0);
									actualCoursedSemester = null;
								} else {
									actualCoursedSemester = coursedSemesters.get(newValue.intValue());
									actualSemester = null;
								}
							}
						}
			}
		});
			
	}

	public void changeToEditionMode() {
		chBxSemesters.setVisible(false);
		btnCreateSemester.setVisible(false);
		btnEditSemester.setVisible(false);
		chBxCourses.setVisible(true);
		labelSemesterEditorWelcomeMessage.setText("Agrega o elimina cursos al semestre");
		listCoursesInSemester.setVisible(true);
		btnRemoveCourse.setVisible(true);
		btnAddCourse.setVisible(true);
		btnSaveSemester.setVisible(true);
		chBxSemesterType.setVisible(true);
		txBxYear.setVisible(true);
		txBxMaxCredits.setVisible(true);
		labelSelectSemester.setVisible(true);
		labelYear.setVisible(true);
		labelMaxCredits.setVisible(true);
		labelSelectCourse.setVisible(true);
		
		chBxSemesterType.setItems(FXCollections.observableArrayList(
				AcademicSemester.BOTH.toString(),
				AcademicSemester.FIRST.toString(),
				AcademicSemester.SECOND.toString()));
		
		ArrayList<String> courses = new ArrayList<String>();
		for (Course course : Manager.INSTANCE.courses) {
			courses.add(course.getInitials());
		}
		
		chBxCourses.setItems(FXCollections.observableArrayList(courses));
		
	}
	
	@Override
	public void setUp() {
		// TODO Make language stuff
		
	}
}
