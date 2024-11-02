package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepDefinitions", "hook"},
    plugin = {"pretty", "html:target/site/cucumber-pretty.html", "json:target/cucumber.json"},
    tags = "@Logout"
)

public class TestRunnerLogout extends AbstractTestNGCucumberTests {
}
