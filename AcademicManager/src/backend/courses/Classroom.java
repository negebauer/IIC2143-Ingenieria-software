package backend.courses;

import backend.enums.School;
import backend.others.Const;

/**
 * Class that represents a Classroom.
 */
public class Classroom {

	public enum Campus {
		CASA_CENTRAL, LO_CONTADOR, ORIENTE, SAN_JOAQUIN, VILLARRICA;

		public static Campus defaultCampus() {
			return Campus.SAN_JOAQUIN;
		}
	}

	private String initials;
	private School school;
	private Campus campus;
	private int size;

	/**
	 * Creates an instance of Classroom. Supports default values for every
	 * parameter, therefore null is a valid value for every parameter.
	 * 
	 * @param initials
	 *            The initials that identify this Classroom.
	 * @param school
	 *            The school to which this Classroom belongs.
	 * @param campus
	 *            The campus in which this Classroom is located.
	 * @param size
	 *            How many students fit in the Classroom.
	 */
	public Classroom(String initials, School school, Campus campus, int size) {
		this.initials = initials != null ? initials : "InitialsNil";
		this.school = school != null ? school : School.defaultSchool();
		this.campus = campus != null ? campus : Classroom.Campus.defaultCampus();
		this.size = size > 0 ? size : Const.DEFAULT_CLASSROOM_SIZE;
	}

	/**
	 * @return This Classroom initials.
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * Modifies this Classroom's initials.
	 * 
	 * @param initials
	 *            The new initials of this Classroom.
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
	 * 
	 * @param size
	 *            The new size of this Classroom.
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
	 * 
	 * @param school
	 *            The new school of this Classroom.
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
	 * 
	 * @param school
	 *            The new campus in which this Classroom will be located.
	 */
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
}
