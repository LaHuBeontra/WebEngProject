package org.login;

import java.util.ArrayList;
import java.util.List;

public class UserService {
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
}
