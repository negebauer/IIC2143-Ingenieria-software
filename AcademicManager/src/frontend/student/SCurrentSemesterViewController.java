package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.courses.Semester.AddOrRemoveCourseResponse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Student;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.Validate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/* TODO Connect combobox
 * TODO Connect courses list
 * TODO Implement all buttons
 * TODO Button and combobox to show a course details
 */
public class SCurrentSemesterViewController extends MCourseSearcherSelectorViewController {

	@FXML
	Label labelMainMessage;
	@FXML
	Label labelCurrentSemester;
	@FXML
	Label labelCurrentCourses;
	@FXML
	Label labelModification;
	@FXML
	Label labelModificationResult;
	@FXML
	Label labelCurrentCoursesNames;
	@FXML
	Label labelCarreer;
	@FXML
	Button btnCreateNewSemester;
	@FXML
	ListView<String> listSelectedCourses;
	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnEditSemester;
	@FXML
	ComboBox<String> chBxCarreer;

	String responseToAddOrRemoveCourse = "";
	Boolean firstLoad = true;
	Student user = (Student) Manager.INSTANCE.currentUser;
	public static URL view = Object.class.getResource("/frontend/student/SCurrentSemesterView.fxml");
	ArrayList<ArrayList<Course>> courses = new ArrayList<ArrayList<Course>>();
	ArrayList<String> sp;
	String carreer = "";
	
	@Override
	public void setUp() {
		super.setUp();

		labelMainMessage.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_EDITOR_MAIN));
		labelCurrentCourses.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_CURRENT_COURSES));
		btnCreateNewSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_CREATE_NEW));
		labelModification.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_MAIN));
		btnCreateNewSemester.setCursor(Cursor.HAND);
		btnAddCourse.setCursor(Cursor.HAND);
		btnRemoveCourse.setCursor(Cursor.HAND);
		btnEditSemester.setCursor(Cursor.HAND);

		sp = new ArrayList<String>();
		for (StudyProgram p : user.getCurriculum().getStudyPrograms()) {
			sp.add(p.getName());
		}
		chBxCarreer.setItems(FXCollections.observableArrayList(sp));
		chBxCarreer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					carreer = newValue.trim();
					if (btnEditSemester.isVisible()) {
						showInfoOfSemesterOrEditor();
					} else {
						btnEditSemester_Pressed();
					}
				}
			}			
		});		
		
		if (responseToAddOrRemoveCourse == "") {
			labelModificationResult.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_SUCCESS)
					+ "\n" + responseToAddOrRemoveCourse);
		} else {
			labelModificationResult.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_FAILED)
					+ "\n" + responseToAddOrRemoveCourse);
		}

		Semester currentSemester = user.getCurriculum().getCurrentSemester();
		if (currentSemester == null || currentSemester.getCourses().size() == 0) {
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_NO_CURRENT_SEMESTER));
		} else {
			String semesterInfo = currentSemester.getYear() + "-" + currentSemester.getSemester().getSemesterNumber();
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_INFO) + semesterInfo);
		
			for (int i = 0; i < sp.size(); i++) {
				courses.add(new ArrayList<Course>());
			}				
			for (Course course : currentSemester.getCourses()) {
				for (int i = 0; i < sp.size(); i++) {
					if (Validate.checkCourse(course.getInitials(), sp.get(i))) {
						courses.get(i).add(course);
					}
				}
			}	
		}
	
		if(sp.size() > 1 && currentSemester != null)
			currentSemester.setMaxCredits(currentSemester.getMaxCredits() * sp.size());

		chBxCarreer.getSelectionModel().selectFirst();
		
		if (firstLoad) {
			showInfoOfSemesterOrEditor();
			firstLoad = false;
		}
	}

	public void showInfoOfSemesterOrEditor() {
		Semester currentSemester = user.getCurriculum().getCurrentSemester();
		if (currentSemester == null || currentSemester.getCourses().size() == 0) {
			user.getCurriculum()
					.setCurrentSemester(new Semester(Manager.INSTANCE.currentSemester.getSemester(), Manager.getYear(),
							user.getCurriculum().getMaxSemesterCredits(), user.getCurriculum().getCoursedCourses(),
							null));
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_NO_CURRENT_SEMESTER));
			hideSemesterInfo();
			showSemesterCreation();
		} else {
			String semesterInfo = currentSemester.getYear() + "-" + currentSemester.getSemester().getSemesterNumber();
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_INFO) + semesterInfo);
			String names = "";		
			int count = 0;
			for (String prog : sp) {
				if (prog == carreer) {
					break;
				}
				count++;
			}			
			for (Course course : currentSemester.getCourses()) {
				for (Course c : courses.get(count)) {
					if (course.getInitials().equals(c.getInitials())) {
						String courseName = course.getInitials() + "-" + course.getSection() + ": " + course.getName();
						names = names == "" ? courseName : names + courseName;
						names += "\n";
					}			
				}				
			}
			labelCurrentCoursesNames.setText(names);
			showSemesterInfo();
			hideSemesterCreation();
		}
	}

	public void btnCreateNewSemester_Pressed() {
		showInfoOfSemesterOrEditor();
	}

	private void hideSemesterInfo() {
		labelCurrentCourses.setVisible(false);
		labelCurrentCoursesNames.setVisible(false);
		btnEditSemester.setVisible(false);
	}

	private void hideSemesterCreation() {
		hideCourseSearcher();
		hideCourseSelector();
		labelModification.setVisible(false);
		labelModificationResult.setVisible(false);
		btnCreateNewSemester.setVisible(false);
		listSelectedCourses.setVisible(false);
		btnAddCourse.setVisible(false);
		btnRemoveCourse.setVisible(false);
	}

	private void showSemesterInfo() {
		labelCurrentCourses.setVisible(true);
		labelCurrentCoursesNames.setVisible(true);
		btnEditSemester.setVisible(true);
	}

	private void showSemesterCreation() {
		showCourseSearcher();
		showCourseSelector();
		labelModification.setVisible(true);
		labelModificationResult.setVisible(true);
		btnCreateNewSemester.setVisible(true);
		listSelectedCourses.setVisible(true);
		btnAddCourse.setVisible(true);
		btnRemoveCourse.setVisible(true);
	}

	public void btnAddCourse_Pressed() {
		String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
		String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
		String initials = parsed[0];
		int section = Integer.valueOf(parsed[1]);
		String name = parsed[2];
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section
					&& course.getName().equals(name)) {
				AddOrRemoveCourseResponse response = user.getCurriculum().getCurrentSemester().addCourse(course);
				if (response.success) {
					ObservableList<String> currentCourses = listSelectedCourses.getItems();
					currentCourses.add(getParsedCourse(initials, section, name));
					listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
					responseToAddOrRemoveCourse = "";
					labelModificationResult
							.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_SUCCESS) + "\n"
									+ responseToAddOrRemoveCourse);
					int count = 0;
					for (String prog : sp) {
						if (prog == carreer) {
							break;
						}
						count++;
					}	
					courses.get(count).add(course);	
				} else {
					responseToAddOrRemoveCourse = response.response;
					labelModificationResult
							.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_FAILED) + "\n"
									+ responseToAddOrRemoveCourse);
				}
			}
		}
	}

	public void btnRemoveCourse_Pressed() {
		String rawCourseInfo = listSelectedCourses.getSelectionModel().getSelectedItem();
		String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
		String initials = parsed[0];
		int section = Integer.valueOf(parsed[1]);
		String name = parsed[2];
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section
					&& course.getName().equals(name)) {
				AddOrRemoveCourseResponse response = user.getCurriculum().getCurrentSemester().removeCourse(course);
				if (response.success) {
					ObservableList<String> currentCourses = listSelectedCourses.getItems();
					currentCourses.remove(getParsedCourse(initials, section, name));
					listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
					responseToAddOrRemoveCourse = "";
					labelModificationResult
							.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_SUCCESS) + "\n"
									+ responseToAddOrRemoveCourse);
					int count = 0;
					for (String prog : sp) {
						if (prog == carreer) {
							break;
						}
						count++;
					}
					for (Course c : courses.get(count)) {
						if (course.getInitials().equals(c.getInitials())) {
							courses.remove(c);
						}
					}
				} else {
					responseToAddOrRemoveCourse = response.response;
					labelModificationResult
							.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_FAILED) + "\n"
									+ responseToAddOrRemoveCourse);
				}
			}
		}
	}

	public void btnEditSemester_Pressed() {
		ArrayList<Course> coursess = user.getCurriculum().getCurrentSemester().getCourses();
		user.getCurriculum().setCurrentSemester(null);
		showInfoOfSemesterOrEditor();
		user.getCurriculum().getCurrentSemester().setCourses(coursess);
		ArrayList<String> currentCourses = new ArrayList<String>();
		int count = 0;
		for (String prog : sp) {
			if (prog == carreer) {
				break;
			}
			count++;
		}			
		for (Course course : coursess) {
			for (Course c : courses.get(count)) {
				if (course.getInitials().equals(c.getInitials())) {
					String parsedCourse = getParsedCourse(course.getInitials(), course.getSection(), course.getName());
					currentCourses.add(parsedCourse);
				}
			}
		}
		listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
	}
}
