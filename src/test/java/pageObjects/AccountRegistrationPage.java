package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txt_firstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txt_lastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txt_Tel;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txt_confirmPassword;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btn_continue;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chck_Agree;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msg_Confirmation;
	
	
	
	public void setfirstName(String fname) {
		txt_firstName.sendKeys(fname);
	}
	
	public void setlastName(String lname) {
		txt_lastName.sendKeys(lname);
	}
	
	public void setemail(String email) {
		txt_email.sendKeys(email);
	}
	
	public void settelPhno(String telno) {
		txt_Tel.sendKeys(telno);
	}
	
	public void setpassword(String pwd) {
		txt_password.sendKeys(pwd);
	}
	
	public void setconfirmpassword(String confirmpwd) {
		txt_confirmPassword.sendKeys(confirmpwd);
	}
	
	public void clickPolicyAgree() {
		chck_Agree.click();
	}
	
	public void clickContinueBtn() {
		btn_continue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return (msg_Confirmation.getText());
		}catch (Exception e) {
			return (e.getMessage());
		}
	}
	
}
