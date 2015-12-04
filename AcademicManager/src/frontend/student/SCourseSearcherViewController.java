package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.Validate;
import frontend.others.ViewSchedule;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class SCourseSearcherViewController extends MCourseSearcherSelectorViewController {

	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnSaveSemester;
	@FXML
	Button btnCleanCourses;
	@FXML
	Button btnDetails;
	@FXML
	Label labelSearchCourses;
	@FXML
	Label labelSemester;
	@FXML
	Label labelSchedule;
	@FXML
	Label labelDetails;
	@FXML
	Label labelStatusBar;
	@FXML
	Label labelLecture;
	@FXML
	Label labelAssistantship;
	@FXML
	Label labelLaboratory;
	@FXML
	Label labelCoursesList;
	@FXML
	Label labelCoursesInfo;
	@FXML
	ListView<String> listDetails;
	@FXML
	ListView<String> listCoursesInSemester;
	@FXML
	ComboBox<String> chBxSemester;
	@FXML
	GridPane gridSchedule;

	public final static URL VIEW = Object.class.getResource("/frontend/student/SCourseSearcherView.fxml");
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ViewSchedule schedule;

	@Override
	public void setUp() {
		super.setUp();
		
		schedule = new ViewSchedule(gridSchedule);	
		chBxSemester.setCursor(Cursor.HAND);
		chBxSemester.setItems(FXCollections.observableArrayList("1","2"));
		chBxSemester.getSelectionModel().selectFirst();
		
		chBxSelectedCourse.setOnAction((event) -> {		
			String[] selection = getParsedInitialsSectionName(chBxSelectedCourse.getSelectionModel().getSelectedItem());
			listDetails.setItems(FXCollections.observableArrayList(ViewUtilities.getDetails(selection)));
			labelStatusBar.setText("");
		});	
		chBxSemester.setOnAction((event) -> { updateCoursesShow(); });	
		updateCoursesShow();
	}
	
	@Override
	public void updateCoursesShow() {
		
		AcademicSemester semester = AcademicSemester.getAcademicSemester(chBxSemester.getSelectionModel().getSelectedItem());	
		ArrayList<String> strCourses = new ArrayList<String>();
		for (Course course : coursesToShow) {
			if (course.getSemester() == semester || course.getSemester() == AcademicSemester.BOTH
					|| semester == AcademicSemester.BOTH) {
				strCourses.add(getParsedCourse(course.getInitials(), course.getSection(), course.getName()));
			}
		}
		chBxSelectedCourse.setItems(FXCollections.observableArrayList(strCourses));
		ViewUtilities.autoComplete(chBxSelectedCourse);
	}

	public void btnAddCourse_Pressed() {
		if (!chBxSelectedCourse.getSelectionModel().isEmpty()
				& chBxSelectedCourse.getItems().contains(chBxSelectedCourse.getSelectionModel().getSelectedItem())) {			
			String[] selection = getParsedInitialsSectionName(chBxSelectedCourse.getSelectionModel().getSelectedItem());
			
			for (Course course : Manager.INSTANCE.courses) {
				if (Validate.checkCourse(course, selection)) {									
					if(Validate.checkOverlap(course, courses)) {
						labelStatusBar.setText("Se ha detectado tope de horario con " + Validate.getOverlap(course, courses).getName());
						return;
					}
					ObservableList<String> currentCourses = listCoursesInSemester.getItems();	
					String parsedCourse = getParsedCourse(course.getInitials(), course.getSection(), course.getName());													
					if(!currentCourses.contains(parsedCourse)) {
						currentCourses.add(parsedCourse);
						listCoursesInSemester.setItems(FXCollections.observableArrayList(currentCourses));						
						schedule.add(course);
						courses.add(course);
						break;
					}
				}
			}
		} else {
			// TODO Uncomment when function is created
			// ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION)
			// + "(" + Messages.getUILabel(UILabel.ADD_COURSE) + ")");
		}
	}

	public void btnRemoveCourse_Pressed() {
		if (!listCoursesInSemester.getSelectionModel().isEmpty()) {
			String[] selection = getParsedInitialsSectionName(listCoursesInSemester.getSelectionModel().getSelectedItem());
					
			for (Course course : Manager.INSTANCE.courses) {
				if (Validate.checkCourse(course, selection)) {										
					ObservableList<String> currentCourses = listCoursesInSemester.getItems();
					String parsedCourse = getParsedCourse(course.getInitials(), course.getSection(), course.getName());			
					currentCourses.remove(parsedCourse);
					listCoursesInSemester.setItems(FXCollections.observableArrayList(currentCourses));			
					schedule.remove(course);
					courses.remove(course);				
					break;
				}
			}
		} else {
			// TODO Uncomment when function is created
			// ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION)
			// + "(" + Messages.getUILabel(UILabel.REMOVE_COURSE) + ")");
		}
	}

	public void btnCleanCourses_Pressed() {
		listCoursesInSemester.setItems(FXCollections.observableArrayList(new ArrayList<String>()));
		schedule.clear();
		courses.clear();
	}
	
	@Override
	public void setLabels() {	
		//		// TODO: [STUDENT] Create UILabel	
		ViewUtilities.setButtonText(btnAddCourse, UILabel.ADD);	
		ViewUtilities.setButtonText(btnRemoveCourse, UILabel.REMOVE);	
		labelTitle.setText(Messages.getUILabel(UILabel.STUDENT_SEARCH_COURSE).toUpperCase());
		labelSchedule.setText(Messages.getUILabel(UILabel.SCHEDULE));
		labelSemester.setText(Messages.getUILabel(UILabel.SEMESTER));
		labelDetails.setText(Messages.getUILabel(UILabel.COURSE_DETAILS));
		labelLecture.setText(Messages.getUILabel(UILabel.LECTURE));
		labelAssistantship.setText(Messages.getUILabel(UILabel.ASSISTANTSHIP));
		labelLaboratory.setText(Messages.getUILabel(UILabel.LABORATORY));
	}
}