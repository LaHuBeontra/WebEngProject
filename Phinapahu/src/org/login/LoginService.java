package org.login;

import java.util.*;


public class LoginService {
	HashMap<String, User> users = new HashMap<String, User>();
	HashMap<String, Household> households = new HashMap<String, Household>();
		public LoginService() {
			//TODO fetch List of Users/ Households from database
			
			User test1 = new User ();
			test1.setUserName("test1");
			test1.setEmail("test1");
			test1.setPassword("test1");			
			test1.setAdmin(true);
			
			User test2 = new User ();
			test2.setEmail("test2@test2");
			test2.setUserName("test2");
			test2.setPassword("test2");		
			test2.setAdmin(true);
			
			users.put(test1.getUserName(),test1);
			users.put(test2.getUserName(), test2);
			
			Household household1 = new Household();
			household1.setHouseholdName("household1");
			household1.setId(1);
			household1.setPassword("household1");
			households.put(household1.getPassword(), household1);
		}
		
		public boolean validate (String userName, String password) {
			
			//check if user exists
			if(users.containsKey(userName)) {
				//check if password is correct
				if(users.get(userName).getPassword().equals(password)) {
				    return true;
				}else {
					return false;
				}
			}
			else
				return false;
			
		}
		public String checkData(String userName, String password, String email, String household) {
			String result;
			if(userName.isEmpty())
				result = "You have to enter a username";
			if(!isUsernameUnique(userName))
				result = "A user with this name already exists. Please choose another username";
			else if(password.isEmpty())
				result = "You have to enter a password";
			else if(email.isEmpty())
				result = "You have to enter a email adress";
			else if(!isEmailValid(email))
				result = "You have to enter a valid email adress";
			else if(household == null) {
				result = "Please select if you want to create or join a household";
			}
			else 
				result = null;
			return result;
				
		}
		private boolean isUsernameUnique(String username) {
			//TODO check if user is already in database
			return true;
		}
		private boolean isEmailValid(String email) {
			//TODO Email Validation
			return true;
		}
		
		public User getUserDetails(String userName) {
			return users.get(userName);			
		}
		public Household getHouseholdDetail(String householdPassword) {
			return households.get(householdPassword);
			
		}
		public boolean checkHouseholdExists(String householdPassword) {
			if(households.containsKey(householdPassword)) {
				return true;
			}else
				return false;
		}
	}

