package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Lecture;
import backend.courses.Schedule.DayModuleTuple;
import backend.enums.AcademicSemester;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Professor;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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
	Label labelSearchCourses;
	@FXML
	Label labelSemester;
	@FXML
	Label labelSchedule;
	@FXML
	Label labelCourseInfo;
	@FXML
	Label labelStatusBar;
	@FXML
	ListView<String> listDetails;
	@FXML
	ListView<String> listCoursesInSemester;
	@FXML
	ChoiceBox<String> chSelectSemester;
	@FXML
	GridPane gridSchedule;

	public static URL view = Object.class.getResource("/frontend/student/SCourseSearcherView.fxml");
	public ArrayList<Course> courses = new ArrayList<Course>();

	@Override
	public void setUp() {
		super.setUp();

		btnAddCourse.setText(Messages.getUILabel(UILabel.ADD_COURSE));
		btnRemoveCourse.setText(Messages.getUILabel(UILabel.REMOVE_COURSE));
		btnSaveSemester.setText(Messages.getUILabel(UILabel.SAVE_SEMESTER));

		chSelectSemester.setCursor(Cursor.HAND);
		chSelectSemester.setItems(FXCollections.observableArrayList("1","2"));
		chSelectSemester.getSelectionModel().selectFirst();
		updateCoursesShow(AcademicSemester.getAcademicSemester(chSelectSemester.getSelectionModel().getSelectedItem()));
	
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				gridSchedule.add(new Text(""), i, j);
			}
		}
		
		chBxSelectedCourse.setOnAction((event) -> {
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			
			ArrayList<String> details = new ArrayList<String>();			
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {										

					Lecture lecture = course.getLecture();					
					details.add(course.getInitials());
					details.add(Messages.getUILabel(UILabel.PROFESSOR) + ":");
					for(Professor p : lecture.getProfessors()) {
						details.add(p.getName() + " " + p.getLastnameFather());
					}
					details.add("Sala: " + lecture.getClassroom().getInitials());
					details.add("Cupos: " + lecture.getClassroom().getSize());
					String schedule = "Horario: ";
					for(DayModuleTuple t : lecture.getSchedule().getModules()) {
						schedule += (t.day.getDayString() + t.module.getInt() + " ");
					}
					details.add(schedule);
					break;
				}
			}		
			listDetails.setItems(FXCollections.observableArrayList(details));
		});
		
		gridSchedule.add(new Text("08:30"), 1, 0);
		gridSchedule.add(new Text("10:00"), 2, 0);
		gridSchedule.add(new Text("11:30"), 3, 0);
		gridSchedule.add(new Text("14:00"), 5, 0);
		gridSchedule.add(new Text("15:30"), 6, 0);
		gridSchedule.add(new Text("17:00"), 7, 0);
		gridSchedule.add(new Text("18:30"), 8, 0);
		gridSchedule.add(new Text("20:00"), 9, 0);
		gridSchedule.add(new Text("L"), 0, 1);
		gridSchedule.add(new Text("M"), 0, 2);
		gridSchedule.add(new Text("W"), 0, 3);
		gridSchedule.add(new Text("J"), 0, 4);
		gridSchedule.add(new Text("V"), 0, 5);
		gridSchedule.add(new Text("S"), 0, 6);
		
		Node node = this.getGridNode(1, 0);
		System.out.println((String)node.getUserData());
	}
	
	public void updateCoursesShow(AcademicSemester semester) {
		ArrayList<String> coursesStrings = new ArrayList<String>();
		for (Course course : coursesToShow) {
			if (course.getSemester() == semester || course.getSemester() == AcademicSemester.BOTH
					|| semester == AcademicSemester.BOTH) {
				coursesStrings.add(getParsedCourse(course.getInitials(), course.getSection(), course.getName()));
			}
		}
		chBxSelectedCourse.setItems(FXCollections.observableArrayList(coursesStrings));
		ViewUtilities.autoComplete(chBxSelectedCourse);
	}

	public void btnAddCourse_Pressed() {
		if (!chBxSelectedCourse.getSelectionModel().isEmpty()
				& chBxSelectedCourse.getItems().contains(chBxSelectedCourse.getSelectionModel().getSelectedItem())) {
			
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {					
					
					ArrayList<DayModuleTuple> t = course.getLecture().getSchedule().getModules();
					ArrayList<DayModuleTuple> s;					
					
					for (Course c : courses) {						
						s = c.getLecture().getSchedule().getModules();
						for (DayModuleTuple sm : s) {
							for (DayModuleTuple tm : t) {
								if (sm.day.equals(tm.day) && sm.module.equals(tm.module)) {
									labelStatusBar.setText("Se ha detectado tope de horario con " + c.getName());
									return;
								}
							}
						}
					}			
					
					ObservableList<String> currentCourses = listCoursesInSemester.getItems();
					String parsedCourse = getParsedCourse(initials, section, name);
					
					if(!currentCourses.contains(parsedCourse)) {					
						currentCourses.add(parsedCourse);
						listCoursesInSemester.setItems(FXCollections.observableArrayList(currentCourses));					
						for (DayModuleTuple tm : t) {
							int day = tm.day.getInt();
							int mod = tm.module.getInt();
							if (day > 3) {
								day++;
							}
							gridSchedule.add(new Text(course.getInitials()), mod, day);
						}
						courses.add(course);
					}
					break;
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
			
			String rawCourseInfo = listCoursesInSemester.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section
						&& course.getName().equals(name)) {					
					
					ObservableList<String> currentCourses = listCoursesInSemester.getItems();
					currentCourses.remove(getParsedCourse(initials, section, name));
					listCoursesInSemester.setItems(FXCollections.observableArrayList(currentCourses));
					
					ArrayList<DayModuleTuple> t = course.getLecture().getSchedule().getModules();
					for (DayModuleTuple tm : t) {
						int day = tm.day.getInt();
						int mod = tm.module.getInt();
						if (day > 3) {
							day++;
						}
						gridSchedule.add(new Text(""), mod, day);
					}
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

	public void btnSaveSemester_Pressed() {
		
	}

	public void btnCleanCourses_Pressed() {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 7; j++) {
				gridSchedule.add(new Text(""), i, j);
			}
		}
		listCoursesInSemester.setItems(FXCollections.observableArrayList(""));
		courses.clear();
	}
	
	@FXML
	 private void handleChangeSemesterAction(ActionEvent event) {
		updateCoursesShow(AcademicSemester.getAcademicSemester(chSelectSemester.getSelectionModel().getSelectedItem()));
	 }
	
	@SuppressWarnings("static-access")
	private Node getGridNode(final int row, final int column) {
		
		Node result = null;
		for (Node node : gridSchedule.getChildren()) {
			if (gridSchedule.getRowIndex(node) == row && gridSchedule.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}
		return result;
	}
}