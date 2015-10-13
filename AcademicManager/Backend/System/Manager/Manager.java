package System.Manager;

import java.util.ArrayList;

import System.Users.User;

/**
 * [Singleton] Main class that does what the application user requires. 
 */
public class Manager {

	private ArrayList<User> users = new ArrayList<User>();
	
	private final static Manager INSTANCE = new Manager();
	
	/**
	 * @return The instance of Manager.
	 */
	public static Manager getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates the instance of manager.
	 */
	private Manager() {
//		TODO Load files and stuff.
	}
	
	/**
	 * Adds a user to the application users.
	 * @param user The new user of the application.
	 */
	public void addUser(User user) {
		this.users.add(user);
	}
	
	/**
	 * Removes a user to the application users.
	 * @param user The user of the application to be removed.
	 */
	public void removeUser(User user) {
		this.users.remove(user);
	}
	
	/**
	 * Gets a user given a user index.
	 * @param index The user index.
	 * @return The user that matches the given index.
	 */
	public User getUser(int index) {
		return this.users.get(index);
	}
	
	/**
	 * @return The list of all the users.
	 */
	public ArrayList<User> getList() {
		return this.users;
	}
	
	/**
	 * @return The amount of users of the application.
	 */
	public int getSize() {
		return this.users.size();
	}
}
