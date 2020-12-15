package hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import selenium.base.SeleniumBase;

public class HooksFile extends SeleniumBase {
	
	public Properties prop; 
	
	@Before
	public void before(Scenario sc) throws IOException {
		System.out.println("Hooks");
		testCaseName = sc.getName();
		testDescription = sc.getId();
		Startreport(testCaseName,testDescription);
		File f = new File("./reports/AutomationScreenshots/" + sc.getName() + ".docx");
        if(f.exists()) {
        	f.delete();
        	System.out.println("Old screenshots deleted");
        }
	    prop=new Properties();
		FileInputStream file= new FileInputStream(propertyFile);
		prop.load(file);
		String browser=prop.getProperty("browser");
		String url=prop.getProperty("url");
		FileOutputStream fos=new FileOutputStream(propertyFile);
		prop.store(fos, null);
	    startApp(browser,url);
	    node = test.createNode(testCaseName);
		
	}
	
	@After
	public void after()	{
		close();
	}

}
