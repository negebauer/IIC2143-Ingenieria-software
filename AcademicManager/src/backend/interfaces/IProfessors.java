package backend.interfaces;

import java.util.ArrayList;

import backend.users.Professor;

/**
 * Protocol used to define an ICourse class that has professors.
 */
public interface IProfessors extends ICourse {

	/**
	 * Adds a professor to this Lecture
	 * 
	 * @param professor
	 *            The professor to be added to this Lecture
	 */
	public void addProfessor(Professor professor);

	/**
	 * Removes a professor from this Lecture
	 * 
	 * @param professor
	 *            The professor to be removed from this Lecture
	 */
	public void removeProfessor(Professor professor);

	/**
	 * @return The Array of professors of this Lecture
	 */
	public ArrayList<Professor> getProfessors();

	/**
	 * Returns the professor from the professors list that corresponds to the
	 * given index
	 * 
	 * @param index
	 *            The index of the professor to be returned
	 * @return The requested professor
	 */
	public Professor getProfessor(int index);

}
