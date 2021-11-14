package com.harish.institutemanagement.models;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Student {

	private String rollNumber;

	@NotNull
	private int joinYear;

	@NotBlank
	private String caste;

	@NotBlank
	private String familyIncome;

	@NotBlank
	private String gaurdianName;

	@NotBlank
	private String gaurdianRelation;

	@Pattern(regexp = "^[1-9][0-9]{9,9}$", message = "must be a valid phone number")
	private String gaurdianPhoneNumber;

	@NotBlank
	private String departmentId;

	@Valid
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public int getJoinYear() {
		return joinYear;
	}

	public void setJoinYear(int joinYear) {
		this.joinYear = joinYear;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(String familyIncome) {
		this.familyIncome = familyIncome;
	}

	public String getGaurdianName() {
		return gaurdianName;
	}

	public void setGaurdianName(String gaurdianName) {
		this.gaurdianName = gaurdianName;
	}

	public String getGaurdianRelation() {
		return gaurdianRelation;
	}

	public void setGaurdianRelation(String gaurdianRelation) {
		this.gaurdianRelation = gaurdianRelation;
	}

	public String getGaurdianPhoneNumber() {
		return gaurdianPhoneNumber;
	}

	public void setGaurdianPhoneNumber(String gaurdianPhoneNumber) {
		this.gaurdianPhoneNumber = gaurdianPhoneNumber;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
