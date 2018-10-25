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
 * @author Pradip.Nemane
 *
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
		try {

			long startTime = System.currentTimeMillis();
			logger.info("Click on Candidate Queue !!");
			this.candidateQueuePage = this.candidateQueuePage.clickOnCandidateLink();

			String firstAndLastName = this.candidateQueuePage.getFirstAndLastName();
			String updatedDate = this.candidateQueuePage.getQueuedDate();
			int totalCandidate = this.candidateQueuePage.getTotalNumberofCandidatePresent();

			logger.info("Total Candidate Count" + totalCandidate);
			logger.info("Click on Candidate Name Link and get CandidateMatchPage ");
			CandidateMatchPage candidateMatchPage = this.candidateQueuePage.selectAndClickCandidateNameLink();
			profile.setCandidateId(candidateMatchPage.getCandidateId());
			profile.setFirstName(firstAndLastName.split(",")[0]);
			profile.setLastName(firstAndLastName.split(",")[1]);
			logger.info("Click on Match Last Name Link and get CandidateReconciliationPage ");
			CandidateReconciliationPage candidateProfilePage = candidateMatchPage.clickOnMatchLastName();

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

				if (inBoundDate.after(masterDate)) {
					candidateProfilePage.clickOnNewMasterLink();
					logger.info("Apply Inbound Record to Master Record!!");

				} else {
					candidateProfilePage.clickOnDontApplyToMasterLink();
					logger.info("Don't Apply Inbound Record to Master Record!!");

				}
			} else {
				candidateProfilePage.clickOnInBoundToMasterLink();
				logger.info("Create a new Master Record!!");

			}
			candidateProfilePage.clickOnAlertCancel();
			this.candidateQueuePage = this.candidateQueuePage.clickOnCandidateLink();
			this.candidateQueuePage.setNameSearchTextBoxAndSearch(firstAndLastName);
			if (this.candidateQueuePage.isMergeCandidate(updatedDate, firstAndLastName)) {
				profile.setTestResult("Pass");
			} else {
				profile.setTestResult("Fail");
			}

			long endTime = System.currentTimeMillis();

			String excutionTime = DateConverter.millisecondsToTime(endTime - startTime);
			profile.setExcutionTime(excutionTime);
			this.candidateProfile.add(profile);
			System.out.println(profile);
		} catch (Exception e) {
			profile.setReason(e.getMessage());
			logger.error(e.getMessage());
			this.candidateProfile.add(profile);

		}
	}
}
