package com.harish.institutemanagement.models;

import javax.validation.constraints.Pattern;

public class UserPhoneNumber {

	private String username;

	@Pattern(regexp = "^[1-9][0-9]{9,9}$", message = "must be a valid phone number")
	private String phoneNumber;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
