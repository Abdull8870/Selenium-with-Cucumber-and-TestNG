package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Then;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

public class CreateLeadPage extends ProjectSpecificMethods {
	
	public CreateLeadPage()
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how=How.ID,using="createLeadForm_companyName")
	private WebElement eleCompanyName;
	
	@FindBy(how=How.ID,using="createLeadForm_firstName")
	private WebElement eleFirstName;
	
	@FindBy(how=How.ID,using="createLeadForm_lastName")
	private WebElement eleLastName;
	
	@FindBy(how=How.ID,using="createLeadForm_primaryEmail")
	private WebElement eleEmailId;
	
	@FindBy(how=How.ID,using="createLeadForm_primaryPhoneNumber")
	private WebElement elePhoneNumber;
	
	@Then("Enter the lead details (.*) (.*) (.*) (.*) (.*)")
	public CreateLeadPage enterLeadDetails(String companyName,String firstName,String lastName,
			String phoneNumber,String email) {

		clearAndType(eleCompanyName, companyName);
		clearAndType(eleFirstName, firstName);
		clearAndType(eleLastName, lastName);
		clearAndType(elePhoneNumber, phoneNumber);
		clearAndType(eleEmailId, email);
		return this;
	}
	
	@FindBy(how=How.CLASS_NAME,using="smallSubmit")
	private WebElement eleSubmit;
	
	@Then("Click on create lead")
	public ViewLeadPage clickSubmit() {
		click(eleSubmit);
		return new ViewLeadPage().storeLeadId();
	}
}
