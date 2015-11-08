package frontend.admin;

import java.net.URL;

import backend.courses.Schedule;
import backend.courses.Schedule.Day;
import backend.courses.Schedule.DayModuleTuple;
import backend.courses.Schedule.Module;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class AScheduleViewController extends MViewController {

	//Monday CheckBoxes
	@FXML
	CheckBox chckBxMondayModule5;
	@FXML
	CheckBox chckBxMondayModule6;
	@FXML
	CheckBox chckBxMondayModule8;
	@FXML
	CheckBox chckBxMondayModule7;
	@FXML
	CheckBox chckBxMondayModule3;
	@FXML
	CheckBox chckBxMondayModule4;
	@FXML
	CheckBox chckBxMondayModule2;
	@FXML
	CheckBox chckBxMondayModule1;

	//Tuesday CheckBoxes
	@FXML
	CheckBox chckBxTuesdayModule1;
	@FXML
	CheckBox chckBxTuesdayModule2;
	@FXML
	CheckBox chckBxTuesdayModule4;
	@FXML
	CheckBox chckBxTuesdayModule3;
	@FXML
	CheckBox chckBxTuesdayModule7;
	@FXML
	CheckBox chckBxTuesdayModule8;
	@FXML
	CheckBox chckBxTuesdayModule6;
	@FXML
	CheckBox chckBxTuesdayModule5;

	//Wednesday CheckBoxes
	@FXML
	CheckBox chckBxWednesdayModule1;
	@FXML
	CheckBox chckBxWednesdayModule2;
	@FXML
	CheckBox chckBxWednesdayModule4;
	@FXML
	CheckBox chckBxWednesdayModule3;
	@FXML
	CheckBox chckBxWednesdayModule7;
	@FXML
	CheckBox chckBxWednesdayModule8;
	@FXML
	CheckBox chckBxWednesdayModule6;
	@FXML
	CheckBox chckBxWednesdayModule5;

	//Thursday CheckBoxes
	@FXML
	CheckBox chckBxThursdayModule1;
	@FXML
	CheckBox chckBxThursdayModule2;
	@FXML
	CheckBox chckBxThursdayModule4;
	@FXML
	CheckBox chckBxThursdayModule3;
	@FXML
	CheckBox chckBxThursdayModule7;
	@FXML
	CheckBox chckBxThursdayModule8;
	@FXML
	CheckBox chckBxThursdayModule6;
	@FXML
	CheckBox chckBxThursdayModule5;
	
	//Friday CheckBoxes
	@FXML
	CheckBox chckBxFridayModule1;
	@FXML
	CheckBox chckBxFridayModule2;
	@FXML
	CheckBox chckBxFridayModule4;
	@FXML
	CheckBox chckBxFridayModule3;
	@FXML
	CheckBox chckBxFridayModule7;
	@FXML
	CheckBox chckBxFridayModule8;
	@FXML
	CheckBox chckBxFridayModule6;
	@FXML
	CheckBox chckBxFridayModule5;

	//Saturday CheckBoxes
	@FXML
	CheckBox chckBxSaturdayModule1;
	@FXML
	CheckBox chckBxSaturdayModule2;
	@FXML
	CheckBox chckBxSaturdayModule4;
	@FXML
	CheckBox chckBxSaturdayModule3;
	@FXML
	CheckBox chckBxSaturdayModule7;
	@FXML
	CheckBox chckBxSaturdayModule8;
	@FXML
	CheckBox chckBxSaturdayModule6;
	@FXML
	CheckBox chckBxSaturdayModule5;

	//Sunday CheckBoxes
	@FXML
	CheckBox chckBxSundayModule1;
	@FXML
	CheckBox chckBxSundayModule2;
	@FXML
	CheckBox chckBxSundayModule4;
	@FXML
	CheckBox chckBxSundayModule3;
	@FXML
	CheckBox chckBxSundayModule7;
	@FXML
	CheckBox chckBxSundayModule8;
	@FXML
	CheckBox chckBxSundayModule6;
	@FXML
	CheckBox chckBxSundayModule5;

	//Days Labels
	@FXML
	Label labelMonday;
	@FXML
	Label labelTuesday;
	@FXML
	Label labelWednesday;
	@FXML
	Label labelThursday;
	@FXML
	Label labelFriday;
	@FXML
	Label labelSaturday;
	@FXML
	Label labelSunday;

	//Else
	@FXML
	Button btnSaveSchedule;
	@FXML
	Label labelScheduleTitle;

	static URL view = Object.class.getResource("/frontend/admin/AScheduleView.fxml");

	public void setUp() {
		super.setUp();
		super.hideBack();
		super.hideLanguage();
		super.hideLogout();
		super.hideReload();
		
		labelMonday.setText(Messages.getDay(Day.MONDAY));
		labelTuesday.setText(Messages.getDay(Day.TUESDAY));
		labelWednesday.setText(Messages.getDay(Day.WEDNESDAY));
		labelThursday.setText(Messages.getDay(Day.THURSDAY));
		labelFriday.setText(Messages.getDay(Day.FRIDAY));
		labelSaturday.setText(Messages.getDay(Day.SATURDAY));
		labelSunday.setText(Messages.getDay(Day.SUNDAY));
		btnSaveSchedule.setText(Messages.getUILabel(UILabel.SAVE_SCHEDULE));
		labelScheduleTitle.setText(Messages.getUILabel(UILabel.SCHEDULE_VIEW_TITLE));
		
		Schedule editingSchedule = Manager.INSTANCE.currentEditingSchedule;
		setCheckBoxes(editingSchedule);
		
	}
	
	public void btnSaveSchedule_Pressed() {
		Schedule editingSchedule = new Schedule();


		if (chckBxMondayModule5.isSelected()) {
			editingSchedule.addModule(new DayModuleTuple(Day.MONDAY, Module._5));
		}
		if (chckBxMondayModule6.isSelected()) {

		}
		if (chckBxMondayModule8.isSelected()) {

		}
		if (chckBxMondayModule7.isSelected()) {

		}
		if (chckBxMondayModule3.isSelected()) {

		}
		if (chckBxMondayModule4.isSelected()) {

		}
		if (chckBxMondayModule2.isSelected()) {

		}
		if (chckBxMondayModule1.isSelected()) {

		}
		if (chckBxTuesdayModule1.isSelected()) {

		}
		if (chckBxTuesdayModule2.isSelected()) {

		}
		if (chckBxTuesdayModule4.isSelected()) {

		}
		if (chckBxTuesdayModule3.isSelected()) {

		}
		if (chckBxTuesdayModule7.isSelected()) {

		}
		if (chckBxTuesdayModule8.isSelected()) {

		}
		if (chckBxTuesdayModule6.isSelected()) {

		}
		if (chckBxTuesdayModule5.isSelected()) {

		}
		if (chckBxWednesdayModule1.isSelected()) {

		}
		if (chckBxWednesdayModule2.isSelected()) {

		}
		if (chckBxWednesdayModule4.isSelected()) {

		}
		if (chckBxWednesdayModule3.isSelected()) {

		}
		if (chckBxWednesdayModule7.isSelected()) {

		}
		if (chckBxWednesdayModule8.isSelected()) {

		}
		if (chckBxWednesdayModule6.isSelected()) {

		}
		if (chckBxWednesdayModule5.isSelected()) {

		}
		if (chckBxThursdayModule1.isSelected()) {

		}
		if (chckBxThursdayModule2.isSelected()) {

		}
		if (chckBxThursdayModule4.isSelected()) {

		}
		if (chckBxThursdayModule3.isSelected()) {

		}
		if (chckBxThursdayModule7.isSelected()) {

		}
		if (chckBxThursdayModule8.isSelected()) {

		}
		if (chckBxThursdayModule6.isSelected()) {

		}
		if (chckBxThursdayModule5.isSelected()) {

		}
		if (chckBxFridayModule1.isSelected()) {

		}
		if (chckBxFridayModule2.isSelected()) {

		}
		if (chckBxFridayModule4.isSelected()) {

		}
		if (chckBxFridayModule3.isSelected()) {

		}
		if (chckBxFridayModule7.isSelected()) {

		}
		if (chckBxFridayModule8.isSelected()) {

		}
		if (chckBxFridayModule6.isSelected()) {

		}
		if (chckBxFridayModule5.isSelected()) {

		}
		if (chckBxSaturdayModule1.isSelected()) {

		}
		if (chckBxSaturdayModule2.isSelected()) {

		}
		if (chckBxSaturdayModule4.isSelected()) {

		}
		if (chckBxSaturdayModule3.isSelected()) {

		}
		if (chckBxSaturdayModule7.isSelected()) {

		}
		if (chckBxSaturdayModule8.isSelected()) {

		}
		if (chckBxSaturdayModule6.isSelected()) {

		}
		if (chckBxSaturdayModule5.isSelected()) {

		}
		if (chckBxSundayModule1.isSelected()) {

		}
		if (chckBxSundayModule2.isSelected()) {

		}
		if (chckBxSundayModule4.isSelected()) {

		}
		if (chckBxSundayModule3.isSelected()) {

		}
		if (chckBxSundayModule7.isSelected()) {

		}
		if (chckBxSundayModule8.isSelected()) {

		}
		if (chckBxSundayModule6.isSelected()) {

		}
		if (chckBxSundayModule5.isSelected()) {

		}
	}

	public void setCheckBoxes(Schedule editingSchedule) {
		for (DayModuleTuple tuple : editingSchedule.getModules()) {
			if (tuple.day.equals(Day.MONDAY)) {
				if (tuple.module.equals(Module._1)) {
					chckBxMondayModule1.setSelected(true);
				} else if (tuple.module.equals(Module._2)) {
					chckBxMondayModule2.setSelected(true);
				} else if (tuple.module.equals(Module._3)) {
					chckBxMondayModule3.setSelected(true);
				} else if (tuple.module.equals(Module._4)) {
					chckBxMondayModule4.setSelected(true);
				} else if (tuple.module.equals(Module._5)) {
					chckBxMondayModule5.setSelected(true);
				} else if (tuple.module.equals(Module._6)) {
					chckBxMondayModule6.setSelected(true);
				} else if (tuple.module.equals(Module._7)) {
					chckBxMondayModule7.setSelected(true);
				} else if (tuple.module.equals(Module._8)) {
					chckBxMondayModule8.setSelected(true);
				}
			} else if (tuple.day.equals(Day.TUESDAY)) {
				if (tuple.module.equals(Module._1)) {
					chckBxTuesdayModule1.setSelected(true);
				} else if (tuple.module.equals(Module._2)) {
					chckBxTuesdayModule2.setSelected(true);
				} else if (tuple.module.equals(Module._3)) {
					chckBxTuesdayModule3.setSelected(true);
				} else if (tuple.module.equals(Module._4)) {
					chckBxTuesdayModule4.setSelected(true);
				} else if (tuple.module.equals(Module._5)) {
					chckBxTuesdayModule5.setSelected(true);
				} else if (tuple.module.equals(Module._6)) {
					chckBxTuesdayModule6.setSelected(true);
				} else if (tuple.module.equals(Module._7)) {
					chckBxTuesdayModule7.setSelected(true);
				} else if (tuple.module.equals(Module._8)) {
					chckBxTuesdayModule8.setSelected(true);
				}
			} else if (tuple.day.equals(Day.WEDNESDAY)) {
				if (tuple.module.equals(Module._1)) {
					chckBxWednesdayModule1.setSelected(true);
				} else if (tuple.module.equals(Module._2)) {
					chckBxWednesdayModule2.setSelected(true);
				} else if (tuple.module.equals(Module._3)) {
					chckBxWednesdayModule3.setSelected(true);
				} else if (tuple.module.equals(Module._4)) {
					chckBxWednesdayModule4.setSelected(true);
				} else if (tuple.module.equals(Module._5)) {
					chckBxWednesdayModule5.setSelected(true);
				} else if (tuple.module.equals(Module._6)) {
					chckBxWednesdayModule6.setSelected(true);
				} else if (tuple.module.equals(Module._7)) {
					chckBxWednesdayModule7.setSelected(true);
				} else if (tuple.module.equals(Module._8)) {
					chckBxWednesdayModule8.setSelected(true);
				}
			} else if (tuple.day.equals(Day.THURSDAY)) {
				if (tuple.module.equals(Module._1)) {
					chckBxThursdayModule1.setSelected(true);
				} else if (tuple.module.equals(Module._2)) {
					chckBxThursdayModule2.setSelected(true);
				} else if (tuple.module.equals(Module._3)) {
					chckBxThursdayModule3.setSelected(true);
				} else if (tuple.module.equals(Module._4)) {
					chckBxThursdayModule4.setSelected(true);
				} else if (tuple.module.equals(Module._5)) {
					chckBxThursdayModule5.setSelected(true);
				} else if (tuple.module.equals(Module._6)) {
					chckBxThursdayModule6.setSelected(true);
				} else if (tuple.module.equals(Module._7)) {
					chckBxThursdayModule7.setSelected(true);
				} else if (tuple.module.equals(Module._8)) {
					chckBxThursdayModule8.setSelected(true);
				}
			} else if (tuple.day.equals(Day.FRIDAY)) {
				if (tuple.module.equals(Module._1)) {
					chckBxFridayModule1.setSelected(true);
				} else if (tuple.module.equals(Module._2)) {
					chckBxFridayModule2.setSelected(true);
				} else if (tuple.module.equals(Module._3)) {
					chckBxFridayModule3.setSelected(true);
				} else if (tuple.module.equals(Module._4)) {
					chckBxFridayModule4.setSelected(true);
				} else if (tuple.module.equals(Module._5)) {
					chckBxFridayModule5.setSelected(true);
				} else if (tuple.module.equals(Module._6)) {
					chckBxFridayModule6.setSelected(true);
				} else if (tuple.module.equals(Module._7)) {
					chckBxFridayModule7.setSelected(true);
				} else if (tuple.module.equals(Module._8)) {
					chckBxFridayModule8.setSelected(true);
				}
			} else if (tuple.day.equals(Day.SATURDAY)) {
				if (tuple.module.equals(Module._1)) {
					chckBxSaturdayModule1.setSelected(true);
				} else if (tuple.module.equals(Module._2)) {
					chckBxSaturdayModule2.setSelected(true);
				} else if (tuple.module.equals(Module._3)) {
					chckBxSaturdayModule3.setSelected(true);
				} else if (tuple.module.equals(Module._4)) {
					chckBxSaturdayModule4.setSelected(true);
				} else if (tuple.module.equals(Module._5)) {
					chckBxSaturdayModule5.setSelected(true);
				} else if (tuple.module.equals(Module._6)) {
					chckBxSaturdayModule6.setSelected(true);
				} else if (tuple.module.equals(Module._7)) {
					chckBxSaturdayModule7.setSelected(true);
				} else if (tuple.module.equals(Module._8)) {
					chckBxSaturdayModule8.setSelected(true);
				}
			} else if (tuple.day.equals(Day.SUNDAY)) {
				if (tuple.module.equals(Module._1)) {
					chckBxSundayModule1.setSelected(true);
				} else if (tuple.module.equals(Module._2)) {
					chckBxSundayModule2.setSelected(true);
				} else if (tuple.module.equals(Module._3)) {
					chckBxSundayModule3.setSelected(true);
				} else if (tuple.module.equals(Module._4)) {
					chckBxSundayModule4.setSelected(true);
				} else if (tuple.module.equals(Module._5)) {
					chckBxSundayModule5.setSelected(true);
				} else if (tuple.module.equals(Module._6)) {
					chckBxSundayModule6.setSelected(true);
				} else if (tuple.module.equals(Module._7)) {
					chckBxSundayModule7.setSelected(true);
				} else if (tuple.module.equals(Module._8)) {
					chckBxSundayModule8.setSelected(true);
				}
			}
		}
	}
	
}
