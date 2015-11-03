package frontend.student;

import java.net.URL;

import backend.courses.Course;
import backend.courses.Semester;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Student;
import frontend.main.MViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/* TODO Connect choicebox
 * TODO Connect courses list
 * TODO Implement all buttons
 */
public class SCurrentSemesterViewController extends MViewController {

	@FXML
	Label labelMainMessage;
	@FXML
	Label labelCurrentSemester;
	@FXML
	Label labelCurrentCourses;
	@FXML
	Label labelSearchCourse;
	@FXML
	Label labelSelectCoure;
	@FXML
	Label labelModification;
	@FXML
	Label labelModificationResult;
	@FXML
	Label labelCurrentCoursesNames;
	@FXML
	Button btnCreateNewSemester;
	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnContinue;
	@FXML
	Button btnSearchCourse;
	
	Student user = (Student) Manager.INSTANCE.currentUser;
	public static URL view = Object.class.getResource("/frontend/student/SCurrentSemesterView.fxml");
	
	@Override
	public void setUp() {
		super.setUp();
		
		labelMainMessage.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_EDITOR_MAIN));
		labelCurrentCourses.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_CURRENT_COURSES));
		btnCreateNewSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_CREATE_NEW));
		labelSearchCourse.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_SEARCH_COURSE));
		// TODO Set text values in Messages
//		labelSelectCoure.setText(value);
//		labelModification.setText(value);
//		labelModificationResult.setText(value);
//		btnCreateNewSemester.setText(value);
//		btnAddCourse.setText(value);
//		btnRemoveCourse.setText(value);
//		btnContinue.setText(value);
		btnSearchCourse.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_SEARCH_COURSE));
		
		Semester currentSemester = user.getCurriculum().getCurrentSemester();
		if (currentSemester == null) {
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_NO_CURRENT_SEMESTER));
			hideSemesterInfo();
			showSemesterCreation();
		} else {
			String semesterInfo = currentSemester.getYear() + "-" + currentSemester.getSemester().getSemesterNumber();
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_INFO) + semesterInfo);
			String names = "";
			for (Course course : currentSemester.getCourses()) {
				String courseName = course.getInitials() + "-" + course.getSection() + ": " + course.getName() + "\n";
				names = names == "" ? courseName : names + courseName;
			}
			labelCurrentCoursesNames.setText(names);
			showSemesterInfo();
			hideSemesterCreation();
		}
	}
	
	public void btnCreateNewSemester_Pressed() {
		
	}

	public void btnAddCourse_Pressed() {

	}

	public void btnRemoveCourse_Pressed() {

	}

	public void btnContinue_Pressed() {

	}
	
	public void btnSearchCourse_Pressed() {
		
	}
	
	private void hideSemesterInfo() {
		labelCurrentCourses.setVisible(false);
		labelCurrentCoursesNames.setVisible(false);
	}
	
	private void showSemesterInfo() {
		labelCurrentCourses.setVisible(true);
		labelCurrentCoursesNames.setVisible(true);
	}
	
	private void hideSemesterCreation() {
		btnCreateNewSemester.setVisible(false);
		labelSearchCourse.setVisible(false);
		labelSelectCoure.setVisible(false);
		labelModification.setVisible(false);
		labelModificationResult.setVisible(false);
		btnAddCourse.setVisible(false);
		btnRemoveCourse.setVisible(false);
		btnContinue.setVisible(false);
		btnSearchCourse.setVisible(false);
	}
	
	private void showSemesterCreation() {
		btnCreateNewSemester.setVisible(true);
		labelSearchCourse.setVisible(true);
		labelSelectCoure.setVisible(true);
		labelModification.setVisible(true);
		labelModificationResult.setVisible(true);
		btnAddCourse.setVisible(true);
		btnRemoveCourse.setVisible(true);
		btnContinue.setVisible(true);
		btnSearchCourse.setVisible(true);
	}
}
