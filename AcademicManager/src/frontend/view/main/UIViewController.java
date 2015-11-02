package frontend.view.main;

import java.net.URL;

import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UIViewController {

	@FXML
	Button btnLanguage;
	@FXML
	Button btnReload;
	@FXML
	Button btnBack;
	@FXML
	Button btnLogout;

	/**
	 * Use this variable to define the URL of the view represented by the ViewController. MUST OVERRIDE
	 */
	static URL view = Object.class.getResource("/frontend/view/main/UIView.fxml");

	/**
	 * You must override this function to setUp all the
	 * buttons and labels of the View to the current language.
	 * Override and call super.setUp() ALWAYS.
	 */
	public void setUp() {
		btnLanguage.setText(Messages.LANGUAGE().toString().substring(0, 2));
		btnReload.setText("R");
		btnBack.setText(Messages.getUILabel(UILabel.BACK));
		btnLogout.setText(Messages.getUILabel(UILabel.LOGOUT));

		//Stage stage = CurrentViewHandler.INSTANCE.primaryStage;
		//stage.setTitle("RENNAB asdadwsads");
	}

	/**
	 * Toggles the current language
	 */
	public void btnLanguage_Pressed() {
		Manager.INSTANCE.changeLanguage();
		setUp();
	}

	public void btnReload_Pressed() {
		Manager.INSTANCE.reloadData();
	}

	public void btnBack_Pressed() {
		URL parentView = CurrentViewHandler.INSTANCE.getParentView();
		if (parentView != null) {
			ViewUtilities.openView(parentView);
		}
	}

	public void btnLogout_Pressed() {
		Manager.INSTANCE.currentUser = null;
		CurrentViewHandler.INSTANCE.clearParentView();
		ViewUtilities.openView(LogInController.view);
	}

	public void hideLanguage() {
		btnLanguage.setVisible(false);
	}

	public void hideReload() {
		btnReload.setVisible(false);
	}

	public void hideBack() {
		btnBack.setVisible(false);
	}

	public void hideLogout() {
		btnLogout.setVisible(false);
	}

}
