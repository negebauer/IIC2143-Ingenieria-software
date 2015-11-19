package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Course;
import backend.courses.Laboratory;
import backend.courses.Lecture;
import backend.interfaces.ICourse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class AICourseManagerMainViewController extends MViewController {

	@FXML
	ChoiceBox<String> chBxICourses;
	@FXML
	Button btnEditICourse;
	@FXML
	Button btnCreateLecture;
	@FXML
	Button btnCreateLaboratory;
	@FXML
	Button btnCreateAssistantship;
	@FXML
	Label labelWelcomeMessage;

	static URL view = Object.class.getResource("/frontend/admin/AICourseManagerMainView.fxml");

	@Override
	public void setUp() {
		super.setUp();

		btnEditICourse.setText(Messages.getUILabel(UILabel.EDIT_ICOURSE));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.ADMIN_ICOURSE_MANAGER_WELCOME_MESSAGE));
		
		Course currentCourse = Manager.INSTANCE.currentEditignCourse;
		
		if (currentCourse != null) {
				ArrayList<String> iCourses = new ArrayList<String>();
			for (ICourse iCourse : currentCourse.getCourses()) {
				iCourses.add(Messages.getICourseName(iCourse));
			}
			chBxICourses.setItems(FXCollections.observableArrayList(iCourses));
			
			if (currentCourse.getAssistantship() != null) {
				btnCreateAssistantship.setVisible(false);
			}
			
			if (currentCourse.getLecture() != null) {
				btnCreateLecture.setVisible(false);
			}
			
			if (currentCourse.getLaboratory() != null) {
				btnCreateLaboratory.setVisible(false);
			}
		}
	}

	public void btnEditICourse_Pressed() {
		String selectedICourse = chBxICourses.getSelectionModel().getSelectedItem();
		
		if (selectedICourse == Messages.getICourseName(new Assistantship(null, null, null))) {
			Manager.INSTANCE.currentEditignICourse = Manager.INSTANCE.currentEditignCourse.getAssistantship();
			ViewUtilities.openView(AICourseManagerAssistantshipEditingViewController.view, view);
			
		} else if (selectedICourse == Messages.getICourseName(new Lecture(null, null, null))) {
			Manager.INSTANCE.currentEditignICourse = Manager.INSTANCE.currentEditignCourse.getLecture();
			ViewUtilities.openView(AICourseManagerLectureEditingViewController.view, view);

		} else if (selectedICourse == Messages.getICourseName(new Laboratory(null, null, null))) {
			Manager.INSTANCE.currentEditignICourse = Manager.INSTANCE.currentEditignCourse.getLaboratory();
			ViewUtilities.openView(AICourseManagerLaboratoryEditingViewController.view, view);
			
		} else {
			System.out.println("no me meti");
		}
	}

	public void btnCreateLecture_Pressed() {
		Manager.INSTANCE.currentEditignICourse = null;
		ViewUtilities.openView(AICourseManagerLectureEditingViewController.view, view);
	}
	
	public void btnCreateLaboratory_Pressed() {
		Manager.INSTANCE.currentEditignICourse = null;
		ViewUtilities.openView(AICourseManagerLaboratoryEditingViewController.view, view);
	}
	
	public void btnCreateAssistantship_Pressed() {
		Manager.INSTANCE.currentEditignICourse = null;
		ViewUtilities.openView(AICourseManagerAssistantshipEditingViewController.view, view);
	}
	
}
