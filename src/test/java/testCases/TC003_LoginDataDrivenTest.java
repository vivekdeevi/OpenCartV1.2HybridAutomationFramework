package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import testBase.BaseClass3;
import utilities.DataProviders;

/*Data is valid  - login success - test pass  - logout
	               login failed - test fail

Data is invalid - login success - test fail  - logout
	              login failed - test pass
*/

public class TC003_LoginDataDrivenTest extends BaseClass {
	
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups ="datadriven") //getting data provider from different class
	public void verify_loginDDT(String email, String password, String expresult)
	{
		logger.info("****** Starting TC003_LoginDataDriverTest *****");
		
		try {
			//HomePage
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			//Login
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			
			//MyAccount
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage=macc.isMyAccountPageExists();
			
			/*Data is valid  - login success - test pass  - logout
			                    login failed - test fail

			Data is invalid - login success - test fail  - logout
			                 login failed - test pass
*/
			if(expresult.equalsIgnoreCase("Valid")) 
			{
				if(targetPage==true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				}else {
					Assert.assertTrue(false);
				}
			}
			
			if(expresult.equalsIgnoreCase("Invalid")) 
			{
				if(targetPage==true) {
					macc.clickLogout();
					Assert.assertTrue(false);
				}else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		
		//Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage, true,"Login failed");
		
		logger.info("****** Finished TC003_LoginDataDriverTest *****");
	}
	

}
