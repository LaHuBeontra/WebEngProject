package org.login;

import java.util.ArrayList;
import java.util.List;

public class UserList {
	private static List<User> users = new ArrayList<User>();
	
	static {
		User test1 = new User ();
		User test2 = new User ();
		test1.setUserName("test1");
		test1.setAdmin(true);
		test2.setUserName("test2");
		test2.setAdmin(false);
		users.add(test1);
		users.add(test2);
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public User getUser(User user) {
		for (User thisUser: users) {
			if (thisUser.equals(user)) return thisUser;
		}
		return null;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}
}
