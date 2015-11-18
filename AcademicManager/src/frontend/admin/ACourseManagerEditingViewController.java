package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.enums.AcademicSemester;
import backend.enums.School;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ACourseManagerEditingViewController extends MViewController {
	
	@FXML
	TextField txBxCourseName;
	@FXML
	TextField txBxInitialsCourse;
	@FXML
	TextField txBxCourseCredits;
	@FXML
	TextField txBxCourseSection;
	@FXML
	ComboBox<String> chBxShcools;
	@FXML
	ChoiceBox<String> chBxAcademicSemesters;
	@FXML
	TextArea txArCourseDetails;
	@FXML
	Button btnSaveCourse;
	@FXML
	Label labelNameCourse;
	@FXML
	Label labelCourseInitials;
	@FXML
	Label labelCourseCredits;
	@FXML
	Label labelCourseSection;
	@FXML
	Label labelCourseSchool;
	@FXML
	Label labelSemesterDictated;
	@FXML
	Label labelCourseDetails;
	@FXML
	ChoiceBox<String> chBxCoordination;
	@FXML
	Label labelCoordination;
	@FXML
	Button btnShowCourses;
	@FXML
	Button btnShowEvaluations;
	@FXML
	Button btnShowRequirements;
	@FXML
	Button btnShowCoRequirements;

	public static URL view = Object.class.getResource("/frontend/admin/ACourseManagerEditingView.fxml");	
	public boolean isCreating = true;
	
	public void setUp() {
		super.setUp();
		
		btnSaveCourse.setText(Messages.getUILabel(UILabel.SAVE_COURSE));
		labelNameCourse.setText(Messages.getUILabel(UILabel.COURSE_NAME));
		labelCourseInitials.setText(Messages.getUILabel(UILabel.COURSE_INITIALS));
		labelCourseCredits.setText(Messages.getUILabel(UILabel.COURSE_CREDITS));
		labelCourseSection.setText(Messages.getUILabel(UILabel.COURSE_SECTION));
		labelCourseSchool.setText(Messages.getUILabel(UILabel.COURSE_SCHOOL));
		labelSemesterDictated.setText(Messages.getUILabel(UILabel.COURSE_SEMESTER_DICTATED));
		labelCourseDetails.setText(Messages.getUILabel(UILabel.COURSE_DETAILS));
		labelCoordination.setText(Messages.getUILabel(UILabel.COURSE_COORDINATION));
		btnShowCourses.setText(Messages.getUILabel(UILabel.SHOW_ICOURSES));
		btnShowEvaluations.setText(Messages.getUILabel(UILabel.SHOW_EVALUATIONS));
		btnShowRequirements.setText(Messages.getUILabel(UILabel.SHOW_REQUIREMENTS));
		btnShowCoRequirements.setText(Messages.getUILabel(UILabel.SHOW_COREQUIREMENTS));

		ArrayList<String> schools = new ArrayList<String>();
		for (School school : School.values()) {
			schools.add(School.getSchoolMessage(school));
		}
		chBxShcools.setItems(FXCollections.observableArrayList(schools));
		
		ArrayList<String> academicSemesters = new ArrayList<String>();
		for (AcademicSemester academicSemester : AcademicSemester.values()) {
			academicSemesters.add(AcademicSemester.getAcademicSemesterMessage(academicSemester));
		}
		chBxAcademicSemesters.setItems(FXCollections.observableArrayList(academicSemesters));
		chBxCoordination.setItems(FXCollections.observableArrayList(Messages.getUILabel(UILabel.TRUE), Messages.getUILabel(UILabel.FALSE)));		
		
		if (Manager.INSTANCE.currentEditignCourse != null) {
			
			txBxCourseName.setText(Manager.INSTANCE.currentEditignCourse.getName());
			txBxInitialsCourse.setText(Manager.INSTANCE.currentEditignCourse.getInitials());
			txBxCourseCredits.setText(Manager.INSTANCE.currentEditignCourse.getCredits() + "");
			txBxCourseSection.setText(Manager.INSTANCE.currentEditignCourse.getSection() + "");
			chBxShcools.getSelectionModel().select(Manager.INSTANCE.currentEditignCourse.getSchool().toString());
			chBxAcademicSemesters.getSelectionModel().select(Manager.INSTANCE.currentEditignCourse.getSemester().toString());
			txArCourseDetails.setText(Manager.INSTANCE.currentEditignCourse.getDetails());
			
			isCreating = false;
		} else {
			isCreating = true;
		}
		
		ViewUtilities.autoComplete(chBxShcools);
	}
	
	public void saveCourse() {
		String name = txBxCourseName.getText();
		String initials = txBxInitialsCourse.getText();
		int credits = Integer.parseInt(txBxCourseCredits.getText());
		int section = Integer.parseInt(txBxCourseSection.getText());
		
		School school = School.defaultSchool();
		if (!chBxShcools.getSelectionModel().isEmpty() & chBxShcools.getItems().contains(chBxShcools.getSelectionModel().getSelectedItem())) {
			school = School.getSchool(chBxShcools.getSelectionModel().getSelectedItem());
		}
		
		AcademicSemester semester = AcademicSemester.defaultSemester();
		if (!chBxAcademicSemesters.getSelectionModel().isEmpty()) {
			semester = AcademicSemester.getAcademicSemester(chBxAcademicSemesters.getSelectionModel().getSelectedItem());
		}
		
		String details = txArCourseDetails.getText();
		String coordinatedBool = chBxCoordination.getSelectionModel().getSelectedItem();
		
		boolean coordinated = false;
		
		if (coordinatedBool == Messages.getUILabel(UILabel.TRUE)) {
			coordinated = true;
		}
		
		Course course = Manager.INSTANCE.currentEditignCourse;
		
		if (isCreating) {
			course = new Course(name, initials, section, credits, details, school, semester, null, null, null, null, coordinated);
			Manager.INSTANCE.courses.add(course);
			isCreating = false;
		} else {
			course.setName(name);
			course.setInitials(initials);
			course.setCredits(credits);
			course.setSection(section);
			course.setSchool(school);
			course.setSemester(semester);
			course.setDetails(details);
		}
	}
	
	public void btnSaveCourse_Pressed() {
		saveCourse();				
		Manager.INSTANCE.currentEditignCourse = null;
		super.btnBack_Pressed();
	}
	
	public void btnShowCourses_Pressed() {
		saveCourse();
		ViewUtilities.openView(AICourseManagerMainViewController.view, view);
	}

	public void btnShowEvaluations_Pressed() {
		saveCourse();
		ViewUtilities.openView(AEvaluationManagerMainViewController.view, view);
	}

	public void btnShowRequirements_Pressed() {
		saveCourse();
		ViewUtilities.openView(ACourseManagerEditingRequirementsViewController.view, view);		
	}
	
	public void btnShowCoRequirements_Pressed() {
		saveCourse();
		ViewUtilities.openView(ACourseManagerEditingCoRequirementsViewController.view, view);	
	}

	
}
