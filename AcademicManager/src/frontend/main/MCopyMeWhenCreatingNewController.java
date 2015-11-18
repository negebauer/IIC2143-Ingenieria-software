package frontend.main;

import java.net.URL;

/**
 * When creating a new ViewController this file allows to reduce the amount of
 * writing.
 */
public class MCopyMeWhenCreatingNewController extends MViewController {

	// TODO Change this view to reference the view of the controller
	public static URL view = Object.class.getResource("/frontend/main/MView.fxml");

	// TODO Set up any additional buttons here
	@Override
	public void setUp() {
		super.setUp();
		// hideBack();
		// hideReload();
		// hideLanguage();
		// hideLogout();
	}
}
