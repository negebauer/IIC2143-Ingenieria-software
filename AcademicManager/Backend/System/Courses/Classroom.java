package System.Courses;

import Tools.Enums.School;
import Tools.Others.Const;

/**
 * Class that represents a Classroom.
 */
public class Classroom {

	private String initials;
	private int size = Const.DEFAULT_SIZE;
	private School school;
	
	/**
	 * Creates an instance of Classroom.
	 * @param initials The initials that identify this Classroom.
	 * @param school The school to which this Classroom belongs.
	 */
	public Classroom(String initials, School school) {
	
		this.initials = initials;
		this.school = school;
	}
	
	/**
	 * Creates an instance of Classroom.
	 * @param initials The initials that identify this Classroom.
	 * @param school The school to which this Classroom belongs.
	 * @param size How many students fit in the Classroom.
	 */
	public Classroom(String initials, School school, int size) {
	
		this.initials = initials;
		this.school = school;
		this.size = size;
	}
	
	/**
	 * @return This Classroom initials
	 */
	public String getInitials() {
		return initials;
	}
	
	/**
	 * Modifies this Classroom initials
	 * @param initials The new initials of this Classroom
	 */
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
