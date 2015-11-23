package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.CoursedSemester;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.interfaces.ICourse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Student;
import frontend.main.MViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SShowScheduleController extends MViewController {

	@FXML
	Label labelShowScheduleWelcomeMessage;
	@FXML
	ChoiceBox<String> chBxSemesters;
	@FXML
	TextArea txASchedule;
	@FXML
	TextArea txAScheduleComplete;
	@FXML
	ComboBox<String> chBxCarreer;

	Boolean firstLoad = true;
	Student user = (Student) Manager.INSTANCE.currentUser;
	public static URL view = Object.class.getResource("/frontend/student/SShowSchedule.fxml");
	String carreer = "";

	@Override
	public void setUp() {
		super.setUp();

		ArrayList<String> sp = new ArrayList<String>();
		for (StudyProgram p : user.getCurriculum().getStudyPrograms()) {
			sp.add(p.getName());
		}
		chBxCarreer.setItems(FXCollections.observableArrayList(sp));
		chBxCarreer.setOnAction((event) -> {
			carreer = chBxCarreer.getSelectionModel().getSelectedItem().trim();
			chBxSemesters.getSelectionModel().selectLast();
		});
		chBxCarreer.getSelectionModel().selectFirst();
		
		labelShowScheduleWelcomeMessage.setText(Messages.getUILabel(UILabel.SCHEDULE_MAIN_MESSAGE));
	
		ArrayList<String> semesterInfo = new ArrayList<String>();
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			int year = coursedSemester.getYear();
			String semester = coursedSemester.getSemester().getSemesterNumber();
			semesterInfo.add(year + " - " + semester);
		}
		if (user.getCurriculum().getCurrentSemester() != null) {
			int currentYear = user.getCurriculum().getCurrentSemester().getYear();
			String currentSemester = user.getCurriculum().getCurrentSemester().getSemester().getSemesterNumber();
			semesterInfo.add(currentYear + " - " + currentSemester);
		}
		chBxSemesters.setItems(FXCollections.observableArrayList(semesterInfo));
		chBxSemesters.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					showSchedule(newValue);
				}
			}
		});
		if (semesterInfo.size() > 0 && firstLoad) {
			chBxSemesters.getSelectionModel().selectLast();
			firstLoad = false;
		}
	}

	private void showSchedule(String rawString) {
		String[] split = rawString.split(" - ");
		int year = Integer.valueOf(split[0]);
		int semester = Integer.valueOf(split[1]);
		String allSchedulesString = "";
		String completeSchedule = "";
		
		if (year == Manager.getYear()
				&& semester == Integer.valueOf(Manager.INSTANCE.currentSemester.getSemester().getSemesterNumber())) {	
			for (Course course : user.getCurriculum().getCurrentSemester().getCourses()) {
				
				String initials = course.getInitials();
				String section = "" + course.getSection();
				String name = course.getName();
				String firstPart = initials + "-" + section + " - " + name + "\n\t";
				String courseSchedules = "";
				
				for (StudyProgram sp : Manager.INSTANCE.studyPrograms) {
					if (sp.getName().equals(carreer)) {
						for (Semester s : sp.getSemesters()) {
							for (Course c : s.getCourses()) {
								if (c.getInitials().equals(course.getInitials())) {
									
									for (ICourse icourse : course.getCourses()) {
										String schedule = icourse.getSchedule().getSchedule(course.getInitials());
										String newLine = firstPart + Messages.getICourseName(icourse) + "\n\t\t" + schedule;
										courseSchedules = courseSchedules == "" ? newLine : courseSchedules + "\n" + newLine;
									}
									allSchedulesString = allSchedulesString == "" ? courseSchedules : allSchedulesString + courseSchedules;
									allSchedulesString = allSchedulesString + "\n";	
								}
							}
						}
					}
				}
				
				for (ICourse icourse : course.getCourses()) {
					String schedule = icourse.getSchedule().getSchedule(course.getInitials());
					String newLine = firstPart + Messages.getICourseName(icourse) + "\n\t\t" + schedule;
					courseSchedules = courseSchedules == "" ? newLine : courseSchedules + "\n" + newLine;
				}
				completeSchedule = completeSchedule == "" ? courseSchedules : completeSchedule + courseSchedules;
				completeSchedule = completeSchedule + "\n";	
			}						
		} else {

		}
		txASchedule.setText(allSchedulesString);
		txAScheduleComplete.setText(completeSchedule);
	}
}
