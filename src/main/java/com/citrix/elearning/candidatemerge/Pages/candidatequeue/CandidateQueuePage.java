package com.citrix.elearning.candidatemerge.Pages.candidatequeue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.Pages.BasePage;
import com.citrix.elearning.candidatemerge.Pages.candidatematch.CandidateMatchPage;

/**
 *
 * @author Pradip.Nemane
 *
 */
public class CandidateQueuePage extends BasePage {

	/**
	 * Candidate Queue Link
	 */
	@FindBy(xpath = "//A[contains(text(),'Candidate Queue')]")

	WebElement candidateQueueLink;

	/**
	 * Name Search TextBox webElement
	 */
	@FindBy(xpath = "//TABLE[1]/THEAD[1]/TR[1]/th[6]/input")
	WebElement nameSearchTextBox;
	/**
	 * for updated date of first Candidate
	 */
	@FindBy(xpath = "//FORM[1]/TABLE[1]/TBODY[1]/TR[1]/td[1]")
	WebElement queuedDate;

	/**
	 * Candidate table first element
	 */
	@FindBy(xpath = "//TABLE[1]/TBODY[1]/TR[1]/TD[6]//A")
	WebElement tableFirstRow;
	/**
	 * total number of candidate Queue
	 */
	@FindBy(xpath = "//DIV[1]/DIV[2]/span")
	WebElement totalNumberofCandidate;

	/**
	 * Constructor initialization
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public CandidateQueuePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * to Click on candidate Queue Link
	 *
	 * @return
	 *
	 * @return {@link CandidateProfileMatchPage}
	 */
	public CandidateQueuePage clickOnCandidateLink() {
		highLighterMethod(this.candidateQueueLink);

		click(this.candidateQueueLink);
		waitUntilPageLoad();
		return new CandidateQueuePage(this.driver);
	}

	/**
	 * Get First and last name
	 *
	 * @return {@link String}
	 */
	public String getFirstAndLastName() {

		return getText(this.tableFirstRow);
	}

	/**
	 * Get Updated Date
	 *
	 * @return {@link String}
	 */
	public String getQueuedDate() {
		return getText(this.queuedDate);
	}

	/**
	 * for get Total Candidate Queue Count .
	 *
	 * @return {@link Integer}
	 */
	public int getTotalNumberofCandidatePresent() {
		waitUntilPageLoad();
		String candidateWithResultText = getText(this.totalNumberofCandidate);
		int totalCandidate = Integer.parseInt(candidateWithResultText.split(" ")[0].trim());
		return totalCandidate;

	}

	/**
	 * Verifying Candidate is removed from Queue
	 *
	 * @param updatedDate
	 *            candidate profile update date
	 * @param firstAndLastName
	 *            candidate first and last name with comma(,) separated
	 * @return {@link Boolean}
	 */
	public boolean isMergeCandidate(String updatedDate, String firstAndLastName) {
		WebElement searchElement = null;
		while (true) {
			try {
				searchElement = this.driver.findElement(
						By.xpath("//tr/td[text()='" + updatedDate + "']/../td/a[text()='" + firstAndLastName + "']"));
			} catch (NoSuchElementException e) {

			}

			this.totalNumberofCandidate = this.driver.findElement(By.xpath("//DIV[1]/DIV[2]/span"));
			int count = getTotalNumberofCandidatePresent();

			if (count == 0 && searchElement == null) {
				return true;
			}
			Pattern p = Pattern.compile("([1-5])");
			Matcher m = p.matcher((Integer.toString(count)));
			if (m.matches()) {
				return false;
			}

			this.totalNumberofCandidate = this.driver.findElement(By.xpath("//DIV[1]/DIV[2]/span"));
			count = getTotalNumberofCandidatePresent();

		}

	}

	/**
	 * to select and click on candidate Last Name
	 *
	 * @return {@link CandidateProfileMatchPage}
	 */
	public CandidateMatchPage selectAndClickCandidateNameLink() {

		highLighterMethod(this.tableFirstRow);
		click(this.tableFirstRow);
		waitUntilPageLoad();
		return new CandidateMatchPage(this.driver);
	}

	/**
	 * enter First name and last in search box
	 *
	 * @param firstAndLastName
	 *            candidate first and last name with comma(,) separated
	 */
	public void setNameSearchTextBoxAndSearch(String firstAndLastName) {
		clearAndType(this.nameSearchTextBox, firstAndLastName);
		sendKey(this.nameSearchTextBox, Keys.ENTER);
		waitUntilPageLoad();
	}
}
