package org.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.*;

/**
 * This class is responsible for everything related to registering and
 * logging in. It is probably too long, but refactoring it into multiple
 * classes would take too long now. Every method has its own description
 * and a clear name, so things are still easy to find.
 */
public class FileService {
	List<String> userStrings      = new ArrayList<>();
	List<String> householdStrings = new ArrayList<>();
	List<String> passwordStrings  = new ArrayList<>();

	final File   folder           = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileSystem");


	public FileService() {
		//Fetch List of Users/ Households from FileSystem
		addHouseholdAndUserNamesToLists(folder);
	}
	
	//Takes users, households and passwords from FileSystem and saves them to lists
	public void addHouseholdAndUserNamesToLists(File folder) {
		
		emptyLists();
	    for (File household : folder.listFiles()) {
	        householdStrings.add(household.getName());
	        for (File file : household.listFiles()) {
	            if (file.getName().equals("Password")) {
		        	passwordStrings.add(readPassword(file));
		        } else userStrings.add(file.getName());
	        }
	        
	    }
	}
	
	//Reads Household Password from the "Password" file
	public String readPassword(File file) {
		String filePath = file.toString();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
		{
			return br.readLine();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//Reads Password from a User file
	public String readUserPassword(File file) {
        String filePath = file.toString();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
		{
			br.readLine();
			return br.readLine();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//Reads Status from a User file
	public String readUserStatus(File file) {
        String filePath = file.toString();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
		{
			br.readLine();
			br.readLine();
			return br.readLine();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	//Gets the Status of a User by his username
	public String getStatusFromUser(String userName) {
		File userFile = this.getUserFile(userName);
		return this.readUserStatus(userFile);
	}
	
	//Checks if a User is an Admin
	public boolean isAdmin(String userName) {
		String status = this.getStatusFromUser(userName);
		if (status.equals("Admin")) return true;
		else return false;
	}
	
	//Gets the FileSystem folder
	public File getFileSystem() {
		return folder;
	}
	
	//Clears both lists
	public void emptyLists() {
		userStrings.clear();
		householdStrings.clear();
		passwordStrings.clear();
	}
		
	//Gets the Household name of a User
	public String getUsersHouseholdName(String username) {
		addHouseholdAndUserNamesToLists(folder);
		for (File household : folder.listFiles()) {
	        for (File file : household.listFiles()) {
	            if (file.getName().equals(username)) {
		        	return file.getParentFile().getName();
		        }
	        }
	        
	    }
		return null;
	}
	
	//Checks if a User is in the system and if his Password is correct
	public boolean validate(String userName, String userPassword) {
		if (this.listContainsUser(userName)) {
			File userFile = this.getUserFile(userName);
			String filePassword = this.readUserPassword(userFile);
			if (userPassword.equals(filePassword)) return true;
			else return false;
		} else return false;
	}
	
	//Checks if User's status is Admin
	public boolean userIsAdmin(String userName) {
		File userFile = this.getUserFile(userName);
		String fileStatus = this.readUserStatus(userFile);
		if (fileStatus.equals("Admin")) return true;
		else return false;
	}
	
	//Gets a Users file by his Username
	public File getUserFile(String userName) {
		addHouseholdAndUserNamesToLists(folder);
		for (File household : folder.listFiles()) {
	        for (File file : household.listFiles()) {
	            if (file.getName().equals(userName)) {
		        	return file;
		        }
	        }
	    }
		return null;
	}
	
	//Gets a Household file by its name
	public File getHouseholdFile(String householdName) {
		addHouseholdAndUserNamesToLists(folder);
		for (File household : folder.listFiles()) {
		    if (household.getName().equals(householdName)) {
	            return household;
			}
		}
		return null;
	}
	
	//True if FileSystem already contains user
	public boolean listContainsUser(String username) {
		addHouseholdAndUserNamesToLists(folder);
		for (String userString : userStrings) {
			if (username.equals(userString)) return true;
		}
		return false;
	}
	
	//Gets the Household Password
	public String getHouseholdPassword(String householdName) {
		File householdFile = this.getHouseholdFile(householdName);
		
		for (File file : householdFile.listFiles()) {
            if (file.getName().equals("Password")) {
	        	return this.readPassword(file);
	        }
        }
		return null;
	}
	
	//True if FileSystem already contains user
	public boolean listContainsHousehold(String household) {
		addHouseholdAndUserNamesToLists(folder);
		for (String householdString : householdStrings) {
			if (household.equalsIgnoreCase(householdString)) return true;
		}
		return false;
	}

    //Checks user data for validity
	public String checkData(String userName, String password, String email, String household) {
		String result;
		if (userName.isEmpty())
			result = "You have to enter a username!";
		else if (listContainsUser(userName))
			result = "A user with this name already exists. Please choose a different username!";
		else if (usernameIsPassword(userName))
			result = "Please do not pick the username \"Password\", as this conflicts with our naming conventions!";
		else if (password.isEmpty())
			result = "You have to enter a password!";
		else if (email.isEmpty())
			result = "You have to enter an email address!";
		else if (!isEmailValid(email))
			result = "You have to enter a valid email address!";
		else if (household == null) {
			result = "Please select if you want to create or join a household!";
		} else
			result = null;
		return result;

	}

	//Checks if user name is equal to "Password" (will not work due to file naming)
	public boolean usernameIsPassword(String username) {
		if (username.equals("Password")) return true;
		else return false;
	}

	//True if email is in a valid format
	private boolean isEmailValid(String email) {

  if(email == null) {
			return false;
		}else {
		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
		}
	}
	
	//True if all emails are valid
	public boolean areEmailsValid(String [] emails) {
		boolean valid = true;
		for(int i = 0; i< emails.length; i++) {
			if(!isEmailValid(emails[i])) {
				valid = false;
			}
		}

		return valid;
	}

	//True if the password belongs to a Household
	public boolean passwordFitsHousehold(String password) {
		addHouseholdAndUserNamesToLists(folder);
		for (String passwordString : passwordStrings) {
			if (password.equals(passwordString)) return true;
		}
		return false;
	}
	
	//Gets the Household file by its password
	public File getHouseholdFileByPassword(String password) {
		for (int i = 0; i <= passwordStrings.size(); i++) {
			if (passwordStrings.get(i).equals(password)) {
				String householdString = householdStrings.get(i);
				for (File fileEntry : folder.listFiles()) {
					if (fileEntry.getName().equals(householdString))
						return fileEntry;
			    }
			}
		}
		return null;
	}
	
	//Creates Household with User and Password files
	public void createHousehold(String householdName, String userName, String userPassword, String householdPassword) {
		try {
			//Create new File instance of the Household
			File householdFile = new File(folder, householdName);
			//Create the Household directory
			householdFile.mkdir();
			System.out.println(householdFile);
			//Create new File instance of the Household Password
			File passwordFile = new File(householdFile, "Password");
			//Create the Password file
			passwordFile.createNewFile();
					
			//Create new File instance of the Household User
			File userFile = new File(householdFile, userName);
			//Create the User file
			userFile.createNewFile();
					
			//Store Password to Password file
			List<String> passwordLine = Arrays.asList(householdPassword);
			Path passwordFilePath = passwordFile.toPath();
			Files.write(passwordFilePath, passwordLine, Charset.forName("UTF-8"));

			//Store User to User file
			List<String> userLines = Arrays.asList(userName, userPassword, "Admin");
			Path userFilePath = userFile.toPath();
			Files.write(userFilePath, userLines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Creates User file in existing Household
	public void joinHousehold(String userName, String userPassword, String householdPassword) {
		try {
			//Get Household file
			File householdFile = this.getHouseholdFileByPassword(householdPassword);
			
			//Create new File instance of the Household User
			File userFile = new File(householdFile, userName);
			//Create the User file
			userFile.createNewFile();

			//Store User to User file
			List<String> userLines = Arrays.asList(userName, userPassword, "Member");
			Path userFilePath = userFile.toPath();
			Files.write(userFilePath, userLines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Generates random household password
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

	//Gets the Users of a Household
	public List<String> getUsersOfHousehold(String householdName) {
		List<String> users = new ArrayList<>();
		File householdFile = this.getHouseholdFile(householdName);
		
		for (File file : householdFile.listFiles()) {
            if (!file.getName().equals("Password")) {
	        	users.add(file.getName());
	        }
        }
		
		return users;
	}

	//Deletes a User
    public void deleteUser(String userName) {
    	File userFile = this.getUserFile(userName);
    	userFile.delete();
    }

    //Changes Status of a User
    public void changeStatus(String userName, String status) {
    	File userFile      = this.getUserFile(userName);
        File householdFile = this.getHouseholdFile(this.getUsersHouseholdName(userName));
        File outputFile    = new File(householdFile, "Temp");
        try {
			outputFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                
            	
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("Admin")) {
                        writer.write("Member");
                        writer.newLine();
                    } else if (line.equals("Member")) {
                    	writer.write("Admin");
                        writer.newLine();
                    } else {
                    	writer.write(line);
                        writer.newLine();
                    }
                }
            }

            userFile.delete();
            outputFile.renameTo(userFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
