package cucumberRunner;

import org.testng.annotations.BeforeTest;

import cucumber.api.CucumberOptions;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

@CucumberOptions(features="src/main/java/features/DeleteLead.feature",
glue={"pages","hooks","selenium.base","selenium.projectSpecificMethods"},
monochrome=true)
public class DeleteLead extends ProjectSpecificMethods{

	
	@BeforeTest
	public void setValues() {
		nodes = "Leads";
		authors = "Abdul";
		category = "Functional";
		
	}
	
	
	
	
}
