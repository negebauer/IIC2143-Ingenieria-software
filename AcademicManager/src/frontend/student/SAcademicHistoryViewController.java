package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Coursed;
import backend.courses.CoursedSemester;
import backend.courses.StudyProgram;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.users.Student;
import frontend.main.MViewController;
import frontend.others.Validate;
import frontend.others.ViewUtilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SAcademicHistoryViewController extends MViewController {

	@FXML
	TextArea txAAcademicHistory;
	@FXML
	TextArea txAAcademic;
	@FXML
	ComboBox<String> chBxCarreer;
	@FXML
	ComboBox<String> chBxCoursedSemesters;
	@FXML
	Label labelCoursedSemesterCourses;
	@FXML
	Label labelMain;
	
	public static final URL VIEW = Object.class.getResource("/frontend/student/SAcademicHistoryView.fxml");
	private Student user = (Student) Manager.INSTANCE.currentUser;
	private String carreer = "";
	
	@Override
	public void setUp() {
		super.setUp();

		ArrayList<String> sp = new ArrayList<String>();
		for (StudyProgram p : user.getCurriculum().getStudyPrograms()) {
			sp.add(p.getName());
		}
		chBxCarreer.setItems(FXCollections.observableArrayList(sp));
		chBxCoursedSemesters.setItems(generateCoursedSemesters());
		chBxCoursedSemesters.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				showCoursedSemesterGrades(chBxCoursedSemesters.getSelectionModel().getSelectedItem());
			}
		});	
		chBxCarreer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				carreer = chBxCarreer.getSelectionModel().getSelectedItem().trim();
				refresh();
			}
		});
		chBxCarreer.getSelectionModel().selectFirst();
		chBxCoursedSemesters.getSelectionModel().selectLast();
	}
	
	public void refresh() {
		String fullText = "Promedio General: ";
		String academic = "Promedio Carrera: ";
		double totalGrade = 0;
		double totalCarreerGrade = 0;
		int totalCourses = 0;
		
		ArrayList<Coursed> ofgs = ViewUtilities.getOFGs(user, carreer);			
		for (Coursed coursed : user.getCurriculum().getCoursedCourses()) {
			totalGrade += coursed.getGrade();		
			if (Validate.checkCourse(coursed.getInitials(), carreer) || Validate.checkOFG(coursed, ofgs)) {
				totalCarreerGrade += coursed.getGrade();
				totalCourses++;
			}
		}
		totalGrade = totalGrade / user.getCurriculum().getCoursedCourses().size();
		totalGrade = Math.rint(totalGrade*100)/100;
		if (totalCourses > 0) {
			totalCarreerGrade = totalCarreerGrade / totalCourses;
			totalCarreerGrade = Math.rint(totalCarreerGrade*100)/100;
		}
		fullText += totalGrade + "\n\n";
		academic += totalCarreerGrade + "\n\n";
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			fullText += coursedSemester.getYear() + "-" + coursedSemester.getSemester().getSemesterNumber() + ": "
					+ coursedSemester.getGrade() + "\n\t";			
			String aux = "";
			double sum = 0;
			double prom = 0;
			int count = 0;
			for (Coursed coursed : coursedSemester.getCoursedCourses()) {
				fullText += coursed.getInitials() + "-" + coursed.getSection() + " " + coursed.getName() + ": "
						+ coursed.getGrade() + "\n\t";
				if (Validate.checkCourse(coursed.getInitials(), carreer) || Validate.checkOFG(coursed, ofgs)) {
					String ofg = "";
					if(Validate.checkOFG(coursed, ofgs)) {
						ofg = " **OFG";
					}	
					aux += coursed.getInitials() + "-" + coursed.getSection() + " " + coursed.getName() + ": "
							+ coursed.getGrade() + ofg +"\n\t";
					sum += coursed.getGrade();
					count++;
				}
			}			
			if (count > 0) {
				prom = sum/count;
			}
			else {
				aux += "No hay ramos este semestre\n";
			}
			academic += coursedSemester.getYear() + "-" + coursedSemester.getSemester().getSemesterNumber() + ": "
					+ prom + "\n\t" + aux + "\n";
			fullText += "\n";
		}
		
		
		
		txAAcademicHistory.setText(fullText);
		txAAcademic.setText(academic);
	}
	
	
	
	private ObservableList<String> generateCoursedSemesters() {
		ArrayList<String> coursedSemesters = new ArrayList<String>();
		
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			String coursedSemesterString = coursedSemester.getYear() + " - "
					+ coursedSemester.getSemester().getSemesterNumber();
			if (!(coursedSemesters.contains(coursedSemesterString))) {
				coursedSemesters.add(coursedSemesterString);
			}
		}
		coursedSemesters.sort(null);		
		return FXCollections.observableArrayList(coursedSemesters);
	}
	
	private void showCoursedSemesterGrades(String yearSemesterRawString) {
		String coursedCoursesString = "";
		int year = Integer.valueOf(yearSemesterRawString.split(" - ")[0]);
		AcademicSemester semester = AcademicSemester.createWithNumber(yearSemesterRawString.split(" - ")[1]);
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {			
			if (coursedSemester.getYear() == year && coursedSemester.getSemester() == semester) {								
				int count = 0;
				double sum = 0;
				String aux = "";				
				for (Coursed coursed : coursedSemester.getCoursedCourses()) {
					if (Validate.checkCourse(coursed.getInitials(), carreer)) {
						aux += coursed.getInitials() + "-" + coursed.getSection() + " " + coursed.getName()
						+ ": " + coursed.getGrade() + "\n\t";	
						sum+= coursed.getGrade();
						count++;	
					}
				}
				double prom = 0;
				if(count != 0) {
					prom = sum/count;
				}				
				coursedCoursesString += year + "-" + semester.getSemesterNumber() + ": " + prom + "\n\t" + aux;
				if (prom == 0) {
					coursedCoursesString += "No hay ramos cursados este semestres";
				}
			}
		}
		labelCoursedSemesterCourses.setText(coursedCoursesString);
	}
}
