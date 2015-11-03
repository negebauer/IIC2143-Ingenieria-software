package frontend.main;

import java.net.URL;

import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.others.CurrentViewHandler;
import frontend.others.ViewUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MViewController {

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
	public static URL view = Object.class.getResource("/frontend/main/MView.fxml");

	/**
	 * You must override this function to setUp all the
	 * buttons and labels of the View to the current language.
	 * Override and call super.setUp() ALWAYS.
	 */
	public void setUp() {
		Manager.INSTANCE.changeLanguage();
		btnLanguage.setText(Messages.LANGUAGE().toString().substring(0, 2));
		Manager.INSTANCE.changeLanguage();
		btnReload.setText("R");
		btnBack.setText(Messages.getUILabel(UILabel.BACK));
		btnLogout.setText(Messages.getUILabel(UILabel.LOGOUT));
		
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
		ViewUtilities.openView(MLogInController.view);
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
	
	/**
	 * Transform the nice course info string into an easy access String[].
	 * [initials, section, name]
	 */
	public String[] getParsedInitialsSectionName(String raw) {
		String[] split1 = raw.split(" - ");
		String[] split2 = split1[0].split("-");
		String initials = split2[0];
		int section = Integer.valueOf(split2[1]);
		String name = split1[1];
		String complete = initials + "&" + section + "&" + name;
		return complete.split("&");
		
	}
	
	/**
	 * Returns the course info in a nice string for the user.
	 * Initials-Section - Name
	 */
	public String getParsedCourse(String initials, int section, String name) {
		String parsed = initials + "-" + section + " - " + name;
		return parsed;
	}
}
