package frontend.view.main;

import java.net.URL;

import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UIViewController {

	@FXML
	Button btnLanguage;
	@FXML
	Button btnReload;
	@FXML
	Button btnBack;
	@FXML
	Button btnLogout;
	
	User currentUser;
	URL parentView;
	
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
		ViewUtilities.openView(parentView);
	}

	public void btnLogout_Pressed() {
	
	}
	
}
