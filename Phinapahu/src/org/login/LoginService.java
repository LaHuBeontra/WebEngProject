package org.login;

import java.util.*;
import java.util.regex.*;


public class LoginService {
	HashMap<String, User> users = new HashMap<String, User>();
	HashMap<String, Household> households = new HashMap<String, Household>();

	public LoginService() {
		// TODO fetch List of Users/ Households from database

		User test1 = new User();
		test1.setUserName("test1");
		test1.setEmail("test1");
		test1.setPassword("test1");
		test1.setAdmin(true);

		User test2 = new User();
		test2.setEmail("test2@test2");
		test2.setUserName("test2");
		test2.setPassword("test2");
		test2.setAdmin(true);

		users.put(test1.getUserName(), test1);
		users.put(test2.getUserName(), test2);

		Household household1 = new Household();
		household1.setHouseholdName("household1");
		household1.setId(1);
		household1.setPassword("household1");
		households.put(household1.getPassword(), household1);
	}

	public boolean validate(String userName, String password) {

		// check if user exists
		if (users.containsKey(userName)) {
			// check if password is correct
			if (users.get(userName).getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		} else
			return false;

	}

	public String checkData(String userName, String password, String email, String household) {
		String result;
		if (userName.isEmpty())
			result = "You have to enter a username";
		if (!isUsernameUnique(userName))
			result = "A user with this name already exists. Please choose another username";
		else if (password.isEmpty())
			result = "You have to enter a password";
		else if (email.isEmpty())
			result = "You have to enter a email adress";
		else if (!isEmailValid(email))
			result = "You have to enter a valid email adress";
		else if (household == null) {
			result = "Please select if you want to create or join a household";
		} else
			result = null;
		return result;

	}

	private boolean isUsernameUnique(String username) {
		// TODO check if user is already in database
		return true;
	}

	private boolean isEmailValid(String email) {
		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}
	public boolean areEmailsValid(String [] emails) {
		boolean valid = true;
		for(int i = 0; i< emails.length; i++) {
			if(!isEmailValid(emails[i])) {
				valid = false;
			}
		}
		return valid;
	}

	public User getUserDetails(String userName) {
		return users.get(userName);
	}

	public Household getHouseholdDetail(String householdPassword) {
		return households.get(householdPassword);

	}

	public boolean checkHouseholdExists(String householdPassword) {
		if (households.containsKey(householdPassword)) {
			return true;
		} else
			return false;
	}

	public String generatePassword() {
		int numLeft = 48;
		int numRight = 57;

		int letLeft = 97;
		int letRight = 122;

		int passwordLength = 10;

		char[] signs = new char[numRight - numLeft + letRight - letLeft + 2];

		for (int i = 0; i < 10; i++) {
			signs[i] = (char) (numLeft + i);
		}
		for (int i = 0; i < 26; i++) {
			signs[10 + i] = (char) (letLeft + i);
		}
		StringBuilder password = new StringBuilder(passwordLength);
		Random r = new Random();
		int randIndex;
		for (int i = 0; i < passwordLength; i++) {
			randIndex = r.nextInt(26);
			password.append(signs[randIndex]);
		}

		return password.toString();
	}
}
