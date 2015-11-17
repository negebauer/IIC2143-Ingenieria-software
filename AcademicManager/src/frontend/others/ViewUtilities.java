package frontend.others;

import java.io.IOException;
import java.net.URL;

import frontend.main.MViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public final class ViewUtilities {
	
	public enum AutoCompleteMode {
        STARTS_WITH, CONTAINING;
    }
	
	/**
	 * Open a new window view while keeping the view hierarchy.
	 * Allows the operation of the back button.
	 */
	public static void openView(URL location, URL sender) {
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null; 	
		try {
			root = (Parent) loader.load();
			((MViewController) loader.getController()).setUp();
			Stage stage = CurrentViewHandler.INSTANCE.primaryStage;
			if (sender != null) {
				CurrentViewHandler.INSTANCE.addNewParentView(sender);
			}
			stage.setScene(new Scene(root));
			stage.setTitle("RENNAB");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a new window without adding it to the view hierarchy.
	 */
	public static void openView(URL location) {
		openView(location, null);
	}
	
	/**
	 * Opens a new window
	 * @param location
	 */
	public static void openNewView(URL location) {
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null; 	
		try {
			root = (Parent) loader.load();
			((MViewController) loader.getController()).setUp();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("RENNAB");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Change Object Visibility
	 * @param o
	 */
	public static void changeOV(Object o) {
		if(o instanceof Button)
			((Button)o).visibleProperty().set(!((Button)o).visibleProperty().get());
		else if(o instanceof Label)
			((Label)o).visibleProperty().set(!((Label)o).visibleProperty().get());
		else if(o instanceof TextField)
			((TextField)o).visibleProperty().set(!((TextField)o).visibleProperty().get());
		else if(o instanceof ChoiceBox)
			((ChoiceBox<?>)o).visibleProperty().set(!((ChoiceBox<?>)o).visibleProperty().get());
		else if(o instanceof TextArea)
			((TextArea)o).visibleProperty().set(!((TextArea)o).visibleProperty().get());
		else if(o instanceof ListView)
			((ListView<?>)o).visibleProperty().set(!((ListView<?>)o).visibleProperty().get());
	}
	
	/***
	 * Auto Complete Combo Box
	 * @param comboBox
	 * @param mode
	 */
	public static<T> void autoComplete(ComboBox<T> comboBox) {
        ObservableList<T> data = comboBox.getItems();
        AutoCompleteMode mode = AutoCompleteMode.CONTAINING;
        
        comboBox.setEditable(true);
        comboBox.getEditor().focusedProperty().addListener(observable -> {
            if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
                comboBox.getEditor().setText(null);
            }
        });
        comboBox.addEventHandler(KeyEvent.KEY_PRESSED, t -> comboBox.hide());
        comboBox.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

            private boolean moveCaretToPos = false;
            private int caretPos;

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP) {
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.DOWN) {
                    if (!comboBox.isShowing()) {
                        comboBox.show();
                    }
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                } else if (event.getCode() == KeyCode.DELETE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                }

                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT || event.getCode().equals(KeyCode.SHIFT) || event.getCode().equals(KeyCode.CONTROL)
                        || event.isControlDown() || event.getCode() == KeyCode.HOME
                        || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
                    return;
                }

                ObservableList<T> list = FXCollections.observableArrayList();
                for (T aData : data) {
                    if (mode.equals(AutoCompleteMode.STARTS_WITH) && aData.toString().toLowerCase().startsWith(comboBox.getEditor().getText().toLowerCase())) {
                        list.add(aData);
                    } else if (mode.equals(AutoCompleteMode.CONTAINING) && aData.toString().toLowerCase().contains(comboBox.getEditor().getText().toLowerCase())) {
                        list.add(aData);
                    }
                }
                String t = comboBox.getEditor().getText();

                comboBox.setItems(list);
                comboBox.getEditor().setText(t);
                if (!moveCaretToPos) {
                    caretPos = -1;
                }
                moveCaret(t.length());
                if (!list.isEmpty()) {
                    comboBox.show();
                }
            }

            private void moveCaret(int textLength) {
                if (caretPos == -1) {
                    comboBox.getEditor().positionCaret(textLength);
                } else {
                    comboBox.getEditor().positionCaret(caretPos);
                }
                moveCaretToPos = false;
            }
        });
    }

    public static<T> T getComboBoxValue(ComboBox<T> comboBox) {
        if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
            return null;
        } else {
            return comboBox.getItems().get(comboBox.getSelectionModel().getSelectedIndex());
        }
    }
}