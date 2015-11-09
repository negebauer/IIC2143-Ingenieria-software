package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

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
		super.hideLanguage();
		super.hideBack();
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
		ArrayList<DayModuleTuple> modules = new ArrayList<DayModuleTuple>();
		Schedule editingSchedule = new Schedule();

		if (chckBxMondayModule5.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.MONDAY, Module._5));
		}
		if (chckBxMondayModule6.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.MONDAY, Module._6));
		}
		if (chckBxMondayModule8.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.MONDAY, Module._8));
		}
		if (chckBxMondayModule7.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.MONDAY, Module._7));
		}
		if (chckBxMondayModule3.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.MONDAY, Module._3));
		}
		if (chckBxMondayModule4.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.MONDAY, Module._4));
		}
		if (chckBxMondayModule2.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.MONDAY, Module._2));
		}
		if (chckBxMondayModule1.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.MONDAY, Module._1));
		}
		if (chckBxTuesdayModule1.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.TUESDAY, Module._1));
		}
		if (chckBxTuesdayModule2.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.TUESDAY, Module._2));
		}
		if (chckBxTuesdayModule4.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.TUESDAY, Module._4));
		}
		if (chckBxTuesdayModule3.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.TUESDAY, Module._3));
		}
		if (chckBxTuesdayModule7.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.TUESDAY, Module._7));
		}
		if (chckBxTuesdayModule8.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.TUESDAY, Module._8));
		}
		if (chckBxTuesdayModule6.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.TUESDAY, Module._6));
		}
		if (chckBxTuesdayModule5.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.TUESDAY, Module._5));
		}
		if (chckBxWednesdayModule1.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.WEDNESDAY, Module._1));
		}
		if (chckBxWednesdayModule2.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.WEDNESDAY, Module._2));
		}
		if (chckBxWednesdayModule4.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.WEDNESDAY, Module._4));
		}
		if (chckBxWednesdayModule3.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.WEDNESDAY, Module._3));
		}
		if (chckBxWednesdayModule7.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.WEDNESDAY, Module._7));
		}
		if (chckBxWednesdayModule8.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.WEDNESDAY, Module._8));
		}
		if (chckBxWednesdayModule6.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.WEDNESDAY, Module._6));
		}
		if (chckBxWednesdayModule5.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.WEDNESDAY, Module._5));
		}
		if (chckBxThursdayModule1.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.THURSDAY, Module._1));
		}
		if (chckBxThursdayModule2.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.THURSDAY, Module._2));
		}
		if (chckBxThursdayModule4.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.THURSDAY, Module._4));
		}
		if (chckBxThursdayModule3.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.THURSDAY, Module._3));
		}
		if (chckBxThursdayModule7.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.THURSDAY, Module._7));
		}
		if (chckBxThursdayModule8.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.THURSDAY, Module._8));
		}
		if (chckBxThursdayModule6.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.THURSDAY, Module._6));
		}
		if (chckBxThursdayModule5.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.THURSDAY, Module._5));
		}
		if (chckBxFridayModule1.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.FRIDAY, Module._1));
		}
		if (chckBxFridayModule2.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.FRIDAY, Module._2));
		}
		if (chckBxFridayModule4.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.FRIDAY, Module._4));
		}
		if (chckBxFridayModule3.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.FRIDAY, Module._3));
		}
		if (chckBxFridayModule7.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.FRIDAY, Module._7));
		}
		if (chckBxFridayModule8.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.FRIDAY, Module._8));
		}
		if (chckBxFridayModule6.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.FRIDAY, Module._6));
		}
		if (chckBxFridayModule5.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.FRIDAY, Module._5));
		}
		if (chckBxSaturdayModule1.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SATURDAY, Module._1));
		}
		if (chckBxSaturdayModule2.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SATURDAY, Module._2));
		}
		if (chckBxSaturdayModule4.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SATURDAY, Module._4));
		}
		if (chckBxSaturdayModule3.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SATURDAY, Module._3));
		}
		if (chckBxSaturdayModule7.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SATURDAY, Module._7));
		}
		if (chckBxSaturdayModule8.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SATURDAY, Module._8));
		}
		if (chckBxSaturdayModule6.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SATURDAY, Module._6));
		}
		if (chckBxSaturdayModule5.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SATURDAY, Module._5));
		}
		if (chckBxSundayModule1.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SUNDAY, Module._1));
		}
		if (chckBxSundayModule2.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SUNDAY, Module._2));
		}
		if (chckBxSundayModule4.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SUNDAY, Module._4));
		}
		if (chckBxSundayModule3.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SUNDAY, Module._3));
		}
		if (chckBxSundayModule7.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SUNDAY, Module._7));
		}
		if (chckBxSundayModule8.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SUNDAY, Module._8));
		}
		if (chckBxSundayModule6.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SUNDAY, Module._6));
		}
		if (chckBxSundayModule5.isSelected()) {
			modules.add(editingSchedule.new DayModuleTuple(Day.SUNDAY, Module._5));
		}
		
		Manager.INSTANCE.currentEditingSchedule = new Schedule(modules);
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
