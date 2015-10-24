package frontend.view.main;

import java.util.ArrayList;

import backend.courses.Course;
import backend.manager.Manager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;

public class CourseEditorCreatorController {
	
	@FXML
	Label labelCourseEditorWelcomeMessage;
	@FXML
	ChoiceBox<String> chBxCoursesAsRequirements;
	@FXML
	Button btnEditCourse;
	@FXML
	Button btnCreateCourse;
	@FXML
	TextField txBxCourseName;
	@FXML
	TextField txBxInitialsCourse;
	@FXML
	TextField txBxCourseCredits;
	@FXML
	TextField txBxCourseSection;
	@FXML
	ChoiceBox<String> chBxShcools;
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
	@FXML
	ListView<String> listRequirements;
	@FXML
	ChoiceBox<String> chBxCourses;
	@FXML
	Button btnAddRequirement;
	@FXML
	Button btnRemoveRequirement;
	@FXML
	Label labelSelectCourseAsRequirement;
	
	public CourseEditorCreatorController() {
		
		this.chBxCourses = new ChoiceBox<String>();
		this.chBxCourses.setTooltip(new Tooltip("Selecciona un curso"));
		
		ArrayList<String> bxCourses = new ArrayList<String>();		
		for (Course course : Manager.INSTANCE.courses)
			bxCourses.add(course.getName());
	
		for (String str : bxCourses)
			System.out.println(str);
		
		//this.chBxCourses.setItems(FXCollections.observableArrayList(bxCourses));
		this.chBxCourses.getItems().addAll(bxCourses);
		this.chBxCourses.setCursor(Cursor.HAND);
		this.chBxCourses.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number value, Number newValue) {
				// TODO Auto-generated method stub
				
			}
		});
		
			}
}
