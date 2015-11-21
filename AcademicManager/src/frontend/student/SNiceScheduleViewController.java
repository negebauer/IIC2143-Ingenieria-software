package frontend.student;

import java.net.URL;

import frontend.main.MViewController;

/**
 * When creating a new ViewController this file allows to reduce the amount of
 * writing.
 */
public class SNiceScheduleViewController extends MViewController {

	public static URL view = Object.class.getResource("/frontend/main/SNiceScheduleView.fxml");

	@Override
	public void setUp() {
		super.setUp();
		 hideBack();
		 hideReload();
		 hideLanguage();
		 hideLogout();
		 
		 
	}
}
