package com.gsp.utils;

import javax.inject.Inject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class SeleniumDriver {

	private static PropertyReader propertyReader = new PropertyReader();
	private static final String geckoPath = propertyReader.getProperty("geckopath");
	//private static final String geckoPath = "";
	private static WebDriver driver; 
	private static WebDriverWait wait;
	@BeforeSuite
	public static WebDriver getFirefoxdriver(){
		if(driver != null){
			return driver;
		}else{
		System.out.println("Starting Firefox browser");
		System.setProperty("webdriver.gecko.driver", geckoPath + "geckodriver.exe");
		 driver = new FirefoxDriver();
		return driver;
		}
	}
	public static WebDriverWait getWebDriverWait(){
		if(wait!=null){
			return wait;
		}else{
		 wait=new WebDriverWait(getFirefoxdriver(),100);
		return wait;
		}
	}
	
}
