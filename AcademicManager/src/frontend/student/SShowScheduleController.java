package frontend.student;

import java.net.URL;
import backend.courses.Course;
import backend.interfaces.ICourse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Student;
import frontend.main.MViewController;
import frontend.others.Validate;
import frontend.others.ViewSchedule;
import frontend.others.ViewUtilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class SShowScheduleController extends MViewController {

	@FXML
	ComboBox<String> chBxSemester;
	@FXML
	ComboBox<String> chBxCarreer;
	@FXML
	TextArea txAScheduleByCarreer;
	@FXML
	TextArea txAScheduleComplete;
	@FXML
	Label labelWelcomeMessage;
	@FXML
	Label labelAllCourses;
	@FXML
	Label labelCoursesByCarreer;
	@FXML
	Label labelCarreer;
	@FXML
	Label labelSemester;
	@FXML
	Label labelDetails1;
	@FXML
	Label labelDetails2;
	@FXML
	Label labelSchedule;
	@FXML
	Label labelLecture;
	@FXML
	Label labelAssistantship;
	@FXML
	Label labelLaboratory;
	@FXML
	CheckBox checkShowOthers;
	@FXML
	GridPane gridSchedule;

	public final static URL VIEW = Object.class.getResource("/frontend/student/SShowSchedule.fxml");
	private Student user = (Student) Manager.INSTANCE.currentUser;
	private ViewSchedule schedule;
	private Boolean load = true;
	private String carreer = "";

	@Override
	public void setUp() {
		super.setUp();
		
		schedule = new ViewSchedule(gridSchedule);	
		
		chBxCarreer.setCursor(Cursor.HAND);
		chBxCarreer.setItems(ViewUtilities.getCarreersList(user));
		chBxCarreer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					carreer = newValue.trim();
					showSchedule();
				}
			}			
		});

		chBxSemester.setCursor(Cursor.HAND);
		chBxSemester.setItems(ViewUtilities.getActualSemester(user));
		chBxSemester.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					showSchedule();
				}
			}
		});

		checkShowOthers.setCursor(Cursor.HAND);
		checkShowOthers.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				showSchedule();			
			}
		});
		
		chBxSemester.getSelectionModel().selectLast();
		chBxCarreer.getSelectionModel().selectFirst();

		if (ViewUtilities.getSemestersList(user).size() > 0 && load) {
			load = false;
		}
	}

	private void showSchedule() {

		String[] split = chBxSemester.getSelectionModel().getSelectedItem().split(" - ");
		int year = Integer.valueOf(split[0]);
		int semester = Integer.valueOf(split[1]);

		String carreerSchedule = "";
		String completeSchedule = "";

		if (year == Manager.getYear() && semester == Integer.valueOf(
				Manager.INSTANCE.currentSemester.getSemester().getSemesterNumber())) {
			
			schedule.clear();		
			for (Course course: user.getCurriculum().getCurrentSemester().getCourses()) {
				String head = course.getInitials() + "-" + course.getSection() + " - " + course.getName();
				String body = "";
				
				for (ICourse icourse : course.getCourses()) {
					String schedule = icourse.getSchedule().getSchedule(course.getInitials());
					String newLine = "\n\t" + Messages.getICourseName(icourse) + "\n\t\t" + schedule;
					body += newLine;
				}									
				if (Validate.checkCourse(course.getInitials(), carreer) || checkShowOthers.selectedProperty().get()) {
					schedule.add(course);
				}	
				if (Validate.checkCourse(course.getInitials(), carreer)) {
					carreerSchedule += head + body + "\n";
				}
				completeSchedule += head + body + "\n";	
			}		
		}
		else {

		}
		txAScheduleByCarreer.setText(carreerSchedule);
		txAScheduleComplete.setText(completeSchedule);
	}
	
	@Override
	public void setLabels() {	
		//		// TODO: [STUDENT] Create UILabel
		labelCarreer.setText(Messages.getUILabel(UILabel.CARREER));
		labelSemester.setText(Messages.getUILabel(UILabel.SEMESTER));
		labelDetails1.setText(Messages.getUILabel(UILabel.COURSE_DETAILS));
		labelDetails2.setText(Messages.getUILabel(UILabel.COURSE_DETAILS));
		labelSchedule.setText(Messages.getUILabel(UILabel.SCHEDULE));
		//		labelCoursesByCarreer.setText(Messages.getUILabel(UILabel.STUDENT_COURSES_BY_CARREER));
		//		labelAllCourses.setText(Messages.getUILabel(UILabel.STUDENT_ALL_COURSES));
		labelTitle.setText(Messages.getUILabel(UILabel.SCHEDULE).toUpperCase());
		labelLecture.setText(Messages.getUILabel(UILabel.LECTURE));
		labelAssistantship.setText(Messages.getUILabel(UILabel.ASSISTANTSHIP));
		labelLaboratory.setText(Messages.getUILabel(UILabel.LABORATORY));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.SCHEDULE_MAIN_MESSAGE));
	}
}
