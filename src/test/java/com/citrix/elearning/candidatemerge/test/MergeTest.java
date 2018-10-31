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

			logger.info("Click on Candidate Queue !!");
			this.candidateQueuePage = this.candidateQueuePage.clickOnCandidateLink();

			String firstAndLastName = this.candidateQueuePage.getName();

			String updatedDate = this.candidateQueuePage.getQueuedDate();

			int totalCandidate = this.candidateQueuePage.getTotalNumebrOfCandidateRecords();

			logger.info("Total Candidate Count" + totalCandidate);

			logger.info("Click on Candidate Name Link and get CandidateMatchPage ");
			CandidateMatchPage candidateMatchPage = this.candidateQueuePage.selectAndClickCandidateNameLink();

			profile.setCandidateId(candidateMatchPage.getCandidateId());
			profile.setFirstName(firstAndLastName.split(",")[0]);
			profile.setLastName(firstAndLastName.split(",")[1]);

			logger.info("Click on Match Last Name Link and get CandidateReconciliationPage ");
			CandidateReconciliationPage candidateProfilePage = candidateMatchPage.clickOnMatchLastName();
			logger.info("Verifying Inbound email with master email ");

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
				logger.info("Verifying Inbound Date with master Date ");

				if (inBoundDate.after(masterDate)) {
					candidateProfilePage.clickOnInBoundToMasterLink();
					logger.info("Apply Inbound Record to Master Record!!");

				} else {
					candidateProfilePage.clickOnDontApplyToMasterLink();
					logger.info("Don't Apply Inbound Record to Master Record!!");

				}
			} else {
				candidateProfilePage.clickOnNewMasterLink();

				logger.info("Create a new Master Record!!");

			}
			logger.info("Click on alert cancel");
			String alertText = candidateProfilePage.getAlertText();

			if (alertText.equals(
					"This inbound record has an older revision date than the Master Record.  The demographic information in the inbound record will not be applied to the Master Record.")) {
				logger.info("ok clicked on Apply Inbound Record to Master Record!! alert box");
				candidateProfilePage.alertDismiss();
			} else if (alertText
					.equals("Please verify that you wish to update only the myCitrix record with the new data.")) {
				logger.info("ok clicked on Don't Apply Inbound Record to Master Record!!!! alert box");
				candidateProfilePage.alertDismiss();
			} else if (alertText
					.equals("Please verify that you wish to create a new candidate record with this data.")) {
				logger.info("ok clicked on Create a new Master Record!!!! alert box");
				candidateProfilePage.alertDismiss();
			} else {
				logger.info("alert text is not matched to given condition !!");
			}

			this.candidateQueuePage = this.candidateQueuePage.clickOnCandidateLink();

			this.candidateQueuePage.setNameSearchTextBoxAndSearch(firstAndLastName);
			logger.info("Verifying candidate profile is removed from queue ");

			this.candidateQueuePage.isCandidateMerged(updatedDate, firstAndLastName);

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
			logger.error(e.getMessage());
			this.candidateProfile.add(profile);

			System.out.println(profile);

		}
	}
}
