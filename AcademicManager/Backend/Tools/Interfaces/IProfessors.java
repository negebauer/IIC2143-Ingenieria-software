package Tools.Interfaces;

import java.util.ArrayList;

import System.Users.Professor;

/**
 * Protocol used to define a ICourse class that has professors.
 */
public interface IProfessors extends ICourse {
	
	/**
	 * Adds a professor to this Professorship
	 * @param professor The professor to be added to this Professorship
	 */
	public void addProfessor(Professor professor);
	
	/**
	 * Removes a professor from this Professorship
	 * @param professor The professor to be removed from this Professorship
	 */
	public void removeProfessor(Professor professor);
	
	/**
	 * @return The Array of professors of this Professorship
	 */
	public ArrayList<Professor> getProfessors();

	/**
	 * Returns the professor from the professors list that corresponds to the given index
	 * @param index The index of the professor to be returned 
	 * @return The requested professor
	 */
	public Professor getProfessor(int index);
	
}
