package frontend.view.main;

import javafx.stage.Stage;

public class CurrentViewHandler {
	public final static CurrentViewHandler INSTANCE = new CurrentViewHandler();
	
	public Stage primaryStage;
	
	private CurrentViewHandler() {
		
	}
}
