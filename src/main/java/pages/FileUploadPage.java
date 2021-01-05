package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import selenium.projectSpecificMethods.ProjectSpecificMethods;

public class FileUploadPage extends ProjectSpecificMethods{

	public FileUploadPage() {
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(how=How.ID,using="dropzoneInput-label")
	private  WebElement eleChooseFileBtn;
	
	@FindBy(how=How.XPATH,using="//button[text()=\"Convert Now!\"]")
	private WebElement eleConvertBtn;
	
	@Given("Navigate to the website (.*) and upload the file (.*)")
	public FileUploadPage clickChooseFile(String webSite,String fileName) throws InterruptedException {
		driver.navigate().to(webSite);
		click(eleChooseFileBtn);
		autoITExecutor(fileName);
		checkExistence(eleConvertBtn);
		click(eleConvertBtn);
		Thread.sleep(5000);
		return this;
	}
}
