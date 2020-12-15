package cucumberRunner;

import org.testng.annotations.BeforeTest;

import cucumber.api.CucumberOptions;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

@CucumberOptions(features="src/main/java/features/CreateLead.feature",
glue={"pages","hooks","selenium.base","selenium.projectSpecificMethods"},
monochrome=true)
public class CreateLead extends ProjectSpecificMethods {

	

	@BeforeTest
	public void setValues() {
		System.out.println("first");
		nodes = "Leads";
		authors = "Abdul";
		category = "Functional";
		
	}
	
	
	
}
