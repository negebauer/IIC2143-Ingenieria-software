package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Semester;
import backend.courses.Semester.AddOrRemoveCourseResponse;
import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Student;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.Validate;
import frontend.others.ViewSchedule;
import frontend.others.ViewUtilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class SCurrentSemesterViewController extends MCourseSearcherSelectorViewController {

	@FXML
	Button btnCreateNewSemester;
	@FXML
	Button btnEditSemester;
	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnForums;
	@FXML
	Button btnCurricularAdvance;
	@FXML
	Button btnAcademicHistory;
	@FXML
	Button btnShowSchedule;
	@FXML
	ComboBox<String> chBxCarreer;
	@FXML
	Label labelMainMessage;
	@FXML
	Label labelCurrentCoursesNames;
	@FXML
	Label labelModificationResult;
	@FXML
	Label labelCurrentSemester;
	@FXML
	Label labelCurrentCourses;
	@FXML
	Label labelCarreer;
	@FXML
	Label labelCoursesList;
	@FXML
	Label labelDetails;
	@FXML
	Label labelCoursesInfo;
	@FXML
	Label labelSchedule;
	@FXML
	Label labelLecture;
	@FXML
	Label labelAssistantship;
	@FXML
	Label labelLaboratory;
	@FXML
	Label labelGoTo;
	@FXML
	ListView<String> listDetails;
	@FXML
	ListView<String> listSelectedCourses;
	@FXML
	GridPane gridSchedule;

	public final static URL VIEW = Object.class.getResource("/frontend/student/SCurrentSemesterView.fxml");
	private Student user = (Student) Manager.INSTANCE.currentUser;
	private ArrayList<ArrayList<Course>> courses = new ArrayList<ArrayList<Course>>();
	private String responseToAddOrRemoveCourse = "";
	private Boolean firstLoad = true;
	private ViewSchedule schedule;
	private ArrayList<String> sp;
	private String carreer = "";
	
	@Override
	public void setUp() {
		super.setUp();
		
		schedule = new ViewSchedule(gridSchedule);
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
		
		chBxSelectedCourse.setOnAction((event) -> {		
			String[] selection = getParsedInitialsSectionName(chBxSelectedCourse.getSelectionModel().getSelectedItem());
			listDetails.setItems(FXCollections.observableArrayList(ViewUtilities.getDetails(selection)));
		});	
		
		if (responseToAddOrRemoveCourse == "") {
			labelModificationResult.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_SUCCESS)
					+ "\n" + responseToAddOrRemoveCourse);
		} else {
			labelModificationResult.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_FAILED)
					+ "\n" + responseToAddOrRemoveCourse);
		}
			
		if (firstLoad) {		
			Semester currentSemester = user.getCurriculum().getCurrentSemester();
			if (currentSemester != null && currentSemester.getCourses().size() != 0) {
				String semesterInfo = currentSemester.getYear() + "-" + currentSemester.getSemester().getSemesterNumber();
				labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_INFO) + semesterInfo);
				
				ObservableList<String> currentCourses = listSelectedCourses.getItems();
				for (int i = 0; i < sp.size(); i++) {
					courses.add(new ArrayList<Course>());
				}				
				for (Course course : currentSemester.getCourses()) {
					for (int i = 0; i < sp.size(); i++) {
						if (Validate.checkCourse(course.getInitials(), sp.get(i))) {
							currentCourses.add(getParsedCourse(course.getInitials(), course.getSection(), course.getName()));
							courses.get(i).add(course);
							schedule.add(course);
							break;
						}
					}
					listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
				}	
			}
			showInfoOfSemesterOrEditor();
			firstLoad = false;
		}
		else {
			showSchedule();
		}
		chBxCarreer.getSelectionModel().selectFirst();
	}

	public void showSchedule() {		
		schedule.clear();
		for (ArrayList<Course> a1 : courses) {
			for (Course a2 : a1) {
				schedule.add(a2);
			}
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
			if(!carreer.equals("")) {
				for (Course course : currentSemester.getCourses()) {
					for (Course c : courses.get(count)) {
						if (course.getInitials().equals(c.getInitials())) {
							String courseName = course.getInitials() + "-" + course.getSection() + ": " + course.getName();
							names = names == "" ? courseName : names + courseName;
							names += "\n";
						}			
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
		labelCurrentCoursesNames.setVisible(false);
		labelCurrentSemester.setVisible(false);
		btnEditSemester.setVisible(false);
		btnForums.setVisible(false);
		btnCurricularAdvance.setVisible(false);
		btnAcademicHistory.setVisible(false);
		btnShowSchedule.setVisible(false);
		labelGoTo.setVisible(false);
	}

	private void hideSemesterCreation() {
		hideCourseSearcher();
		hideCourseSelector();
		labelModificationResult.setVisible(false);
		btnCreateNewSemester.setVisible(false);
		listSelectedCourses.setVisible(false);
		labelCoursesList.setVisible(false);
		btnAddCourse.setVisible(false);
		btnRemoveCourse.setVisible(false);
		listDetails.setVisible(false);
		labelCoursesInfo.setVisible(false);
		labelDetails.setVisible(false);
	}

	private void showSemesterInfo() {
		labelCurrentCoursesNames.setVisible(true);
		labelCurrentSemester.setVisible(true);
		btnEditSemester.setVisible(true);
		btnForums.setVisible(true);
		btnCurricularAdvance.setVisible(true);
		btnAcademicHistory.setVisible(true);
		btnShowSchedule.setVisible(true);
		labelGoTo.setVisible(true);
	}

	private void showSemesterCreation() {
		showCourseSearcher();
		showCourseSelector();
		labelModificationResult.setVisible(true);
		btnCreateNewSemester.setVisible(true);
		listSelectedCourses.setVisible(true);
		labelCoursesList.setVisible(true);
		btnAddCourse.setVisible(true);
		btnRemoveCourse.setVisible(true);
		listDetails.setVisible(true);
		labelCoursesInfo.setVisible(true);
		labelDetails.setVisible(true);
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
				int count = 0;
				for (String prog : sp) {
					if (prog == carreer) {
						break;
					}
					count++;
				}
				int all = 0;
				int tot = 0;
				for(ArrayList<Course> a1 : courses) {
					for(Course a2 : a1) {
						all += a2.getCredits();
					}
				}		
				for(String c : sp) {
					for(StudyProgram prog : Manager.INSTANCE.studyPrograms) {
						if(c.equals(prog.getName())) {
							if(prog.getMaxCreditsPerSemester() > tot) {
								tot = prog.getMaxCreditsPerSemester();
							}
						}
					}
				}				
				if(Validate.exceedCredits(course, courses.get(count), carreer, all, tot)) {
					labelModificationResult.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_FAILED) + " excess credits");
					return;
				}
				AddOrRemoveCourseResponse response = user.getCurriculum().getCurrentSemester().addCourse(course);
				if (response.success) {
					ObservableList<String> currentCourses = listSelectedCourses.getItems();
					currentCourses.add(getParsedCourse(initials, section, name));
					listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
					responseToAddOrRemoveCourse = "";
					labelModificationResult
							.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_SUCCESS) + "\n"
									+ responseToAddOrRemoveCourse);					
					courses.get(count).add(course);
					schedule.add(course);
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
							schedule.remove(c);
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

	public void btnShowSchedule_Pressed() {
		ViewUtilities.openView(SShowScheduleController.VIEW, VIEW);
	}

	public void btnCurricularAdvance_Pressed() {
		ViewUtilities.openView(SCurricularAdvanceController.VIEW, VIEW);
	}
	
	public void btnAcademicHistory_Pressed() {
		ViewUtilities.openView(SAcademicHistoryViewController.VIEW, VIEW);
	}
	
	public void btnForums_Pressed() {
		ViewUtilities.openView(SForumsViewController.VIEW, VIEW);
	}
	
	@Override
	public void setLabels() {
		labelMainMessage.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_EDITOR_MAIN));
		labelDetails.setText(Messages.getUILabel(UILabel.COURSE_DETAILS));
		labelLecture.setText(Messages.getUILabel(UILabel.LECTURE));
		labelAssistantship.setText(Messages.getUILabel(UILabel.ASSISTANTSHIP));
		labelLaboratory.setText(Messages.getUILabel(UILabel.LABORATORY));
		labelSchedule.setText(Messages.getUILabel(UILabel.SCHEDULE));
		ViewUtilities.setButtonText(btnCreateNewSemester, UILabel.CREATE_SEMESTER);
		ViewUtilities.setButtonText(btnAddCourse, UILabel.ADD);	
		ViewUtilities.setButtonText(btnRemoveCourse, UILabel.REMOVE);
		ViewUtilities.setButtonText(btnEditSemester, UILabel.EDIT_SEMESTER);
		ViewUtilities.setButtonText(btnCurricularAdvance, UILabel.STUDENT_CURRICULAR_ADVANCE);
		ViewUtilities.setButtonText(btnShowSchedule, UILabel.STUDENT_SEE_SCHEDULE);
		ViewUtilities.setButtonText(btnAcademicHistory, UILabel.STUDENT_ACADEMIC_HISTORY);
		ViewUtilities.setButtonText(btnForums, UILabel.STUDENT_SHOW_FORUM);
	} 
}
