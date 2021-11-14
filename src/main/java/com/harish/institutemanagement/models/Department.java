package com.harish.institutemanagement.models;

import javax.validation.constraints.NotBlank;

public class Department {

	private String departmentId;

	@NotBlank
	private String departmentName;

	@NotBlank
	private String address;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
