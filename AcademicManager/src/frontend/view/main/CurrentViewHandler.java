package frontend.view.main;

import java.net.URL;
import java.util.ArrayList;

import javafx.stage.Stage;

public class CurrentViewHandler {
	public final static CurrentViewHandler INSTANCE = new CurrentViewHandler();
	
	public Stage primaryStage;
	
	private CurrentViewHandler() {
		
	}

	private ArrayList<URL> viewTree = new ArrayList<URL>();
	
	public URL getParentView() {
		if (viewTree.size() == 0) {
			return null;
		}
		URL requested = viewTree.get(viewTree.size() - 1);
		viewTree.remove(viewTree.size() - 1);
		return requested;
	}
	
	public void addNewParentView(URL parent) {
		viewTree.add(parent);
	}
	
	public void clearParentView() {
		viewTree = new ArrayList<URL>();
	}
}
