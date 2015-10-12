package System.Manager;

import java.util.ArrayList;

import System.Users.User;

// TODO Write java doc
/**
 * 
 */
public class Manager {

	private ArrayList<User> users = new ArrayList<User>();
	
	private final static Manager INSTANCE = new Manager();
	
	public static Manager getInstance() {
		return INSTANCE;
	}

	private Manager() {
		
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	public void removeUser(User user) {
		this.users.remove(user);
	}
	public User getUser(int index) {
		return this.users.get(index);
	}
	public ArrayList<User> getList() {
		return this.users;
	}
	public int getSize() {
		return this.users.size();
	}
}
