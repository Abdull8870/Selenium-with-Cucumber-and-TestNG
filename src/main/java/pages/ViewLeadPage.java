package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Then;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

public class ViewLeadPage extends ProjectSpecificMethods {
	
	public ViewLeadPage(){
	
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how=How.ID,using="viewLead_companyName_sp")
	private WebElement eleCompanyName;
	
	public ViewLeadPage storeLeadId() {
		System.out.println("Inside store lead ID");
		storeExtractedText(eleCompanyName,"leadId");
		return this;
		
	}
	
	@FindBy(how=How.LINK_TEXT,using="Delete")
	private WebElement eleDelete;
	
	@Then("Click on delete button")
	public MyLeadPage clickDelete() {
		Boolean check=checkExistence(eleDelete);
		if(check) {
		click(eleDelete);
		}
		return new MyLeadPage();
	}
	
	
}
