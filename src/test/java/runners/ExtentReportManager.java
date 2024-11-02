package runners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

  private static ExtentReports extent;

  // Private constructor to prevent instantiation
  private ExtentReportManager() {}

  public static void setup() {
    if (extent == null) {
      // Initialize Extent Reports only once
      ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myReport.html");
      sparkReporter.config().setDocumentTitle("Automation Report");
      sparkReporter.config().setReportName("Saucedemo");
      sparkReporter.config().setTheme(Theme.DARK);

      extent = new ExtentReports();
      extent.attachReporter(sparkReporter);
    }
  }

  public static ExtentTest createTest(String testName, String tags) {
    // Create a new test instance with scenario name and tags
    return extent.createTest(testName).assignCategory(tags); // Add tags as categories
  }

  public static void flush() {
    if (extent != null) {
      extent.flush(); // Write the report to disk
    }
  }
}
