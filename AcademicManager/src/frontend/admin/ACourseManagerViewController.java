package frontend.admin;

import java.net.URL;

import frontend.main.MCourseSearcherSelectorViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ACourseManagerViewController extends MCourseSearcherSelectorViewController {
	
	@FXML
	Label labelCourseEditorWelcomeMessage;
	@FXML
	Button btnEditCourse;
	@FXML
	Button btnCreateNewCourse;
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
	ChoiceBox<String> chBxCoursesAsRequirements;
	@FXML
	Button btnAddRequirement;
	@FXML
	Button btnRemoveRequirement;
	@FXML
	Label labelSelectCourseAsRequirement;
	@FXML
	Button btnCreateCourse;

	public static URL view = Object.class.getResource("/frontend/admin/ACourseManagerView.fxml");
	
	public void setUp() {
		super.setUp();
	}
	
}
