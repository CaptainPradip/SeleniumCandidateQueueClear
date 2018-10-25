package com.citrix.elearning.candidatemerge.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.citrix.elearning.candidatemerge.Pages.candidatequeue.CandidateQueuePage;
import com.citrix.elearning.candidatemerge.Pages.login.LoginPage;
import com.citrix.elearning.candidatemerge.utility.CandidateProfile;
import com.citrix.elearning.candidatemerge.utility.ExcelUtils;
import com.citrix.elearning.candidatemerge.utility.MailService;
import com.citrix.elearning.candidatemerge.utility.PropertyUtil;

public class BaseTest {

	static Logger logger = Logger.getLogger(BaseTest.class.getName());
	public String baseUrl = PropertyUtil.getProperty("baseUrl");
	List<CandidateProfile> candidateProfile = new ArrayList<CandidateProfile>();
	CandidateQueuePage candidateQueuePage;
	protected WebDriver driver;

	/**
	 * @return Object[][] where first column contains 'author' and second column
	 *         contains 'searchKey'
	 */

	@DataProvider(name = "CandidateCount")
	public Object[][] getCandidateCount() {
		Object[][] count = new Object[this.candidateQueuePage.getTotalNumberofCandidatePresent()][1];
		for (int i = 0; i < this.candidateQueuePage.getTotalNumberofCandidatePresent(); i++) {
			count[i][0] = i;

		}
		return count;

	}

	/**
	 * method for get driver instance
	 *
	 * @param browserName
	 * @return
	 */
	private WebDriver getWebDriver(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/binaries/chromedriver.exe");
			this.driver = new ChromeDriver();
		} else {
			System.setProperty("wedriver.gecko.driver", "src/test/resources/binaries/geckodriver.exe");
			this.driver = new FirefoxDriver();
		}
		return this.driver;

	}

	/**
	 * method to mail result data
	 *
	 * @param candidateProfile
	 *            list of candidate profile
	 */
	public void mailTestCaseResult(List<CandidateProfile> candidateProfile) {
		String filePath = ExcelUtils.writeDataIntoExcel(candidateProfile);
		try {
			MailService.sendMail(PropertyUtil.getProperty("fromEmail"), PropertyUtil.getProperty("subjectMail"),
					PropertyUtil.getProperty("toEmail"), PropertyUtil.getProperty("filePath"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * method for setup before Test Start
	 */
	@BeforeTest
	public void setUp() {
		final String browser = PropertyUtil.getProperty("driver");
		getWebDriver(browser);

		this.driver.get(this.baseUrl);
		this.driver.manage().window().maximize();

		LoginPage loginPage = new LoginPage(this.driver);
		this.candidateQueuePage = loginPage.login(PropertyUtil.getProperty("username"),
				PropertyUtil.getProperty("password"));

		this.candidateQueuePage.clickOnCandidateLink();

	}

	/**
	 * method to quit browser
	 */
	@AfterTest
	public void tearDown() {
		if (this.driver != null) {
			this.driver.quit();
		}
		// mailTestCaseResult(this.candidateProfile);
	}

}
