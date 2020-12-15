package cucumberRunner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyAccountPage;
import pages.loginPage;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

@CucumberOptions(features="src/main/java/features/CreateAccount.feature",
glue={"pages","hooks","selenium.base","selenium.projectSpecificMethods"},
monochrome=true)
public class CreateAccount extends ProjectSpecificMethods{

	@BeforeTest
	public void setValues() {
		nodes = "Leads";
		authors = "Abdul";
		category = "Functional";
		
	}
	

}
