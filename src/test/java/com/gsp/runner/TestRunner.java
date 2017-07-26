package com.gsp.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src/test/resources/feature",glue="com.gsp.steps")
public class TestRunner extends AbstractTestNGCucumberTests{

}
