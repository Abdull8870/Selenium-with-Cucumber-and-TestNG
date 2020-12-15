package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import selenium.base.SeleniumBase;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

public class loginPage extends ProjectSpecificMethods {
    int i=1;
	public loginPage() {
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(how=How.ID,using="username")
	private  WebElement eleUserName;
//	
	@Given("Enter the username as (.*)")
	public loginPage enerUsername(String userName) {
		System.out.println(userName);
		clearAndType(eleUserName, userName);
		return this;
	}
	
	@FindBy(how=How.ID,using="password")
	private  WebElement elePassword;
	
	@Given("Enter the Password as (.*)")
	public loginPage enterPassword(String passWord) {
	
		clearAndType(elePassword, passWord);
		return  this;
	}
	
	@FindBy(how=How.CLASS_NAME,using="decorativeSubmit")
	private  WebElement eleLogin;
	
    @When("Click on the Login")
    public WelcomePage clickLogin()
     { 

      click(eleLogin);
	  return new WelcomePage();
	
}    
    
 
  
 
}
