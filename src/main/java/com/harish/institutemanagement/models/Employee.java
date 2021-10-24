package com.harish.institutemanagement.models;

import java.util.Date;

public class Employee {

	private String employeeId;
	private Date joinDate;
	private Date endDate;
	private String accountNumber;
	private String bank_IFSC_code;
	private String panNumber;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBank_IFSC_code() {
		return bank_IFSC_code;
	}

	public void setBank_IFSC_code(String bank_IFSC_code) {
		this.bank_IFSC_code = bank_IFSC_code;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

}
