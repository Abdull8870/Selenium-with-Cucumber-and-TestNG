package utils;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;

import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.io.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class Reporter extends AbstractTestNGCucumberTests{
	
	public RemoteWebDriver driver;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test, node;
	
	public static String testCaseName;
	public String testDescription;
	public String nodes;
	public String authors;
	public String category;
	public String excelFileName;
	
	@BeforeSuite
	public void startReport() {
		reporter = new ExtentHtmlReporter("./reports/result.html");
		reporter.setAppendExisting(true); 
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	

	public void Startreport(String testCaseName,String testDescription) throws IOException {
		test = extent.createTest(testCaseName, testDescription);
		test.assignAuthor(authors);
		test.assignCategory(category);   
	}
    
    public abstract long takeSnap();
    public void reportStep(String dec, String status,boolean bSnap) {
    	MediaEntityModelProvider img = null;
		if(bSnap && !status.equalsIgnoreCase("INFO")){

			long snapNumber = 100000L;
			snapNumber = takeSnap();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath
						("./../reports/images/"+snapNumber+".jpg").build();
			} catch (IOException e) {
				
			}
		}
    	if(status.equalsIgnoreCase("pass")) {
 
    		node.pass(dec, img);
    	} else if(status.equalsIgnoreCase("fail")) {
    		node.fail(dec, img); 
    	}
    }
    
    public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}

    
    @AfterSuite
    public void stopReport() {
    	extent.flush();
    }
}














