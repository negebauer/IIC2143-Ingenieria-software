package frontend.main;

import java.net.URL;
import java.util.ArrayList;

import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Admin;
import backend.users.Assistant;
import backend.users.Professor;
import backend.users.Student;
import backend.users.User;
import frontend.admin.AMainViewController;
import frontend.others.ViewUtilities;
import frontend.professor.PMainViewController;
import frontend.student.SMainViewController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MLogInController extends MViewController {

	@FXML
	ComboBox<String> chBxUsers;
	@FXML
	Button btnSignIn;
	@FXML
	Button btnRegister;
	@FXML
	Label labelSignIn;
	@FXML
	Label labelRegistration;
	@FXML
	Label labelLogIn;

	static URL view = Object.class.getResource("/frontend/main/MLogIn.fxml");

	@Override
	public void setUp() {
		super.setUp();
		hideBack();
		hideLogout();
		
		Manager.INSTANCE.currentUser = null;

		btnSignIn.setText(Messages.getUILabel(UILabel.SIGN_IN));
		btnRegister.setText(Messages.getUILabel(UILabel.REGISTER));
		labelSignIn.setText(Messages.getUILabel(UILabel.SIGN_IN_AS_USER));
		labelRegistration.setText(Messages.getUILabel(UILabel.DONT_HAVE_ACCOUNT_REGISTER));
		labelLogIn.setText(Messages.getUILabel(UILabel.LOG_IN));

		btnSignIn.setCursor(Cursor.HAND);
		btnRegister.setCursor(Cursor.HAND);
		ArrayList<String> users = new ArrayList<String>();

		for (Professor professor : Manager.INSTANCE.professors) {
			users.add(getUser(UILabel.PROFESSOR, professor));
		}
		for (Student student : Manager.INSTANCE.students) {
			users.add(getUser(UILabel.STUDENT, student));
		}
		for (Assistant assistant : Manager.INSTANCE.assistants) {
			users.add(getUser(UILabel.ASSISTANT, assistant));
		}
		for (Admin admin : Manager.INSTANCE.admins) {
			users.add(getUser(UILabel.ADMINISTRATOR, admin));
		}

		chBxUsers.setItems(FXCollections.observableArrayList(users));
		ViewUtilities.autoComplete(chBxUsers);
	}

	private String getUser(Messages.UILabel label, User user) {
		return Messages.getUILabel(label) + " " + user.getRut() + " " + user.getName() + " " + user.getLastnameFather();
	}

	public void btnSignIn_Pressed() {
		ArrayList<User> users = new ArrayList<User>();
		users.addAll(Manager.INSTANCE.students);
		users.addAll(Manager.INSTANCE.admins);
		users.addAll(Manager.INSTANCE.professors);

		if (this.chBxUsers.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		
		for (User user : users) {
			if (this.chBxUsers.getSelectionModel().getSelectedItem().split(" ")[1].equals(user.getRut())) {
				Manager.INSTANCE.currentUser = user;
				break;
			}
		}

		if (Manager.INSTANCE.currentUser instanceof Student) {
			ViewUtilities.openView(SMainViewController.VIEW, view);
		} else if (Manager.INSTANCE.currentUser instanceof Admin) {
			ViewUtilities.openView(AMainViewController.view, view);
		} else if (Manager.INSTANCE.currentUser instanceof Professor) {
			ViewUtilities.openView(PMainViewController.view, view);
		}
	}

	public void btnRegister_Pressed() {
		ViewUtilities.openView(MNewUserRegistrationController.view, view);
	}

	public static URL getView() {
		return view;
	}
}