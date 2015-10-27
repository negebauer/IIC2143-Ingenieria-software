package frontend.view.main;

import java.util.ArrayList;

import backend.courses.Semester;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SemesterEditorCreatorController implements IController {
	
	@FXML
	Label labelSemesterEditorWelcomeMessage;
	@FXML
	Button btnCreateSemester;
	@FXML
	Button btnEditSemester;
	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnSaveSemester;
	@FXML
	ChoiceBox<String> chBxSemesters;
	@FXML
	ChoiceBox<String> chBxCourses;
	@FXML
	ListView<String> listCoursesInSemester;
	
	private Semester actual;
	private ArrayList<String> courses = new ArrayList<String>();
	
	public void btnCreateSemester_Pressed(){

		this.createSemesterVisibility();
	}

	public void btnEditSemester_Pressed(){

		this.createSemesterVisibility();
	}

	public void btnAddCourse_Pressed(){

		boolean exist = false;
		String course = chBxCourses.getSelectionModel().getSelectedItem().toString();
		
		for(String str : courses)
			if(str == course)
				exist = true;
		
		if(exist)
			courses.add(course);	

		this.listCoursesInSemester.setItems(FXCollections.observableArrayList(courses));	
	}

	public void btnRemoveCourse_Pressed(){

		String course = this.listCoursesInSemester.getSelectionModel().getSelectedItem();
		
		for(String str : courses)
			if(str == course)
				courses.remove(str);
				
		this.listCoursesInSemester.setItems(FXCollections.observableArrayList(courses));
	}

	public void btnSaveSemester_Pressed(){

		//Save semester
		//actual = new Semester(AcademicSemester.BOTH, 2015, 55, null, null);
	}
	
	public SemesterEditorCreatorController(){
			
		if(!ViewUtilities.EDITOR)
			createSemesterVisibility();
	}
	
	public void createSemesterVisibility(){
		
		//Buttons
		ViewUtilities.changeOV(this.btnCreateSemester);
		ViewUtilities.changeOV(this.btnEditSemester);
		ViewUtilities.changeOV(this.btnSaveSemester);
		ViewUtilities.changeOV(this.btnAddCourse);
		ViewUtilities.changeOV(this.btnRemoveCourse);
		
		//ChoiceBoxes
		ViewUtilities.changeOV(this.chBxSemesters);
		ViewUtilities.changeOV(this.chBxCourses);
		
		//ListView
		ViewUtilities.changeOV(this.listCoursesInSemester);
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}
}
