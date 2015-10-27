package frontend.view.main;

import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Curriculum;
import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.others.Utilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CurricularAdvanceController implements IController {
	
	@FXML
	Label labelCurricularAdvanceWelcomeMessage;	
	@FXML
	ChoiceBox<?> chBxStudyProgram;
	@FXML
	ListView<?> listCurriculum;
	@FXML
	ListView<?> listAdvance;	
	@FXML
	Button btnShowAdvance;
	
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}
	
	public void btnShowAdvance_Pressed(){
	
		String name = chBxStudyProgram.getSelectionModel().getSelectedItem().toString();
		
		Curriculum curriculum = Manager.INSTANCE.students.get(0).getCurriculum(); //CURRICULUM ALUMNO
		StudyProgram program = null; //ASOCIAR PROGRAMA
		
		for(StudyProgram sp : Manager.INSTANCE.studyPrograms)
			if(name == sp.getName()) {
				program = sp;
				break;
			}
										
		ArrayList<Course> advance = Utilities.getFinichedCourses(program, curriculum, true);
		ArrayList<Course> unfinished = Utilities.getFinichedCourses(program, curriculum, false);
		
		ArrayList<String> aux = new ArrayList<String>();
		for(Course course : advance)
			aux.add(course.getInitials() + " " + course.getName());
		
		//this.listAdvance.setItems(FXCollections.observableArrayList(aux));
	}
}
