package com.harish.institutemanagement.models;

import java.sql.Date;
import java.sql.Time;

import javax.validation.constraints.NotBlank;

public class FeePayment {

	private String transactionId;
	private Date transactionDate;
	private Time transactionTime;

	@NotBlank
	private String semester;
	private int year;
	private int amount;

	@NotBlank
	private String modeOfPayment;

	private Student student;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Time getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Time transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
