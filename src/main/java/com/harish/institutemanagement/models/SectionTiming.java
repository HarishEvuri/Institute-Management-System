package com.harish.institutemanagement.models;

import javax.validation.constraints.NotBlank;

public class SectionTiming {

	private String sectionId;
	private String courseId;

	@NotBlank
	private String day;

	@NotBlank
	private String slot;

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

}
