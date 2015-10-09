package Tools.Interfaces;

import java.util.ArrayList;

import System.Users.Assistant;

/**
 * Protocol used to define a ICourse class that has assistants.
 */
public interface IAssistants extends ICourse {

	/**
	 * Adds an assistant to this Assistantship
	 * @param assistant The assistant to be added to this Assistantship
	 */
	public void addAssistant(Assistant assistant);
	
	/**
	 * Removes an assistant from this Assistantship
	 * @param assistant The assistant to be removed from this Assistantship
	 */
	public void removeAssistant(Assistant assistant);
	/**
	 * @return The Array of assistants of this Assistantship
	 */
	public ArrayList<Assistant> getAssistants();
	/**
	 * Returns the assistant from the assistants list that corresponds to the given index
	 * @param index The index of the assistant to be returned 
	 * @return The requested assistant
	 */
	public Assistant getAssistant(int index);
	
}
