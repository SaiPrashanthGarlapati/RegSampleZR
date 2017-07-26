package com.gsp.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

public class PropertyReader {

	@BeforeSuite
	public String getProperty(String name){
		System.out.println("In Property Reader");
		String property="";
		try{
			Properties prop = new Properties();
			System.out.println("In Property Reader1");
			String config = System.getenv("config_path");
			System.out.println("In Property Reader : "+config);
			InputStream input = new FileInputStream(config +"/config.properties");
			prop.load(input);
			property = prop.getProperty(name);
			System.out.print("Requested property for :"+ name + " and property is "+ property);
			}catch(Exception e){
				System.out.println("Exception Occured");
			}
		return property;
	}
}
