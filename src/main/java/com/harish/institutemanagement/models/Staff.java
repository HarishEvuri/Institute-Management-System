package com.harish.institutemanagement.models;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Staff {

	private String staffId;

	@NotBlank
	private String designation;

	@Valid
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
