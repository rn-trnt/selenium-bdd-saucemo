package hook;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import runners.ExtentReportManager;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Hooks {

  private static WebDriver driver;
  private ExtentTest currentTest; // Store current test instance

  @Before
  public void setup(Scenario scenario) {
    // Initialize Extent Reports once
    ExtentReportManager.setup();

    // Setup Chrome options
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--incognito");
    options.addArguments("--headless=new");

    // Setup WebDriver
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    // Launch the application
    driver.get("https://www.saucedemo.com/");

    // Retrieve tags as a single string
    String tags = scenario.getSourceTagNames().stream()
        .map(tag -> tag.replace("@", ""))
        .collect(Collectors.joining(", "));

    // Create a new test with the scenario name and tags
    currentTest = ExtentReportManager.createTest(scenario.getName(), tags);

    // Log the start time
    String startTime = DateTimeFormatter.ofPattern("HH:mm:ss").format(java.time.LocalTime.now());
    currentTest.log(Status.INFO, "Test started at: " + startTime);
  }

  @After
  public void tearDown(Scenario scenario) {
    // Log the result of the scenario in the report
    if (scenario.isFailed()) {
      currentTest.log(Status.FAIL, "Scenario failed: " + scenario.getName());
    } else {
      currentTest.log(Status.PASS, "Scenario passed: " + scenario.getName());
    }

    // Log the end time
    String endTime = DateTimeFormatter.ofPattern("HH:mm:ss").format(java.time.LocalTime.now());
    currentTest.log(Status.INFO, "Test ended at: " + endTime);

    if (driver != null) {
      driver.quit(); // Close the browser after each scenario
    }
  }

  @AfterAll
  public static void afterAll() {
    // Finalize the report at the end of all tests
    ExtentReportManager.flush();
  }

  public static WebDriver getDriver() {
    return driver;
  }

  public static void setTemporarilyImplicitWait(int second) {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(second));
  }

  public static void restoreImplicitWait() {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }
}
