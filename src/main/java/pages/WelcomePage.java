package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Then;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

public class WelcomePage extends ProjectSpecificMethods {
	
	 public WelcomePage() {
		 PageFactory.initElements(driver, this);
	 }
	
	@FindBy(how=How.LINK_TEXT,using="CRM/SFA")
	private WebElement eleLink;
	
	@Then("Demo should be displayed in HomePage")
	public WelcomePage clickHomeLink() {
		
      boolean displayed = driver.findElementByLinkText("CRM/SFA").isDisplayed();
		
		if(displayed) {
			
			System.out.println("homepage is displayed");
		}
		else {
			System.out.println("homepage is not displayed");
		}
		
		System.out.println(driver.getTitle());
//		click(eleLink);
		return this;
	}
	
	@Then("Click on the crmsfa link")
	public HomePage clickCRMSFA() {
			click(eleLink);
			return new HomePage();
		}

}
