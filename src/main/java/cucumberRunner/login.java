package cucumberRunner;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import hooks.HooksFile;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyAccountPage;
import pages.loginPage;
import selenium.projectSpecificMethods.ProjectSpecificMethods;


@CucumberOptions(features="src/main/java/features/Login.feature",
glue={"pages","hooks","selenium.base","selenium.projectSpecificMethods"},
monochrome=true)
public class login extends ProjectSpecificMethods {
	
	@BeforeTest
	public void setValues() {
		System.out.println("first");
		nodes = "Leads";
		authors = "Abdul";
		category = "Functional";
		dataSheetName="./data/CreateAccount.xlsx";
	}
	
	
//	@Test
//	public void check() {
//		startApp("http\\://leaftaps.com/opentaps/control/main");
//		new loginPage().enerUsername("Demosalesmanager").enterPassword("crmsfa").clickLogin().clickCRMSFA();
//		new HomePage().clickAccountTab();
//		new MyAccountPage().clickCreateAccount();
//		new CreateAccountPage().enterAccountDetails("abd", "AMD", "Insurance", "9875", "ab77@gmail.com");
//	}
//	
	

	
	
}

