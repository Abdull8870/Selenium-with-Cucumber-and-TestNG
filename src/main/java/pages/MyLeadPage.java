package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Then;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

public class MyLeadPage extends ProjectSpecificMethods{
	
	public MyLeadPage(){
	
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(how=How.XPATH,using="//td[@class=\"x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first \"]")
	private List<WebElement> eleAllLeads;
	
	@FindBy(how=How.XPATH,using="//div[text()=\"Lead ID\"]/child::img")
	private WebElement eleSort;
	
	
	public ViewLeadPage clickOnTheCreatedLead() throws IOException {
		click(eleSort);
		Properties prop=new Properties();
		File f=new File(propertyFile);
		try {
			FileInputStream fis=new FileInputStream(f);
			prop.load(fis);
			String leadId=prop.getProperty("leadId");
			System.out.println(prop.getProperty("browser"));
			System.out.println(leadId);
			searchAndClick(eleAllLeads,leadId);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ViewLeadPage();
	}
	
	
	@FindBy(how=How.LINK_TEXT,using="Find Leads")
	private WebElement eleFindLead;
	
	@Then("Click on find lead button")
	public FindLeadPage clickFindLead() {
		
		click(eleFindLead);
		return new FindLeadPage ();
		
	}
	
	
	

}
