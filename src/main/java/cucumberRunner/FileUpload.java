package cucumberRunner;

import org.testng.annotations.BeforeTest;

import cucumber.api.CucumberOptions;
import selenium.projectSpecificMethods.ProjectSpecificMethods;


@CucumberOptions(features="src/main/java/features/FileUpload.feature",
glue={"pages","hooks","selenium.base","selenium.projectSpecificMethods"},
monochrome=true)
public class FileUpload extends ProjectSpecificMethods{
	

	@BeforeTest
	public void setValues() {
		
		nodes = "AutoIT";
		authors = "Abdul";
		category = "Fileupload example";
		
	}


}
