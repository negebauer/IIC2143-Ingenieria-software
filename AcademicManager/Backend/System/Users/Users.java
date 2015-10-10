package System.Users;

import java.util.ArrayList;

// TODO Should this really be a class? Better if users is an attribute of Manager
public class Users {

	private ArrayList<User> users = new ArrayList<User>();
	
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