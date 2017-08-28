package com.refri.model;

public class UserDTO {
	String userID;
	String password;
	String userName;
	
	
	@Override
	public String toString() {
		return "UserDTO [ userID = " + userID + ", password = " + password + ", userName = " + userName + " ]";
	}
	public UserDTO() {
	}
	public UserDTO(String userID, String password, String userName) {
		this.userID = userID;
		this.password = password;
		this.userName = userName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
