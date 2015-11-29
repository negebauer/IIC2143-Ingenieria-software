package frontend.others;

import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Schedule.DayModuleTuple;
import backend.interfaces.ICourse;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ViewSchedule {

	private Label[][] schedule = new Label[10][7];
	private GridPane gridSchedule;

	public ViewSchedule(GridPane gridSchedule) {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				schedule[i][j] = new Label("");
			}
		}		
		schedule[1][0].setText("08:30");
		schedule[2][0].setText("10:00");
		schedule[3][0].setText("11:30");
		schedule[5][0].setText("14:00");
		schedule[6][0].setText("15:30");
		schedule[7][0].setText("17:00");
		schedule[8][0].setText("18:30");
		schedule[9][0].setText("20:00");
		schedule[0][1].setText("L");
		schedule[0][2].setText("M");
		schedule[0][3].setText("W");
		schedule[0][4].setText("J");
		schedule[0][5].setText("V");
		schedule[0][6].setText("S");

		for (int i = 0; i < 10; i++) {
			schedule[i][0].setStyle("-fx-background-color: #ff8;");
			schedule[i][0].setText(reSize(schedule[i][0].getText(), 9));
		}
		for (int j = 0; j < 7; j++) {
			schedule[0][j].setStyle("-fx-background-color: #ff8;");
			schedule[0][j].setText(reSize(schedule[0][j].getText(), 17));
		}	
		this.gridSchedule = gridSchedule;
		refresh();
	}

	public void add(Course course) {
		if (course.getLecture() != null) {
			add(course, course.getLecture().getSchedule().getModules(), "#1ab");
		} 
		if (course.getAssistantship() != null) {
			add(course, course.getAssistantship().getSchedule().getModules(), "#f70");
		} 
		if (course.getLaboratory() != null) {
			add(course, course.getLaboratory().getSchedule().getModules(), "#060");
		}
		refresh();
	}

	private void add(Course course, ArrayList<DayModuleTuple> tuple, String color) {
		for (DayModuleTuple tm : tuple) {
			int day = tm.day.getInt();
			int mod = tm.module.getInt();
			if (mod > 3) {
				mod++;
				
			}
			schedule[mod][day].setStyle("-fx-text-fill: " + color + ";");
			schedule[mod][day].setText(reSize(course.getInitials(), 13));
		}
	}

	public void remove(Course course) {
		for (ICourse i : course.getCourses()) {			
			ArrayList<DayModuleTuple> t = i.getSchedule().getModules();
			for (DayModuleTuple tm : t) {
				int day = tm.day.getInt();
				int mod = tm.module.getInt();
				if (mod > 3) {
					mod++;
				}				
				schedule[mod][day].setText("");
			}					
		}
		refresh();
	}

	public void clear() {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 7; j++) {
				schedule[i][j].setStyle("-fx-background-color: white;");
				schedule[i][j].setText("");
			}
		}
		refresh();
	}

	private String reSize(String str, int size) {		
		int len = size - str.length();
		if(len > 0) {
			for (int i = 0; i < len / 2; i++) {
				str = " " + str + " ";
			}
		}
		return str;
	}

	@SuppressWarnings("static-access")
	private void refresh() {
		Node node = gridSchedule.getChildren().get(0);
		gridSchedule.getChildren().clear();
		gridSchedule.getChildren().add(0,node);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {				
				if(!schedule[i][j].getText().trim().equals(""))
				{	
					gridSchedule.setHalignment(schedule[i][j], HPos.CENTER);
					gridSchedule.add(schedule[i][j], j, i);
				}
			}
		}
	}
}