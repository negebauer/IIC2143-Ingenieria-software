package frontend.admin;

import java.net.URL;

import backend.courses.Course;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.ViewUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ACourseManagerMainViewController extends MCourseSearcherSelectorViewController {
	
	@FXML
	Label labelCourseEditorWelcomeMessage;
	@FXML
	Button btnEditCourse;
	@FXML
	Button btnCreateNewCourse;
	@FXML
	Button btnDeleteCourse;

	public static URL view = Object.class.getResource("/frontend/admin/ACourseManagerMainView.fxml");
	
	public void setUp() {
		labelCourseEditorWelcomeMessage.setText(Messages.getUILabel(UILabel.COURSE_MANAGER_WELCOME_MESSAGE));
		btnEditCourse.setText(Messages.getUILabel(UILabel.EDIT_COURSE));
		btnCreateNewCourse.setText(Messages.getUILabel(UILabel.CREATE_COURSE));
		btnDeleteCourse.setText(Messages.getUILabel(UILabel.DELETE_COURSE));
		
		super.setUp();
		
		Course currentCourse = Manager.INSTANCE.currentEditignCourse;
		
		if (currentCourse != null) {
			chBxSelectedCourse.getSelectionModel().select(getParsedCourse(currentCourse.getInitials(), currentCourse.getSection(), currentCourse.getName()));
		}
		
	}

	public void btnEditCourse_Pressed() {
		if (!super.chBxSelectedCourse.getSelectionModel().isEmpty()) {
			
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
					Manager.INSTANCE.currentEditignCourse = course;
					break;
				}
			}
			ViewUtilities.openView(ACourseManagerEditingViewController.view, view);
		}
	}
	
	public void btnCreateNewCourse_Pressed() {
		Manager.INSTANCE.currentEditignCourse = null;
		ViewUtilities.openView(ACourseManagerEditingViewController.view, view);		
	}
	
	public void btnDeleteCourse_Pressed() {
		if (!super.chBxSelectedCourse.getSelectionModel().isEmpty()) {

			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
					Manager.INSTANCE.currentEditignCourse = course;
					break;
				}
			}
			
			if (Manager.INSTANCE.courses.contains(Manager.INSTANCE.currentEditignCourse)) {
				Manager.INSTANCE.courses.remove(Manager.INSTANCE.currentEditignCourse);
			}
		}
	}	
}
