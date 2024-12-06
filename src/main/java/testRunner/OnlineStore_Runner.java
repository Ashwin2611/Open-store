package testRunner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import Utils.ExtentReportManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(	
		features = {"C:\\Users\\ashwin.murugan\\eclipse-workspace\\onlineStore\\src\\main\\java\\Features\\OnlineStore.feature"},
		glue = {"StepDefinition"},
		plugin={
			"pretty",
			"html:target/cucumber-reports.html",
			"json:target/cucumber-reports.json"
		},
		monochrome = true
		)

public class OnlineStore_Runner {

    @BeforeClass
    public static void setup() {
        // Initialize Extent Report
        ExtentReportManager.setupExtentReport();
    }
}
