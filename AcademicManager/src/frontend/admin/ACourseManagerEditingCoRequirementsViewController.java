package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Course.AddOrRemoveRequirementResponse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MCourseSearcherSelectorViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ACourseManagerEditingCoRequirementsViewController extends MCourseSearcherSelectorViewController {

	@FXML
	ListView<String> listCoRequirements;
	@FXML
	Button btnAddCoRequirement;
	@FXML
	Button btnRemoveCoRequirement;
	@FXML
	Label labelSelectCourseAsCoRequirement;
	@FXML
	Label labelModificationResult;

	public static URL view = Object.class.getResource("/frontend/admin/ACourseManagerEditingCoRequirementsView.fxml");

	@Override
	public void setUp() {
		super.setUp();

		btnAddCoRequirement.setText(Messages.getUILabel(UILabel.ADD_REQUIREMENT));
		btnRemoveCoRequirement.setText(Messages.getUILabel(UILabel.REMOVE_REQUIREMENT));
		labelSelectCourseAsCoRequirement.setText(Messages.getUILabel(UILabel.SELECT_REQUIREMENT));
		labelModificationResult.setText("");

		ArrayList<String> coRequirements = new ArrayList<String>();
		for (Course coRequirement : Manager.INSTANCE.currentEditignCourse.getCoRequirements()) {
			coRequirements.add(coRequirement.getInitials());
		}
		listCoRequirements.setItems(FXCollections.observableArrayList(coRequirements));
	}

	public void btnAddCoRequirement_Pressed() {
		if (!chBxSelectedCourse.getSelectionModel().isEmpty()
				& chBxSelectedCourse.getItems().contains(chBxSelectedCourse.getSelectionModel().getSelectedItem())) {
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section
						&& course.getName().equals(name)) {
					AddOrRemoveRequirementResponse response = Manager.INSTANCE.currentEditignCourse
							.addCoRequirement(course);
					if (response.success) {
						ObservableList<String> currentRequirements = listCoRequirements.getItems();
						currentRequirements.add(getParsedCourse(initials, section, name));
						listCoRequirements.setItems(FXCollections.observableArrayList(currentRequirements));
						labelModificationResult.setText(Messages.getUILabel(UILabel.SUCCESS));
					} else {
						labelModificationResult
								.setText(Messages.getUILabel(UILabel.NOT_ADDED) + ": " + response.response);
					}
					break;
				}
			}
		} else {
			// TODO Uncomment when function is created
			// ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION)
			// + "(" + Messages.getUILabel(UILabel.ADD_CO_REQUIREMENT) + ")");
		}
	}

	public void btnRemoveCoRequirement_Pressed() {
		if (!listCoRequirements.getSelectionModel().isEmpty()) {
			String rawCourseInfo = listCoRequirements.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section
						&& course.getName().equals(name)) {
					AddOrRemoveRequirementResponse response = Manager.INSTANCE.currentEditignCourse
							.removeCoRequirement(course);
					if (response.success) {
						ObservableList<String> currentRequirements = listCoRequirements.getItems();
						currentRequirements.remove(getParsedCourse(initials, section, name));
						listCoRequirements.setItems(FXCollections.observableArrayList(currentRequirements));
						labelModificationResult.setText(Messages.getUILabel(UILabel.SUCCESS));
					} else {
						labelModificationResult
								.setText(Messages.getUILabel(UILabel.NOT_REMOVED) + ": " + response.response);
					}
					break;
				}
			}
		} else {
			// TODO Uncomment when function is created
			// ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION)
			// + "(" + Messages.getUILabel(UILabel.REMOVE_CO_REQUIREMENT) +
			// ")");
		}
	}

}
