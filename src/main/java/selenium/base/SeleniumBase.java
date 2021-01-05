package selenium.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import selenium.design.Browser;
import selenium.design.Element;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.*;
import org.openqa.selenium.*;

import utils.Reporter;

public class SeleniumBase extends Reporter implements Browser, Element{

	public static RemoteWebDriver driver;
	public WebDriverWait wait;
	public String DOWNLOAD_FOLDER_PATH="./downloads";
	public String AutoITExecutor_FOLDER_PATH="./AutoITScriptExecutor";
	public String propertyFile="./properties/data.properties";
	public SoftAssert softAssert = new SoftAssert();

	int i=1;
	
	
	
	public void typeAndEnter(WebElement ele,String value)
	{
		
		try {
			
			wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.clear();
			ele.sendKeys(value);
			ele.sendKeys(Keys.ENTER);
			reportStep("The Data "+value+"Has enterred Successfully", "pass"); 
			softAssert.assertTrue(true, "Success");
		} 
		catch (Exception e) {
			reportStep("The Element "+ele+"is not interactable", "fail");
			throw new RuntimeException();
		} 
	}
	
	public boolean checkExistence(WebElement ele) {
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(ele));
			if(ele.isDisplayed()) {
				reportStep("The Element is Visible", "pass"); 
				return true;
			}
			else {
				reportStep("The Element is Not visible", "fail"); 
				return false;
			}
		}
		
		catch(ElementNotVisibleException e) {
			reportStep("The Element is Not visible", "fail"); 

			return false;
		}

	}
	
	
	@Override
	public void click(WebElement ele) {
		String text="";
		try {
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			text = ele.getText();
			ele.click();
			reportStep("The Element "+text+" clicked", "pass"); 
			addSnapToWord("The Element "+text+" is clicked", takeScreenShot());
			softAssert.assertTrue(true, "Success");
		} catch (StaleElementReferenceException e) {
			reportStep("The Element "+text+" could not be clicked", "fail");
			throw new RuntimeException();
		} 
	}
	public void clickWithNoSnap(WebElement ele) {
		String text = "";
		try {
			text = ele.getText();
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			reportStep("The Element with text: "+text+" clicked", "pass", true);
		} catch (StaleElementReferenceException e) {
			reportStep("The Element "+ele+" could not be clicked", "fail");
			throw new RuntimeException();
		}catch(Exception e) {
			System.err.println(e);
		}

	}

	@Override
	public void append(WebElement ele, String data) {
		ele.sendKeys(data);
	}

	@Override
	public void clear(WebElement ele) {
		try {
			ele.clear();
			reportStep("The field is cleared Successfully", "pass");
		} catch (ElementNotInteractableException e) {
			reportStep("The field is not Interactable", "fail");
			throw new RuntimeException();
		}
	}

	@Override
	public void clearAndType(WebElement ele, String data) {
		try {
			
			ele.clear();
			ele.sendKeys(data);			
			addSnapToWord("The input "+data+" has been entered sucessfully", takeScreenShot());
			reportStep("The Data :"+data+" entered Successfully", "pass");
		} catch (ElementNotInteractableException e) {
			reportStep("The Element "+ele+" is not Interactable", "fail");
			throw new RuntimeException();
		}

	}

	@Override
	public String getElementText(WebElement ele) {
		String text = ele.getText();
		return text;
	}

	@Override
	public String getBackgroundColor(WebElement ele) {
		String cssValue = ele.getCssValue("color");
		return cssValue;
	}

	@Override
	public String getTypedText(WebElement ele) {
		String attributeValue = ele.getAttribute("value");
		return attributeValue;
	}

	@Override
	public void selectDropDownUsingText(WebElement ele, String value) {
		
	   wait =new WebDriverWait(driver,10);
	   wait.until(ExpectedConditions.elementToBeClickable(ele));
		new Select(ele)
		.selectByVisibleText(value);
	}

	@Override
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		
		wait =new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		new Select(ele)
		.selectByIndex(index);
		softAssert.assertTrue(true, "Success");
	}

	@Override
	public void selectDropDownUsingValue(WebElement ele, String value) {
		
		wait =new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		new Select(ele)
		.selectByValue(value);
	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		try {
			if(ele.getText().equals(expectedText)) {
				reportStep("The expected text contains the actual "+expectedText,"pass");
				return true;
			}else {
				reportStep("The expected text doesn't contain the actual "+expectedText,"fail");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;
	}

	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		wait =new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println(ele.getText());
		try {
			if(ele.getText().contains(expectedText)) {
				reportStep("The expected text contains the actual "+expectedText,"pass");
				addSnapToWord("The expected text contains the actual "+expectedText+"pass", takeScreenShot());
				return true;
			}else {
				reportStep("The expected text doesn't contain the actual "+expectedText,"fail");
				addSnapToWord("The expected text doesn't contains the actual "+expectedText+"fail", takeScreenShot());
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;
	}

	@Override
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			if(ele.getAttribute(attribute).equals(value)) {
				reportStep("The expected attribute :"+attribute+" value contains the actual "+value," pass");
				return true;
			}else {
				reportStep("The expected attribute :"+attribute+" value does not contains the actual "+value," fail");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}
		return false;
	}

	@Override
	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try {
			if(ele.getAttribute(attribute).contains(value)) {
				reportStep("The expected attribute :"+attribute+" value contains the actual "+value,"pass");
			}else {
				reportStep("The expected attribute :"+attribute+" value does not contains the actual "+value,"fail");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}

	}

	@Override
	public boolean verifyDisplayed(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				reportStep("The element "+ele+" is visible","pass");
				return true;
			} else {
				reportStep("The element "+ele+" is not visible","fail");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
		return false;

	}

	@Override
	public boolean verifyDisappeared(WebElement ele) {
		return false;

	}

	@Override
	public boolean verifyEnabled(WebElement ele) {
		try {
			if(ele.isEnabled()) {
				reportStep("The element "+ele+" is Enabled","pass");
				return true;
			} else {
				reportStep("The element "+ele+" is not Enabled","fail");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;
	}

	@Override
	public void verifySelected(WebElement ele) {
		try {
			if(ele.isSelected()) {
				reportStep("The element "+ele+" is selected","pass");
				//				return true;
			} else {
				reportStep("The element "+ele+" is not selected","fail");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		//		return false;

	}

	@Override
	public RemoteWebDriver startApp(String url) {
		 startApp("chrome", url);
		 return null;
		
	}

	@Override
	public RemoteWebDriver startApp(String browser, String url) {
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				System.out.println("Inside Chrome");
				System.setProperty("webdriver.chrome.driver",
						"./drivers/chromedriver.exe");
				String path=System.getProperty("user.dir")+"\\downloads";
				
				/**
				 * DesiredCapabilities is used to make webdriver to accept
				   insecure certifications and SSL certifications
				 
				  *setExperimentalOption is used to inform webdriver the location of downloading file path
				   and always download pdf instead of opening it in the browser
				 
				 */
				
				DesiredCapabilities dc=DesiredCapabilities.chrome();
// 				dc.acceptInsecureCerts();
				dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				ChromeOptions options = new ChromeOptions();
				HashMap<String,Object> chromePrefs = new HashMap<>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory",path);	
				chromePrefs.put("plugins.always_open_pdf_externally", true);
				options.setExperimentalOption("prefs", chromePrefs);
				options.merge(dc);
                driver = new ChromeDriver(options);
                driver.get(url);
			} else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if(browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						"./drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			
			System.out.println(url);
//			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			reportStep("The Browser Could not be Launched. Hence Failed", "fail");
			throw new RuntimeException();
		} 
		
		return null;

	}

	@Override
	public WebElement locateElement(String locatorType, String value) {
		
		try {
			switch(locatorType.toLowerCase()) {
			case "id": return driver.findElementById(value);
			case "name": return driver.findElementByName(value);
			case "class": return driver.findElementByClassName(value);
			case "link": return driver.findElementByLinkText(value);
			case "xpath": return driver.findElementByXPath(value);
			}
		} catch (NoSuchElementException e) {
			reportStep("The Element with locator:"+locatorType+" Not Found with value: "+value, "fail");
			throw new RuntimeException();
		}catch (Exception e) {
			reportStep("The Element with locator:"+locatorType+" Not Found with value: "+value, "fail");
		}
		return null;
	}

	@Override
	public WebElement locateElement(String value) {
		WebElement findElementById = driver.findElementById(value);
		return findElementById;
	}

	@Override
	public List<WebElement> locateElements(String type, String value) {
		try {
			switch(type.toLowerCase()) {
			case "id": return driver.findElementsById(value);
			case "name": return driver.findElementsByName(value);
			case "class": return driver.findElementsByClassName(value);
			case "link": return driver.findElementsByLinkText(value);
			case "xpath": return driver.findElementsByXPath(value);
			}
		} catch (NoSuchElementException e) {
			System.err.println("The Element with locator:"+type+" Not Found with value: "+value);
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public void switchToAlert() {
		driver.switchTo().alert();
	}

	@Override
	public void acceptAlert() {
		String text = "";		
		try {
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			reportStep("The alert "+text+" is accepted.", "pass");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "fail");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}  

	}

	@Override
	public void dismissAlert() {
		String text = "";		
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.dismiss();
			System.out.println("The alert "+text+" is accepted.");
		} catch (NoAlertPresentException e) {
			System.out.println("There is no alert present.");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}  


	}

	@Override
	public String getAlertText() {
		String text = "";		
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
		} catch (NoAlertPresentException e) {
			System.out.println("There is no alert present.");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
		return text;
	}

	@Override
	public void typeAlert(String data) {
		driver.switchTo().alert().sendKeys(data);

	}

	@Override
	public void switchToWindow(int index) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			List<String> allhandles = new ArrayList<String>(allWindows);
			String exWindow = allhandles.get(index);
			driver.switchTo().window(exWindow);
			System.out.println("The Window With index: "+index+
					" switched successfully");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With index: "+index+
					" not found");	
		}
	}

	@Override
	public void switchToWindow(String title) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for (String eachWindow : allWindows) {
				driver.switchTo().window(eachWindow);
				if (driver.getTitle().equals(title)) {
					break;
				}
			}
			System.out.println("The Window With Title: "+title+
					"is switched ");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With Title: "+title+
					" not found");
		} finally {
			takeSnap();
		}
	}

	@Override
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);

	}

	@Override
	public void switchToFrame(WebElement ele) {
		driver.switchTo().frame(ele);

	}

	@Override
	public void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);

	}

	@Override
	public void defaultContent() {
		driver.switchTo().defaultContent();

	}

	@Override
	public boolean verifyUrl(String url) {
		if (driver.getCurrentUrl().equals(url)) {
			System.out.println("The url: "+url+" matched successfully");
			return true;
		} else {
			System.out.println("The url: "+url+" not matched");
		}
		return false;
	}

	@Override
	public boolean verifyTitle(String title) {
		if (driver.getTitle().equals(title)) {
			System.out.println("Page title: "+title+" matched successfully");
			return true;
		} else {
			System.out.println("Page url: "+title+" not matched");
		}
		return false;
	}


	public long takeSnap(){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		String filePath="./reports/images/"+number+".jpg";
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	
	}
	
	
	public String takeScreenShot() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		String filePath="./reports/images/"+number+".jpg";
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile , new File(filePath));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return filePath;
		
	}
	
	@Override
	public void close() {
		driver.close();

	}

	@Override
	public void quit() {
		driver.quit();

	}
	
	
	public void addSnapToWord(String stepDef, String imageFile) {

		// # logic to be incorporated: If the file already exist, open the file
		// and append the images, if not, create the file and add the first pic
		XWPFDocument doc = null;
		// Below program is to display the System date and Time
		//String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
		File f = new File("./reports/AutomationScreenshots/" + testCaseName + ".docx");

		FileInputStream input = null;

		// Check for File Existence. If the file exist then Open the document
		// and append the pic
		try {
			if (f.exists()) {
				doc = new XWPFDocument(new FileInputStream(f));
				// To add a new Paragraph to the document
			} else {
				// Create a new DOcument and add the picture to it
				System.out.println("File is created");
				doc = new XWPFDocument();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XWPFParagraph title = doc.createParagraph();

		// To specify particular setting for the new Paragraph like font style,
		// colour, alignment
		XWPFRun run = title.createRun();
		//run.setText("Time in DD-MM-YYYY hh:mm:ss format: " + timeStamp + "\n");
		run.addBreak();
		run.setText(stepDef);

		run.setBold(true);
		title.setAlignment(ParagraphAlignment.CENTER);

		// Read the input picture from the file
		try {
			input = new FileInputStream(imageFile);

			// Add the picture to the paragraph
			run.addPicture(input, XWPFDocument.PICTURE_TYPE_JPEG, imageFile, Units.toEMU(480), Units.toEMU(280)); // width*height

			input.close();

			// Write the content to the file
			FileOutputStream fos = new FileOutputStream(
					"./reports/AutomationScreenshots/" + testCaseName + ".docx");
			doc.write(fos);
			doc.close();
			fos.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void storeExtractedText(WebElement ele,String propertyName) {
		String[] text=ele.getText().split("\\(");
		String leadId=text[1].split("\\)")[0];
		// TODO Auto-generated method stub
		Properties prop=new Properties();
		File f = new File(propertyFile);
		try {
			prop.load(new FileInputStream(f));
			prop.setProperty(propertyName, leadId);
			FileOutputStream fos = new FileOutputStream(propertyFile);
			prop.store(fos, null);
			Assert.assertTrue(true);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			
		   e.printStackTrace(); 
		}
	}
	
	public void searchAndClick(List<WebElement> webEle,String leadId) {
		
		  Actions a=new Actions(driver);
	    
		for (WebElement ele : webEle) {
			System.out.println(ele.getText());
			String Id=ele.getText();
			if(Id.equals(leadId)) {
				driver.findElementByLinkText(Id).click();
			    addSnapToWord("The Element "+ Id +" is clicked", takeScreenShot());
			    reportStep("The Element "+ Id +" is clicked", "Pass");
			    break;
			}
			else {
				reportStep("Element Not found", "fail");
			}
			
		}
		
		
	}

	@Override
	public void clickAndHold(WebElement ele) {
		// TODO Auto-generated method stub
		
		try {
			Actions action=new Actions(driver);
			action.clickAndHold(ele).build().perform();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Element Not found", "fail");

		}

		
	}

	@Override
	public void dragAndDrop(WebElement eleSrc, WebElement eleDest) {
		// TODO Auto-generated method stub
		try {
			Actions action=new Actions(driver);
			action.dragAndDrop(eleSrc, eleDest).build().perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Element Not found", "fail");
		}
		
	}

	@Override
	public void doubleClick(WebElement ele) {
		
		// TODO Auto-generated method stub
		try {
			
			Actions action=new Actions(driver);
			action.doubleClick(ele).build().perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Element Not found", "fail");
		}
	}

	@Override
	public void zoom(int zoomPercenatge) {
		// TODO Auto-generated method stub
		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			String script="document.body.style.zoom ='"+zoomPercenatge+"%'";
			System.out.println(script);
			executor.executeScript(script);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Some internal error occured", "fail");
		}
		
	}

	@Override
	public void autoITExecutor(String exeFilepath) {
		// TODO Auto-generated method stub
		
		try {
			Runtime.getRuntime().exec(AutoITExecutor_FOLDER_PATH+"/"+exeFilepath+".exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Some internal error occured", "fail");
		}
		
	}

	@Override
	public void ScrolDown(int x, int y) {
		// TODO Auto-generated method stub
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			String script="window.scrollBy("+x+","+y+")";
			js.executeScript(script, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Some internal error occured", "fail");
		}
	}

	@Override
	public void scrollTillTheElement(WebElement ele) {
		// TODO Auto-generated method stub
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true)", ele);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Element not found", "fail");
		}
		
	}
	
	
	
	
	
	

}
