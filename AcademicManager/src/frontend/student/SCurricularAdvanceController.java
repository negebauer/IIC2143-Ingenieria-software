package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Curriculum;
import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.others.Utilities;
import backend.users.Student;
import frontend.main.MViewController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SCurricularAdvanceController extends MViewController {
	
	@FXML
	Label labelCurricularAdvanceWelcomeMessage;	
	@FXML
	ChoiceBox<String> chBxStudyProgram;
	@FXML
	ListView<String> listUnfinished;
	@FXML
	ListView<String> listAdvance;	
	@FXML
	Button btnShowAdvance;
	
	public static URL view = Object.class.getResource("/frontend/student/SCurricularAdvance.fxml");
	
	public void setUp() {
		super.setUp();
		
	}
	
	public void btnShowAdvance_Pressed(){
		String name = chBxStudyProgram.getSelectionModel().getSelectedItem().toString();
		
		Curriculum curriculum = null; //CURRICULUM ALUMNO
		StudyProgram program = null; //ASOCIAR PROGRAMA
		
		for(StudyProgram sp : Manager.INSTANCE.studyPrograms)
			if (name == sp.getName()) {
				program = sp;
				break;
			}
					
		for(Student student : Manager.INSTANCE.students)
			if (student.getRut() == Manager.INSTANCE.currentUser.getRut())
				curriculum = student.getCurriculum();		
		
		ArrayList<String> advance = Utilities.getCoursesList(program, curriculum, true);
		this.listAdvance.setItems(FXCollections.observableArrayList(advance));
		
		ArrayList<String> unfinished = Utilities.getCoursesList(program, curriculum, false);
		this.listUnfinished.setItems(FXCollections.observableArrayList(unfinished));
	}
}
