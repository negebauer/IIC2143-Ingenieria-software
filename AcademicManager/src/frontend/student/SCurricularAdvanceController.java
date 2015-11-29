package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.others.Utilities;
import backend.users.Student;
import frontend.main.MViewController;
import frontend.others.Parser;
import frontend.others.ViewUtilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class SCurricularAdvanceController extends MViewController {

	@FXML
	Label labelCurricularAdvanceWelcomeMessage;
	@FXML
	Label labelCurricularAdvanceWelcomeMessage2;
	@FXML
	Label labelApproved;
	@FXML
	Label labelNotApproved;
	@FXML
	Label labelStudyProgram;
	@FXML
	TextArea txASemestersAndCourses;
	@FXML
	ChoiceBox<String> chBxStudyProgram;
	@FXML
	ListView<String> listUnfinished;
	@FXML
	ListView<String> listAdvance;
	@FXML
	ListView<String> listStudyPrograms;
	@FXML
	Button btnAddStudyProgram;

	public final static URL VIEW = Object.class.getResource("/frontend/student/SCurricularAdvance.fxml");
	Student user = (Student) Manager.INSTANCE.currentUser;
	StudyProgram currentStudyProgram;

	@Override
	public void setUp() {
		super.setUp();

		labelApproved.setText(Messages.getUILabel(UILabel.CURRICULAR_ADVANCE_APPROVED));
		labelNotApproved.setText(Messages.getUILabel(UILabel.CURRICULAR_ADVANCE_NOT_APPROVED));

		chBxStudyProgram.setItems(FXCollections.observableArrayList(generateStudyProgramsList()));
		chBxStudyProgram.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					showCurricularAdvance(newValue);
					currentStudyProgram = Parser.getStudyProgramForParsed(newValue, user.getCurriculum().getStudyPrograms());
					showStudyProgram(currentStudyProgram);
				}
			}
		});
		chBxStudyProgram.getSelectionModel().selectFirst();
	}

	public ArrayList<String> generateStudyProgramsList() {
		ArrayList<String> studyProgramsList = new ArrayList<String>();
		for (StudyProgram studyProgram : user.getCurriculum().getStudyPrograms()) {
			studyProgramsList.add(studyProgram.getName() + " - " + studyProgram.getyearProgram());
		}
		return studyProgramsList;
	}

	public void showCurricularAdvance(String studyProgramRawInfo) {
		String[] splitStudyProgramRawInfo = studyProgramRawInfo.split(" - ");
		String studyProgramName = splitStudyProgramRawInfo[0];
		int studyProgramYear = Integer.valueOf(splitStudyProgramRawInfo[1]);

		StudyProgram program = null;
		for (StudyProgram studyProgram : Manager.INSTANCE.studyPrograms) {
			if (studyProgram.getName().equals(studyProgramName) && studyProgram.getyearProgram() == studyProgramYear) {
				program = studyProgram;
			}
		}

		ArrayList<String> advance = Utilities.getCoursesList(program, user.getCurriculum(), true);
		this.listAdvance.setItems(FXCollections.observableArrayList(advance));

		ArrayList<String> unfinished = Utilities.getCoursesList(program, user.getCurriculum(), false);
		this.listUnfinished.setItems(FXCollections.observableArrayList(unfinished));
	}
	
	public void showStudyProgram(StudyProgram studyProgram) {
		String completeStudyProgram = "";
		int semesterIndex = 1;
		for (Semester semester : studyProgram.getSemesters()) {
			completeStudyProgram += "Semester " + semesterIndex + ":\n";
			for (Course course : semester.getCourses()) {
				completeStudyProgram += "\t" + course.getInitials() + " " + course.getName() + "\n";
			}
			semesterIndex++;
			completeStudyProgram += "\n";
		}
		txASemestersAndCourses.setText(completeStudyProgram);
	}
	
	public void btnAddStudyProgram_Pressed() {
		ViewUtilities.openView(SChangeStudyProgramViewController.VIEW, VIEW);
	}
}
