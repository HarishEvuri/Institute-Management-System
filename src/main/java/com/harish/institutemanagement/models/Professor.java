package com.harish.institutemanagement.models;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Professor {

	private String professorId;

	@NotBlank
	private String qualification;

	@NotBlank
	private String departmentId;

	@Valid
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
