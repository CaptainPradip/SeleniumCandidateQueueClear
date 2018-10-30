package com.citrix.elearning.candidatemerge.utility;

/**
 * This class for Storing candidate details.
 *
 * @author Pradip.Nemane
 *
 */
public class CandidateProfile {
	private String candidateId;
	private String executionTime;
	private String firstName;
	private String lastName;
	private String reason;

	public CandidateProfile() {
	}

	/**
	 * Constructor initialization.
	 *
	 * @param candidateId
	 * @param firstName
	 * @param lastName
	 */
	public CandidateProfile(String candidateId, String firstName, String lastName) {
		this.candidateId = candidateId;

		this.firstName = firstName;
		this.lastName = lastName;

	}

	/**
	 * Method for get candidate id.
	 *
	 * @return candidate id
	 */
	public String getCandidateId() {
		return this.candidateId;
	}

	/**
	 * Method for get Execution time.
	 *
	 * @return time in millisecond.
	 */
	public String getExecutionTime() {
		return this.executionTime;
	}

	/**
	 * Method for get candidate first name.
	 *
	 * @return first name of candidate.
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Method for get candidate last name.
	 *
	 * @return last name of candidate.
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Method for get reason.
	 *
	 * @return reason.
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * Method for set candidate Id.
	 *
	 * @param candidateId
	 *            candidate id.
	 */
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * Method for set execution time.
	 *
	 * @param excutionTime
	 *            total execution time
	 */
	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}

	/**
	 * Method for set first name.
	 *
	 * @param firstName
	 *            candidate first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Method for set last name.
	 *
	 * @param lastName
	 *            candidate last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method for set Reason.
	 *
	 * @param reason
	 *
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return String.format(
				"CandidateProfile [candidateId=%s, executionTime=%s, firstName=%s, lastName=%s, reason=%s, testResult=%s]",
				this.candidateId, this.executionTime, this.firstName, this.lastName, this.reason);
	}

}
