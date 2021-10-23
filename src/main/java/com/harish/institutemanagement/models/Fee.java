package com.harish.institutemanagement.models;

public class Fee {

	private int feeId;
	private String semester;
	private String incomeSlab;
	private String caste;
	private int amount;

	public int getFeeId() {
		return feeId;
	}

	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getIncomeSlab() {
		return incomeSlab;
	}

	public void setIncomeSlab(String incomeSlab) {
		this.incomeSlab = incomeSlab;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
