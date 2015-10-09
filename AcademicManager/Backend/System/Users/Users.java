package System.Users;

import java.util.ArrayList;

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