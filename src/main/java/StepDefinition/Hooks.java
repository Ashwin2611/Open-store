package StepDefinition;
 
import Utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
 
public class Hooks {
 
    @Before
    public void setup(Scenario scenario) {
        // Initialize Extent Report for the scenario
        ExtentTest test = ExtentReportManager.getExtent().createTest(scenario.getName());
        ExtentReportManager.setTest(test);
 
        ExtentReportManager.getTest().log(Status.INFO, "Starting Scenario: " + scenario.getName());
    }
 
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportManager.getTest().log(Status.FAIL, "Scenario Failed: " + scenario.getName());
        } else {
            ExtentReportManager.getTest().log(Status.PASS, "Scenario Passed: " + scenario.getName());
        }
        ExtentReportManager.getTest().log(Status.INFO, "Ending Scenario: " + scenario.getName());
 
        // Finalize the Extent Report after all tests
        ExtentReportManager.tearDown();
    }
}