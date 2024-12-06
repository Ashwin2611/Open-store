package Utils;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class ExtentReportManager {
 
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
 
    /**
     * Sets up the Extent Report with a Spark Reporter.
     */
    public static void setupExtentReport() {
        String reportPath = System.getProperty("user.dir") + "/Reports/ExtentReport.html";
 
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Online Store API Test Report");
        sparkReporter.config().setReportName("API Functional Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
 
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
 
        // Adding system info
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Your Name");
    }
 
    /**
     * Returns the ExtentReports object.
     */
    public static ExtentReports getExtent() {
        return extent;
    }
 
    /**
     * Sets the ExtentTest object for the current thread.
     */
    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }
 
    /**
     * Gets the ExtentTest object for the current thread.
     */
    public static ExtentTest getTest() {
        return extentTest.get();
    }
 
    /**
     * Flushes the Extent Report.
     */
    public static void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }
}