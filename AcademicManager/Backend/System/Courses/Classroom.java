package System.Courses;

import Tools.Enums.School;
import Tools.Others.Const;

/**
 * Class that represents a Classroom.
 */
public class Classroom {
	
	public enum Campus {
		CASACENTRAL,
		LOCONTADOR,
		ORIENTE,
		SANJOAQUIN,
		VILLARRICA
	}

	private String initials;
	private int size = Const.DEFAULT_SIZE;
	private School school;
	private Campus campus;
	
	/**
	 * Creates an instance of Classroom.
	 * @param initials The initials that identify this Classroom.
	 * @param school The school to which this Classroom belongs.
	 * @param campus The campus in which this Classroom is located.
	 */
	public Classroom(String initials, School school, Campus campus) {
		this.initials = initials;
		this.school = school;
		this.setCampus(campus);
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
	 * @return This Classroom initials.
	 */
	public String getInitials() {
		return initials;
	}
	
	/**
	 * Modifies this Classroom's initials.
	 * @param initials The new initials of this Classroom.
	 */
	public void setInitials(String initials) {
		this.initials = initials;
	}
	
	/**
	 * @return The size of this Classroom.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Modifies this Classroom's size.
	 * @param size The new size of this Classroom.
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * @return The School to which this Classroom belongs.
	 */
	public School getSchool() {
		return school;
	}
	
	/**
	 * Modifies this Classroom's school.
	 * @param school The new school of this Classroom.
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/**
	 * @return The campus in which this Classroom is located.
	 */
	public Campus getCampus() {
		return campus;
	}

	/**
	 * Modifies this Classroom's campus.
	 * @param school The new campus in which this Classroom will be located.
	 */
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
}
