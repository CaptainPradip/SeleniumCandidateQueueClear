package com.citrix.elearning.candidatemerge.test;

import java.text.ParseException;
import java.util.Date;

import org.testng.annotations.Test;

import com.citrix.elearning.candidatemerge.Pages.candidatematch.CandidateMatchPage;
import com.citrix.elearning.candidatemerge.Pages.candidateprofile.CandidateReconciliationPage;
import com.citrix.elearning.candidatemerge.utility.CandidateProfile;
import com.citrix.elearning.candidatemerge.utility.DateConverter;

/**
 *
 * @author Pradip.Nemane class for
 */

public class MergeTest extends BaseTest {

	/**
	 * to Verify Merge Candidate Queue Data
	 *
	 * @throws InterruptedException
	 */
	@Test(dataProvider = "CandidateCount")
	public void mergeCandidateQueueData(int count) {
		CandidateProfile profile = new CandidateProfile();
		long startTime = System.currentTimeMillis();
		try {

			BaseTest.logger.info("Click on Candidate Queue !!");
			this.candidateQueuePage = this.candidateQueuePage.clickOnCandidateLink();

			String firstAndLastName = this.candidateQueuePage.getName();
			String updatedDate = this.candidateQueuePage.getQueuedDate();
			int totalCandidate = this.candidateQueuePage.getTotalNumberofCandidateRecord();

			BaseTest.logger.info("Total Candidate Count" + totalCandidate);
			BaseTest.logger.info("Click on Candidate Name Link and get CandidateMatchPage ");
			CandidateMatchPage candidateMatchPage = this.candidateQueuePage.selectAndClickCandidateNameLink();
			profile.setCandidateId(candidateMatchPage.getCandidateId());
			profile.setFirstName(firstAndLastName.split(",")[0]);
			profile.setLastName(firstAndLastName.split(",")[1]);
			BaseTest.logger.info("Click on Match Last Name Link and get CandidateReconciliationPage ");
			CandidateReconciliationPage candidateProfilePage = candidateMatchPage.clickOnMatchLastName();
			BaseTest.logger.info("Verifying Inbound email with master email ");

			if (candidateProfilePage.getInboundPrimaryEmail().equals(candidateProfilePage.getMasterPrimaryEmail())) {

				Date inBoundDate = null;
				Date masterDate = null;
				try {
					inBoundDate = DateConverter.convertTextToDate("MM/DD/YYYY HH:MM:SS",
							candidateProfilePage.getInboundUpdateDate());
					masterDate = DateConverter.convertTextToDate("MM/DD/YYYY HH:MM:SS",
							candidateProfilePage.getMasterUpdateDate());

				} catch (ParseException e) {

					e.printStackTrace();
				}
				BaseTest.logger.info("Verifying Inbound Date with master Date ");

				if (inBoundDate.after(masterDate)) {
					candidateProfilePage.clickOnNewMasterLink();
					BaseTest.logger.info("Apply Inbound Record to Master Record!!");

				} else {
					candidateProfilePage.clickOnDontApplyToMasterLink();
					BaseTest.logger.info("Don't Apply Inbound Record to Master Record!!");

				}
			} else {
				candidateProfilePage.clickOnInBoundToMasterLink();
				BaseTest.logger.info("Create a new Master Record!!");

			}
			BaseTest.logger.info("Click on alert cancel");

			candidateProfilePage.alertDismiss();
			this.candidateQueuePage = this.candidateQueuePage.clickOnCandidateLink();
			this.candidateQueuePage.setNameSearchTextBoxAndSearch(firstAndLastName);
			BaseTest.logger.info("Verifying candidate profile is removed from queue ");

			this.candidateQueuePage.isMergeCandidate(updatedDate, firstAndLastName);

			long endTime = System.currentTimeMillis();

			String excutionTime = DateConverter.millisecondsToTime(endTime - startTime);
			profile.setExecutionTime(excutionTime);
			this.candidateProfile.add(profile);
			System.out.println(profile);
		} catch (Exception e) {
			profile.setReason(e.getMessage());
			long endTime = System.currentTimeMillis();

			String excutionTime = DateConverter.millisecondsToTime(endTime - startTime);
			profile.setExecutionTime(excutionTime);

			BaseTest.logger.error(e.getMessage());
			this.candidateProfile.add(profile);
			System.out.println(profile);

		}
	}
}
