package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.Laboratory;
import backend.courses.Lecture;
import backend.courses.Semester;
import backend.courses.Schedule.DayModuleTuple;
import backend.courses.Semester.AddOrRemoveCourseResponse;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Assistant;
import backend.users.Professor;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ASemesterManagerEditingViewController extends MCourseSearcherSelectorViewController {

	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnSaveSemester;
	@FXML
	Label labelSearchCourses;
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
	ComboBox<String> chSelectSemester;
	@FXML
	GridPane gridSchedule;

	public static URL view = Object.class.getResource("/frontend/admin/ASemesterManagerEditingView.fxml");
	public boolean isCreating = false;
	boolean firstSetUp = true;
	public Text[][] schedule = new Text[10][7];

	@Override
	public void setUp() {
		super.setUp();
		btnAddCourse.setText(Messages.getUILabel(UILabel.ADD_COURSE));
		btnRemoveCourse.setText(Messages.getUILabel(UILabel.REMOVE_COURSE));
		btnSaveSemester.setText(Messages.getUILabel(UILabel.SAVE_SEMESTER));
		labelSearchCourses.setText(Messages.getUILabel(UILabel.EDIT_SEMESTER));
		labelSchedule.setText(Messages.getUILabel(UILabel.SCHEDULE_VIEW_TITLE));
		labelCourseInfo.setText(Messages.getUILabel(UILabel.COURSE_DETAILS));
		labelStatusBar.setText("");
		
		if (firstSetUp) {	
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 7; j++) {
					schedule[i][j] = new Text("");
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
						details.add(course.getInitials());
						if (course.getLecture() != null) {
							details.add(Messages.getUILabel(UILabel.LECTURE));
							Lecture lecture = course.getLecture();					
												
							String teachers = Messages.getUILabel(UILabel.PROFESSOR) + ": ";
							if (lecture.getProfessors().size() > 1) {
								details.add(teachers);
								for(Professor p : lecture.getProfessors()) {
									details.add(p.getName() + " " + p.getLastnameFather());
								}
							}
							else
							{
								Professor p = lecture.getProfessors().get(0);
								teachers += p.getName() + " " + p.getLastnameFather();
								details.add(teachers);
							}	
							String schedule = "Horario: ";
							for(DayModuleTuple t : lecture.getSchedule().getModules()) {
								schedule += (t.day.getDayString() + " " + t.module.getInt() + " ");
							}
							details.add(schedule);				
							details.add("Sala: " + lecture.getClassroom().getInitials());
							details.add("Cupos: " + lecture.getClassroom().getSize());
							
						} if (course.getAssistantship() != null) {
							details.add(" ");
							details.add(Messages.getUILabel(UILabel.ASSISTANTSHIP));
							
							Assistantship assistantship = course.getAssistantship();
							String assistants = Messages.getUILabel(UILabel.ASSISTANT) + ": ";
							if (assistantship.getAssistants().size() > 1) {
								details.add(assistants);
								for(Assistant p : assistantship.getAssistants()) {
									details.add(p.getName() + " " + p.getLastnameFather());
								}
							}
							else
							{
								Assistant p = assistantship.getAssistants().get(0);
								assistants += p.getName() + " " + p.getLastnameFather();
								details.add(assistants);
							}	
							String schedule = "Horario: ";
							for(DayModuleTuple t : assistantship.getSchedule().getModules()) {
								schedule += (t.day.getDayString() + " " + t.module.getInt() + " ");
							}
							details.add(schedule);				
							details.add("Sala: " + assistantship.getClassroom().getInitials());
							details.add("Cupos: " + assistantship.getClassroom().getSize());
							
						} if (course.getLaboratory() != null) {
							details.add(" ");
							details.add(Messages.getUILabel(UILabel.LABORATORY));
							
							Laboratory laboratory = course.getLaboratory();
							String teachers = Messages.getUILabel(UILabel.PROFESSOR) + ": ";
							if (laboratory.getProfessors().size() > 1) {
								details.add(teachers);
								for(Professor p : laboratory.getProfessors()) {
									details.add(p.getName() + " " + p.getLastnameFather());
								}
							}
							else
							{
								Professor p = laboratory.getProfessors().get(0);
								teachers += p.getName() + " " + p.getLastnameFather();
								details.add(teachers);
							}	
							String schedule = "Horario: ";
							for(DayModuleTuple t : laboratory.getSchedule().getModules()) {
								schedule += (t.day.getDayString() + " " + t.module.getInt() + " ");
							}
							details.add(schedule);				
							details.add("Sala: " + laboratory.getClassroom().getInitials());
							details.add("Cupos: " + laboratory.getClassroom().getSize());
							
						}
							
						break;
					}
				}		
				listDetails.setItems(FXCollections.observableArrayList(details));
			});
						
			schedule[1][0] = new Text("08:30");
			schedule[2][0] = new Text("10:00");
			schedule[3][0] = new Text("11:30");
			schedule[5][0] = new Text("14:00");
			schedule[6][0] = new Text("15:30");
			schedule[7][0] = new Text("17:00");
			schedule[8][0] = new Text("18:30");
			schedule[9][0] = new Text("20:00");
			schedule[0][1] = new Text("L");
			schedule[0][2] = new Text("M");
			schedule[0][3] = new Text("W");
			schedule[0][4] = new Text("J");
			schedule[0][5] = new Text("V");
			schedule[0][6] = new Text("S");
	
			if (Manager.INSTANCE.currentSemester != null) {
				isCreating = false;			
			} else {
				isCreating = true;
				ArrayList<Coursed> passedCourses = new ArrayList<Coursed>();
				for (Semester semester : Manager.INSTANCE.currentEditingStudyProgram.getSemesters()) {
					for (Course course : semester.getCourses()) {
						passedCourses.add(new Coursed(course, true, 7, AcademicSemester.defaultSemester(), 0));
					}
				}
				AcademicSemester acdemicSemester = AcademicSemester.FIRST;
				if (Manager.INSTANCE.currentEditingStudyProgram.getSemesters().size() % 2 != 0) {
					acdemicSemester = AcademicSemester.SECOND;
				}
				Manager.INSTANCE.currentSemester = new Semester(acdemicSemester, 0,
						Manager.INSTANCE.currentEditingStudyProgram.getMaxCreditsPerSemester(), passedCourses,
						new ArrayList<Course>());
			}
			
			ArrayList<String> semesterCourses = new ArrayList<String>();
			for (Course course : Manager.INSTANCE.currentSemester.getCourses()) {
				String parsedCourse = getParsedCourse(course.getInitials(), course.getSection(), course.getName());
				semesterCourses.add(parsedCourse);
				
				if (course.getLecture() != null) {
					ArrayList<DayModuleTuple> lectureSchedule = course.getLecture().getSchedule().getModules();
					for (DayModuleTuple tm : lectureSchedule) {
						int day = tm.day.getInt();
						int mod = tm.module.getInt();
						if (mod > 3) {
							mod++;
						}
						Text textToPut = new Text(course.getInitials());
						textToPut.setFill(Color.YELLOW);
						schedule[mod][day] = textToPut;
					}
				} if (course.getAssistantship() != null) {
					ArrayList<DayModuleTuple> assistantshipSchedule = course.getAssistantship().getSchedule().getModules();
					for (DayModuleTuple tm : assistantshipSchedule) {
						int day = tm.day.getInt();
						int mod = tm.module.getInt();
						if (mod > 3) {
							mod++;
						}
						Text textToPut = new Text(course.getInitials());
						textToPut.setFill(Color.GREENYELLOW);
						schedule[mod][day] = textToPut;
					}
				} if (course.getLaboratory() != null) {
					ArrayList<DayModuleTuple> laboratorySchedule = course.getLaboratory().getSchedule().getModules();
					for (DayModuleTuple tm : laboratorySchedule) {
						int day = tm.day.getInt();
						int mod = tm.module.getInt();
						if (mod > 3) {
							mod++;
						}
						Text textToPut = new Text(course.getInitials());
						textToPut.setFill(Color.AQUA);
						schedule[mod][day] = textToPut;
					}
				}
			}
			refresh();
			listCoursesInSemester.setItems(FXCollections.observableArrayList(semesterCourses));
			
			updateCoursesShow(Manager.INSTANCE.currentSemester.getSemester());
			
			refresh();
			firstSetUp = false;
		}
	}
	
	@Override
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
		if (!chBxSelectedCourse.getSelectionModel().isEmpty() & chBxSelectedCourse.getItems().contains(chBxSelectedCourse.getSelectionModel().getSelectedItem())) {
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section
						&& course.getName().equals(name)) {
					AddOrRemoveCourseResponse response = Manager.INSTANCE.currentSemester.addCourse(course);
					if (response.success) {
						ObservableList<String> currentCourses = listCoursesInSemester.getItems();
						currentCourses.add(getParsedCourse(initials, section, name));
						listCoursesInSemester.setItems(currentCourses);
						
						if (course.getLecture() != null) {
							ArrayList<DayModuleTuple> lectureSchedule = course.getLecture().getSchedule().getModules();
							for (DayModuleTuple tm : lectureSchedule) {
								int day = tm.day.getInt();
								int mod = tm.module.getInt();
								if (mod > 3) {
									mod++;
								}
								Text textToPut = new Text(course.getInitials());
								textToPut.setFill(Color.YELLOW);
								
								schedule[mod][day] = textToPut;
							}
						} if (course.getAssistantship() != null) {
							ArrayList<DayModuleTuple> assistantshipSchedule = course.getAssistantship().getSchedule().getModules();
							for (DayModuleTuple tm : assistantshipSchedule) {
								int day = tm.day.getInt();
								int mod = tm.module.getInt();
								if (mod > 3) {
									mod++;
								}
								Text textToPut = new Text(course.getInitials());
								textToPut.setFill(Color.GREENYELLOW);
								schedule[mod][day] = textToPut;
							}
						} if (course.getLaboratory() != null) {
							ArrayList<DayModuleTuple> laboratorySchedule = course.getLaboratory().getSchedule().getModules();
							for (DayModuleTuple tm : laboratorySchedule) {
								int day = tm.day.getInt();
								int mod = tm.module.getInt();
								if (mod > 3) {
									mod++;
								}
								Text textToPut = new Text(course.getInitials());
								textToPut.setFill(Color.AQUA);
								schedule[mod][day] = textToPut;
							}
						}
						refresh();
						labelStatusBar.setText("Success");
					} else {
						labelStatusBar.setText("Not added: " + response.response);
					}
					break;
				}
			}
		} else {
			ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION) + "(" + Messages.getUILabel(UILabel.ADD_COURSE) + ")");
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
					AddOrRemoveCourseResponse response = Manager.INSTANCE.currentSemester.removeCourse(course);
					if (response.success) {
						ObservableList<String> currentCourses = listCoursesInSemester.getItems();
						currentCourses.remove(getParsedCourse(initials, section, name));
						listCoursesInSemester.setItems(currentCourses);
						
						if (course.getLecture() != null) {
							ArrayList<DayModuleTuple> lectureSchedule = course.getLecture().getSchedule().getModules();
							for (DayModuleTuple tm : lectureSchedule) {
								int day = tm.day.getInt();
								int mod = tm.module.getInt();
								if (mod > 3) {
									mod++;
								}
								Text textToPut = new Text("");
								schedule[mod][day] = textToPut;
							}
						} if (course.getAssistantship() != null) {
							ArrayList<DayModuleTuple> assistantshipSchedule = course.getAssistantship().getSchedule().getModules();
							for (DayModuleTuple tm : assistantshipSchedule) {
								int day = tm.day.getInt();
								int mod = tm.module.getInt();
								if (mod > 3) {
									mod++;
								}
								Text textToPut = new Text("");
								schedule[mod][day] = textToPut;
							}
						} if (course.getLaboratory() != null) {
							ArrayList<DayModuleTuple> laboratorySchedule = course.getLaboratory().getSchedule().getModules();
							for (DayModuleTuple tm : laboratorySchedule) {
								int day = tm.day.getInt();
								int mod = tm.module.getInt();
								if (mod > 3) {
									mod++;
								}
								Text textToPut = new Text("");
								schedule[mod][day] = textToPut;
							}
						}
						refresh();
						labelStatusBar.setText("Success");
					} else {
						labelStatusBar.setText("Not removed: " + response.response);
					}
					break;
				}
			}
		} else {
			ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION) + "(" + Messages.getUILabel(UILabel.REMOVE_COURSE) + ")");
		}

	}

	public void btnSaveSemester_Pressed() {
		Semester currentSemester = Manager.INSTANCE.currentSemester;

		if (isCreating) {
			Manager.INSTANCE.currentEditingStudyProgram.addSemester(currentSemester);
			isCreating = false;
		}

		Manager.INSTANCE.currentSemester = null;
		super.btnBack_Pressed();
	}
	
	public void btnBack_Pressed() {
		btnSaveSemester_Pressed();
	}
	
	private void refresh() {				
		gridSchedule.getChildren().clear();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				gridSchedule.add(schedule[i][j], j, i);
			}
		}
	}
}
