package com.cgm.model;

public class AccountModel {
	
	public boolean login(String username, String password) {
		return username.equalsIgnoreCase("user1")&& password.equalsIgnoreCase("123");
	}
	

}
