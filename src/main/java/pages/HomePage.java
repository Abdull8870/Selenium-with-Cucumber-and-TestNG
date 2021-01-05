package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Then;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

public class HomePage extends ProjectSpecificMethods{
	
	public HomePage() {

		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how=How.LINK_TEXT,using="Create Lead")
	private WebElement eleCreateLead;
	
	@FindBy(how=How.LINK_TEXT,using="Accounts")
	private WebElement eleAccounTab;
	
	@FindBy(how=How.LINK_TEXT,using="Create Contact")
	private WebElement eleCreateContact;
	
	@FindBy(how=How.LINK_TEXT,using="Leads")
	private WebElement eleLeads;
	
	@FindBy(how=How.XPATH,using="//img[@alt=\"twitter\"]")
	private WebElement eleTwitter;
	
	@Then("Click on create lead button")
	public HomePage clickCreateLead() throws InterruptedException {
		click(eleCreateLead);
		return this;
	}
	
	
	@Then("Click on Accounts tab")
	public MyAccountPage clickAccountTab() {
		click(eleAccounTab);
		return new MyAccountPage();
	}
	
	public HomePage clickCreateContact() {
		click(eleCreateContact);
		return this;
	}
	
	@Then("Click on leads tab")
	public HomePage clickLeadTab() {
		click(eleLeads);
		return this;
	}
	
	public HomePage clickCreateAccountTab() {
		click(eleAccounTab);
		return this;
	}
	
	
	
	
	
}


