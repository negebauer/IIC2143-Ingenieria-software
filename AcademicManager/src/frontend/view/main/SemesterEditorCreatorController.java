package frontend.view.main;

import java.util.ArrayList;

import backend.courses.Semester;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SemesterEditorCreatorController {
	
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
			
		if(!Util.EDITOR)
			createSemesterVisibility();
	}
	
	public void createSemesterVisibility(){
		
		//Buttons
		Util.changeOV(this.btnCreateSemester);
		Util.changeOV(this.btnEditSemester);
		Util.changeOV(this.btnSaveSemester);
		Util.changeOV(this.btnAddCourse);
		Util.changeOV(this.btnRemoveCourse);
		
		//ChoiceBoxes
		Util.changeOV(this.chBxSemesters);
		Util.changeOV(this.chBxCourses);
		
		//ListView
		Util.changeOV(this.listCoursesInSemester);
	}
}
