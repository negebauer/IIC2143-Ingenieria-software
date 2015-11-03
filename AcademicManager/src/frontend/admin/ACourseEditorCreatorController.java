package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.manager.Manager;
import frontend.main.MViewController;
import frontend.main.MViewUtilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;

public class ACourseEditorCreatorController extends MViewController {

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
	
	static URL view = Object.class.getResource("/frontend/admin/ACourseEditorCreator.fxml");

	public ACourseEditorCreatorController() {
		this.chBxCourses = new ChoiceBox<String>();
		this.chBxCourses.setTooltip(new Tooltip("Selecciona un curso"));
		this.chBxCourses.setCursor(Cursor.HAND);

		ArrayList<String> bxCourses = new ArrayList<String>();		
		for (Course course : Manager.INSTANCE.courses)
			bxCourses.add(course.getName());

		for (String str : bxCourses)
			System.out.println(str);

		this.chBxCourses.setItems(FXCollections.observableArrayList(bxCourses));
		this.chBxCourses.getSelectionModel().selectFirst();
		this.chBxCourses.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number value, Number newValue) {

			}
		});

	}
	
	public void btnCreateCourse_Pressed() {
		this.createCourseVisibility();
	}
	
	public void btnEditCourse_Pressed() {

	}
	
	public void btnSaveCourse_Pressed() {
		this.createCourseVisibility();
	}
	
	public void btnShowCourses_Pressed() {
		
	}
	
	public void btnShowEvaluations_Pressed() {
		
	}
	
	public void btnShowRequirements_Pressed() {
		
	}
	
	public void btnShowCoRequirements_Pressed() {
		
	}
	
	public void btnAddRequirement_Pressed() {
		
	}
	
	public void btnRemoveRequirement_Pressed() {
		
	}
	
	public void createCourseVisibility() {
		//Buttons
		MViewUtilities.changeOV(this.btnEditCourse);
		MViewUtilities.changeOV(this.btnCreateCourse);
		MViewUtilities.changeOV(this.btnSaveCourse);
		MViewUtilities.changeOV(this.btnShowCourses);
		MViewUtilities.changeOV(this.btnShowEvaluations);
		MViewUtilities.changeOV(this.btnShowRequirements);
		MViewUtilities.changeOV(this.btnShowCoRequirements);
		
		//Labels
		MViewUtilities.changeOV(this.labelNameCourse);
		MViewUtilities.changeOV(this.labelCourseInitials);
		MViewUtilities.changeOV(this.labelCourseDetails);
		MViewUtilities.changeOV(this.labelCoordination);
		MViewUtilities.changeOV(this.labelCourseCredits);
		MViewUtilities.changeOV(this.labelCourseSchool);
		MViewUtilities.changeOV(this.labelCourseSection);
		MViewUtilities.changeOV(this.labelSemesterDictated);
		
		//TextFields
		MViewUtilities.changeOV(this.txBxCourseName);
		MViewUtilities.changeOV(this.txBxInitialsCourse);
		MViewUtilities.changeOV(this.txBxCourseCredits);
		MViewUtilities.changeOV(this.txBxCourseSection);
		
		//ChoiceBoxes
		MViewUtilities.changeOV(this.chBxCourses);
		MViewUtilities.changeOV(this.chBxShcools);
		MViewUtilities.changeOV(this.chBxAcademicSemesters);
		MViewUtilities.changeOV(this.chBxCoordination);
		
		//TextAreas
		MViewUtilities.changeOV(this.txArCourseDetails);
	}

	@Override
	public void setUp() {
		super.setUp();
		
	}
}
