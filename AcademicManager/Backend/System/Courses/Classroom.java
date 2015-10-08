package System.Courses;

import Tools.Enums.School;
import Tools.Others.Const;

public class Classroom {

	private String initials;
	private int size = Const.DEFAULT_SIZE;
	private School school;
	
	public Classroom(String initials, School school) {
	
		this.initials = initials;
		this.school = school;
	}
	public Classroom(String initials, School school, int size) {
	
		this.initials = initials;
		this.school = school;
		this.size = size;
	}
	
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
}
