package org.login;

public class User {

	private String UserName;
	private String Email;
	private String Password;
	private boolean Admin;
	
	private Household Household;

	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean isAdmin() {
		return Admin;
	}
	public void setAdmin(boolean admin) {
		Admin = admin;
	}
	
	public Household getHousehold() {
		return Household;
	}
	public void setHouseholdName(Household household) {
		Household = household;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (UserName == null) {
			if (other.UserName != null)
				return false;
		} else if (!UserName.equals(other.UserName))
			return false;
		return true;
	}
}
