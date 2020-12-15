package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Then;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

public class MyAccountPage extends ProjectSpecificMethods {
	
	public MyAccountPage()
	{
		
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how=How.LINK_TEXT,using="Create Account")
	private WebElement eleCreateAccount;
	
	@Then("Click on create account button")
	public CreateAccountPage clickCreateAccount() {
		click(eleCreateAccount);
		return new CreateAccountPage();
	}
   
}
