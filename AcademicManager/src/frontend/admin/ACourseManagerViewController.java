package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Course.AddOrRemoveRequirementResponse;
import backend.enums.AcademicSemester;
import backend.enums.School;
import backend.manager.Manager;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	Button btnDeleteCourse;
	@FXML
	Label labelModificationResult;

	public static URL view = Object.class.getResource("/frontend/admin/ACourseManagerView.fxml");
	private boolean firstStartUp = true;
	private boolean isEditingRequirements = false;
	private boolean isEditingCoRequirements = false;
	
	
	
	public void setUp() {
		// TODO Make the messages for every component and set their text to them.
		
		if (Manager.INSTANCE.alreadyEditing && firstStartUp) {
			
			changeToEditCourseMode();
			
			txBxCourseName.setText(Manager.INSTANCE.currentEditignCourse.getName());
			txBxInitialsCourse.setText(Manager.INSTANCE.currentEditignCourse.getInitials());
			txBxCourseCredits.setText(Manager.INSTANCE.currentEditignCourse.getCredits() + "");
			txBxCourseSection.setText(Manager.INSTANCE.currentEditignCourse.getSection() + "");
			chBxShcools.getSelectionModel().select(Manager.INSTANCE.currentEditignCourse.getSchool().toString());
			chBxAcademicSemesters.getSelectionModel().select(Manager.INSTANCE.currentEditignCourse.getSemester().toString());
			txArCourseDetails.setText(Manager.INSTANCE.currentEditignCourse.getDetails());
			labelModificationResult.setText("");
			
			btnSaveCourse.setVisible(true);
			
			Manager.INSTANCE.alreadyEditing = false;
			
		} else if (firstStartUp){
		
			txBxCourseName.setVisible(false);
			txBxInitialsCourse.setVisible(false);
			txBxCourseCredits.setVisible(false);
			txBxCourseSection.setVisible(false);
			chBxShcools.setVisible(false);
			chBxAcademicSemesters.setVisible(false);
			txArCourseDetails.setVisible(false);
			labelNameCourse.setVisible(false);
			labelCourseInitials.setVisible(false);
			labelCourseCredits.setVisible(false);
			labelCourseSection.setVisible(false);
			labelCourseSchool.setVisible(false);
			labelSemesterDictated.setVisible(false);
			labelCourseDetails.setVisible(false);
			chBxCoordination.setVisible(false);
			labelCoordination.setVisible(false);
			btnShowCourses.setVisible(false);
			btnShowEvaluations.setVisible(false);
			btnShowRequirements.setVisible(false);
			btnShowCoRequirements.setVisible(false);
			
			btnSaveCourse.setVisible(false);
			
			listRequirements.setVisible(false);
			chBxCoursesAsRequirements.setVisible(false);
			btnAddRequirement.setVisible(false);
			btnRemoveRequirement.setVisible(false);
			labelSelectCourseAsRequirement.setVisible(false);
			
			labelModificationResult.setText("");
			
			super.setUp();
		}
		
		firstStartUp = false;
	}

	public void btnEditCourse_Pressed() {
		if (!super.chBxSelectedCourse.getSelectionModel().isEmpty()) {
			changeToEditCourseMode();
			
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
					Manager.INSTANCE.currentEditignCourse = course;
					Manager.INSTANCE.courses.remove(course);
					break;
				}
			}
			
			txBxCourseName.setText(Manager.INSTANCE.currentEditignCourse.getName());
			txBxInitialsCourse.setText(Manager.INSTANCE.currentEditignCourse.getInitials());
			txBxCourseCredits.setText(Manager.INSTANCE.currentEditignCourse.getCredits() + "");
			txBxCourseSection.setText(Manager.INSTANCE.currentEditignCourse.getSection() + "");
			chBxShcools.getSelectionModel().select(Manager.INSTANCE.currentEditignCourse.getSchool().toString());
			chBxAcademicSemesters.getSelectionModel().select(Manager.INSTANCE.currentEditignCourse.getSemester().toString());
			txArCourseDetails.setText(Manager.INSTANCE.currentEditignCourse.getDetails());
			chBxCoordination.getSelectionModel().select(Manager.INSTANCE.currentEditignCourse.isCoordinated().toString());
			
			btnSaveCourse.setVisible(true);
			
			Manager.INSTANCE.alreadyEditing = false;
		}
	}
	
	public void changeToEditCourseMode() {
		
		txBxCourseName.setVisible(true);
		txBxInitialsCourse.setVisible(true);
		txBxCourseCredits.setVisible(true);
		txBxCourseSection.setVisible(true);
		chBxShcools.setVisible(true);
		chBxAcademicSemesters.setVisible(true);
		txArCourseDetails.setVisible(true);
		labelNameCourse.setVisible(true);
		labelCourseInitials.setVisible(true);
		labelCourseCredits.setVisible(true);
		labelCourseSection.setVisible(true);
		labelCourseSchool.setVisible(true);
		labelSemesterDictated.setVisible(true);
		labelCourseDetails.setVisible(true);
		chBxCoordination.setVisible(true);
		labelCoordination.setVisible(true);
		btnShowCourses.setVisible(true);
		btnShowEvaluations.setVisible(true);
		btnShowRequirements.setVisible(true);
		btnShowCoRequirements.setVisible(true);
		
		hideCourseSelectionView();
		hideRequirementsView();
		
		ArrayList<String> schools = new ArrayList<String>();
		for (School school : School.values()) {
			schools.add(school.toString());
		}
		chBxShcools.setItems(FXCollections.observableArrayList(schools));
		
		ArrayList<String> academicSemesters = new ArrayList<String>();
		for (AcademicSemester academicSemester : AcademicSemester.values()) {
			academicSemesters.add(academicSemester.toString());
		}
		chBxAcademicSemesters.setItems(FXCollections.observableArrayList(academicSemesters));
		chBxCoordination.setItems(FXCollections.observableArrayList("true", "false"));
	}

	public void showRequirementsView() {
		listRequirements.setVisible(true);
		chBxCoursesAsRequirements.setVisible(true);
		btnAddRequirement.setVisible(true);
		btnRemoveRequirement.setVisible(true);
		labelSelectCourseAsRequirement.setVisible(true);
		Manager.INSTANCE.alreadyEditing = true;
	}
	
	public void hideRequirementsView() {
		listRequirements.setVisible(false);
		chBxCoursesAsRequirements.setVisible(false);
		btnAddRequirement.setVisible(false);
		btnRemoveRequirement.setVisible(false);
		labelSelectCourseAsRequirement.setVisible(false);
	}
	
	public void btnCreateNewCourse_Pressed() {
		changeToEditCourseMode();		
		btnSaveCourse.setVisible(true);
		Manager.INSTANCE.currentEditignCourse = new Course(null, null, 0, -1, null, null, null, null, null, null, null, null);
	}

	public void btnSaveCourse_Pressed() {
		
		String name = txBxCourseName.getText();
		String initials = txBxInitialsCourse.getText();
		int credits = Integer.parseInt(txBxCourseCredits.getText());
		int section = Integer.parseInt(txBxCourseSection.getText());
		School school = School.valueOf(chBxShcools.getSelectionModel().getSelectedItem());
		AcademicSemester semester = AcademicSemester.valueOf(chBxAcademicSemesters.getSelectionModel().getSelectedItem());
		String details = txArCourseDetails.getText();
		
		Course course = Manager.INSTANCE.currentEditignCourse;
		course.setName(name);
		course.setInitials(initials);
		course.setCredits(credits);
		course.setSection(section);
		course.setSchool(school);
		course.setSemester(semester);
		course.setDetails(details);
		
		Manager.INSTANCE.courses.add(Manager.INSTANCE.currentEditignCourse);
		Manager.INSTANCE.currentEditignCourse = null;
		isEditingRequirements = false;
		isEditingCoRequirements = false;
		ViewUtilities.openView(view, AMainViewController.view);
		
		Manager.INSTANCE.alreadyEditing = false;
	}
	
	public void btnBack_Pressed() {
		ViewUtilities.openView(view, AMainViewController.view);
	}
	
	public void btnDeleteCourse_Pressed() {
		if (!super.chBxSelectedCourse.getSelectionModel().isEmpty()) {
			changeToEditCourseMode();
			
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
					Manager.INSTANCE.currentEditignCourse = course;
				}
			}
			Manager.INSTANCE.courses.remove(Manager.INSTANCE.currentEditignCourse);
		}
	}
	
	// ICourses Stuff
	public void btnShowCourses_Pressed() {
		ViewUtilities.openView(AICourseManagerViewController.view, view);
		Manager.INSTANCE.alreadyEditing = true;
	}

	// Evaluations Stuff
	public void btnShowEvaluations_Pressed() {
		ViewUtilities.openView(AEvaluationManagerViewController.view, view);
		Manager.INSTANCE.alreadyEditing = true;
	}

	// Requirements Stuff
	public void btnShowRequirements_Pressed() {
		showRequirementsView();
		hideCourseEditingView();
		hideCourseSelectionView();
		isEditingRequirements = true;
		isEditingCoRequirements = false;
		
		ArrayList<String> requirements = new ArrayList<String>();
		for (Course requirement : Manager.INSTANCE.currentEditignCourse.getRequirements()) {
			requirements.add(requirement.getInitials());
		}
		listRequirements.setItems(FXCollections.observableArrayList(requirements));
		
		ArrayList<String> coursesStrings = new ArrayList<String>();
		for (Course course : coursesToShow) {
			coursesStrings.add(getParsedCourse(course.getInitials(), course.getSection(), course.getName()));
		}
		chBxCoursesAsRequirements.setItems(FXCollections.observableArrayList(coursesStrings));
		
		Manager.INSTANCE.alreadyEditing = true;
	}
	
	public void btnShowCoRequirements_Pressed() {
		showRequirementsView();
		hideCourseEditingView();
		hideCourseSelectionView();
		isEditingCoRequirements = true;
		isEditingRequirements = false;
		
		ArrayList<String> coRequirements = new ArrayList<String>();
		for (Course coRequirement : Manager.INSTANCE.currentEditignCourse.getCoRequirements()) {
			coRequirements.add(coRequirement.getInitials());
		}
		listRequirements.setItems(FXCollections.observableArrayList(coRequirements));
		
		ArrayList<String> coursesStrings = new ArrayList<String>();
		for (Course course : coursesToShow) {
			coursesStrings.add(getParsedCourse(course.getInitials(), course.getSection(), course.getName()));
		}
		chBxCoursesAsRequirements.setItems(FXCollections.observableArrayList(coursesStrings));
		
		Manager.INSTANCE.alreadyEditing = true;
	}

	public void btnAddRequirement_Pressed() {
		if (!chBxCoursesAsRequirements.getSelectionModel().isEmpty()) {		
			if (isEditingRequirements) {
				String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
				String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
				String initials = parsed[0];
				int section = Integer.valueOf(parsed[1]);
				String name = parsed[2];
				for (Course course : coursesToShow) {
					if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
						AddOrRemoveRequirementResponse response = Manager.INSTANCE.currentEditignCourse.addRequirement(course);
						if (response.success) {
							ObservableList<String> currentRequirements = listRequirements.getItems();
							currentRequirements.add(getParsedCourse(initials, section, name));
							listRequirements.setItems(FXCollections.observableArrayList(currentRequirements));
							labelModificationResult.setText("Success");
						} else {
							labelModificationResult.setText("Not added: " + response.response);
						}
					}
				}
			} else if (isEditingCoRequirements) {
				String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
				String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
				String initials = parsed[0];
				int section = Integer.valueOf(parsed[1]);
				String name = parsed[2];
				for (Course course : coursesToShow) {
					if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
						AddOrRemoveRequirementResponse response = Manager.INSTANCE.currentEditignCourse.addCoRequirement(course);
						if (response.success) {
							ObservableList<String> currentCoRequirements = listRequirements.getItems();
							currentCoRequirements.add(getParsedCourse(initials, section, name));
							listRequirements.setItems(FXCollections.observableArrayList(currentCoRequirements));
							labelModificationResult.setText("Success");
						} else {
							labelModificationResult.setText("Not added: " + response.response);
						}
					}
				}
			}
		}
	}

	public void btnRemoveRequirement_Pressed() {
		if (!chBxCoursesAsRequirements.getSelectionModel().isEmpty()) {		
			if (isEditingRequirements) {
				String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
				String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
				String initials = parsed[0];
				int section = Integer.valueOf(parsed[1]);
				String name = parsed[2];
				for (Course course : coursesToShow) {
					if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
						AddOrRemoveRequirementResponse response = Manager.INSTANCE.currentEditignCourse.removeRequirement(course);
						if (response.success) {
							ObservableList<String> currentRequirements = listRequirements.getItems();
							currentRequirements.add(getParsedCourse(initials, section, name));
							listRequirements.setItems(FXCollections.observableArrayList(currentRequirements));
							labelModificationResult.setText("Success");
						} else {
							labelModificationResult.setText("Not added: " + response.response);
						}
					}
				}
			} else if (isEditingCoRequirements) {
				String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
				String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
				String initials = parsed[0];
				int section = Integer.valueOf(parsed[1]);
				String name = parsed[2];
				for (Course course : coursesToShow) {
					if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
						AddOrRemoveRequirementResponse response = Manager.INSTANCE.currentEditignCourse.removeCoRequirement(course);
						if (response.success) {
							ObservableList<String> currentCoRequirements = listRequirements.getItems();
							currentCoRequirements.add(getParsedCourse(initials, section, name));
							listRequirements.setItems(FXCollections.observableArrayList(currentCoRequirements));
							labelModificationResult.setText("Success");
						} else {
							labelModificationResult.setText("Not added: " + response.response);
						}
					}
				}
			}
		}		
	}
	
	public void hideCourseEditingView() {
		txBxCourseName.setVisible(false);
		txBxInitialsCourse.setVisible(false);
		txBxCourseCredits.setVisible(false);
		txBxCourseSection.setVisible(false);
		chBxShcools.setVisible(false);
		chBxAcademicSemesters.setVisible(false);
		txArCourseDetails.setVisible(false);
		labelNameCourse.setVisible(false);
		labelCourseInitials.setVisible(false);
		labelCourseCredits.setVisible(false);
		labelCourseSection.setVisible(false);
		labelCourseSchool.setVisible(false);
		labelSemesterDictated.setVisible(false);
		labelCourseDetails.setVisible(false);
		chBxCoordination.setVisible(false);
		labelCoordination.setVisible(false);
		btnShowCourses.setVisible(false);
		btnShowEvaluations.setVisible(false);
		btnShowRequirements.setVisible(false);
		btnShowCoRequirements.setVisible(false);
		
	}
	
	public void showCourseEditingView() {
		txBxCourseName.setVisible(true);
		txBxInitialsCourse.setVisible(true);
		txBxCourseCredits.setVisible(true);
		txBxCourseSection.setVisible(true);
		chBxShcools.setVisible(true);
		chBxAcademicSemesters.setVisible(true);
		txArCourseDetails.setVisible(true);
		labelNameCourse.setVisible(true);
		labelCourseInitials.setVisible(true);
		labelCourseCredits.setVisible(true);
		labelCourseSection.setVisible(true);
		labelCourseSchool.setVisible(true);
		labelSemesterDictated.setVisible(true);
		labelCourseDetails.setVisible(true);
		chBxCoordination.setVisible(true);
		labelCoordination.setVisible(true);
		btnShowCourses.setVisible(true);
		btnShowEvaluations.setVisible(true);
		btnShowRequirements.setVisible(true);
		btnShowCoRequirements.setVisible(true);
	}

	public void hideCourseSelectionView() {
		labelCourseEditorWelcomeMessage.setVisible(false);
		btnCreateNewCourse.setVisible(false);
		btnEditCourse.setVisible(false);
		btnDeleteCourse.setVisible(false);
		super.hideCourseSearcher();
		super.hideCourseSelector();
	}
	
	public void showCourseSelectionView() {
		labelCourseEditorWelcomeMessage.setVisible(true);
		btnCreateNewCourse.setVisible(true);
		btnEditCourse.setVisible(true);
		btnDeleteCourse.setVisible(true);
		super.showCourseSearcher();
		super.showCourseSelector();
	}
	
}
