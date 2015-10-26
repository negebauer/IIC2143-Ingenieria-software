package frontend.view.main;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
	ChoiceBox<?> chBxSemesters;
	@FXML
	ChoiceBox<?> chBxCourses;
	@FXML
	ListView<?> listCoursesInSemester;
	@FXML 
	
	public void btnCreateSemester_Pressed(){

		this.createSemesterVisibility();
	}

	public void btnEditSemester_Pressed(){

		this.createSemesterVisibility();
	}

	public void btnAddCourse_Pressed(){

	}

	public void btnRemoveCourse_Pressed(){

	}

	public void btnSaveSemester_Pressed(){

	}
	
	public SemesterEditorCreatorController(){
	
		System.out.println("Nueva Ventana");
		System.out.println(Util.EDITOR);
		
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
