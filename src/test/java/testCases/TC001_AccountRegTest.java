package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import testBase.BaseClass3;

public class TC001_AccountRegTest extends BaseClass{
	
	
	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("*******Staring TC001_AccountRegTest***********");
		
		try {
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("clicked on my account");
		hp.clickRegister();
		logger.info("clicked on register");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("providing customer details");
		regpage.setfirstName(randomString().toUpperCase());
		regpage.setlastName(randomString().toUpperCase());
		regpage.setemail(randomString()+"@gmail.com");
		regpage.settelPhno("934343443438");
		regpage.setpassword("dfdfdf343");
		regpage.setconfirmpassword("dfdfdf343");
		
		regpage.clickPolicyAgree();
		regpage.clickContinueBtn();
		
		String cnfmmsg = regpage.getConfirmationMsg();
		logger.info("validating expected msg");
		
		if(cnfmmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test failed");
			logger.debug("Debug logs");
		}
		}
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("*******Finished TC001_AccountRegTest***********");
		
	}
	
	
}
