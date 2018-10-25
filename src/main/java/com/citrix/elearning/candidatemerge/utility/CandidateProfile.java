package com.citrix.elearning.candidatemerge.utility;

public class CandidateProfile {
	private String candidateId;

	private String excutionTime;
	private String firstName;
	private String lastName;
	private String reason;
	private String testResult;

	public CandidateProfile() {
		// TODO Auto-generated constructor stub
	}

	public CandidateProfile(String candidateId, String firstName, String lastName) {
		super();
		this.candidateId = candidateId;

		this.firstName = firstName;
		this.lastName = lastName;

	}

	public String getCandidateId() {
		return this.candidateId;
	}

	public String getExcutionTime() {
		return this.excutionTime;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getReason() {
		return this.reason;
	}

	public String getTestResult() {
		return this.testResult;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public void setExcutionTime(String excutionTime) {
		this.excutionTime = excutionTime;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	@Override
	public String toString() {
		return String.format(
				"CandidateProfile [candidateId=%s, excutionTime=%s, firstName=%s, lastName=%s, reason=%s, testResult=%s]",
				this.candidateId, this.excutionTime, this.firstName, this.lastName, this.reason, this.testResult);
	}

}
