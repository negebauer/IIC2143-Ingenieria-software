package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.CoursedSemester;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Student;
import frontend.main.MViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SShowScheduleController extends MViewController {

	@FXML
	Label labelShowScheduleWelcomeMessage;	
	@FXML
	ChoiceBox<?> chBxSemesters;
	@FXML
	TextArea txASchedule;

	Student user = (Student) Manager.INSTANCE.currentUser;
	public static URL view = Object.class.getResource("/frontend/student/SShowSchedule.fxml");

	public void setUp() {
		super.setUp();

		ArrayList<String> semesterInfo = new ArrayList<String>();
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			int year = coursedSemester.getYear();
			String semester = coursedSemester.getSemester().getSemesterNumber();
			semesterInfo.add(year + " - " + semester);
		}
		int currentYear = user.getCurriculum().getCurrentSemester().getYear();
		String currentSemester = user.getCurriculum().getCurrentSemester().getSemester().getSemesterNumber();
		semesterInfo.add(currentYear + " - " + currentSemester);

		chBxSemesters.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed (ObservableValue<? extends Number> observableValue, Number value, Number newValue) {
						if (accesos[newValue.intValue()] == Messages.getUILabel(UILabel.STUDENT)){
							showStudentFields();
							isAdmin = false;
							isProfessor = false;
							isStudent = true;
						} else if (accesos[newValue.intValue()] == Messages.getUILabel(UILabel.PROFESSOR)) {
							hideStudentFields();
							isAdmin = false;
							isProfessor = true;
							isStudent = false;
							listCarreers.setItems(FXCollections.observableArrayList());
						} else if (accesos[newValue.intValue()] == Messages.getUILabel(UILabel.ADMINISTRATOR)) {
							hideStudentFields();
							isAdmin = true;
							isProfessor = false;
							isStudent = false;
							listCarreers.setItems(FXCollections.observableArrayList());
						}
					}
				});
	}
}
