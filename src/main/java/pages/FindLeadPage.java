package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Then;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

public class FindLeadPage extends ProjectSpecificMethods{
	
	public FindLeadPage(){
	
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(how=How.XPATH,using="(//div[@class=\"x-form-item x-tab-item\"]/child::div/child::input)[1]")
	private WebElement eleSearchLead;
	
	@FindBy(how=How.XPATH,using="//button[text()=\"Find Leads\"]")
	private WebElement eleFindBtn;
	
	@Then("Find the lead which is created recently and navigate to the view lead page")
	public FindLeadPage searchWithLeadId() throws IOException {
		
		Properties prop=new Properties();
		File f=new File(propertyFile);
		String leadId=null;
		try {
			FileInputStream fis=new FileInputStream(f);
			prop.load(fis);
		    leadId=prop.getProperty("leadId");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clearAndType(eleSearchLead, leadId);
		click(eleFindBtn);
		WebElement ele=locateElement("link", leadId);
		Boolean check=checkExistence(ele);
		if(check) {
			click(ele);
		}
		return this;
	}
	
	
	@Then("Find the lead with lead id (.*)")
	public FindLeadPage findLeadWithLeadId(String leadId) {
		
		clearAndType(eleSearchLead, leadId);
		click(eleFindBtn);
		try {
			WebElement ele=locateElement("link", leadId);
			Boolean check=checkExistence(ele);
			if(check) {
				reportStep("The expected leadId is found", "pass");
			} else 
				reportStep("The expected leadId is not found", "fail");
		} catch (Exception e) {
			
			reportStep("The expected leadId is not found", "fail");
		}
		
		return this;
	}
	

}
